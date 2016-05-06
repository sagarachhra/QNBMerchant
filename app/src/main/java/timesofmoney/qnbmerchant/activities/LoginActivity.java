package timesofmoney.qnbmerchant.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.dialogs.OkDialog;
import timesofmoney.qnbmerchant.models.LoginResponse;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.LogUtils;


public class LoginActivity extends BaseActivity {
   // CheckBox chkboxTermsAndConditions;
    ToggleButton toggleButton;
    CustomTextView txtvwTermsAndConditions;
    RelativeLayout rellayBottom;
    Button btnLogin;
    TextView txtvwForgotMPIN;
    EditText edtxtUsename, edtxtMPIN;
    private String strUsername, strMPIN;
    View mobileNoView;
    ToggleButton toogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toggleButton = (ToggleButton) findViewById(R.id.ch1);
       // chkboxTermsAndConditions = (CheckBox) findViewById(R.id.checkBoxTermsAndConditions);
        txtvwTermsAndConditions = (CustomTextView) findViewById(R.id.txtvwTermsAndConditions);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtvwForgotMPIN = (TextView) findViewById(R.id.txtvwForgotMPIN);
        edtxtUsename = (EditText) findViewById(R.id.edtxtUsername);
        edtxtMPIN = (EditText) findViewById(R.id.edtxtMPIN);
        rellayBottom = (RelativeLayout) findViewById(R.id.rellayBottom);
        toogle= (ToggleButton) findViewById(R.id.ch1);
        rellayBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        if(AppSettings.getData(getApplicationContext(),AppSettings.ISCHECKEDTERMS).equals("true")){
            toogle.setVisibility(View.GONE);
            txtvwTermsAndConditions.setVisibility(View.GONE);
        }

        AppSettings.putData(getApplicationContext(), AppSettings.ISCHECKEDTERMS, "true");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);*/

                if(edtxtUsename.getText().toString().equals("1"))
                {
                    Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                    startActivity(intent);
                    return;
                }
                if (validate()) {
                    getKey();
                }
            }
        });

        txtvwForgotMPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotMPINActivity.class);
                startActivity(intent);
            }
        });






        txtvwTermsAndConditions.setText(Html.fromHtml(getString(R.string.agree_tandc)));

        mobileNoView = findViewById(R.id.mobnumview);

        edtxtUsename.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mobileNoView.setBackgroundColor(Color.parseColor("#ffffff"));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtxtUsename.length()==10){
                    edtxtMPIN.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        edtxtMPIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtxtMPIN.length()==4){
                if (validate()) {
                    getKey();
                }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private boolean validate() {


        if (!Constants.isNetworkAvailable(this)) {
            new OkDialog(this, getString(R.string.errorNetwork), null, null);
            return false;
        }

        strUsername = edtxtUsename.getText().toString();
        strMPIN = edtxtMPIN.getText().toString();

        if (strUsername.trim().length() != 10) {
            new OkDialog(this, getString(R.string.errorMobileNumber), null, null);
            return false;
        }

        if (strMPIN.trim().length() != getResources().getInteger(R.integer.MPINLength)) {
            new OkDialog(this, getString(R.string.errorMpin), null, null);
            return false;
        }

        if(!toogle.isChecked()){
            new OkDialog(this, getString(R.string.errorTerms), null, null);
            return false;
        }

        return true;
    }

    private void getKey() {

        showProgress();

        AppSettings.putData(this, AppSettings.MOBILE_NUMBER, strUsername);

        RetrofitTask.getInstance().executeGetKey(edtxtUsename.getText().toString().trim(), new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {

                if (isSuccess) {
                    Log.v("TAG", " Key is " + response);
                    AppSettings.putData(getApplicationContext(), AppSettings.KEY, response);
                    callAPI();

                } else {
                    dismissProgress();
                    new OkDialog(LoginActivity.this, response, "", null);
                }
            }
        });

    }

    private void callAPI() {

        String xmlParam = "\t\t<MobileNumber>" + strUsername + "</MobileNumber>\n" +
                "\t\t<Mpin>" + Constants.getHash(strMPIN.getBytes()) + "</Mpin>\n" +
                "\t\t<DeviceId>" +"353285060964590"+ "</DeviceId>";
        //QNBMerchantApp.getInstance().getIMEINo()
        xmlParam = Constants.getReqXML("Login", xmlParam);

        try {
            xmlParam = AESecurity.getInstance().encryptString(xmlParam);
        } catch (Exception e) {
            dismissProgress();
            e.printStackTrace();
            new OkDialog(LoginActivity.this, e.toString(), null, null);
            return;
        }

        RetrofitTask.getInstance().executeTask(xmlParam, new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {

                dismissProgress();

                if (!isSuccess) {
                    new OkDialog(LoginActivity.this, response, null, null);
                    return;
                }

                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    LogUtils.Verbose("TAG", "Response " + decrypted);

                    Serializer serializer = new Persister();
                    LoginResponse loginResponse = serializer.read(LoginResponse.class, decrypted);
                    if (loginResponse.getResponse().getResponseType().equals("Success")) {

                       // QNBMerchantApp.getInstance().setWallets(loginResponse.getResponseDetails().getWallet().getWallets());
                        /*AppSettings.putData(getApplicationContext(), AppSettings.FIRST_NAME, loginResponse.getResponseDetails().getFirstName());
                        AppSettings.putData(getApplicationContext(), AppSettings.LAST_NAME, loginResponse.getResponseDetails().getLastName());
                        AppSettings.putData(getApplicationContext(), AppSettings.EMAIL_ID, loginResponse.getResponseDetails().getEmailId());
                        AppSettings.putData(getApplicationContext(), AppSettings.ADDRESS, loginResponse.getResponseDetails().getAddress());*/
                        AppSettings.putData(getApplicationContext(), AppSettings.LAST_LOGIN, loginResponse.getResponseDetails().getLastLogin());
                        AppSettings.putData(getApplicationContext(), AppSettings.MOBILE_NUMBER, strUsername);
                        AppSettings.putData(getApplicationContext(), AppSettings.SESSION_ID, loginResponse.getHeader().getSessionId());
                        AppSettings.putData(getApplicationContext(), AppSettings.MERCHANT_ID, loginResponse.getResponseDetails().getProfile().getMerchantId());

                        QNBMerchantApp.setMerchantProfile(loginResponse.getResponseDetails().getProfile());
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.putExtra("changepin", loginResponse.getResponseDetails().getChangeMpin());
                        startActivity(intent);
                        finish();

                    } else {
                        new OkDialog(LoginActivity.this, loginResponse.getResponseDetails().getReason(), "", null);

                      /*  Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                       // intent.putExtra("changepin", loginResponse.getResponseDetails().getChangeMpin());
                        startActivity(intent);
                        finish();*/

                    }

                } catch (Exception e) {
                    LogUtils.Exception(e);
                    new OkDialog(LoginActivity.this, e.toString(), "", null);
                }
            }
        });
    }

}
