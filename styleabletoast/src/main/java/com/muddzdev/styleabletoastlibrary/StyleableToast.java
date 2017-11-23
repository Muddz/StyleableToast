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
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.TypefaceCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
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


public class StyleableToast extends RelativeLayout implements OnToastFinishedListener {

    private int cornerRadius = -1;
    private int backgroundColor;
    private int strokeColor;
    private int strokeWidth;
    private int iconResLeft;
    private int iconResRight;
    private int textColor;
    private int length;
    private int style;
    private boolean hasAnimation;
    private boolean textBold;
    private String text;
    private TypedArray typedArray;
    private TextView textView;
    private Typeface typeface;
    private ImageView iconLeft;
    private ImageView iconRight;
    private Toast styleableToast;
    private LinearLayout rootLayout;
    private final Context context;

    @Deprecated
    public static StyleableToast makeText(@NonNull Context context, String text, int length, @StyleRes int style) {
        return new StyleableToast(context, text, length, style);
    }

    public static StyleableToast makeText(@NonNull Context context, String text, @StyleRes int style) {
        return new StyleableToast(context, text, Toast.LENGTH_LONG, style);
    }

    //TODO to remove the old makeText() or not
    // TODO Be sure on the custom attribute namings!!!
    // TODO make a release note already now!!
    //TODO read all comments and methods.. Refactoring round 2!
    //TODO new samples for the show case on github.
    //TODO Test if Full alpha is even nessecery else completely remove alpha logic


    //TODO REFACTOR THIS TO BETTER
    private void initLayout() {
        View v = inflate(getContext(), R.layout.styleable_layout, null);
        rootLayout = v.findViewById(R.id.root);
        textView = v.findViewById(R.id.textview);
        iconLeft = v.findViewById(R.id.icon_left);
        iconRight = v.findViewById(R.id.icon_right);
        if (style > 0) {
            typedArray = getContext().obtainStyledAttributes(style, R.styleable.StyleableToast);
        }
        makeShape();
        makeIcon();
        makeTextView();

        if (style > 0 && typedArray != null) {
            typedArray.recycle();
        }

        if (hasAnimation) {
            iconLeft.setAnimation(getAnimation());
            new ToastLengthTracker(length, this);
        }
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
        this.cornerRadius = builder.cornerRadius;
        this.iconResLeft = builder.iconResLeft;
        this.iconResRight = builder.iconResRight;
        this.hasAnimation = builder.hasAnimation;
        this.typeface = builder.typeface;
    }

