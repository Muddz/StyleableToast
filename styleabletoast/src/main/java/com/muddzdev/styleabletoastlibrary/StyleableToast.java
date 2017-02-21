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
import android.support.annotation.StringRes;
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
    private static int MAX_ALPHA = 255;

    private final Context context;
    private TextView textView;
    private Typeface font;
    private Toast toast;

    private float strokeWidth;
    private int duration, style, alpha, drawable;
    private int backgroundColor, textColor, strokeColor;
    private int cornerRadius = -1;
    private boolean isBold;
    private boolean isAnimation;
    private String toastMsg;
    private ToastDurationWatcher durationTracker;


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

	private StyleableToast(Builder builder) {
		this.context = builder.context;
		this.toastMsg = builder.message;
		this.duration = builder.length;
		setupFromBuilder(builder);
	}

	private void setupFromBuilder(@NonNull Builder builder) {
		if (isValidValue(builder.bgColor)) {
			setBackgroundColor(builder.bgColor);
		}
		if (isValidValue(builder.textColor)) {
			setTextColor(builder.textColor);
		}
		if (isValidValue(builder.icon)) {
			setIcon(builder.icon);
		}
		if (isValidValue(builder.styleRes)) {
			setStyle(builder.styleRes);
		}
		if (isValidValue(builder.cornerRadius)) {
			setCornerRadius(builder.cornerRadius);
		}
		if (isValidValue(builder.strokeWidth) && isValidValue(builder.strokeColor)) {
			setToastStroke(builder.strokeWidth, builder.strokeColor);
		}
		if (builder.typeface != null) {
			setTextFont(builder.typeface);
		}
		if (builder.maxAlpha) {
			setMaxAlpha();
		}
		if (builder.boldText) {
			setBoldText();
		}
		if (builder.iconAnimation) {
			spinIcon();
		}
	}

	private boolean isValidValue(int value) {
		//builder default int values are set to -1
		return value >= 0;
	}


    /**
     * @param style Style your toast via styles.xml and pass the style id R.style.xxx
     *              <p>The attributes that must be used:</p>
     *              android:textColor.<br>
     *              android:textStyle.<br>
     *              android:fontFamily. If custom font, just write the path to it like: "fonts/myfont.ttf"<br>
     *              android:colorBackground.<br>
     *              android:strokeWidth.  API 21+<br>
     *              android:strokeColor.  API 21+<br>
     *              android:radius. Corner radius<br>
     *              android:alpha. Value between 1-255 where 255 is full solid<br>
     *              android:icon.<br>
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
        this.isBold = isBold;
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
    public StyleableToast spinIcon() {
        isAnimation = true;
        return this;
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
     * Makes the toast background full solid instead of the default 75% transparency.
     */
    public void setMaxAlpha() {
        this.alpha = MAX_ALPHA;
    }

    /**
     * @param drawable Sets a icon on the left side of the toast text
     */
    public void setIcon(@DrawableRes int drawable) {
        this.drawable = drawable;
    }


    //Returns the relative layout containing: textview, icons, shape
    private View getToastLayout() {

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


    /**
     * loads style attributes from styles.xml if a style resource is used.
     */
    @SuppressWarnings("ResourceType")
    private void getToastShapeAttrs() {
        if (style > 0) {

            //array entries must be alphabetic ordered
            int[] colorAttrs = {android.R.attr.colorBackground, android.R.attr.strokeColor};
            int[] floatAttrs = {android.R.attr.alpha, android.R.attr.strokeWidth};
            int[] dimenAttrs = {android.R.attr.radius};

            TypedArray colors = context.obtainStyledAttributes(style, colorAttrs);
            TypedArray floats = context.obtainStyledAttributes(style, floatAttrs);
            TypedArray dimens = context.obtainStyledAttributes(style, dimenAttrs);

            backgroundColor = colors.getColor(0, DEFAULT_BACKGROUND);
            cornerRadius = (int) dimens.getDimension(0, DEFAULT_CORNER_RADIUS);
            alpha = (int) floats.getFloat(0, DEFAULT_ALPHA);

            if (Build.VERSION.SDK_INT >= 21) {
                strokeWidth = floats.getFloat(1, 0);
                strokeColor = colors.getColor(1, Color.TRANSPARENT);
            }

            colors.recycle();
            floats.recycle();
            dimens.recycle();
        }

    }

    private GradientDrawable getToastShape() {
        getToastShapeAttrs();

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
            String passedFont = strings.getString(0);

            if (passedFont != null && !passedFont.isEmpty()) {
                if (passedFont.contains("fonts")) {
                    font = Typeface.createFromAsset(context.getAssets(), passedFont);
                } else {
                    font = Typeface.create(passedFont, Typeface.NORMAL);
                }
            }
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
        if (isAnimation) {
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
            imageView.setImageDrawable(context.getResources().getDrawable(drawable));
            imageView.setAnimation(getAnimation());
            imageView.setMaxWidth(marginLeft + maxWidthVal);
            imageView.setMaxHeight(maxHeightVal);
            imageView.setAdjustViewBounds(true);


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
            return Typeface.create(DEFAULT_CONDENSED_FONT, Typeface.BOLD);
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

        toast = new Toast(context);
        toast.setView(getToastLayout());
        toast.setDuration(duration);
        toast.show();

        if (isAnimation) {
            durationTracker = new ToastDurationWatcher(toast.getDuration(), this);
        }
    }

    public void cancel() {
        toast.cancel();

    }


    /**
     * A callback that automatically cancels and resets animation effect from spinIcon(); when the toast is finished showing on screen.
     * Users should not call this method as this is used internally in the library.
     */
    @Override
    public void onToastFinished() {

        getAnimation().cancel();
        getAnimation().reset();
    }


    public static StyleableToast makeText(Context context, CharSequence text, int duration, int style) {

        StyleableToast styleableToast = new StyleableToast(context, text.toString(), duration, style);

        return styleableToast;
    }

	public static final class Builder {

		private final Context context;

		private String message;
		private int length = Toast.LENGTH_LONG;

		@ColorInt
		private int bgColor     = -1;
		@ColorInt
		private int textColor   = -1;
		@DrawableRes
		private int icon        = -1;
		@StyleRes
		private int styleRes    = -1;
		@ColorInt
		private int strokeColor = -1;

		private boolean  iconAnimation = false;
		private boolean  boldText      = false;
		private boolean  maxAlpha      = false;
		private Typeface typeface      = null;
		private int      cornerRadius  = -1;
		private int      strokeWidth   = -1;


		public Builder(Context context, @NonNull String message) {
			this.context = context.getApplicationContext();
			this.message = message;
		}

		public Builder(Context context, @StringRes int message) {
			this(context, context.getString(message));
		}

		/**
		 * @param backgroundColor if not set the default color grey will be used.
		 */
		public Builder withBackgroundColor(@ColorInt int backgroundColor) {
			this.bgColor = backgroundColor;
			return this;
		}

		/**
		 * @param textColor if not set the default color white will be used.
		 */
		public Builder withTextColor(@ColorInt int textColor) {
			this.textColor = textColor;
			return this;
		}

		/**
		 * @param duration Sets the toast's duration. Either {@linkplain Toast#LENGTH_LONG} or {@linkplain Toast#LENGTH_SHORT}
		 */
		public Builder withDuration(int duration) {
			if (duration == Toast.LENGTH_LONG || duration == Toast.LENGTH_SHORT) {
				this.length = duration;
			}
			return this;
		}

		/**
		 * @param iconRes The icon's resource which should be used within the toast
		 */
		public Builder withIcon(@DrawableRes int iconRes) {
			return withIcon(iconRes, false);
		}

		/**
		 * @param iconRes  The icon's resource which should be used within the toast
		 * @param spinIcon Enables spinning animation of the passed icon by its around its own center.
		 */
		public Builder withIcon(@DrawableRes int iconRes, boolean spinIcon) {
			this.icon = iconRes;
			this.iconAnimation = spinIcon;
			return this;
		}

		/**
		 * @param style Style your toast via styles.xml and pass the style id R.style.xxx <p>The attributes that must be used:</p> android:textColor.<br> android:textStyle.<br> android:fontFamily. If
		 *              custom font, just write the path to it like: "fonts/myfont.ttf"<br> android:colorBackground.<br> android:strokeWidth.  API 21+<br> android:strokeColor.  API 21+<br>
		 *              android:radius. Corner radius<br> android:alpha. Value between 1-255 where 255 is full solid<br> android:icon.<br>
		 */
		public Builder withStyle(@StyleRes int style) {
			this.styleRes = style;
			return this;
		}

		/**
		 * Specify the message displayed in this Toast.
		 *
		 * @param toastMessage The message displayed in this Toast.
		 */
		public Builder withToastMessage(@NonNull String toastMessage) {
			this.message = toastMessage;
			return this;
		}

		/**
		 * Specify whether to display the Toast's message with a bold typeface.
		 *
		 * @param useBoldText Whether to display the Toast's message with a bold typeface.
		 */
		public Builder withBoldText(boolean useBoldText) {
			this.boldText = useBoldText;
			return this;
		}

		/**
		 * @param typeface Set a different font than the standard <i>sans-serif-condensed</i>
		 */
		public Builder withTextFont(@NonNull Typeface typeface) {
			this.typeface = typeface;
			return this;
		}

		/**
		 * Specify the Stroke for this Toast
		 *
		 * @param strokeWidth Width of the Toast's stroke
		 * @param strokeColor Color of the Toast's stroke
		 */
		public Builder withToastStroke(int strokeWidth, @ColorInt int strokeColor) {
			if (strokeWidth > 0) {
				this.strokeWidth = strokeWidth;
				this.strokeColor = strokeColor;
			}
			return this;
		}

		/**
		 * @param cornerRadius Sets the corner radius of the toast shape. Pass 0 for a flat rectangle shape
		 */
		public Builder withCornerRadius(int cornerRadius) {
			if (cornerRadius >= 0) {
				this.cornerRadius = cornerRadius;
			}
			return this;
		}

		/**
		 * Set the alpha/Transparency of the Toast background between 0-255. 255 is full opque and 0 is full transparency.
		 */
		public Builder withMaxAlpha() {
			this.maxAlpha = true;
			return this;
		}

		/**
		 * Build and returns the configured instance
		 *
		 * @return The configured {@link StyleableToast} instance.
		 */
		public StyleableToast build() {
			return new StyleableToast(this);
		}

		/**
		 * Build and displays the configured {@link StyleableToast} instance
		 */
		public void show() {
			new StyleableToast(this).show();
		}

	}

}
