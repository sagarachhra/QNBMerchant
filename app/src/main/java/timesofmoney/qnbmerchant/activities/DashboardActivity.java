package timesofmoney.qnbmerchant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.simonvt.menudrawer.MenuDrawer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.TodaysTransactionsAdapter;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.customviews.MenuDrawerView;
import timesofmoney.qnbmerchant.gcm.RegistrationIntentService;
import timesofmoney.qnbmerchant.models.QNBNotofication;
import timesofmoney.qnbmerchant.models.TodaysTransaction;
import timesofmoney.qnbmerchant.utilities.AppSettings;

public class DashboardActivity extends BaseActivity {

    MenuDrawer menuDrawer;

    //ImageView imgvwHamIcon;

    RecyclerView recyclerView;
    LinearLayout img_menu;
    CustomTextView txtvwComapnyName,txtvwmVisaID;
    RelativeLayout layout_notification;
    CustomTextView txtNotificationCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_dashboard);


        menuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
        menuDrawer.setContentView(R.layout.activity_dashboard);
        menuDrawer.setMenuView(R.layout.menu_list);

        //imgvwHamIcon = (ImageView) findViewById(R.id.imgvwHamIcon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        img_menu = (LinearLayout) toolbar.findViewById(R.id.action_bar_imageview_left_menu);
        img_menu.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTodaysTransactions);

        txtvwComapnyName = (CustomTextView) findViewById(R.id.txtvwCompanyName);
        txtvwmVisaID = (CustomTextView) findViewById(R.id.txtvwmVisaID);

        layout_notification = (RelativeLayout) findViewById(R.id.layout_notofication);
        layout_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        txtNotificationCount = (CustomTextView) findViewById(R.id.txtCount);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        TodaysTransactionsAdapter todaysTransactionsAdapter = new TodaysTransactionsAdapter(createList(5));
        recyclerView.setAdapter(todaysTransactionsAdapter);

        /*imgvwHamIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuDrawer.isMenuVisible()) {
                    menuDrawer.closeMenu();
                } else {
                    menuDrawer.openMenu();
                }
            }
        });*/

        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuDrawer.toggleMenu();
            }
        });


        new MenuDrawerView(this, findViewById(R.id.menu_root));


        txtvwComapnyName.setText(QNBMerchantApp.getMerchantProfile().getCompanyName() + " " + QNBMerchantApp.getMerchantProfile().getBusinessDescription());
        txtvwmVisaID.setText("mVisa " + QNBMerchantApp.getMerchantProfile().getMerchantId());

        if (!AppSettings.getData(getApplicationContext(), AppSettings.isTOKENSENDTOSERVER).equals("true")) {
            Intent intent = new Intent(getApplicationContext(), RegistrationIntentService.class);
            startService(intent);
        }
    }

    private List<TodaysTransaction> createList(int size) {

        List<TodaysTransaction> result = new ArrayList<TodaysTransaction>();
        for (int i = 1; i <= size; i++) {
            TodaysTransaction objTodaysTransaction = new TodaysTransaction();
            objTodaysTransaction.setName("Name " + i);
            objTodaysTransaction.setCardNo("xxxx xxxx xxxx " + "1234");
            objTodaysTransaction.setTranactionAmount(String.valueOf(500 * i));

            result.add(objTodaysTransaction);

        }

        return result;
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            Gson gson = new Gson();
            String json = AppSettings.getData(this, AppSettings.NOTIFICATIONS);
            Type type = new TypeToken<ArrayList<QNBNotofication>>() {
            }.getType();
            final List<QNBNotofication> qnbNotoficationList = gson.fromJson(json, type);
            txtNotificationCount.setText(qnbNotoficationList.size() + "");
            txtNotificationCount.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            txtNotificationCount.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (menuDrawer.isMenuVisible()) {
            menuDrawer.closeMenu();

        }
    }
}
