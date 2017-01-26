package com.muddzdev.styleabletoastlibrary;

import android.content.Context;
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

}
