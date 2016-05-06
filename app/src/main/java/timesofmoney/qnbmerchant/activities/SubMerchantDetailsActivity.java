package timesofmoney.qnbmerchant.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.models.SubUser;

public class SubMerchantDetailsActivity extends BaseActivity {

    CustomTextView subMerchantName,subMerchantMobileNumber,subMerchantEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_merchant_details);

        setToolbar("");


        SubUser s = getIntent().getParcelableExtra("subMerchantsDetails");

        subMerchantName = (CustomTextView)findViewById(R.id.txtvwSubMerchantName);
        subMerchantMobileNumber = (CustomTextView)findViewById(R.id.txtvwSubMerchantMobileNumber);
        subMerchantEmail = (CustomTextView)findViewById(R.id.txtvwSubMerchantEmailID);
        if(s!=null) {
            subMerchantName.setText(s.getFirstName() + " " + s.getLastName());
            subMerchantMobileNumber.setText(s.getMobileNumber());
            subMerchantEmail.setText(s.getEmailID());
        }
    }
}
