package timesofmoney.qnbmerchant.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.activities.SubMerchantActivity;
import timesofmoney.qnbmerchant.activities.SubMerchantDetailsActivity;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.models.SubMerchant;
import timesofmoney.qnbmerchant.models.SubUser;

/**
 * Created by pankajp on 06-04-2016.
 */
public class SubMerchantAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubUser> alSubMerchants;
    private  LayoutInflater inflater;


    public SubMerchantAdapter(Context mContext, List<SubUser> alSubMerchants) {
        this.mContext = mContext;
        this.alSubMerchants = alSubMerchants;
       inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return this.alSubMerchants.size();//this.alSubMerchants.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View gridView;
        CustomTextView txtSubmerchantName,txtSubmerchantId;
        if (convertView==null)
        {

            gridView = inflater.inflate( R.layout.sub_merchant_item , null);

            txtSubmerchantName= (CustomTextView) gridView.findViewById(R.id.txtvwSubMerchantName);
            txtSubmerchantId= (CustomTextView) gridView.findViewById(R.id.txtvwSubMerchantID);

            txtSubmerchantName.setText(alSubMerchants.get(position).getFirstName().toString() +
            " "+alSubMerchants.get(position).getLastName().toString());
            txtSubmerchantId.setText(alSubMerchants.get(position).getId());



        }

        else
        {
            gridView = (View) convertView;
        }

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SubMerchantDetailsActivity.class);
                intent.putExtra("subMerchantsDetails",alSubMerchants.get(position));
                mContext.startActivity(intent);
            }
        });


        return gridView;
    }
}
