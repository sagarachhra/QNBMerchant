package timesofmoney.qnbmerchant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import timesofmoney.qnbmerchant.R;

public class SettingsActivity extends BaseActivity {

    TextView txtvwChangeMPIN, txtvwChangeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setToolbar(getString(R.string.settings));

        txtvwChangeMPIN = (TextView) findViewById(R.id.txtvwChangeMPIN);
        txtvwChangeLanguage = (TextView) findViewById(R.id.txtvwChangeLanguage);



        txtvwChangeMPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChangeMPINActivity.class);
                startActivity(intent);
            }
        });


        txtvwChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
