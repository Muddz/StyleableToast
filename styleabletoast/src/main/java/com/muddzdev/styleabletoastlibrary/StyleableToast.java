package com.muddzdev.styleabletoastlibrary;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.muddzdev.styleabletoastlibrary.Utils.getTypedValueInDP;

//        Copyright 2017 Muddii Walid (Muddz)
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.


/**
 * StyleableToast is a very easy and quick way to style your toast and gives them an unique style and feeling compared
 * to the default boring grey ones. StyleableToast have 10 styling options.
 * <p>If a particular style option is not set, the option will fall back to the standard Android Toast style</p>
 */

public class StyleableToast implements OnToastFinished {

    private static final String TAG = "StyleableToast";
    private static final String DEFAULT_CONDENSED_FONT = "sans-serif-condensed";
    private static final int DEFAULT_BACKGROUND = Color.parseColor("#555555");
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_SIZE = 16;
    private static final int DEFAULT_CORNER_RADIUS = 25;
    private static final int DEFAULT_HORIZONTAL_PADDING = 25;
    private static final int DEFAULT_VERTICAL_PADDING = 11;
    private static final int DEFAULT_ALPHA = 230;
    public static int MAX_VISIBILTY = 255;

    private final Context context;
    private TextView textView;
    private Typeface font;

    private int strokeWidth, duration, style, alpha, drawable;
    private int backgroundColor, textColor, strokeColor;
    private int cornerRadius = -1;
    private boolean isBold, animIconRotation;
    private String toastMsg;


    public StyleableToast(Context context) {
        this.context = context.getApplicationContext();
    }


    public StyleableToast(Context context, String toastMsg, int duration) {
        this.context = context.getApplicationContext();
        this.toastMsg = toastMsg;
        this.duration = duration;


    }

    public StyleableToast(Context context, String toastMsg, int duration, @StyleRes int styleId) {
        this.context = context.getApplicationContext();
        this.toastMsg = toastMsg;
        this.duration = duration;
        this.style = styleId;
    }

    /**
     * @param style Style your toast via styles.xml and pass the style id R.style.xxx
     */
    public void setStyle(@StyleRes int style) {
        this.style = style;
    }

    public void setToastMsg(String toastMsg) {
        this.toastMsg = toastMsg;
    }

    public void setBoldText() {
        isBold = true;
    }

    /**
     * @param typeface Set a different font than the standard <i>sans-serif-condensed</i>
     */
    public void setTextFont(Typeface typeface) {
        this.font = typeface;
    }

