package timesofmoney.qnbmerchant.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.NotificationAdapter;
import timesofmoney.qnbmerchant.models.QNBNotofication;
import timesofmoney.qnbmerchant.utilities.AppSettings;


public class NotificationActivity extends BaseActivity {

    ListView list;
    LinearLayout layout_clear;
    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setToolbar("Notification");

        list = (ListView) findViewById(R.id.list);
        layout_clear = (LinearLayout) findViewById(R.id.clear);

        Gson gson = new Gson();
        String json = AppSettings.getData(this, AppSettings.NOTIFICATIONS);

        Type type = new TypeToken<ArrayList<QNBNotofication>>() {
        }.getType();
        final List<QNBNotofication> qnbNotoficationList = gson.fromJson(json, type);

        if (qnbNotoficationList != null) {
            notificationAdapter = new NotificationAdapter(this, qnbNotoficationList);
            list.setAdapter(notificationAdapter);


        }else
            layout_clear.setVisibility(View.GONE);

        layout_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppSettings.putData(NotificationActivity.this, AppSettings.NOTIFICATIONS, "");
                qnbNotoficationList.clear();
                notificationAdapter.notifyDataSetChanged();
                layout_clear.setVisibility(View.GONE);
            }
        });
    }
}
