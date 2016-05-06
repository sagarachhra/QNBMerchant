package timesofmoney.qnbmerchant.customviews;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Map;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.activities.GenerateQRCodeActivity;
import timesofmoney.qnbmerchant.activities.LoginActivity;
import timesofmoney.qnbmerchant.activities.ProfileActivity;
import timesofmoney.qnbmerchant.activities.SettingsActivity;
import timesofmoney.qnbmerchant.activities.SubMerchantActivity;
import timesofmoney.qnbmerchant.activities.TransactionHistoryActivity;
import timesofmoney.qnbmerchant.dialogs.OkDialog;
import timesofmoney.qnbmerchant.dialogs.ProgressDialog;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.GenericResponseHandler;


/**
 * Created by kunalk on 1/27/2016.
 */
public class MenuDrawerView implements View.OnClickListener {

    Context context;
    View view;
    CustomTextView ctv;
    LinearLayout linlayTransactionHistory, linlayQRCodes, linlaySubMerchants, linlayProfile, linlaySettings, linlayLogout;


    public MenuDrawerView(Context context, View view) {
        this.context = context;
        this.view = view;

        initView();
    }

    private void initView() {

        linlayTransactionHistory = (LinearLayout) view.findViewById(R.id.linlayTransactionHistory);
        linlayQRCodes = (LinearLayout) view.findViewById(R.id.linlayQRCodes);
        linlaySubMerchants = (LinearLayout) view.findViewById(R.id.linlaySubMerchants);
        linlayProfile = (LinearLayout) view.findViewById(R.id.linlayProfile);
        linlaySettings = (LinearLayout) view.findViewById(R.id.linlaySettings);
        linlayLogout = (LinearLayout) view.findViewById(R.id.linlayLogout);
        ctv= (CustomTextView)view.findViewById(R.id.txt1);
        ctv.setText(QNBMerchantApp.getMerchantProfile().getCompanyName());



        linlayTransactionHistory.setOnClickListener(this);
        linlayQRCodes.setOnClickListener(this);
        linlaySubMerchants.setOnClickListener(this);
        linlayProfile.setOnClickListener(this);
        linlaySettings.setOnClickListener(this);
        linlayLogout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v==linlayTransactionHistory)
        {
            Intent intent = new Intent(context, TransactionHistoryActivity.class);
            context.startActivity(intent);

        }

        else if (v==linlayQRCodes)
        {
            Intent intent = new Intent(context, GenerateQRCodeActivity.class);
            context.startActivity(intent);

        }

        else if (v==linlayProfile)
        {
            Intent intent = new Intent(context, ProfileActivity.class);
            context.startActivity(intent);

                 }

        else if (v==linlaySubMerchants)
        {
            Intent intent = new Intent(context, SubMerchantActivity.class);
            context.startActivity(intent);

        }

        else if (v==linlaySettings)
        {
            Intent intent = new Intent(context, SettingsActivity.class);
            context.startActivity(intent);

        }

        else if (v==linlayLogout)
        {
            logutUser();

        }

    }

    private void logutUser() {


        if (!Constants.isNetworkAvailable(context)) {
            new OkDialog(context, context.getString(R.string.errorNetwork), null, null);
            return;
        }

        String xmlParam = "\t\t<MobileNumber>" + AppSettings.getData(context, AppSettings.MOBILE_NUMBER) + "</MobileNumber>";
        xmlParam = Constants.getReqXML("Logout", xmlParam);

        try {
            xmlParam = AESecurity.getInstance().encryptString(xmlParam);
        } catch (Exception e) {
            e.printStackTrace();
            Constants.showToast(context, e.toString());
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(((Activity) context), context.getString(R.string.msgWait));
        progressDialog.show();
        RetrofitTask retrofitTask = RetrofitTask.getInstance();

        retrofitTask.executeTask(xmlParam, new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {

                progressDialog.dismiss();

                if (!isSuccess) {
                    new OkDialog(context, response, null, null);
                    return;
                }

                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    Log.v("TAG", " Decrypted " + decrypted);
                    Map<String, String> responseMap = GenericResponseHandler.handleResponse(decrypted);


                    if (responseMap.get("ResponseType").equals("Success")) {

                        new OkDialog(context, responseMap.get("Message"), null, new OkDialog.IOkDialogCallback() {
                            @Override
                            public void handleResponse() {
                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                ((Activity) context).finish();
                            }
                        });

                    } else
                        Constants.showToast(context, responseMap.get("Message"));

                } catch (Exception e) {
                    e.printStackTrace();
                    new OkDialog(context, context.getString(R.string.errorAPI), null, null);
                }


            }
        });
    }


}