    /**
     * @param textColor if not set the default color white will be used.
     */
    public void setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
    }

    public void setTextStyle(boolean isBold, Typeface font) {
        this.textColor = textColor;
        this.font = font;
    }

    public void setTextStyle(@ColorInt int textColor, Typeface font) {
        this.textColor = textColor;
        this.font = font;
    }

    public void setTextStyle(@ColorInt int textColor, boolean isBold) {
        this.textColor = textColor;
        this.isBold = isBold;
    }

    public void setTextStyle(@ColorInt int textColor, boolean isBold, Typeface font) {
        this.textColor = textColor;
        this.isBold = isBold;
        this.font = font;
    }

    /**
     * Enables spinning animation of the passed icon by its around its own center.
     */
    public void spinIconAnimation() {
        this.animIconRotation = true;
    }


    /**
     * @param backgroundColor if not set the default color grey will be used.
     */
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public void setToastStroke(int strokeWidth, @ColorInt int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    /**
     * @param cornerRadius Sets the corner radius of the toast shape. Pass 0 for a flat rectangle shape
     */
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    /**
     * @param alpha Set the alpha/Transparency of the Toast background between 0-255.
     *              255 is full opque and 0 is full transparency.
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * @param drawable Sets a icon on the left side of the toast text
     */
    public void setIcon(@DrawableRes int drawable) {
        this.drawable = drawable;
    }

    private Toast toaster() {

        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(getToastLayout());
        return toast;
    }

    private void initStyle() {

    }


    private View getToastLayout() {

        int horizontalPadding = (int) getTypedValueInDP(context, DEFAULT_HORIZONTAL_PADDING);
        int verticalPadding = (int) getTypedValueInDP(context, DEFAULT_VERTICAL_PADDING);

        int horizontalPadding2 = (int) getTypedValueInDP(context, 20);
        int verticalPadding2 = (int) getTypedValueInDP(context, 8);

        RelativeLayout toastLayout = new RelativeLayout(context);
        toastLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        toastLayout.setBackground(getToastShape());
        toastLayout.addView(getTextView());

        if (drawable > 0) {
            toastLayout.addView(getIcon());
            toastLayout.setPadding(0, verticalPadding, 0, verticalPadding);
        }

        return toastLayout;
    }

    private GradientDrawable getToastShape() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getShapeCornerRadius());
        gradientDrawable.setStroke(getStrokeWidth(), getStrokeColor());
        gradientDrawable.setColor(getBackgroundColor());
        gradientDrawable.setAlpha(getShapeAlpha());
        return gradientDrawable;
    }

    private TextView getTextView() {

        textView = new TextView(context);
        textView.setText(toastMsg);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE);
        textView.setTextColor(getTextColor());
        textView.setTypeface(getTypeface());
        textView.setMaxLines(2);

        if (drawable > 0) {

            int leftPadding = (int) getTypedValueInDP(context, 18 * 2 + 8);
            int rightPadding = (int) getTypedValueInDP(context, 22);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            textView.setLayoutParams(layoutParams);

            //Make space between icon and textview / textview and right edge of the toast
            textView.setPadding(leftPadding, 0, rightPadding, 0);
        }

        return textView;
    }

    private Animation getAnimation() {
        if (animIconRotation) {
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(1000);
            return anim;
        }

        return null;
    }


    private ImageView getIcon() {

        if (drawable > 0) {

            int marginLeft = (int) getTypedValueInDP(context, 18);
            int maxHeightVal = (int) getTypedValueInDP(context, 20);
            int maxWidthVal = (int) getTypedValueInDP(context, 20);

            ImageView imageView = new ImageView(context);
            imageView.setAnimation(getAnimation());
            imageView.setMaxWidth(marginLeft + maxWidthVal);
            imageView.setMaxHeight(maxHeightVal);
            imageView.setAdjustViewBounds(true);
            imageView.setImageDrawable(context.getResources().getDrawable(drawable));

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            //Push the icon 18dp from the left edge of the shape
            layoutParams.setMargins(marginLeft, 0, 0, 0);

            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            imageView.setLayoutParams(layoutParams);
            return imageView;
        }
        return null;
    }

    private int getStrokeWidth() {
        return strokeWidth;
    }

    private int getStrokeColor() {
        return strokeColor;
    }

    private float getShapeCornerRadius() {
        if (cornerRadius >= 0) {
            return getTypedValueInDP(context, cornerRadius);
        } else {
            return getTypedValueInDP(context, DEFAULT_CORNER_RADIUS);
        }
    }

    private int getBackgroundColor() {
        if (backgroundColor == 0) {
            return DEFAULT_BACKGROUND;
        } else {
            return backgroundColor;
        }
    }


    private int getShapeAlpha() {
        if (alpha == 0) {
            return DEFAULT_ALPHA;
        } else {
            return alpha;
        }
    }


    private Typeface getTypeface() {
        Typeface result;

        if (isBold) {
            result = Typeface.DEFAULT_BOLD;
        } else if (isBold && font != null) {
            result = Typeface.create(font, Typeface.BOLD);
        } else if (font != null) {
            result = Typeface.create(font, Typeface.NORMAL);
        } else {
            result = Typeface.create(DEFAULT_CONDENSED_FONT, Typeface.NORMAL);
        }

        return result;
    }

    @ColorInt
    private int getTextColor() {
        int result;
        if (textColor == 0) {
            result = DEFAULT_TEXT_COLOR;
        } else {
            result = textColor;
        }
        return result;
    }


    public void show() {
        toaster().show();
        if (animIconRotation) {
            ToastDurationWatcher durationWatcher = new ToastDurationWatcher(toaster().getDuration(), this);
        }
    }

    /**
     * A callback that automatically cancels and resets animations when the toast is finished showing on screen.
     * Users should not call this method.
     */
    @Override
    public void onCancelAnimation() {
        getAnimation().cancel();
        getAnimation().reset();
    }


    public static StyleableToast makeText(Context context, CharSequence text, int duration, int style) {

        StyleableToast styleableToast = new StyleableToast(context, text.toString(), duration);

        return styleableToast;
    }

}
