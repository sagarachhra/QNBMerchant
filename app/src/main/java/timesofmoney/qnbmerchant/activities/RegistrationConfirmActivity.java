package timesofmoney.qnbmerchant.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import timesofmoney.qnbmerchant.R;

public class RegistrationConfirmActivity extends AppCompatActivity {


    private LinearLayout linlayWelcomeTomVisaContainer, linalyRegistrationConfirmationContainer;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_confirm);

        linlayWelcomeTomVisaContainer = (LinearLayout) findViewById(R.id.linlayWelcomeTomVisaContainer);
        linalyRegistrationConfirmationContainer = (LinearLayout) findViewById(R.id.linlayRegistrationConfirmationContainer);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linalyRegistrationConfirmationContainer.setVisibility(View.GONE);
                linlayWelcomeTomVisaContainer.setVisibility(View.VISIBLE);
            }
        });


    }
}
