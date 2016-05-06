package timesofmoney.qnbmerchant.customviews;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import timesofmoney.qnbmerchant.R;


/**
 * Created by kunalk on 4/5/2016.
 */
public class CustomTabLayout implements View.OnClickListener {


    View root, div1, div2;
    RelativeLayout tab1, tab2;
    //ImageView icon1, icon2;
    TextView txt1, txt2;
    private IOnTabClicked callback;
    String tabtext1,tabtext2;
    int img1_normal,img1_active,img2_normal,img2_active;

    public CustomTabLayout(View root) {
        this.root = root;
        setupTabs();
    }

    private void setupTabs() {

        div1 = root.findViewById(R.id.v1);
        div2 = root.findViewById(R.id.v2);
        tab1 = (RelativeLayout) root.findViewById(R.id.rl1);
        tab2 = (RelativeLayout) root.findViewById(R.id.rl2);

        txt1 = (TextView) root.findViewById(R.id.txt1);
        txt2 = (TextView) root.findViewById(R.id.txt2);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);

    }

    public void setTabTexts(String s1,String s2)
    {
        txt1.setText(s1);
        txt2.setText(s2);

    }

    public void setIcons(int i1_active,int i1_normal ,int i2_active,int i2_normal )
    {
        img1_normal=i1_normal;
        img1_active=i1_active;
        img2_normal=i2_normal;
        img2_active=i2_active;
    }
    public void setCurrentActiveTab(int index) {
        div1.setVisibility(View.INVISIBLE);
        div2.setVisibility(View.INVISIBLE);
        txt1.setTextColor(Color.parseColor("#cabfb7"));
        txt2.setTextColor(Color.parseColor("#cabfb7"));
      //  icon1.setImageResource(img1_normal);
        //icon2.setImageResource(img2_normal);

        switch (index) {
            case 0:

                div1.setVisibility(View.VISIBLE);
                txt1.setTextColor(Color.parseColor("#ffffff"));
            //    icon1.setImageResource(img1_active);
                break;
            case 1:
                div2.setVisibility(View.VISIBLE);
                txt2.setTextColor(Color.parseColor("#ffffff"));
           //     icon2.setImageResource(img2_active);
                break;


        }
    }

    public void setTabListener(IOnTabClicked callback)
    {
        this.callback=callback;
    }

    @Override
    public void onClick(View v) {

        if(v==tab1)
        {
            callback.onTabClicked(0);
            setCurrentActiveTab(0);
        }else
        {
            callback.onTabClicked(1);
            setCurrentActiveTab(1);

        }
    }


    public interface IOnTabClicked {
        void onTabClicked(int index);
    }
}
