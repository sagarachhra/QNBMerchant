package timesofmoney.qnbmerchant.activities;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import net.glxn.qrgen.android.QRCode;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.customviews.CustomTextView;
import timesofmoney.qnbmerchant.utilities.QRCodeEncode;
import timesofmoney.qnbmerchant.utilities.Utils;

public class GenerateQRCodeConfirmationActivity extends BaseActivity {

    private LinearLayout linlayMPINContainer, lilnayQRCodeContainer;
    private Button btnGenerate;
    CustomTextView location, mvisaId,transAmountText;
    String transAmount="";
    ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode_confirmation);
        Bundle b = getIntent().getExtras();
        transAmount = b.getString("amount");
        setToolbar(QNBMerchantApp.getMerchantProfile().getCompanyName());
        transAmountText = (CustomTextView) findViewById(R.id.txtvwAmount);
        transAmountText.setText("Amount: EGP "+transAmount);
        location = (CustomTextView) findViewById(R.id.txtvwLocation);
        location.setText(QNBMerchantApp.getMerchantProfile().getAddress() + "," + QNBMerchantApp.getMerchantProfile().getCity());
        mvisaId = (CustomTextView) findViewById(R.id.txtvwmVisaID);
        mvisaId.setText("mVisa Id: " + QNBMerchantApp.getMerchantProfile().getMerchantId());

        linlayMPINContainer = (LinearLayout) findViewById(R.id.linlayMPINContainer);
        lilnayQRCodeContainer = (LinearLayout) findViewById(R.id.linlayQRCodeContainer);
        qrImage= (ImageView) findViewById(R.id.imgvwQRCode);
        QRCodeEncode qrCodeEncode = new QRCodeEncode();
        String qr= qrCodeEncode.dynamicQR(transAmount);
        Log.v("QR", qr + " ");
        if(qr!=null) {
            Bitmap bitmap = QRCode.from(qr).bitmap();
            qrImage.setImageBitmap(bitmap);
            Utils.showToast(GenerateQRCodeConfirmationActivity.this, "QR Code generated successfully.");
        }

        btnGenerate = (Button) findViewById(R.id.btnGenerate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.hideKeyboard(GenerateQRCodeConfirmationActivity.this);
                //linlayMPINContainer.setVisibility(View.GONE);
                //lilnayQRCodeContainer.setVisibility(View.VISIBLE);

              /*  new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },3000);*/

            }
        });


    }
}
