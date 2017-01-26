package com.muddzdev.styleabletoastlibrary;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.util.Log;
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

    private float strokeWidth;
    private int duration, style, alpha, drawable;
    private int backgroundColor, textColor, strokeColor;
    private int cornerRadius = -1;
    private boolean isBold;
    private boolean animIconRotation;
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


    /**
     * if a style is passed, we load the style attributes
     */
    private void getLayoutStyleAttr() {
        if (style > 0) {

            int[] colorAttrs = {android.R.attr.colorBackground, android.R.attr.strokeColor};
            int[] floatAttrs = {android.R.attr.strokeWidth};
            int[] dimenAttrs = {android.R.attr.radius};


            TypedArray colors = context.obtainStyledAttributes(style, colorAttrs);
            TypedArray dimens = context.obtainStyledAttributes(style, dimenAttrs);
            TypedArray floats = context.obtainStyledAttributes(style, floatAttrs);

            backgroundColor = colors.getColor(0, DEFAULT_BACKGROUND);
            strokeColor = colors.getColor(1, Color.TRANSPARENT);
            strokeWidth = floats.getFloat(0, 0);
            cornerRadius = (int) dimens.getDimension(0, DEFAULT_CORNER_RADIUS);

            colors.recycle();
            dimens.recycle();
            floats.recycle();
        }

    }

    private View getToastLayout() {

        getLayoutStyleAttr();
        getImageViewStyleAttr();

        int horizontalPadding = (int) getTypedValueInDP(context, DEFAULT_HORIZONTAL_PADDING);
        int verticalPadding = (int) getTypedValueInDP(context, DEFAULT_VERTICAL_PADDING);


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


    private void getShapeStylesAttr() {
        if (style > 0) {

            int[] floatAttrs = {android.R.attr.alpha};
            TypedArray floats = context.obtainStyledAttributes(style, floatAttrs);
            alpha = (int) floats.getFloat(0, DEFAULT_ALPHA);
            floats.recycle();
        }
    }

    private GradientDrawable getToastShape() {
        getShapeStylesAttr();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getShapeCornerRadius());
        gradientDrawable.setStroke((int) getStrokeWidth(), getStrokeColor());
        gradientDrawable.setColor(getBackgroundColor());
        gradientDrawable.setAlpha(getShapeAlpha());
        return gradientDrawable;
    }


    private void getTextStylesAttr() {
        if (style > 0) {

            int[] colorAttrs = {android.R.attr.textColor};
            int[] stringAttrs = {android.R.attr.fontFamily};
            int[] intsAttrs = {android.R.attr.textStyle};

            TypedArray colors = context.obtainStyledAttributes(style, colorAttrs);
            TypedArray strings = context.obtainStyledAttributes(style, stringAttrs);
            TypedArray ints = context.obtainStyledAttributes(style, intsAttrs);

            textColor = colors.getColor(0, DEFAULT_TEXT_COLOR);
            font = Typeface.createFromAsset(context.getAssets(), strings.getString(0));

            if (ints.getInt(0, 0) == 1) {
                isBold = true;
            } else {
                isBold = false;
            }

            colors.recycle();
            strings.recycle();
            ints.recycle();
        }

    }

    private TextView getTextView() {
        getTextStylesAttr();

        textView = new TextView(context);
        textView.setText(toastMsg);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE);
        textView.setTextColor(getTextColor());
        textView.setTypeface(getTypeface());
        textView.setMaxLines(2);

        if (drawable > 0) {

            //previous: 18x2  + 8
            int leftPadding = (int) getTypedValueInDP(context, 18 * 2 + 5);
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


    private void getImageViewStyleAttr() {
        if (style > 0) {
            int[] drawableAttrSet = {android.R.attr.icon};
            TypedArray drawableId = context.obtainStyledAttributes(style, drawableAttrSet);
            drawable = drawableId.getResourceId(0, 0);
            drawableId.recycle();
        }
    }


    private ImageView getIcon() {

        if (drawable > 0) {

            //previous 18:
            int marginLeft = (int) getTypedValueInDP(context, 15);
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

            //Push the icon 15dp from the left edge of the shape
            layoutParams.setMargins(marginLeft, 0, 0, 0);

            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            imageView.setLayoutParams(layoutParams);
            return imageView;
        }
        return null;
    }

    private float getStrokeWidth() {
        return getTypedValueInDP(context, strokeWidth);
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
        if (isBold && font == null) {
            return Typeface.DEFAULT_BOLD;
        } else if (isBold && font != null) {
            return Typeface.create(font, Typeface.BOLD);
        } else if (font != null) {
            return Typeface.create(font, Typeface.NORMAL);
        } else {
            return Typeface.create(DEFAULT_CONDENSED_FONT, Typeface.NORMAL);
        }
    }

    @ColorInt
    private int getTextColor() {
        if (textColor == 0 && style <= 0) {
            return DEFAULT_TEXT_COLOR;
        } else {
            return textColor;
        }
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
