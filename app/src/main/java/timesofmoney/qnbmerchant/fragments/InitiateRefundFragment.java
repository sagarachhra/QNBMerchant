package timesofmoney.qnbmerchant.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import timesofmoney.qnbmerchant.R;


/**
 * Created by pankajp on 15-04-2016.
 */
public class InitiateRefundFragment extends Fragment {

    Button initiateRefundBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_initiate_refund, container, false);
        initiateRefundBtn = (Button)rootView.findViewById(R.id.btnRefund);
        initiateRefundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(0);
            }
        });

        return rootView;
    }

    public void replaceFragment(int id) {

        Fragment fragment;
        switch (id) {
            case 0:

                fragment = new RefundConfirmFragment();
                getActivity().getFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.frame_root, fragment).commit();
                break;






        }
    }
}
