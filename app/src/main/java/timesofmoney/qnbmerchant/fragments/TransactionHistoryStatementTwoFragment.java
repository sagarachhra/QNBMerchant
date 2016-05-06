package timesofmoney.qnbmerchant.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.activities.TransactionDetailsActivity;
import timesofmoney.qnbmerchant.activities.TransactionHistoryActivity;
import timesofmoney.qnbmerchant.adapters.RecentTransactionsListAdapter;
import timesofmoney.qnbmerchant.adapters.TransactionHistoryPagerAdapter;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.models.StatementResponseDetails;
import timesofmoney.qnbmerchant.models.TodaysTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionHistoryStatementTwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionHistoryStatementTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionHistoryStatementTwoFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageView imgvwEdit;


    public TransactionHistoryStatementTwoFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionHistoryStatementTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionHistoryStatementTwoFragment newInstance(String param1, String param2) {
        TransactionHistoryStatementTwoFragment fragment = new TransactionHistoryStatementTwoFragment();
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

    ListView transactionStatementList;
    ProgressBar progressBar;
    LinearLayout layout_progress;
    RecentTransactionsListAdapter adapter;
    String  fromDate, toDate;
    CustomTextView textRangeDates;;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_transaction_history_statement_two, container, false);

        Bundle bundle = getArguments();


        fromDate = bundle.getString("fromDate");
        toDate = bundle.getString("toDate");

        imgvwEdit = (ImageView) view.findViewById(R.id.imgvwEdit);
        textRangeDates= (CustomTextView) view.findViewById(R.id.txtDates);
        textRangeDates.setText("Transaction Statement for dates \n"+fromDate + " to " + toDate);

        //StickyListHeadersListView stickyList = (StickyListHeadersListView) view.findViewById(R.id.listVIewRecentTransactions);
        transactionStatementList = (ListView) view.findViewById(R.id.list);
       // RecentTransactionsListAdapter adapter = new RecentTransactionsListAdapter(getActivity(), createList(7));
        //transactionStatementList.setAdapter(adapter);
        layout_progress = (LinearLayout) view.findViewById(R.id.layout_progress);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        transactionStatementList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TransactionDetailsActivity.class);
                startActivity(intent);
            }
        });


        imgvwEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TransactionHistoryStatementFragment)getParentFragment()).replaceFragment(0,"","");
            }
        });

        callAPIPagination("1", "20");
        return view;
    }

    private void callAPIPagination(String pageNo, String pageSize) {


        // list.addFooterView(list_footer);
        ((TransactionHistoryActivity) getActivity()).callAPI("", "", "", pageNo, pageSize, new TransactionHistoryActivity.ITransactionHistory() {
            @Override
            public void handleResponse(boolean isSuccess, String message, StatementResponseDetails statementResponseDetails) {

                // list.removeFooterView(list_footer);
                if (!isSuccess) {
                    progressBar.setVisibility(View.GONE);
                    // txtMessage.setText(message);
                    return;
                }
                adapter= (new RecentTransactionsListAdapter(getActivity(),statementResponseDetails.getTransactions().getTransactions()));
                transactionStatementList.setAdapter(adapter);
                transactionStatementList.setVisibility(View.VISIBLE);
                // list.setAdapter(new StatementAdapter(getActivity(), (ArrayList<Transaction>) statementResponseDetails.getTransactions().getTransactions()));

                layout_progress.setVisibility(View.GONE);
            }
        });
    }

    private List<TodaysTransaction> createList(int size) {

        List<TodaysTransaction> result = new ArrayList<TodaysTransaction>();
        for (int i=1; i <= size; i++) {
            TodaysTransaction objTodaysTransaction = new TodaysTransaction();
            objTodaysTransaction.setName("Name " + i);
            objTodaysTransaction.setCardNo("xxxx xxxx xxxx "+ "1234");
            objTodaysTransaction.setTranactionAmount(String.valueOf(500 * i));

            result.add(objTodaysTransaction);

        }

        return result;
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
