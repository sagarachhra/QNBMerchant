package timesofmoney.qnbmerchant.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import timesofmoney.qnbmerchant.R;


/**
 * Created by kunalk on 4/7/2016.
 */
public class CustomEditText extends FrameLayout {

    EditText editText;
    View v1;
    CustomTextWatcher customTextWatcher;


    public CustomEditText(Context context) {
        super(context);
    }

    public  CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.customEdit);
        initView(context);
        setLayout(t.getString(0), t.getInteger(1, 10), t.getInt(R.styleable.customEdit_inputtypeEdit,0), t.getInt(3,0));
    }


    public EditText getEditText()
    {
        return editText;
    }

    public String getText()
    {
        return editText.getText().toString();
    }


    public void setCustomTextWatcher(CustomTextWatcher customTextWatcher)
    {
        this.customTextWatcher=customTextWatcher;
    }
    private void setLayout(String hint, int maxlength, int input,int gravity) {

        editText.setHint(hint);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxlength);
        editText.setFilters(FilterArray);

        //int grv = Integer.parseInt(gravity);

        switch (gravity) {
            case 1:

                editText.setGravity(Gravity.CENTER);
                break;
            case 3:
                editText.setGravity(Gravity.RIGHT);
                break;

            default:
                 editText.setGravity(Gravity.CENTER);
                break;
        }


        int inputMethod = input;

        Log.v("TAG"," Input "+inputMethod);
        switch (inputMethod) {
            case 1:

                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                break;
            case 3:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
        }

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(s) && !editText.hasFocus())
                    v1.setBackgroundColor(Color.parseColor("#ffffff"));

            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(customTextWatcher!=null)
                    customTextWatcher.onTextChanged(s,start,before,count);

            }

        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean gotFocus) {
                if(v1!=null)
                {
                    if(gotFocus)
                        v1.setBackgroundColor(Color.parseColor("#394040"));
                    else if(TextUtils.isEmpty(editText.getText().toString().trim()))
                        v1.setBackgroundColor(Color.parseColor("#ffffff"));
                    else
                        v1.setBackgroundColor(Color.parseColor("#701246"));

                }

            }
        });
    }


    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_edittext, null);
        editText = (EditText) view.findViewById(R.id.edt);
        v1=view.findViewById(R.id.v1);

        addView(view);
    }

    public interface CustomTextWatcher
    {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
}
