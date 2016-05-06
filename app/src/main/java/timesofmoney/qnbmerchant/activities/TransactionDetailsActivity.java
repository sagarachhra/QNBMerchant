package timesofmoney.qnbmerchant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import timesofmoney.qnbmerchant.R;

public class TransactionDetailsActivity extends BaseActivity {

    Button btnRefund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        btnRefund = (Button) findViewById(R.id.btnRefund);

        btnRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionDetailsActivity.this, RefundActivity.class);
                startActivity(intent);
            }
        });

    }
}
