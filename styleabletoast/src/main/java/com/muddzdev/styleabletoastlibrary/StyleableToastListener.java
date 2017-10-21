package com.muddzdev.styleabletoastlibrary;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.text.BidiFormatter;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
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
 * StyleableToastListener is a very easy and quick way to style your styleableToast and gives them an unique style and feeling compared
 * to the default boring grey ones. StyleableToastListener have 10 styling options.
 * <p>If a particular style option is not set, the option will fall back to the standard Android Toast style</p>
 */

public class StyleableToastListener implements OnToastFinishedListener {
    private static final String TAG = StyleableToastListener.class.getSimpleName();
    private static final String DEFAULT_CONDENSED_FONT = "sans-serif-condensed";
    private static final int DEFAULT_BACKGROUND = Color.parseColor("#555555");
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_SIZE = 16;
    private static final int DEFAULT_CORNER_RADIUS = 24;
    private static final int DEFAULT_HORIZONTAL_PADDING = 25;
    private static final float DEFAULT_VERTICAL_PADDING = 11.3f;
    private static final int DEFAULT_ALPHA = 230;
    private int cornerRadius = DEFAULT_CORNER_RADIUS;
    private int backgroundColor = DEFAULT_BACKGROUND;
    private int alpha = DEFAULT_ALPHA;
    private int textColor = DEFAULT_TEXT_COLOR;
    private final Context context;
    private TextView textView;
    private Typeface typeface;
    private Toast styleableToast;
    private int style, icon, strokeColor, duration;
    private boolean textBold, hasAnimation;
    private float strokeWidth;
    private String text;
    private ToastDurationTracker toastDurationTracker;


    public static StyleableToastListener makeText(Context context, String text, int duration, int style) {
        return new StyleableToastListener(context, text, duration, style);
    }

    private StyleableToastListener(@NonNull Context context, String text, int duration, @StyleRes int style) {
        this.context = context;
        this.text = text;
        this.duration = duration;
        this.style = style;
    }

    private StyleableToastListener(StyleableToastListener.Builder builder) {
        this.context = builder.context.getApplicationContext();
        this.text = builder.text;
        this.textColor = builder.textColor;
        this.textBold = builder.textBold;
        this.duration = builder.duration;
        this.backgroundColor = builder.backgroundColor;
        this.strokeColor = builder.strokeColor;
        this.strokeWidth = builder.strokeWidth;
        this.alpha = builder.alpha;
        this.cornerRadius = builder.cornerRadius;
        this.icon = builder.icon;
        this.hasAnimation = builder.hasAnimation;
        this.typeface = builder.typeface;
        this.toastDurationTracker = new ToastDurationTracker(duration, this);
    }


    /**
     * Style your StyleableToastListener via styles.xml. Any styles set in the styles xlm will override the current attributes.
     *
     * @param style style id "R.style.xxx"
     */
    public void setStyle(@StyleRes int style) {
        this.style = style;
    }


