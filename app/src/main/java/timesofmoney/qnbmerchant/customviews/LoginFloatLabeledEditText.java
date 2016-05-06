package timesofmoney.qnbmerchant.customviews;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;

import timesofmoney.qnbmerchant.R;

/**
 * Created by pankajp on 5/4/2016.
 */




public class LoginFloatLabeledEditText extends RelativeLayout {

    private static final int DEFAULT_PADDING_LEFT = 2;

    private TextView mHintTextView;
    private EditText mEditText, edtCC;
    View line_view;

    private Context mContext;

    public LoginFloatLabeledEditText(Context context) {
        super(context);
        mContext = context;
    }

    public LoginFloatLabeledEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        setAttributes(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LoginFloatLabeledEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        setAttributes(attrs);
    }

    private void setAttributes(AttributeSet attrs) {
        mHintTextView = new TextView(mContext);
        mHintTextView.setId(R.id.Curr);
        mHintTextView.setTextColor(Color.parseColor("#ffffff"));


        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_italic.ttf");
        mHintTextView.setTypeface(typeface);


        final TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.FloatLabeledEditText);

        final int padding = a.getDimensionPixelSize(R.styleable.FloatLabeledEditText_fletPadding, 0);
        final int defaultPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PADDING_LEFT, getResources().getDisplayMetrics());
        final int paddingLeft = a.getDimensionPixelSize(R.styleable.FloatLabeledEditText_fletPaddingLeft, defaultPadding);
        final int paddingTop = a.getDimensionPixelSize(R.styleable.FloatLabeledEditText_fletPaddingTop, 0);
        final int paddingRight = a.getDimensionPixelSize(R.styleable.FloatLabeledEditText_fletPaddingRight, 0);
        final int paddingBottom = a.getDimensionPixelSize(R.styleable.FloatLabeledEditText_fletPaddingBottom, 0);
        Drawable background = a.getDrawable(R.styleable.FloatLabeledEditText_fletBackground);

        if (padding != 0) {
            mHintTextView.setPadding(padding, padding, padding, padding);
        } else {
            mHintTextView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }

        if (background != null) {
            setHintBackground(background);
        }

        mHintTextView.setTextAppearance(mContext, a.getResourceId(R.styleable.FloatLabeledEditText_fletTextAppearance, android.R.style.TextAppearance_Small));

        //Start hidden
        mHintTextView.setVisibility(INVISIBLE);
        AnimatorProxy.wrap(mHintTextView).setAlpha(0);

        //  setOrientation(VERTICAL);
        mHintTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(mHintTextView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        a.recycle();
    }

    @SuppressLint("NewApi")
    private void setHintBackground(Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mHintTextView.setBackground(background);
        } else {
            mHintTextView.setBackgroundDrawable(background);
        }
    }

    @Override
    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {


            RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) params;
            layoutParams.addRule(RelativeLayout.BELOW, mHintTextView.getId());
            if(child.getTag()!=null && child.getTag().equals("CURR"))
            {
                edtCC= (EditText) child;
                edtCC.setTextColor(Color.parseColor("#ffffff"));
            }else
                setEditText((EditText) child);
        }

        else if (!(child instanceof TextView))
        {
            line_view = child;
            RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) params;
            layoutParams.addRule(RelativeLayout.BELOW,mEditText.getId());
        }

        super.addView(child, index, params);
    }

    private void setEditText(EditText editText) {
        mEditText = editText;
        mEditText.setTextColor(Color.parseColor("#ffffff"));
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (line_view != null) {
                    if (TextUtils.isEmpty(s) && !mEditText.hasFocus())
                        line_view.setBackgroundColor(Color.parseColor("#cabfb7"));


                }


                setShowHint(!TextUtils.isEmpty(s));
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!TextUtils.isEmpty(s.toString())) {
                    if (edtCC != null)
                        edtCC.setVisibility(View.VISIBLE);
                } else if (edtCC != null)
                    edtCC.setVisibility(GONE);

            }

        });

        mEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });

        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean gotFocus) {
                if (line_view != null) {
                    if (gotFocus)
                        line_view.setBackgroundColor(Color.parseColor("#ffffff"));
                    else if (TextUtils.isEmpty(mEditText.getText().toString().trim()))
                        line_view.setBackgroundColor(Color.parseColor("#cabfb7"));
                    else
                        line_view.setBackgroundColor(Color.parseColor("#cabfb7"));

                }
                //  onFocusChanged(gotFocus);
            }
        });

        mHintTextView.setText(mEditText.getHint());

        if (!TextUtils.isEmpty(mEditText.getText())) {
            mHintTextView.setVisibility(VISIBLE);
        }
    }

    private void onFocusChanged(boolean gotFocus) {
        if (gotFocus && mHintTextView.getVisibility() == VISIBLE) {
            ObjectAnimator.ofFloat(mHintTextView, "alpha", 0.33f, 1f).start();
        } else if (mHintTextView.getVisibility() == VISIBLE) {
            AnimatorProxy.wrap(mHintTextView).setAlpha(1f);  //Need this for compat reasons
            ObjectAnimator.ofFloat(mHintTextView, "alpha", 1f, 0.33f).start();
        }
    }

    private void setShowHint(final boolean show) {
        AnimatorSet animation = null;
        if ((mHintTextView.getVisibility() == VISIBLE) && !show) {
            animation = new AnimatorSet();
            ObjectAnimator move = ObjectAnimator.ofFloat(mHintTextView, "translationY", 0, mHintTextView.getHeight() / 8);
            ObjectAnimator fade = ObjectAnimator.ofFloat(mHintTextView, "alpha", 1, 0);
            animation.playTogether(move, fade);
        } else if ((mHintTextView.getVisibility() != VISIBLE) && show) {
            animation = new AnimatorSet();
            ObjectAnimator move = ObjectAnimator.ofFloat(mHintTextView, "translationY", mHintTextView.getHeight() / 8, 0);
            ObjectAnimator fade;
            if (mEditText.isFocused()) {
                fade = ObjectAnimator.ofFloat(mHintTextView, "alpha", 0, 1);
            } else {
                fade = ObjectAnimator.ofFloat(mHintTextView, "alpha", 0, 1);
            }
            animation.playTogether(move, fade);
        }

        if (animation != null) {
            animation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mHintTextView.setVisibility(VISIBLE);
                    mHintTextView.setTextColor(Color.parseColor("#ffffff"));
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mHintTextView.setVisibility(show ? VISIBLE : INVISIBLE);
                    AnimatorProxy.wrap(mHintTextView).setAlpha(show ? 1 : 0);
                }
            });
            animation.start();
        }
    }

    public EditText getEditText() {
        return mEditText;
    }

    public void setHint(String hint) {
        mEditText.setHint(hint);
        mHintTextView.setText(hint);
    }

    public CharSequence getHint() {
        return mHintTextView.getHint();
    }

}