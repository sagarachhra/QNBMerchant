package timesofmoney.qnbmerchant.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.dialogs.OkDialog;
import timesofmoney.qnbmerchant.dialogs.ProgressDialog;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.GenericResponseHandler;

public class ForgotMPINActivity extends BaseActivity {

    CustomTextView txtvwThanksMsg;
    Button btnGenerateMPIN;
    LinearLayout linlayGenerateMPINContainer;

    EditText edtxtMobileNumber, edtxtCitizenID;
    String strMobileNumber, strCitizenID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_mpin);

        setToolbar(getString(R.string.forgot_mpin_title));

        txtvwThanksMsg = (CustomTextView) findViewById(R.id.txtvwThanksMsg);
        btnGenerateMPIN = (Button) findViewById(R.id.btnGenerateMPIN);
        linlayGenerateMPINContainer = (LinearLayout) findViewById(R.id.linlayGenerateMPINContainer);
        edtxtMobileNumber = (EditText) findViewById(R.id.edtxtMobileNumber);
        edtxtCitizenID = (EditText) findViewById(R.id.edtxtCitizenID);




        btnGenerateMPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!Constants.isNetworkAvailable(ForgotMPINActivity.this)) {
                    new OkDialog(ForgotMPINActivity.this, getString(R.string.errorNetwork), "", null);
                    return;
                }
                if (validate())
                    callAPI();


               /* linlayGenerateMPINContainer.setVisibility(View.GONE);
                txtvwThanksMsg.setVisibility(View.VISIBLE);*/
            }
        });




    }

    private void callAPI() {

        String xmlParam = "\t\t<MobileNumber>" + strMobileNumber + "</MobileNumber>\n" +
                "\t\t<CitizenId>" + edtxtCitizenID.getText().toString() + "</CitizenId>\n"+
                "\t\t<TaxIdentificationNumber>" + "TIN12345" + "</TaxIdentificationNumber>\n"+
                "\t\t<PinCode>" + "4000004" + "</PinCode>\n"+
                "\t\t<EmailId>" + "test@gmail.com" + "</EmailId>";

        xmlParam = Constants.getReqXML("ForgotMpin", xmlParam);

        try {
            xmlParam = AESecurity.getInstance().encryptString(xmlParam);
        } catch (Exception e) {
            e.printStackTrace();
            Constants.showToast(this, e.toString());
            return;
        }

       showProgress();
        RetrofitTask retrofitTask = RetrofitTask.getInstance();

        retrofitTask.executeTask(xmlParam, new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {

                dismissProgress();

                if (!isSuccess) {
                    new OkDialog(ForgotMPINActivity.this, response, null, null);
                    return;
                }

                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    Log.v("TAG", " Decrypted " + decrypted);
                    Map<String, String> responseMap = GenericResponseHandler.handleResponse(decrypted);

                    if (responseMap.get("ResponseType").equals("Success")) {

                        new OkDialog(ForgotMPINActivity.this, responseMap.get("Message"), null, new OkDialog.IOkDialogCallback() {
                            @Override
                            public void handleResponse() {
                                finish();
                            }
                        });

                    } else
                        new OkDialog(ForgotMPINActivity.this, responseMap.get("Reason"), null, null);

                } catch (Exception e) {
                    e.printStackTrace();
                    new OkDialog(ForgotMPINActivity.this, getString(R.string.errorAPI), null, null);
                }
            }
        });


    }

    private boolean validate() {

        strMobileNumber = edtxtMobileNumber.getText().toString();
        strCitizenID = edtxtCitizenID.getText().toString();

        if (strMobileNumber.trim().length() == 0) {
            new OkDialog(this, getString(R.string.errorMobileNumber), null, null);
            return false;
        }else if(strMobileNumber.trim().length()<10){
            new OkDialog(this, getString(R.string.errorMobileNumberTenDigit), null, null);
            return false;
        }

        if (strCitizenID.trim().length() == 0) {
            new OkDialog(this, getString(R.string.errorCitizenID), null, null);
            return false;
        }

        return true;



    }
}
