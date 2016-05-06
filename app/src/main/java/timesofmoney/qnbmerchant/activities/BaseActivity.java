package timesofmoney.qnbmerchant.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.dialogs.ProgressDialog;


/**
 * Created by kunalk on 3/22/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    public void setToolbar(String titleText)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(null);
        TextView title = (TextView) toolbar.findViewById(R.id.title);
        title.setText(titleText);
        setSupportActionBar(toolbar);

        ImageView backArrow = (ImageView) findViewById(R.id.imgvwBackArrow);

      if(backArrow!=null) {
          backArrow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  onBackPressed();
              }
          });
      }
    }


    public void showProgress()
    {
        progressDialog=new ProgressDialog(this,"Please Wait");
        progressDialog.show();
    }

    public void dismissProgress()
    {
        if(progressDialog!=null)
            progressDialog.dismiss();
    }
}
