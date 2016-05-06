package timesofmoney.qnbmerchant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;


public class Splash extends BaseActivity implements View.OnClickListener {

    CustomTextView txtEnglish,txtArebic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtEnglish= (CustomTextView) findViewById(R.id.txtEnglish);
        txtArebic=(CustomTextView) findViewById(R.id.txtArebic);

        txtEnglish.setOnClickListener(this);
        txtArebic.setOnClickListener(this);


        if(!TextUtils.isEmpty(AppSettings.getData(this,AppSettings.APPLANGUAGE)))
        {
            Constants.changeLanguage(AppSettings.getData(this,AppSettings.APPLANGUAGE),this);
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        String selectedLanguage="";

         if(v==txtEnglish)
             selectedLanguage="en";

        if(v==txtArebic)
            selectedLanguage="ar";

        Log.v("Lang",selectedLanguage);

        AppSettings.putData(this,AppSettings.APPLANGUAGE,selectedLanguage);

        Constants.changeLanguage(selectedLanguage,this);
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
