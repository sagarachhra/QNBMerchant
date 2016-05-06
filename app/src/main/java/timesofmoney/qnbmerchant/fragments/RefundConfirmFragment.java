package timesofmoney.qnbmerchant.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.activities.ReversalSuccessActivity;
import timesofmoney.qnbmerchant.customviews.CustomEditText;

/**
 * Created by pankajp on 15-04-2016.
 */
public class RefundConfirmFragment extends Fragment {

    Button confirmRefund;
    CustomEditText edtPIN1,edtPIN2,edtPIN3,edtPIN4;
    List<String> pinlist;
    private String PIN;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_confirm_refund, container, false);
        confirmRefund = (Button)rootView.findViewById(R.id.btnConfirmRefund);
        pinlist=new ArrayList<>();
        pinlist.add("0");
        pinlist.add("0");
        pinlist.add("0");
        pinlist.add("0");

        edtPIN1 = (CustomEditText) rootView.findViewById(R.id.edtPin1);
        edtPIN2 = (CustomEditText) rootView.findViewById(R.id.edtPin2);
        edtPIN3 = (CustomEditText) rootView.findViewById(R.id.edtPin3);
        edtPIN4 = (CustomEditText) rootView.findViewById(R.id.edtPin4);

        edtPIN1.setCustomTextWatcher(new CustomEditWatcher(edtPIN1,edtPIN2.getEditText(), null,1,0));
        edtPIN2.setCustomTextWatcher(new CustomEditWatcher(edtPIN2,edtPIN3.getEditText(), edtPIN1.getEditText(),1,1));
        edtPIN3.setCustomTextWatcher(new CustomEditWatcher(edtPIN3,edtPIN4.getEditText(), edtPIN2.getEditText(),1,2));
        edtPIN4.setCustomTextWatcher(new CustomEditWatcher(edtPIN4,null, edtPIN3.getEditText(),1,3));

        confirmRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PIN =pinlist.get(0)+""+pinlist.get(1)+""+pinlist.get(2)+""+pinlist.get(3);
                Log.v("PIN",PIN);
                Intent intent = new Intent(getActivity(), ReversalSuccessActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }

    class CustomEditWatcher implements CustomEditText.CustomTextWatcher {
        EditText edtNext, edtPrevious;
        CustomEditText edtCurr;
        int MAXLENGTH=0,index;


        public CustomEditWatcher(CustomEditText edtCurr,EditText edtNext, EditText edtPrevious,int MAXLENGTH,int index) {
            this.edtNext = edtNext;
            this.edtPrevious = edtPrevious;
            this.MAXLENGTH=MAXLENGTH;
            this.edtCurr=edtCurr;
            this.index=index;
        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().length() == MAXLENGTH && edtNext != null) {
                edtNext.requestFocus();
            }
            if (s.toString().length() <= 0 && edtPrevious != null) {
                edtPrevious.requestFocus();
                edtPrevious.setSelection(edtPrevious.getText().toString().length());
            }


            if(MAXLENGTH==1 && edtCurr!=null && !TextUtils.isEmpty(edtCurr.getEditText().getText().toString()))
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        edtCurr.setCustomTextWatcher(null);
                        pinlist.set(index,edtCurr.getText().toString());
                        edtCurr.getEditText().setText("*");
                        edtCurr.setCustomTextWatcher(CustomEditWatcher.this);
                    }
                },400);
            }
        }


    }
}
