package timesofmoney.qnbmerchant.activities;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.GenerateQRCodePagerAdapter;
import timesofmoney.qnbmerchant.adapters.TransactionHistoryPagerAdapter;
import timesofmoney.qnbmerchant.customviews.CustomTabLayout;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.fragments.GenerateQRCodeFragment;
import timesofmoney.qnbmerchant.fragments.MyQRCodeFragment;

public class GenerateQRCodeActivity extends BaseActivity implements GenerateQRCodeFragment.OnFragmentInteractionListener, MyQRCodeFragment.OnFragmentInteractionListener{

    private GenerateQRCodePagerAdapter mAdapter;

    private ViewPager mViewPager;

    private CustomTabLayout customTabLayout;

    CustomTextView location,mvisaId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);


        setToolbar(QNBMerchantApp.getMerchantProfile().getCompanyName());
        location= (CustomTextView)findViewById(R.id.txtvwLocation);
        location.setText(QNBMerchantApp.getMerchantProfile().getAddress()+","+QNBMerchantApp.getMerchantProfile().getCity());
        mvisaId =(CustomTextView) findViewById(R.id.txtvwmVisaID);
        mvisaId.setText("mVisa Id: "+QNBMerchantApp.getMerchantProfile().getMerchantId());

        TextView txtvwAmount = (TextView) findViewById(R.id.txtvwAmount);
        txtvwAmount.setVisibility(View.GONE);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        customTabLayout = new CustomTabLayout(findViewById(R.id.tabLayout));



        // Tab Initialization

        //initialiseTabHost();

        mAdapter = new GenerateQRCodePagerAdapter(getSupportFragmentManager());

        customTabLayout.setTabTexts(getString(R.string.generate), getString(R.string.my_qr_code));

        customTabLayout.setCurrentActiveTab(0);



        mViewPager.setAdapter(mAdapter);


        customTabLayout.setTabListener(new CustomTabLayout.IOnTabClicked() {
            @Override
            public void onTabClicked(int index) {

                mViewPager.setCurrentItem(index);
            }

        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //mTabHost.setCurrentTab(position);
                customTabLayout.setCurrentActiveTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
