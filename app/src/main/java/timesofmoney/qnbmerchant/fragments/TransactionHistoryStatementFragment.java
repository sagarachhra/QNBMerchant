package timesofmoney.qnbmerchant.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.RecentTransactionsListAdapter;
import timesofmoney.qnbmerchant.adapters.TransactionHistoryPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionHistoryStatementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionHistoryStatementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionHistoryStatementFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public TransactionHistoryStatementFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionHistoryStatementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionHistoryStatementFragment newInstance(String param1, String param2) {
        TransactionHistoryStatementFragment fragment = new TransactionHistoryStatementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_transaction_history_statement, container, false);

        replaceFragment(0,"","");


        return view;
    }

    public void replaceFragment(int id,String fromDate,String toDate) {

        Fragment fragment;
        switch (id) {
            case 0:

                fragment = new TransactionHistoryStatementOneFragment();
                getChildFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.frame_root, fragment).commit();
break;

            case 1:
                fragment = new TransactionHistoryStatementTwoFragment();
                Bundle bundle=new Bundle();
                bundle.putString("fromDate",fromDate);
                bundle.putString("toDate",toDate);
                fragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.frame_root, fragment).commit();
break;
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
