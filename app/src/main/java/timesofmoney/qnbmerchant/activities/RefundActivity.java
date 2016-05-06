package timesofmoney.qnbmerchant.activities;



import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.fragments.InitiateRefundFragment;
import timesofmoney.qnbmerchant.fragments.RefundConfirmFragment;

public class RefundActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        setToolbar(getResources().getString(R.string.refund));

        replaceFragment(0);


    }

    public void replaceFragment(int id) {

        Fragment fragment;
        switch (id) {
            case 0:

                fragment = new InitiateRefundFragment();
                getFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.frame_root, fragment).commit();
                break;




        }
    }



}