    /**
     * Style your StyleableToast via styles.xml. Any styles set in the styles.xml will override current attributes.
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

    public void setStroke(int strokeWidth, @ColorInt int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    /**
     * @param cornerRadius Sets the corner radius of the StyleableToast's shape.
     */
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }


    public void setIconResLeft(@DrawableRes int iconResLeft) {
        this.iconResLeft = iconResLeft;
    }

    public void setIconResRight(@DrawableRes int iconResRight) {
        this.iconResRight = iconResRight;
    }

    /**
     * Enables spinning animation of the passed iconResLeft by its around its own center.
     */
    public void spinIcon() {
        this.hasAnimation = true;
    }

    /**
     * Default Toast.LENGTH_LONG
     *
     * @param length {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     * @throws IllegalStateException If non of the above values is used.
     */

    public void setLength(int length) {
        this.length = length;
    }

    public void show() {
        initLayout();
        styleableToast = new Toast(context);
        styleableToast.setDuration(length == Toast.LENGTH_SHORT ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        styleableToast.setView(rootLayout);
        styleableToast.show();
    }

    public void cancel() {
        styleableToast.cancel();
    }

    // ----------------------- PUBLIC METHODS ENDS -----------------------

    private void makeShape() {
        loadShapeAttributes();
        GradientDrawable gradientDrawable = (GradientDrawable) rootLayout.getBackground();
        gradientDrawable.setCornerRadius(cornerRadius != -1 ? cornerRadius : R.dimen.default_corner_radius);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        if (backgroundColor == 0) {
            gradientDrawable.setColor(ContextCompat.getColor(context, R.color.defaultBackgroundColor));
        } else {
            gradientDrawable.setColor(backgroundColor);
        }

        gradientDrawable.setAlpha(getResources().getInteger(R.integer.defaultBackgroundAlpha));
        rootLayout.setBackground(gradientDrawable);
    }

    private void makeTextView() {
        loadTextViewStyleAttributes();
        textView.setText(text);

        if (textColor != 0) {
            textView.setTextColor(textColor);
        }
        if (textBold && typeface == null) {
            textView.setTypeface(Typeface.create(context.getString(R.string.default_font), Typeface.BOLD));
        } else if (textBold) {
            textView.setTypeface(Typeface.create(typeface, Typeface.BOLD));
        } else if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }


    private void makeIcon() {
        loadIconAttributes();
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
    private void loadShapeAttributes() {
        if (style == 0) {
            return;
        }

        length = typedArray.getInt(R.styleable.StyleableToast_length, 0);
        backgroundColor = typedArray.getColor(R.styleable.StyleableToast_backgroundColor, ContextCompat.getColor(context, R.color.defaultBackgroundColor));
        cornerRadius = (int) typedArray.getDimension(R.styleable.StyleableToast_cornerRadius, R.dimen.default_corner_radius);

        if (Build.VERSION.SDK_INT >= 21) {
            if (typedArray.hasValue(R.styleable.StyleableToast_strokeColor) && typedArray.hasValue(R.styleable.StyleableToast_strokeWidth)) {
                strokeWidth = (int) typedArray.getDimension(R.styleable.StyleableToast_strokeWidth, 0);
                strokeColor = typedArray.getColor(R.styleable.StyleableToast_strokeColor, Color.TRANSPARENT);
            }
        }
    }

    private void loadTextViewStyleAttributes() {
        if (style == 0) {
            return;
        }
        textBold = typedArray.getBoolean(R.styleable.StyleableToast_textBold, false);
        textColor = typedArray.getColor(R.styleable.StyleableToast_textColor, Color.WHITE);
        String textFontPath = typedArray.getString(R.styleable.StyleableToast_textFont);
        if (textFontPath != null) {
            if (textFontPath.contains("fonts/") && textFontPath.contains(".otf") || textFontPath.contains(".ttf")) {
                typeface = Typeface.createFromAsset(context.getAssets(), textFontPath);
            }
        }
    }


    private void loadIconAttributes() {
        if (style == 0) {
            return;
        }
        iconResLeft = typedArray.getResourceId(R.styleable.StyleableToast_iconLeft, 0);
        iconResRight = typedArray.getResourceId(R.styleable.StyleableToast_iconRight, 0);
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

        private int cornerRadius = -1;
        private int backgroundColor;
        private int strokeColor;
        private int strokeWidth;
        private int iconResLeft;
        private int iconResRight;
        private int textColor;
        private int length;
        private boolean hasAnimation;
        private boolean textBold;
        private String text;
        private Typeface typeface;
        private final Context context;

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
         * @param cornerRadius Sets the corner radius of the StyleableToast's shape.
         */
        public Builder cornerRadius(int cornerRadius) {
            this.cornerRadius = cornerRadius;
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
         * Enables spinning animation of the passed iconResLeft by its around its own center.
         */
        public Builder spinIcon() {
            this.hasAnimation = true;
            return this;
        }

        /**
         * @param length {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
         * @throws IllegalStateException If a wrong value is used.
         */
        public Builder length(int length) {
            if (length == LENGTH_LONG || length == LENGTH_SHORT) {
                this.length = length;
            } else {
                this.length = LENGTH_LONG;
            }
            return this;
        }

        public void show() {
            StyleableToast styleableToast = new StyleableToast(this);
            styleableToast.show();
        }
    }
}
