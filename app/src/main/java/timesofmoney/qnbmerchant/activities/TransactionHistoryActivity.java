package timesofmoney.qnbmerchant.activities;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.adapters.TransactionHistoryPagerAdapter;
import timesofmoney.qnbmerchant.apicalls.RetrofitTask;
import timesofmoney.qnbmerchant.customviews.CustomTabLayout;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryRecentFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryStatementFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryStatementOneFragment;
import timesofmoney.qnbmerchant.fragments.TransactionHistoryStatementTwoFragment;
import timesofmoney.qnbmerchant.models.StatementResponse;
import timesofmoney.qnbmerchant.models.StatementResponseDetails;
import timesofmoney.qnbmerchant.utilities.AESecurity;
import timesofmoney.qnbmerchant.utilities.AppSettings;
import timesofmoney.qnbmerchant.utilities.Constants;
import timesofmoney.qnbmerchant.utilities.LogUtils;

public class TransactionHistoryActivity extends BaseActivity implements  TransactionHistoryRecentFragment.OnFragmentInteractionListener, TransactionHistoryStatementFragment.OnFragmentInteractionListener, TransactionHistoryStatementOneFragment.OnFragmentInteractionListener, TransactionHistoryStatementTwoFragment.OnFragmentInteractionListener {

    private TransactionHistoryPagerAdapter mAdapter;

    private ViewPager mViewPager;

    private TabHost mTabHost;

    private CustomTabLayout customTabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        customTabLayout = new CustomTabLayout(findViewById(R.id.tabLayout));

        mAdapter = new TransactionHistoryPagerAdapter(getSupportFragmentManager());
        customTabLayout.setCurrentActiveTab(0);

        setToolbar(getResources().getString(R.string.menu_transaction_history));
        mViewPager.setAdapter(mAdapter);


        customTabLayout.setTabListener(new CustomTabLayout.IOnTabClicked() {
            @Override
            public void onTabClicked(int index) {

                mViewPager.setCurrentItem(index);
            }

        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //mTabHost.setCurrentTab(position);
                customTabLayout.setCurrentActiveTab(position);


                if(position==0)
                {
                    setToolbar(getResources().getString(R.string.menu_transaction_history));

                }

                else if (position==1)
                {
                    setToolbar(getString(R.string.transaction_statement));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }





// Method to add a TabHost

    private void AddTab(TabHost tabHost, TabHost.TabSpec tabSpec) {

        tabSpec.setContent(new TabFactory(TransactionHistoryActivity.this));

        tabHost.addTab(tabSpec);

    }



    /**
     * A simple factory that returns dummy views to the Tabhost
     * @author mwho
     */
    class TabFactory implements TabHost.TabContentFactory {

        private final Context mContext;

        /**
         * @param context
         */
        public TabFactory(Context context) {
            mContext = context;
        }

        /** (non-Javadoc)
         * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
         */
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }

    }


    // Tabs Creation

    private void initialiseTabHost() {

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup();


        // TODO Put here your Tabs

        AddTab( this.mTabHost, this.mTabHost.newTabSpec("ButtonTab").setIndicator("Recent"));

        AddTab(this.mTabHost, this.mTabHost.newTabSpec("ImageTab").setIndicator("Statement"));





        //mTabHost.setOnTabChangedListener(this);

    }

   /* @Override
    public void onTabChanged(String tabId) {
        //TabInfo newTab = this.mapTabInfo.get(tag);
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void callAPI(String walletID,String fromDate,String toDate,String pageNo,String pageSize,final ITransactionHistory callback) {

        String xmlParam = "\t\t<MobileNumber>"+ AppSettings.getData(TransactionHistoryActivity.this,AppSettings.MOBILE_NUMBER)+"</MobileNumber>\n" +
                "\t\t<Tpin></Tpin>\n" +
                "\t\t<WalletId>"+walletID+"</WalletId>\n" +
                "\t\t<FromDate>"+fromDate+"</FromDate>\n" +
                "\t\t<ToDate>"+toDate+"</ToDate>\n" +
                "\t\t<PageNo>"+pageNo+"</PageNo>\n" +
                "\t\t<PageSize>"+pageSize+"</PageSize>";

        xmlParam = Constants.getReqXML("TxnHistPagination", xmlParam);
        final String plainXML = xmlParam;
        try {
            xmlParam = AESecurity.getInstance().encryptString(xmlParam);
        } catch (Exception e) {
            LogUtils.Exception(e);
            callback.handleResponse(false,e.getMessage(),null);
            return;
        }


        RetrofitTask retrofitTask = RetrofitTask.getInstance();

        retrofitTask.executeTask(xmlParam, new RetrofitTask.IRetrofitTask() {
            @Override
            public void handleResponse(boolean isSuccess, String response) {

                if (!isSuccess) {

                    callback.handleResponse(false,response,null);
                    return;
                }

                try {
                    String decrypted = AESecurity.getInstance().decryptString(response);
                    LogUtils.Verbose("TAG", " Decrypted " + decrypted);
                    Serializer serializer = new Persister();
                    StatementResponse statementResponse=serializer.read(StatementResponse.class,decrypted);

                    if(statementResponse.getResponse().getResponseType().equals("Success"))
                    {
                        callback.handleResponse(true,null,statementResponse.getStatementResponseDetails());
                    }else
                        callback.handleResponse(false,statementResponse.getStatementResponseDetails().getReason(),null);

                } catch (Exception e) {
                    LogUtils.Exception(e,plainXML);
                    callback.handleResponse(false,e.getMessage(),null);
                }


            }
        });
    }


    public interface ITransactionHistory
    {
        public void handleResponse(boolean isSuccess,String message, StatementResponseDetails statementResponseDetails);
    }

}
