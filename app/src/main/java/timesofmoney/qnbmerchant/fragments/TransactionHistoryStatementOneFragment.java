package timesofmoney.qnbmerchant.fragments;

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
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.utilities.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionHistoryStatementOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionHistoryStatementOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionHistoryStatementOneFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    LinearLayout  layout_from, layout_to, layout_onemth, layout_sixmth, layout_threemth,layout_fifteenDays;
    LayoutInflater inflater;

    CustomTextView txtFrom, txtTo;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private String fromDate = "", toDate = "";

    Button btnProceed;

    public TransactionHistoryStatementOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionHistoryStatementOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionHistoryStatementOneFragment newInstance(String param1, String param2) {
        TransactionHistoryStatementOneFragment fragment = new TransactionHistoryStatementOneFragment();
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

        View view = inflater.inflate(R.layout.fragment_transaction_history_statement_one, container, false);

        layout_from = (LinearLayout) view.findViewById(R.id.layout_from);
        layout_to = (LinearLayout) view.findViewById(R.id.layout_to);
        txtFrom = (CustomTextView) view.findViewById(R.id.txtfrom);
        txtTo = (CustomTextView) view.findViewById(R.id.txtTo);

        layout_onemth = (LinearLayout) view.findViewById(R.id.onemth);
        layout_sixmth = (LinearLayout) view.findViewById(R.id.sixmth);
        layout_threemth = (LinearLayout) view.findViewById(R.id.threemth);
        layout_fifteenDays= (LinearLayout) view.findViewById(R.id.fifteendays);
        btnProceed= (Button) view.findViewById(R.id.btnProceed);

        layout_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new CustomDatePicker(txtFrom), calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        layout_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new CustomDatePicker(txtTo), calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
        layout_onemth.setOnClickListener(this);
        layout_threemth.setOnClickListener(this);
        layout_sixmth.setOnClickListener(this);
        layout_fifteenDays.setOnClickListener(this);
        btnProceed.setOnClickListener(this);

       /* btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TransactionHistoryStatementFragment)getParentFragment()).replaceFragment(1);


            }
        });*/
        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == btnProceed) {
            if (txtFrom.getText().toString().equals(getString(R.string.from))) {
                Constants.showToast(getActivity(), getString(R.string.errFromdate));
                return;
            }

            if (txtTo.getText().toString().equals(getString(R.string.to))) {
                Constants.showToast(getActivity(), getString(R.string.errTodate));
                return;
            }


            try {
                Date from = sdf.parse(txtFrom.getText().toString());
                Date to = sdf.parse(txtTo.getText().toString());

                if (to.before(from)) {
                    Constants.showToast(getActivity(), getString(R.string.errdate));
                    return;
                }

                fromDate = txtFrom.getText().toString();
                toDate = txtTo.getText().toString();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (v == layout_onemth) {
            Calendar calendar = Calendar.getInstance();
            toDate = sdf.format(calendar.getTime());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            fromDate = sdf.format(calendar.getTime());

        }

        if (v == layout_threemth) {
            Calendar calendar = Calendar.getInstance();
            toDate = sdf.format(calendar.getTime());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3);
            fromDate = sdf.format(calendar.getTime());

        }

        if (v == layout_sixmth) {
            Calendar calendar = Calendar.getInstance();
            toDate = sdf.format(calendar.getTime());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
            fromDate = sdf.format(calendar.getTime());

        }

        if (v == layout_fifteenDays) {
            Calendar calendar = Calendar.getInstance();
            toDate = sdf.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 15);
            fromDate = sdf.format(calendar.getTime());

        }


        ((TransactionHistoryStatementFragment) getParentFragment()).replaceFragment(1, fromDate, toDate);
    }

    class CustomDatePicker implements DatePickerDialog.OnDateSetListener {

        TextView txt;

        public CustomDatePicker(TextView txt)
        {
            this.txt=txt;
        }

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);

            month1 = month1.length() > 1 ? month1 : "0" + month1;
            txt.setText(day1 + "-" + month1 + "-" + year1);

        }
    };

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
