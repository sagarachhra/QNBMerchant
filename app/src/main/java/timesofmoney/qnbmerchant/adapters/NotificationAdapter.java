package timesofmoney.qnbmerchant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.models.QNBNotofication;

/**
 * Created by pankajp on 4/28/2016.
 */
public class NotificationAdapter extends BaseAdapter {

    Context context;
    List<QNBNotofication> qnbNotoficationList;
    LayoutInflater inflater;

    public NotificationAdapter(Context context, List<QNBNotofication> qnbNotoficationList) {
        this.context = context;
        this.qnbNotoficationList = qnbNotoficationList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return qnbNotoficationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null)
        {
            holder=new ViewHolder();

            convertView=inflater.inflate(R.layout.notification_row,parent,false);

            holder.txtDate= (CustomTextView) convertView.findViewById(R.id.txtDate);
            holder.txtMessage= (CustomTextView) convertView.findViewById(R.id.txtMessage);

            convertView.setTag(holder);
        }

        holder= (ViewHolder) convertView.getTag();

        QNBNotofication qnbNotofication=qnbNotoficationList.get(position);

        holder.txtDate.setText(qnbNotofication.getTimestamp());
        holder.txtMessage.setText(qnbNotofication.getMessage());



        return convertView;
    }

    class ViewHolder
    {
        CustomTextView txtDate,txtMessage;

    }
}
