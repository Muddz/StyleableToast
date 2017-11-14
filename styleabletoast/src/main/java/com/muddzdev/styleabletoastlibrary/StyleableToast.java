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

    private static final String DEFAULT_CONDENSED_FONT = "sans-serif-condensed";
    private static final int DEFAULT_BACKGROUND = Color.parseColor("#555555"); // D
    private static final int DEFAULT_CORNER_RADIUS = R.dimen.default_corner_radius;
    private static final int DEFAULT_ALPHA = R.integer.defaultBackgroundAlpha;

    private int backgroundColor = DEFAULT_BACKGROUND;
    private int length = Toast.LENGTH_LONG;
    private int alpha = DEFAULT_ALPHA;


    private int iconResLeft;
    private int iconResRight;
    private int cornerRadius;
    private int strokeColor;
    private int textColor;

    private int style;
    private float strokeWidth;
    private boolean hasAnimation;
    private boolean textBold;
    private String text;

    private final Context context;
    private TextView textView;
    private Typeface typeface;
    private ImageView iconLeft;
    private ImageView iconRight;
    private Toast styleableToast;
    private LinearLayout rootLayout;
    private ToastLengthTracker toastDurationTracker; //D

    public static StyleableToast makeText(Context context, String text, int length, int style) {
        return new StyleableToast(context, text, length, style);
    }


    //TODO set default values from values folder
    //TODO read all comments and methods.. Refactoring round 2!
    //TODO Delete unessercery code.
    //TODO new samples for the show case on github.
    //TODO 18dp or 20dp for horizontal padding with icon?


    //TODO refactor this better!
    private void initLayout() {
        View v = inflate(getContext(), R.layout.styleable_layout, null);
        rootLayout = v.findViewById(R.id.root);
        textView = v.findViewById(R.id.textview);
        iconLeft = v.findViewById(R.id.icon_left);
        iconRight = v.findViewById(R.id.icon_right);

        //TODO ***
        makeTextView();
        makeShape();

    }

    //For styles.xml
    private StyleableToast(@NonNull Context context, String text, int length, @StyleRes int style) {
        super(context);
        this.context = context;
        this.text = text;
        this.length = length;
        this.style = style;
    }

    //For builder pattern.
    private StyleableToast(StyleableToast.Builder builder) {
        super(builder.context);
        this.context = builder.context.getApplicationContext();
        this.text = builder.text;
        this.textColor = builder.textColor;
        this.textBold = builder.textBold;
        this.length = builder.length;
        this.backgroundColor = builder.backgroundColor;
        this.strokeColor = builder.strokeColor;
        this.strokeWidth = builder.strokeWidth;
        this.alpha = builder.alpha;
        this.cornerRadius = builder.cornerRadius;
        this.iconResLeft = builder.iconResLeft;
        this.iconResRight = builder.iconResRight;
        this.hasAnimation = builder.hasAnimation;
        this.typeface = builder.typeface;
        this.toastDurationTracker = new ToastLengthTracker(length, this);
        initLayout();
    }

    /**
     * Style your StyleableToastListener via styles.xml. Any styles set in the styles.xml will override the current attributes.
     *
     * @param style style resId.
     */
    public void setStyle(@StyleRes int style) {
        this.style = style;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
    }

    public void setTextBold() {
        this.textBold = true;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeWidth(int strokeWidth, @ColorInt int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }


    /**
     * @param cornerRadius Sets the corner radius of the StyleableToastListener's shape.
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
     * Enables spinning animation of the passed iconResLeft by its around its own center.
     */
    public void spinIcon() {
        this.hasAnimation = true;
    }


    public void setIconResLeft(@DrawableRes int iconResLeft) {
        this.iconResLeft = iconResLeft;
    }

    public void setIconResRight(@DrawableRes int iconResRight) {
        this.iconResRight = iconResRight;
    }

    /**
     * Default Toast.LENGTH_LONG
     *
     * @param length {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     * @throws IllegalStateException If non of the above values is used.
     */

    public void setLength(int length) {
        if (length == LENGTH_LONG) {
            this.length = length;
        } else if (length == LENGTH_SHORT) {
            this.length = length;
        } else {
            throw new IllegalStateException("StyleableB's length must either be LENGTH_LONG or LENGTH_SHORT");
        }
    }

    public void show() {
        styleableToast = new Toast(context);
        styleableToast.setDuration(length);
        styleableToast.setView(rootLayout);
        styleableToast.show();
        if (hasAnimation) {
            toastDurationTracker.trackToastLength();
        }
    }

    public void cancel() {
        styleableToast.cancel();
    }

    //Will be deleted in 2.0.1
    @Deprecated
    public Toast getStyleableToast() {
        return styleableToast;
    }

    // ____________________ PUBLIC METHODS ENDS ________________________


    private void makeTextView() {
        textView.setText(text);
        
        if (textColor > 0) {
            textView.setTextColor(textColor);
        }

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
            int horizontalPadding = (int) getResources().getDimension(R.dimen.toast_horizontal_padding_with_icon);
            int verticalPadding = (int) getResources().getDimension(R.dimen.toast_vertical_padding);
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
                textColor = colors.getColor(0, Color.WHITE);
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

        private int backgroundColor = DEFAULT_BACKGROUND;
        private int alpha = DEFAULT_ALPHA;
        private int length = Toast.LENGTH_SHORT;
        private int cornerRadius = DEFAULT_CORNER_RADIUS;

        private int iconResLeft;
        private int iconResRight;
        private int strokeColor;
        private int strokeWidth;
        private int textColor;
        private String text;
        private boolean hasAnimation;
        private boolean textBold;

        private final Context context;
        private Typeface typeface;


        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder textColor(@ColorInt int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder textBold() {
            this.textBold = true;
            return this;
        }

        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public Builder backgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder stroke(int strokeWidth, int strokeColor) {
            this.strokeWidth = strokeWidth;
            this.strokeColor = strokeColor;
            return this;
        }


        /**
         * @param cornerRadius Sets the corner radius of the StyleableToastListener's shape.
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

        public Builder iconResLeft(@DrawableRes int iconResLeft) {
            this.iconResLeft = iconResLeft;
            return this;
        }

        public Builder iconResRight(@DrawableRes int iconResRight) {
            this.iconResRight = iconResRight;
            return this;
        }

        /**
         * @param duration {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
         * @throws IllegalStateException If a wrong value is used.
         */
        public Builder duration(int duration) {
            if (duration == LENGTH_LONG) {
                this.length = duration;
            } else if (duration == LENGTH_SHORT) {
                this.length = duration;
            } else {
                throw new IllegalStateException("StyleableB's length must either be LENGTH_LONG or LENGTH_SHORT");
            }
            return this;
        }

        /**
         * Enables spinning animation of the passed iconResLeft by its around its own center.
         */
        public Builder spinIcon() {
            this.hasAnimation = true;
            return this;
        }

        public void show() {
            StyleableToast styleableToast = new StyleableToast(this);
            styleableToast.show();
        }
    }
}