    /**
     * @param text Text to be shown in the StyleableToastListener.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @param textColor if not set the default color white will be used.
     */
    public void setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
    }

    /**
     * Makes the StyleableToastListener's text bold.
     */
    public void setTextBold() {
        this.textBold = true;
    }

    /**
     * @param duration {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     * @throws IllegalStateException If a wrong value is used.
     */
    public void setDuration(int duration) {
        if (duration == LENGTH_LONG) {
            this.duration = duration;
        } else if (duration == LENGTH_SHORT) {
            this.duration = duration;
        } else {
            throw new IllegalStateException("StyleableB's duration must either be LENGTH_LONG or LENGTH_SHORT");
        }
    }


    /**
     * @param typeface Set a different typeface than the standard <i>sans-serif-condensed</i>
     */
    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    /**
     * Enables spinning animation of the passed icon by its around its own center.
     */
    public void spinIcon() {
        this.hasAnimation = true;
    }


    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public void setStrokeColor(@ColorInt int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * @param cornerRadius Sets the corner radius of the StyleableToastListener's shape. Pass 0 for a flat rectangle shape
     */
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    /**
     * Sets the transparency of the StyleableToastListener's background.
     *
     * @param alpha A value between 0-255.
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * @param icon Sets a icon on the left side of the StyleableToastListener's text
     */
    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }


    public void show() {
        styleableToast = new Toast(context);
        styleableToast.setDuration(duration);
        styleableToast.setView(getRootLayout());
        styleableToast.show();

        if (hasAnimation) {
            toastDurationTracker.trackToastDuration();
        }
    }

    /**
     * Cancels the ongoing StyleableToastListener.
     */
    public void cancel() {
        styleableToast.cancel();
    }

    public Toast getStyleableToast() {
        return styleableToast;
    }

    // ____________________ PUBLIC METHODS ENDS ________________________


    private GradientDrawable getShape() {
        getShapeAttributes();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getTypedValueInDP(context, cornerRadius));
        gradientDrawable.setStroke((int) getTypedValueInDP(context, strokeWidth), strokeColor);
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setAlpha(alpha);
        return gradientDrawable;
    }


    private View getRootLayout() {
        getIconAttributes();
        int horizontalPadding = (int) getTypedValueInDP(context, DEFAULT_HORIZONTAL_PADDING);
        int verticalPadding = (int) getTypedValueInDP(context, DEFAULT_VERTICAL_PADDING);
        RelativeLayout rootLayout = new RelativeLayout(context);
        rootLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        rootLayout.setBackground(getShape());
        rootLayout.addView(getTextView());
        if (icon > 0) {
            rootLayout.addView(getIcon());
            rootLayout.setPadding(0, verticalPadding, 0, verticalPadding);
        }

        return rootLayout;
    }

    private TextView getTextView() {
        getTextViewAttributes();
        textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE);
        textView.setTextColor(textColor);
        textView.setTypeface(getTypeface());
        textView.setMaxLines(4);

        if (icon > 0) {

            int leftPadding = (int) getTypedValueInDP(context, 41);
            int rightPadding = (int) getTypedValueInDP(context, 25);

            //Make space between icon and textview and textview and edge of the shape.
            if (BidiFormatter.getInstance().isRtlContext()) {
                textView.setPadding(rightPadding, 0, leftPadding, 0);
            } else {
                textView.setPadding(leftPadding, 0, rightPadding, 0);
            }
        }

        return textView;
    }


    private ImageView getIcon() {
        if (icon > 0) {
            int marginLeft = (int) getTypedValueInDP(context, 15);
            int marginRight = (int) getTypedValueInDP(context, 15);
            int maxHeightVal = (int) getTypedValueInDP(context, 20);
            int maxWidthVal = (int) getTypedValueInDP(context, 20);

            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(context.getResources().getDrawable(icon));
            imageView.setAnimation(getAnimation());
            imageView.setMaxWidth(marginLeft + maxWidthVal);
            imageView.setMaxHeight(maxHeightVal);
            imageView.setAdjustViewBounds(true);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            //Push the icon x dp from the edge of the shape
            if (BidiFormatter.getInstance().isRtlContext()) {
                layoutParams.setMargins(0, 0, marginRight, 0);
            } else {
                layoutParams.setMargins(marginLeft, 0, 0, 0);
            }

            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            imageView.setLayoutParams(layoutParams);
            return imageView;
        }
        return null;
    }


    /**
     * loads style attributes from styles.xml if a style resource is used.
     */
    @SuppressWarnings("ResourceType")
    private void getShapeAttributes() {
        if (style > 0) {

            // each entries Attrs must be alphabetic ordered
            int[] colorAttrs = {android.R.attr.colorBackground, android.R.attr.strokeColor};
            int[] floatAttrs = {android.R.attr.alpha, android.R.attr.strokeWidth};
            int[] dimenAttrs = {android.R.attr.radius};

            TypedArray colors = context.obtainStyledAttributes(style, colorAttrs);
            TypedArray floats = context.obtainStyledAttributes(style, floatAttrs);
            TypedArray dimens = context.obtainStyledAttributes(style, dimenAttrs);

            if (colors.hasValue(0)) {
                backgroundColor = colors.getColor(0, DEFAULT_BACKGROUND);
            }

            if (dimens.hasValue(0)) {
                cornerRadius = (int) dimens.getDimension(0, DEFAULT_CORNER_RADIUS);
            }

            if (floats.hasValue(0)) {
                alpha = (int) floats.getFloat(0, DEFAULT_ALPHA);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                strokeWidth = floats.getFloat(1, 0);
                strokeColor = colors.getColor(1, Color.TRANSPARENT);
            }

            colors.recycle();
            floats.recycle();
            dimens.recycle();
        }
    }

    private void getTextViewAttributes() {
        if (style > 0) {

            int[] colorAttrs = {android.R.attr.textColor};
            int[] stringAttrs = {android.R.attr.fontFamily};
            int[] intsAttrs = {android.R.attr.textStyle};

            TypedArray colors = context.obtainStyledAttributes(style, colorAttrs);
            TypedArray strings = context.obtainStyledAttributes(style, stringAttrs);
            TypedArray ints = context.obtainStyledAttributes(style, intsAttrs);

            if (colors.hasValue(0)) {
                textColor = colors.getColor(0, DEFAULT_TEXT_COLOR);
            }

            if (strings.hasValue(0)) {
                String fontStyle = strings.getString(0);
                if (fontStyle != null && !fontStyle.isEmpty()) {
                    if (fontStyle.contains("fonts")) {
                        typeface = Typeface.createFromAsset(context.getAssets(), fontStyle);
                    } else {
                        typeface = Typeface.create(fontStyle, Typeface.NORMAL);
                    }
                }
            }

            if (ints.hasValue(0)) {
                textBold = ints.getInt(0, 0) == 1;
            }

            colors.recycle();
            strings.recycle();
            ints.recycle();
        }
    }


    private void getIconAttributes() {
        if (style > 0) {
            int[] drawableAttrSet = {android.R.attr.icon};
            TypedArray drawables = context.obtainStyledAttributes(style, drawableAttrSet);
            if (drawables.hasValue(0)) {
                icon = drawables.getResourceId(0, 0);
            }
            drawables.recycle();
        }
    }


    private Animation getAnimation() {
        if (hasAnimation) {
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(1000);
            return anim;
        }

        return null;
    }


    private Typeface getTypeface() {
        if (textBold && typeface == null) {
            return Typeface.create(DEFAULT_CONDENSED_FONT, Typeface.BOLD);
        } else if (textBold) {
            return Typeface.create(typeface, Typeface.BOLD);
        } else if (typeface != null) {
            return Typeface.create(typeface, Typeface.NORMAL);
        } else {
            return Typeface.create(DEFAULT_CONDENSED_FONT, Typeface.NORMAL);
        }
    }

    /**
     * A callback that automatically cancels and resets animation effect from spinIcon(); when the StyleableToastListener is finished showing on screen.
     * Users should not call this method as this is used internally in the library.
     */
    @Override
    public void onToastFinished() {
        if (getAnimation() != null) {
            getAnimation().cancel();
            getAnimation().reset();
        }
    }


    //--------------------BUILDER--------------------

    public static class Builder {
        private final Context context;
        private int backgroundColor = DEFAULT_BACKGROUND;
        private int textColor = DEFAULT_TEXT_COLOR;
        private int alpha = DEFAULT_ALPHA;
        private int duration = Toast.LENGTH_SHORT;
        private int cornerRadius = DEFAULT_CORNER_RADIUS;
        private int strokeWidth, icon, strokeColor;
        private boolean hasAnimation, textBold;
        private Typeface typeface;
        private String text;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        /**
         * @param text Text to be shown in the StyleableToastListener
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * @param textColor if not set the default color white will be used.
         */
        public Builder textColor(@ColorInt int textColor) {
            this.textColor = textColor;
            return this;
        }

        /**
         * Makes the StyleableToastListener's text bold.
         */
        public Builder textBold() {
            this.textBold = true;
            return this;
        }

        /**
         * @param duration {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
         * @throws IllegalStateException If a wrong value is used.
         */
        public Builder duration(int duration) {
            if (duration == LENGTH_LONG) {
                this.duration = duration;
            } else if (duration == LENGTH_SHORT) {
                this.duration = duration;
            } else {
                throw new IllegalStateException("StyleableB's duration must either be LENGTH_LONG or LENGTH_SHORT");
            }
            return this;
        }


        /**
         * @param typeface Set a different typeface than the standard <i>sans-serif-condensed</i>
         */
        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        /**
         * Enables spinning animation of the passed icon by its around its own center.
         */
        public Builder spinIcon() {
            this.hasAnimation = true;
            return this;
        }


        public Builder backgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        /**
         * Sets the stroke color of the StylebleX.<br>
         * Use with {@link StyleableToastListener#strokeWidth}
         */
        public Builder strokeColor(@ColorInt int strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        /**
         * Sets the stroke width of the StylebleX.<br>
         * Use with {@link StyleableToastListener#strokeColor}
         */
        public Builder strokeWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        /**
         * @param cornerRadius Sets the corner radius of the StyleableToastListener's shape. Pass 0 for a flat rectangle shape.
         */
        public Builder cornerRadius(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }


        /**
         * Sets the transparency of the StyleableToastListener's background.
         *
         * @param alpha A value between 0-255.
         */
        public Builder alpha(int alpha) {
            this.alpha = alpha;
            return this;
        }

        /**
         * @param icon Sets a icon on the left side of the StyleableToastListener's text.
         */
        public Builder icon(@DrawableRes int icon) {
            this.icon = icon;
            return this;
        }

        /**
         * @return A mutable StyleableToastListener object.
         */
        public StyleableToastListener build() {
            return new StyleableToastListener(this);
        }

    }

}
