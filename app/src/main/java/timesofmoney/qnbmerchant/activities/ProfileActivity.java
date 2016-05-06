package timesofmoney.qnbmerchant.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.models.MerchantProfile;

public class ProfileActivity extends BaseActivity {

    TextView merchantName, merchantAddress, merchantContactNumber, merchantEmailID, txtvwComapnyName, txtvwmVisaID;

    private MerchantProfile objMerchantProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolbar(getString(R.string.profile));
        merchantName = (TextView) findViewById(R.id.txtvwMerchantName);
        merchantAddress = (TextView) findViewById(R.id.txtvwMerchantAddress);
        merchantContactNumber = (TextView) findViewById(R.id.txtvwMerchantContactNumber);
        merchantEmailID = (TextView) findViewById(R.id.txtvwMerchantEmail);
        txtvwComapnyName = (TextView) findViewById(R.id.txtvwCompanyName);
        txtvwmVisaID = (TextView) findViewById(R.id.txtvwmVisaID);

        objMerchantProfile = QNBMerchantApp.getMerchantProfile();

        if (objMerchantProfile != null) {

            txtvwComapnyName.setText(QNBMerchantApp.getMerchantProfile().getCompanyName() + " " + QNBMerchantApp.getMerchantProfile().getBusinessDescription());
            txtvwmVisaID.setText("mVisa " + QNBMerchantApp.getMerchantProfile().getMerchantId());

            merchantName.setText(objMerchantProfile.getFirstName() + " " + objMerchantProfile.getLastName());
            merchantAddress.setText(objMerchantProfile.getAddress());
            merchantContactNumber.setText(objMerchantProfile.getMobileNumber());
            merchantEmailID.setText(objMerchantProfile.getEmailId());
        }


    }
}
