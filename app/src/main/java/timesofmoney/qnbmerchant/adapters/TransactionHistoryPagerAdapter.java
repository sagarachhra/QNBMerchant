package timesofmoney.qnbmerchant.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import timesofmoney.qnbmerchant.fragments.TransactionHistoryRecentFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryStatementFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryStatementTwoFragment;

public class TransactionHistoryPagerAdapter extends FragmentPagerAdapter {


    public TransactionHistoryPagerAdapter(FragmentManager fm) {

        super(fm);


    }


    @Override

    public Fragment getItem(int index) {


        switch (index) {

            case 0:

                // Top Rated fragment activity


               /* Fragment f1 = new TransactionHistoryStatementTwoFragment();
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.replace(R.id.frame_root, f1); // f1_container is your FrameLayout container
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

                return f1;*/
                return new TransactionHistoryRecentFragment();

            case 1:

                // Games fragment activity

              /*  Fragment f2 = new TransactionHistoryStatementFragment();
                FragmentTransaction ft1 = mFragmentManager.beginTransaction();
                ft1.replace(R.id.frame_root, f2); // f1_container is your FrameLayout container
                ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft1.addToBackStack(null);
                ft1.commit();

                return f2;*/

                return new TransactionHistoryStatementFragment();


        }


        return null;

    }


    @Override

    public int getCount() {

        // get item count - equal to number of tabs

        return 2;

    }
}
