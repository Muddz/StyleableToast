package com.muddzdev.styleabletoastlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.util.TypedValue;

/**
 * Created by Muddz on 26-01-2017.
 */

public class Utils {


    public static float getTypedValueInDP(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public static float getTypedValueInSP(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics());
    }


    public static TypedArray getStyleValuesColor(Context context, @AttrRes int attrId, int style) {
        TypedArray a = null;
        int result;

        if (style > 0) {
            int[] AttrSet = {attrId};
            a = context.obtainStyledAttributes(style, AttrSet);
            result = a.getColor(0, Color.LTGRAY);

        }
        return a;

    }

    public static int getStyleValuesInt(Context context, @AttrRes int attrId, int style) {
        TypedArray a = null;

        if (style > 0) {
            int[] AttrSet = {attrId};
            a = context.obtainStyledAttributes(style, AttrSet);
            a.recycle();
        }
        return a.getInt(0, 0);
    }

    public static String getStyleValuesString(Context context, @AttrRes int attrId, int style) {
        TypedArray a = null;

        if (style > 0) {
            int[] AttrSet = {attrId};
            a = context.obtainStyledAttributes(style, AttrSet);
            a.recycle();
        }
        return a.getString(0);
    }


    public static boolean getStyleValuesBoolean(Context context, @AttrRes int attrId, int style) {
        TypedArray a = null;

        if (style > 0) {
            int[] AttrSet = {attrId};
            a = context.obtainStyledAttributes(style, AttrSet);
            a.recycle();
        }
        return a.getBoolean(0, false);
    }

}
