package timesofmoney.qnbmerchant.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import timesofmoney.qnbmerchant.R;

/**
 * Created by kunalk on 2/3/2016.
 */
public class ProgressDialog extends Dialog {

    AnimationDrawable myAnimationDrawable;
    public ProgressDialog(Context context,String message) {
        super(context);
        //setMessage(message);
         getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.progress_loader);

        ImageView imageView = (ImageView) findViewById(R.id.progressBar);
        imageView.setBackgroundResource(R.drawable.loader);
        myAnimationDrawable = (AnimationDrawable) imageView.getBackground();

        myAnimationDrawable.start();

        setCanceledOnTouchOutside(false);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View f = ((Activity)context).getCurrentFocus();
        if (null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass()))
            imm.hideSoftInputFromWindow(f.getWindowToken(), 0);
        else
            ((Activity)context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
