package timesofmoney.qnbmerchant.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;

import timesofmoney.qnbmerchant.R;


/**
 * Created by kunalk on 10/26/2015.
 */
public class OkDialog {

    private AlertDialog.Builder builder;
    AlertDialog dialog;

    public OkDialog(Context context, String message, String title, final IOkDialogCallback callback) {

        builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(context.getResources().getString(R.string.app_name));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
                    callback.handleResponse();
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
 public interface IOkDialogCallback
 {
     public void handleResponse();
 }
}
