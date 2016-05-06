package timesofmoney.qnbmerchant.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.Map;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.SubMerchantAdapter;
import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.dialogs.OkDialog;
import timesofmoney.qnbmerchant.models.LoginResponse;
import timesofmoney.qnbmerchant.models.SubMerchant;
import timesofmoney.qnbmerchant.models.SubMerchantResponse;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.GenericResponseHandler;

public class SubMerchantActivity extends BaseActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_merchant);

        setToolbar(getString(R.string.sub_merchant));

        gridView = (GridView) findViewById(R.id.gridView);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SubMerchantActivity.this, SubMerchantDetailsActivity.class);
                startActivity(intent);
            }
        });

        callAPI();


    }

    private void callAPI() {
        String xmlParam = "\t\t<MobileNumber>" + AppSettings.getData(this, AppSettings.MOBILE_NUMBER) + "</MobileNumber>\n" +
                "\t\t<MerchantId>" + AppSettings.getData(this, AppSettings.MERCHANT_ID) + "</MerchantId>";

        xmlParam = Constants.getReqXML("ListSubUsers", xmlParam);

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
                    new OkDialog(SubMerchantActivity.this, response, null, null);
                    return;
                }


                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    Log.v("TAG", " Decrypted " + decrypted);


                    Serializer serializer = new Persister();
                    SubMerchantResponse subMerchantResponse = serializer.read(SubMerchantResponse.class, decrypted);
                    //Map<String, String> responseMap = GenericResponseHandler.handleResponse(decrypted);

                    if (subMerchantResponse.getResponse().getResponseType().equals("Success")) {



                        //finish();
                        gridView.setAdapter(new SubMerchantAdapter(SubMerchantActivity.this,subMerchantResponse.getSubMerchantResponseDetails().getSubMerchant().getListSubUsers() ));
                        //subMerchantResponse.getSubMerchantResponseDetails().getSubMerchant().getListSubUsers()


                    } else
                        new OkDialog(SubMerchantActivity.this,subMerchantResponse.getSubMerchantResponseDetails().getReason(), "", null);

                } catch (Exception e) {
                    e.printStackTrace();
                    new OkDialog(SubMerchantActivity.this, getString(R.string.errorAPI), null, null);
                }



            }
        });


    }
}
