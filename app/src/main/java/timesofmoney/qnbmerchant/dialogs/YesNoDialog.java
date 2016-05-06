package timesofmoney.qnbmerchant.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;

import timesofmoney.qnbmerchant.R;

/**
 * Created by kunalk on 3/11/2016.
 */
public class YesNoDialog {

    private AlertDialog.Builder builder;
    AlertDialog dialog;
    int responsecode=0;


    public YesNoDialog(Context context, String message, String title, final IYesNoDialogCallback callback) {

        builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(context.getResources().getString(R.string.app_name));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                responsecode = 1;
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                if (callback != null)
                    callback.handleResponse(responsecode);
            }
        });

        if(!((Activity)context).isFinishing())
            dialog.show();

    }

    public boolean isShowing()
    {
        return dialog.isShowing();
    }

    public void dismissDialog()
    {
        if(dialog!=null &&dialog.isShowing())
            dialog.dismiss();
    }
    public interface IYesNoDialogCallback
    {
        public void handleResponse(int responsecode);
    }
}
