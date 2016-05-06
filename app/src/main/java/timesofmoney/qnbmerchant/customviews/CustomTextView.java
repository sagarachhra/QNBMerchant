package timesofmoney.qnbmerchant.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import timesofmoney.qnbmerchant.R;


public class CustomTextView extends TextView {

    public  Typeface typeface;
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        init(t.getInt(R.styleable.CustomTextView_fontType, 0));
    }

    public CustomTextView(Context context) {
        super(context);
        init(0);
    }

    public void init(int i) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/museo_sans_hundred.otf");
//        setTypeface(tf ,1);

        switch (i) {
            case 1:
                typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_regular.ttf");
                break;
            case 3:
                typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_italic.ttf");
                break;

            case 4:
                typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_bold.ttf");
                break;

            default:
                typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_light.ttf");
                break;
        }
        if (typeface != null)
            setTypeface(typeface, 1);

    }
}
