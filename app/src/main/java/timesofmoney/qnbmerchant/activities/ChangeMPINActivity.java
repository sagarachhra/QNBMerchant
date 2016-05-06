package timesofmoney.qnbmerchant.activities;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.github.glomadrian.codeinputlib.CodeInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.customviews.CustomEditText;
import timesofmoney.qnbmerchant.dialogs.OkDialog;
import timesofmoney.qnbmerchant.dialogs.ProgressDialog;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.GenericResponseHandler;

public class ChangeMPINActivity extends BaseActivity {


    Button btnChangeMPIN;
    String oldMpin, newMpin, confirmMpin;
    List<String> pinlist, NewList, ReList;
    CustomEditText edtPIN1, edtPIN2, edtPIN3, edtPIN4, edtNewPIN1, edtNewPIN2, edtNewPIN3, edtNewPIN4, edtRePIN1, edtRePIN2, edtRePIN3, edtRePIN4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mpin);

        setToolbar(getString(R.string.change_mpin));


        btnChangeMPIN = (Button) findViewById(R.id.btnChangeMPIN);

        pinlist = new ArrayList<>();
        pinlist.add("0");
        pinlist.add("0");
        pinlist.add("0");
        pinlist.add("0");

        NewList = new ArrayList<>();
        NewList.add("0");
        NewList.add("0");
        NewList.add("0");
        NewList.add("0");

        ReList = new ArrayList<>();
        ReList.add("0");
        ReList.add("0");
        ReList.add("0");
        ReList.add("0");

        edtPIN1 = (CustomEditText) findViewById(R.id.edtPin1);
        edtPIN2 = (CustomEditText) findViewById(R.id.edtPin2);
        edtPIN3 = (CustomEditText) findViewById(R.id.edtPin3);
        edtPIN4 = (CustomEditText) findViewById(R.id.edtPin4);
        edtNewPIN1 = (CustomEditText) findViewById(R.id.edtNewPin1);
        edtNewPIN2 = (CustomEditText) findViewById(R.id.edtNewPin2);
        edtNewPIN3 = (CustomEditText) findViewById(R.id.edtNewPin3);
        edtNewPIN4 = (CustomEditText) findViewById(R.id.edtNewPin4);
        edtRePIN1 = (CustomEditText) findViewById(R.id.edtRePin1);
        edtRePIN2 = (CustomEditText) findViewById(R.id.edtRePin2);
        edtRePIN3 = (CustomEditText) findViewById(R.id.edtRePin3);
        edtRePIN4 = (CustomEditText) findViewById(R.id.edtRePin4);


        btnChangeMPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Constants.isNetworkAvailable(ChangeMPINActivity.this)) {
                    new OkDialog(ChangeMPINActivity.this, getString(R.string.errorNetwork), "", null);
                    return;
                }
                if (validate())
                    callAPI();

            }
        });

        edtPIN1.setCustomTextWatcher(new CustomEditWatcher(edtPIN1, edtPIN2.getEditText(), null, 1, 0, pinlist));
        edtPIN2.setCustomTextWatcher(new CustomEditWatcher(edtPIN2, edtPIN3.getEditText(), edtPIN1.getEditText(), 1, 1, pinlist));
        edtPIN3.setCustomTextWatcher(new CustomEditWatcher(edtPIN3, edtPIN4.getEditText(), edtPIN2.getEditText(), 1, 2, pinlist));
        edtPIN4.setCustomTextWatcher(new CustomEditWatcher(edtPIN4, edtNewPIN1.getEditText(), edtPIN3.getEditText(), 1, 3, pinlist));


        edtNewPIN1.setCustomTextWatcher(new CustomEditWatcher(edtNewPIN1, edtNewPIN2.getEditText(), null, 1, 0, NewList));
        edtNewPIN2.setCustomTextWatcher(new CustomEditWatcher(edtNewPIN2, edtNewPIN3.getEditText(), edtNewPIN1.getEditText(), 1, 1, NewList));
        edtNewPIN3.setCustomTextWatcher(new CustomEditWatcher(edtNewPIN3, edtNewPIN4.getEditText(), edtNewPIN2.getEditText(), 1, 2, NewList));
        edtNewPIN4.setCustomTextWatcher(new CustomEditWatcher(edtNewPIN4, edtRePIN1.getEditText(), edtNewPIN3.getEditText(), 1, 3, NewList));


        edtRePIN1.setCustomTextWatcher(new CustomEditWatcher(edtRePIN1, edtRePIN2.getEditText(), null, 1, 0, ReList));
        edtRePIN2.setCustomTextWatcher(new CustomEditWatcher(edtRePIN2, edtRePIN3.getEditText(), edtRePIN1.getEditText(), 1, 1, ReList));
        edtRePIN3.setCustomTextWatcher(new CustomEditWatcher(edtRePIN3, edtRePIN4.getEditText(), edtRePIN2.getEditText(), 1, 2, ReList));
        edtRePIN4.setCustomTextWatcher(new CustomEditWatcher(edtRePIN4, null, edtRePIN3.getEditText(), 1, 3, ReList));



    }



    class CustomEditWatcher implements CustomEditText.CustomTextWatcher {
        EditText edtNext, edtPrevious;
        CustomEditText edtCurr;
        int MAXLENGTH = 0, index;
        List<String> list;


        public CustomEditWatcher(CustomEditText edtCurr, EditText edtNext, EditText edtPrevious, int MAXLENGTH, int index, List<String> list) {
            this.edtNext = edtNext;
            this.edtPrevious = edtPrevious;
            this.MAXLENGTH = MAXLENGTH;
            this.edtCurr = edtCurr;
            this.index = index;
            this.list = list;
        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().length() == MAXLENGTH && edtNext != null) {
                edtNext.requestFocus();
            }
            if (s.toString().length() <= 0 && edtPrevious != null) {
                edtPrevious.requestFocus();
                edtPrevious.setSelection(edtPrevious.getText().toString().length());
            }


            if (MAXLENGTH == 1 && edtCurr != null && !TextUtils.isEmpty(edtCurr.getEditText().getText().toString())) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        edtCurr.setCustomTextWatcher(null);
                        list.set(index, edtCurr.getText().toString());
                        edtCurr.getEditText().setText("*");
                        edtCurr.setCustomTextWatcher(CustomEditWatcher.this);
                    }
                }, 400);
            }
        }


    }


    private boolean validate() {

        oldMpin = pinlist.get(0) + "" + pinlist.get(1) + "" + pinlist.get(2) + "" + pinlist.get(3);
        newMpin = NewList.get(0) + "" + NewList.get(1) + "" + NewList.get(2) + "" + NewList.get(3);
        confirmMpin = ReList.get(0) + "" + ReList.get(1) + "" + ReList.get(2) + "" + ReList.get(3);


        if (oldMpin.length() != getResources().getInteger(R.integer.MPINLength)) {
            new OkDialog(this, getString(R.string.erroroldmpin), "", null);
            return false;
        }

        if (newMpin.length() != getResources().getInteger(R.integer.MPINLength)) {
            new OkDialog(this, getString(R.string.errornewmpin), "", null);
            return false;
        }

        if (confirmMpin.length() != getResources().getInteger(R.integer.MPINLength)) {
            new OkDialog(this, getString(R.string.errorconfirmmpin), "", null);
            return false;
        }

        if (!confirmMpin.equals(newMpin)) {
            new OkDialog(this, getString(R.string.errormpinnotconfirm), "", null);
            return false;
        }
        return true;
    }


    private void callAPI() {

        String xmlParam = "\t\t<MobileNumber>" + AppSettings.getData(this, AppSettings.MOBILE_NUMBER) + "</MobileNumber>\n" +
                "\t\t<OldMpin>" + Constants.getHash(oldMpin.getBytes()) + "</OldMpin>\n" +
                "\t\t<NewMpin>" + Constants.getHash(newMpin.getBytes()) + "</NewMpin>";

        xmlParam = Constants.getReqXML("ChangeMpin", xmlParam);

        try {
            xmlParam = AESecurity.getInstance().encryptString(xmlParam);
        } catch (Exception e) {
            e.printStackTrace();
            Constants.showToast(this, e.toString());
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(((Activity) this), this.getString(R.string.msgWait));
        progressDialog.show();
        RetrofitTask retrofitTask = RetrofitTask.getInstance();

        retrofitTask.executeTask(xmlParam, new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {

                progressDialog.dismiss();

                if (!isSuccess) {
                    new OkDialog(ChangeMPINActivity.this, response, null, null);
                    return;
                }

                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    Log.v("TAG", " Decrypted " + decrypted);
                    Map<String, String> responseMap = GenericResponseHandler.handleResponse(decrypted);

                    if (responseMap.get("ResponseType").equals("Success")) {

                        new OkDialog(ChangeMPINActivity.this, responseMap.get("Message"), null, new OkDialog.IOkDialogCallback() {
                            @Override
                            public void handleResponse() {
                                finish();
                            }
                        });

                    } else
                        new OkDialog(ChangeMPINActivity.this, responseMap.get("Reason"), null, null);

                } catch (Exception e) {
                    e.printStackTrace();
                    new OkDialog(ChangeMPINActivity.this, getString(R.string.errorAPI), null, null);
                }

            }
        });
    }
}
