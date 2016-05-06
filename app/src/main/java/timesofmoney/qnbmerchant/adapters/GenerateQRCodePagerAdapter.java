package timesofmoney.qnbmerchant.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import timesofmoney.qnbmerchant.fragments.GenerateQRCodeFragment;
import timesofmoney.qnbmerchant.fragments.MyQRCodeFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryRecentFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryStatementFragment;

public class GenerateQRCodePagerAdapter extends FragmentPagerAdapter {


    public GenerateQRCodePagerAdapter(FragmentManager fm) {

        super(fm);

    }


    @Override

    public Fragment getItem(int index) {


        switch (index) {

            case 0:

                // Top Rated fragment activity

                return new GenerateQRCodeFragment();

            case 1:

                // Games fragment activity

                return new MyQRCodeFragment();



        }


        return null;

    }


    @Override

    public int getCount() {

        // get item count - equal to number of tabs

        return 2;

    }
}
