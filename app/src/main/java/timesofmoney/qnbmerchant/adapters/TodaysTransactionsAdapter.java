package timesofmoney.qnbmerchant.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.models.TodaysTransaction;

/**
 * Created by pankajp on 11-04-2016.
 */
public class TodaysTransactionsAdapter extends RecyclerView.Adapter<TodaysTransactionsAdapter.TodaysTransactionsViewHolder> {

    private List<TodaysTransaction> listTodaysTransactions;


    public TodaysTransactionsAdapter(List<TodaysTransaction> listTodaysTransactions)
    {
        this.listTodaysTransactions = listTodaysTransactions;
    }


    @Override
    public TodaysTransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todays_transaction_list_item, parent, false);
        return new TodaysTransactionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodaysTransactionsViewHolder holder, int position) {

        TodaysTransaction objTodaysTransaction = listTodaysTransactions.get(position);
        holder.txtvwName.setText(objTodaysTransaction.getName());
        holder.txtvwCardNo.setText(objTodaysTransaction.getCardNo());
        holder.txtvwAmount.setText(objTodaysTransaction.getTranactionAmount());
        holder.imgvwRefund.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return listTodaysTransactions.size();
    }

    public static class TodaysTransactionsViewHolder extends RecyclerView.ViewHolder {
        protected CustomTextView txtvwName;
        protected CustomTextView txtvwCardNo;
        protected CustomTextView txtvwAmount;
        protected ImageView imgvwRefund;


        public TodaysTransactionsViewHolder(View v) {
            super(v);
            txtvwName = (CustomTextView) v.findViewById(R.id.txtvwName);
            txtvwCardNo = (CustomTextView) v.findViewById(R.id.txtvwCArdNumber);
            txtvwAmount = (CustomTextView) v.findViewById(R.id.txtvwTransactionAmount);
            imgvwRefund = (ImageView) v.findViewById(R.id.imgvwRefund);

        }
    }
}


