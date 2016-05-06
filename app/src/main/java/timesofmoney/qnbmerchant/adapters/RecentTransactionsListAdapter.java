package timesofmoney.qnbmerchant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.models.TodaysTransaction;
import timesofmoney.qnbmerchant.models.Transaction;
import timesofmoney.qnbmerchant.models.adaptermodels.Item;
import timesofmoney.qnbmerchant.models.adaptermodels.ListSection;

/**
 * Created by pankajp on 13-04-2016.
 */
public class RecentTransactionsListAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    //private List<TodaysTransaction> listRecentTransactions;
    List<Item> listItems;
    List<Transaction> listRecentTransactions;

    private Context mContext;
    private final int SECTIONED_ITEM = 0;
    private final int CONTENT_ITEM = 1;

    private String[] dates = new String[]{"A", "B"};
    boolean isSectional;

    public RecentTransactionsListAdapter(Context mContext, List<Item> listRecentTransactions, boolean isSectional)
    {
        inflater = LayoutInflater.from(mContext);
        this.listItems = listRecentTransactions;
        this.isSectional=isSectional;
    }

    public RecentTransactionsListAdapter(Context mContext, List<Transaction> listRecentTransactions)
    {
        inflater = LayoutInflater.from(mContext);
        this.listRecentTransactions = listRecentTransactions;
        this.isSectional=isSectional;
    }
    /*@Override
    public long getHeaderId(int position) {
        return listRecentTransactions.get(position).getName().charAt(0);
    }*/

    public int getItemType(int position) {
        if (listItems.get(position) instanceof ListSection)
            return SECTIONED_ITEM;
        return CONTENT_ITEM;
    }

    @Override
    public int getCount() {
        return listRecentTransactions.size();
    }

    @Override
    public Object getItem(int position) {
        return listRecentTransactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.todays_transaction_list_item, parent, false);
            holder.txtvwName = (TextView) convertView.findViewById(R.id.txtvwName);
            holder.txtvwCardNo = (TextView) convertView.findViewById(R.id.txtvwCArdNumber);
            holder.txtvwAmount = (TextView) convertView.findViewById(R.id.txtvwTransactionAmount);
            holder.imgvwRefund = (ImageView) convertView.findViewById(R.id.imgvwRefund);
            holder.headerSection = (RelativeLayout) convertView.findViewById(R.id.rl_section);
            holder.txtSection= (TextView) convertView.findViewById(R.id.txtsection);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headerSection.setVisibility(View.GONE);
        holder.txtSection.setVisibility(View.GONE);

    if(isSectional)
    {
         int rowType=getItemType(position);
        holder.headerSection.setVisibility(View.VISIBLE);
        holder.txtSection.setVisibility(View.VISIBLE);

        if(rowType==SECTIONED_ITEM)
        {
            ListSection listSection= (ListSection) listItems.get(position);
            holder.txtSection.setText(listSection.getHeader());

        }else
        {

            Transaction transaction= (Transaction) listItems.get(position);
            holder.txtvwAmount.setText(transaction.getAmount());

        }
    }else
    {
         Transaction transaction=  listRecentTransactions.get(position);
         holder.txtvwAmount.setText(transaction.getAmount());

    }

        if (position%3==0)
        {
            holder.imgvwRefund.setVisibility(View.VISIBLE);
        }


        return convertView;
    }


    class HeaderViewHolder {
        TextView txtvwHeader;
    }

    class ViewHolder {
        protected TextView txtvwName;
        protected TextView txtvwCardNo;
        protected TextView txtvwAmount;
        protected ImageView imgvwRefund;
        RelativeLayout headerSection;
        TextView txtSection;
    }

}
