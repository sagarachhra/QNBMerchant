package timesofmoney.qnbmerchant.activities;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.RegistrationFragmentAdapter;
import timesofmoney.qnbmerchant.fragments.RegistrationDetailsFirstFragment;
import timesofmoney.qnbmerchant.fragments.RegistrationDetailsSecondFragment;
import timesofmoney.qnbmerchant.fragments.RegistrationDetailsThirdFragment;

public class RegistrationActivity extends BaseActivity implements RegistrationDetailsFirstFragment.OnFragmentInteractionListener, RegistrationDetailsSecondFragment.OnFragmentInteractionListener, RegistrationDetailsThirdFragment.OnFragmentInteractionListener {

    RegistrationFragmentAdapter mAdapter;
    private ViewPager mPager;
    CirclePageIndicator circlePageIndicator;
    TextView txtvwTitle, txtvwSubtitle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        mAdapter = new RegistrationFragmentAdapter(getSupportFragmentManager());
        txtvwTitle = (TextView) findViewById(R.id.txtvwTitle);
        txtvwSubtitle = (TextView) findViewById(R.id.txtvwSubtitle);


        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        circlePageIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(mPager);


        final float density = getResources().getDisplayMetrics().density;
        circlePageIndicator.setBackgroundColor(0);
        circlePageIndicator.setRadius(5 * density);
        circlePageIndicator.setPageColor(Color.parseColor("#701246"));
        circlePageIndicator.setFillColor(Color.parseColor("#001435"));
        circlePageIndicator.setStrokeColor(0xFF000000);
        circlePageIndicator.setStrokeWidth(1 * density);

        // To set title and subtitle for each page.

        ViewPager.OnPageChangeListener objPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("TAG", ""+position);
                switch (position)
                {
                    case 0:  setFragmentTitle(getString(R.string.welcome_merchant));
                        setFragmentSubtitle(getString(R.string.provide_your_details));
                        circlePageIndicator.setVisibility(View.VISIBLE);
                        break;

                    case 1: setFragmentTitle(getString(R.string.business_info));
                        hideFragmentSubtitle();
                        circlePageIndicator.setVisibility(View.VISIBLE);
                        break;

                    case 2: setFragmentTitle(getString(R.string.contact_person_info));
                        hideFragmentSubtitle();
                        circlePageIndicator.setVisibility(View.VISIBLE);
                        break;

                    case 3:
                        hideFragmentTitle();
                        hideFragmentSubtitle();
                        circlePageIndicator.setVisibility(View.GONE);

                        break;

                    default: setFragmentTitle(getString(R.string.welcome_merchant));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        mPager.addOnPageChangeListener(objPageChangeListener);
        // First time to set title of page.
        objPageChangeListener.onPageSelected(0);





    }
    public ViewPager getViewPager()
    {
        if(mPager!=null)
        {
            return mPager;
        }
        return null;
    }



    public void setFragmentTitle(String strTitle)
    {
        if (txtvwTitle!=null)
        { if (txtvwTitle.getVisibility() == View.GONE)
            txtvwTitle.setVisibility(View.VISIBLE);
            txtvwTitle.setText(strTitle);
        }
    }

    public void setFragmentSubtitle(String strSubtitle)
    {
        if (txtvwSubtitle!=null)
        {
            if (txtvwSubtitle.getVisibility() == View.GONE)
            txtvwSubtitle.setVisibility(View.VISIBLE);
            txtvwSubtitle.setText(strSubtitle);
        }
    }

    public void hideFragmentTitle()
    {
        if (txtvwTitle!=null)
        {
            txtvwTitle.setVisibility(View.GONE);
        }
    }

    public void hideFragmentSubtitle()
    {
        if (txtvwSubtitle!=null)
        {

                txtvwSubtitle.setVisibility(View.GONE);

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
