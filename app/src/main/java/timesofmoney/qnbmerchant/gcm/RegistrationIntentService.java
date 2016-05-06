/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package timesofmoney.qnbmerchant.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.Map;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.GenericResponseHandler;
import timesofmoney.qnbmerchant.utilities.LogUtils;


public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        Runnable r = new Runnable() {
            @Override
            public void run() {

                getDeviceToken();
            }
        };

        Thread t = new Thread(r);
        t.start();

        // Notify UI that registration has completed, so the progress indicator can be hidden.

    }

    private void getDeviceToken() {
        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]

            sendRegistrationToServer(token);


            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.

            // [END register_for_gcm]
        } catch (Exception e) {
            LogUtils.Verbose(TAG, "Failed to complete token refresh " + e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.

        }
    }

    /**
     * Persist registration to third-party servers.
     * <p/>
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {

        String xmlParam = "<DeviceToken>" + token + "</DeviceToken>\n" +
                "\t\t<MobileNumber>" + AppSettings.getData(getApplicationContext(), AppSettings.MOBILE_NUMBER) + "</MobileNumber>\t";
        xmlParam = Constants.getReqXML("SaveUpdateDeviceToken", xmlParam);

        try {
            xmlParam = AESecurity.getInstance().encryptString(xmlParam);
        } catch (Exception e) {
            LogUtils.Exception(e);
            Constants.showToast(RegistrationIntentService.this, e.toString());
            return;
        }


        RetrofitTask retrofitTask = RetrofitTask.getInstance();

        retrofitTask.executeTask(xmlParam, new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {


                if (!isSuccess) {
                    Constants.showToast(RegistrationIntentService.this, response);
                    AppSettings.putData(RegistrationIntentService.this, AppSettings.isTOKENSENDTOSERVER, "true");
                    return;
                }

                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    Log.v("TAG", " Decrypted " + decrypted);
                    Map<String, String> responseMap = GenericResponseHandler.handleResponse(decrypted);


                    if (responseMap.get("ResponseType").equals("Success")) {

                        Constants.showToast(RegistrationIntentService.this, responseMap.get("Message"));

                    } else
                        Constants.showToast(RegistrationIntentService.this, responseMap.get("Reason"));

                } catch (Exception e) {
                    LogUtils.Exception(e);
                    Constants.showToast(RegistrationIntentService.this, e.toString());
                }


            }
        });
    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */

    // [END subscribe_topics]

}