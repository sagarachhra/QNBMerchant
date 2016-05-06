package timesofmoney.qnbmerchant.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.activities.RegistrationActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationDetailsSecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationDetailsSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationDetailsSecondFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RegistrationActivity objRegistrationActivity;

    private EditText edtxtMerchantNumberOfBraches, edtxtMerchantRegistrationNumber, edtxtMerchantPostalCode, edtxtMerchantNationalID;
    private Button btnContinue;

    public RegistrationDetailsSecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationDetailsSecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationDetailsSecondFragment newInstance(String param1, String param2) {
        RegistrationDetailsSecondFragment fragment = new RegistrationDetailsSecondFragment();
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


        objRegistrationActivity = (RegistrationActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_registration_details_second, container, false);





        edtxtMerchantNumberOfBraches = (EditText) rootView.findViewById(R.id.edtxtMerchantNumberOfBranches);
        edtxtMerchantRegistrationNumber = (EditText) rootView.findViewById(R.id.edtxtMerchantRegistrationNumber);
        edtxtMerchantPostalCode = (EditText) rootView.findViewById(R.id.edtxtMerchantPostalCode);
        edtxtMerchantNationalID = (EditText) rootView.findViewById(R.id.edtxtMerchantNationalID);


        btnContinue = (Button) rootView.findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((RegistrationActivity) getActivity()).getViewPager()!=null)
                {
                    ((RegistrationActivity) getActivity()).getViewPager().setCurrentItem(2);
                }

            }
        });

        return rootView;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
