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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class StyleableToast extends RelativeLayout implements OnToastFinishedListener {

    private static final String TAG = StyleableToast.class.getSimpleName();

    private static final String DEFAULT_CONDENSED_FONT = "sans-serif-condensed";
    private static final int DEFAULT_BACKGROUND = Color.parseColor("#555555"); // D
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE; // D
    private static final int DEFAULT_CORNER_RADIUS = R.dimen.default_corner_radius;
    private static final int DEFAULT_ALPHA = 230; // D

    private int cornerRadius;
    private int backgroundColor = DEFAULT_BACKGROUND;
    private int alpha = DEFAULT_ALPHA;
    private int textColor = DEFAULT_TEXT_COLOR;

    private int style, iconResLeft, iconResRight, strokeColor, duration;
    private boolean textBold, hasAnimation;
    private float strokeWidth;

    private final Context context;
    private TextView textView;
    private Typeface typeface;
    private Toast styleableToast;
    private ImageView iconLeft, iconRight;

    private String text;
    private ToastDurationTracker toastDurationTracker; //D
    private LinearLayout rootLayout;

    //TODO REFACTOR DURATION TO LENGHT

    public static StyleableToast makeText(Context context, String text, int duration, int style) {
        return new StyleableToast(context, text, duration, style);
    }

    private void initLayout() {
        View v = inflate(getContext(), R.layout.styleable_layout, null);
        rootLayout = v.findViewById(R.id.root);
        textView = v.findViewById(R.id.textview);
        iconLeft = v.findViewById(R.id.icon_left);
        iconRight = v.findViewById(R.id.icon_right);
        makeTextView();
        makeShape();
    }

    //For styles xml
    private StyleableToast(@NonNull Context context, String text, int duration, @StyleRes int style) {
        super(context);
        this.context = context;
        this.text = text;
        this.duration = duration;
        this.style = style;
    }

    //For builder pattern.
    private StyleableToast(StyleableToast.Builder builder) {
        super(builder.context);
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
        this.iconResLeft = builder.icon;
        this.hasAnimation = builder.hasAnimation;
        this.typeface = builder.typeface;
        this.toastDurationTracker = new ToastDurationTracker(duration, this);
        initLayout();
    }


    /**
     * Style your StyleableToastListener via styles.xml. Any styles set in the styles xlm will override the current attributes.
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
     * Enables spinning animation of the passed iconResLeft by its around its own center.
     */
    public void spinIcon() {
        this.hasAnimation = true;
    }


    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public void setStrokeWidth(int strokeWidth, @ColorInt int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    /**
     * @param cornerRadius Sets the corner radius of the StyleableToastListener's shape. Pass 0 for a flat rectangle shape
     */
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    /**
     * Sets the transparency of the StyleableToastListener's background.
     * @param alpha A value between 0-255.
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * @param iconResLeft Sets a iconResLeft on the left side of the StyleableToastListener's text
     */
    public void setIconResLeft(@DrawableRes int iconResLeft) {
        this.iconResLeft = iconResLeft;
    }


    public void show() {
        styleableToast = new Toast(context);
        styleableToast.setDuration(duration);
        styleableToast.setView(rootLayout);
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


    private void makeTextView() {
        textView.setText(text);
        textView.setTextColor(textColor);

        if (textBold && typeface == null) {
            textView.setTypeface(Typeface.create(DEFAULT_CONDENSED_FONT, Typeface.BOLD));
        } else if (textBold) {
            textView.setTypeface(Typeface.create(typeface, Typeface.BOLD));
        } else if (typeface != null) {
            textView.setTypeface(typeface);
        }
        getTextViewStyleAttributes();
    }

    private void makeShape() {
        GradientDrawable gradientDrawable = (GradientDrawable) rootLayout.getBackground();
        gradientDrawable.setCornerRadius(getTypedValueInDP(context, cornerRadius));
        gradientDrawable.setStroke((int) getTypedValueInDP(context, strokeWidth), strokeColor);
        gradientDrawable.setAlpha(alpha);
        gradientDrawable.setColor(backgroundColor);
        getShapeAttributes();
        setIconSettings();
    }

    private void setIconSettings() {
        if (iconResLeft > 0 || iconResRight > 0) {
            int horizontalPadding = (int) getTypedValueInDP(context, 20);
            int verticalPadding = (int) context.getResources().getDimension(R.dimen.toast_vertical_padding);

            rootLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }
        if (iconResLeft > 0) {
            iconLeft.setBackgroundResource(iconResLeft);
            iconLeft.setVisibility(VISIBLE);
        }
        if (iconResRight > 0) {
            iconRight.setBackgroundResource(iconResRight);
            iconRight.setVisibility(VISIBLE);
        }
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

    private void getTextViewStyleAttributes() {
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
                iconResLeft = drawables.getResourceId(0, 0);
            }
            drawables.recycle();
        }
    }


    public Animation getAnimation() {
        if (hasAnimation) {
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(1000);
            return anim;
        }

        return null;
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
         * Enables spinning animation of the passed iconResLeft by its around its own center.
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
         * Sets the stroke width and color of the StylebleX.<br>
         * Use with {@link StyleableToast#strokeColor}
         */
        public Builder stroke(int strokeWidth, int strokeColor) {
            this.strokeWidth = strokeWidth;
            this.strokeColor = strokeColor;
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
         * @param icon Sets a iconResLeft on the left side of the StyleableToastListener's text.
         */
        public Builder icon(@DrawableRes int icon) {
            this.icon = icon;
            return this;
        }

        /**
         * @return A mutable StyleableToastListener object.
         */
        public StyleableToast build() {
            return new StyleableToast(this);
        }

    }

}
