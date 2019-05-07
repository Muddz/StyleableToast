package com.muddzdev.styleabletoast.demo

import android.graphics.Color
import android.os.Bundle

import com.muddzdev.styleabletoast.StyleableToast

import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnLongClick


class MainActivity : AppCompatActivity() {

    private var toastMsg = "Hello World!"
    private var redColor = Color.parseColor("#FF5A5F")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    //--------------------------------------------------

    @OnClick(R.id.b1)
    fun coloredBackground() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .backgroundColor(redColor)
                .show()
    }

    @OnLongClick(R.id.b1)
    fun coloredBackgroundStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredBackground).show()
        return true
    }

    //--------------------------------------------------

    @OnClick(R.id.b2)
    fun coloredText() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(redColor)
                .show()
    }

    @OnLongClick(R.id.b2)
    fun coloredTextStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredText).show()
        return true
    }

    //--------------------------------------------------

    @OnClick(R.id.b3)
    fun coloredBoldText() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(redColor)
                .textBold()
                .show()
    }

    @OnLongClick(R.id.b3)
    fun coloredBoldTextStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredBoldText).show()
        return true
    }


    //--------------------------------------------------

    @OnClick(R.id.b4)
    fun customFont() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .font(R.font.dosis)
                .show()
    }


    @OnLongClick(R.id.b4)
    fun customFontStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.CustomFont).show()
        return true
    }

    //--------------------------------------------------

    @OnClick(R.id.b5)
    fun cornerRadius5dp() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .cornerRadius(5)
                .show()
    }


    @OnLongClick(R.id.b5)
    fun cornerRadius5dpStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.CornerRadius5Dp).show()
        return true
    }

    //--------------------------------------------------


    @OnClick(R.id.b6)
    fun iconStart() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_autorenew_black_24dp)
                .show()
    }

    @OnLongClick(R.id.b6)
    fun iconStartStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.IconStart).show()
        return true
    }

    //--------------------------------------------------

    @OnClick(R.id.b7)
    fun iconEnd() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .iconEnd(R.drawable.ic_autorenew_black_24dp)
                .show()
    }


    @OnLongClick(R.id.b7)
    fun iconEndStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.IconEnd).show()
        return true
    }


    //--------------------------------------------------

    @OnClick(R.id.b8)
    fun iconStartEnd() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_autorenew_black_24dp)
                .iconEnd(R.drawable.ic_autorenew_black_24dp)
                .show()
    }


    @OnLongClick(R.id.b8)
    fun iconStartEndStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.IconStartEnd).show()
        return true
    }

    //--------------------------------------------------


    @OnClick(R.id.b9)
    fun coloredStroke() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, redColor)
                .show()
    }

    @OnLongClick(R.id.b9)
    fun coloredStrokeStyle(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredStroke).show()
        return true
    }


    //--------------------------------------------------

    @OnClick(R.id.b10)
    fun allStyles() {
        StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, Color.CYAN)
                .backgroundColor(Color.BLACK)
                .solidBackground()
                .textColor(Color.YELLOW)
                .textBold()
                .font(R.font.dosis)
                .iconStart(R.drawable.ic_autorenew_black_24dp)
                .iconEnd(R.drawable.ic_autorenew_black_24dp)
                .cornerRadius(12)
                .textSize(18f)
                .show()
    }


    @OnLongClick(R.id.b10)
    fun allStylesStyles(): Boolean {
        StyleableToast.makeText(this, toastMsg, R.style.AllStyles).show()
        return true
    }
}
