/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package timesofmoney.qnbmerchant.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.activities.LoginActivity;
import timesofmoney.qnbmerchant.models.QNBNotofication;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.LogUtils;


public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */

        QNBNotofication qnbNotification = parseResponse(message);
        saveMessage(qnbNotification);
       // sendNotification(message);
        // [END_EXCLUDE]
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message) {

        LogUtils.Verbose("TAG"," Message Received "+message);
        Intent intent = new Intent(this, LoginActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("QNB Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void saveMessage(QNBNotofication qnbNotification) {

        Gson gson = new Gson();
        String json = AppSettings.getData(this, AppSettings.NOTIFICATIONS);
        Type type = new TypeToken<ArrayList<QNBNotofication>>() {
        }.getType();
        List<QNBNotofication> qnbNotificationList = null;

        try {
            qnbNotificationList = gson.fromJson(json, type);
        } catch (Exception e) {
            qnbNotificationList = new ArrayList<>();
        }

        if(qnbNotificationList==null)
            qnbNotificationList=new ArrayList<>();

        qnbNotificationList.add(qnbNotification);

        json = gson.toJson(qnbNotificationList);
        LogUtils.Verbose("TAG", " Notification JSON " + json);
        AppSettings.putData(this, AppSettings.NOTIFICATIONS, json);
        sendNotification(qnbNotification.getMessage());
    }

    private QNBNotofication parseResponse(String xml) {
        QNBNotofication qnbNotification = new QNBNotofication();

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            int eventType = xpp.getEventType();
            String text = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagname = xpp.getName();

                if (eventType == XmlPullParser.TEXT) {
                    text = xpp.getText();

                } else if (eventType == XmlPullParser.END_TAG) {
                    if (tagname.equalsIgnoreCase("Timestamp")) {

                        try {
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                            SimpleDateFormat sdf3 = new SimpleDateFormat("MMM");

                            Date d1 = sdf1.parse(text);
                            qnbNotification.setTimestamp(sdf2.format(d1) + "\n" + sdf3.format(d1));

                        } catch (Exception e) {
                            qnbNotification.setTimestamp("N.A.");
                        }
                    }
                    if (tagname.equalsIgnoreCase("Message"))
                        qnbNotification.setMessage(text);

                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return qnbNotification;
    }
}