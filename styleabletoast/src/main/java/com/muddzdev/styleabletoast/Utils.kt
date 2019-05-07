package com.muddzdev.styleabletoast

import android.content.Context
import android.util.TypedValue

import java.util.Locale

import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat

/**
 * Created by Muddz on 07-03-2018.
 */

internal object Utils {

    val isRTL: Boolean
        get() = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL

    fun toDp(context: Context, value: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), context.resources.displayMetrics).toInt()
    }

}
