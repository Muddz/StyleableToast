package com.muddzdev.styleabletoast.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class MainActivity extends AppCompatActivity {

    String toastMsg = "Hello World!";
    int redColor = Color.parseColor("#FF5A5F");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //--------------------------------------------------

    @OnClick(R.id.b1)
    public void coloredBackground() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .backgroundColor(redColor)
                .show();
    }

    @OnLongClick(R.id.b1)
    public boolean coloredBackgroundStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredBackground).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b2)
    public void coloredText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(redColor)
                .show();
    }

    @OnLongClick(R.id.b2)
    public boolean coloredTextStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredText).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b3)
    public void coloredBoldText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(redColor)
                .textBold()
                .show();
    }

    @OnLongClick(R.id.b3)
    public boolean coloredBoldTextStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredBoldText).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b4)
    public void customFont() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .font(R.font.dosis)
                .show();
    }


    @OnLongClick(R.id.b4)
    public boolean customFontStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.CustomFont).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b5)
    public void cornerRadius5dp() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .cornerRadius(5)
                .show();
    }


    @OnLongClick(R.id.b5)
    public boolean cornerRadius5dpStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.CornerRadius5Dp).show();
        return true;
    }

    //--------------------------------------------------


    @OnClick(R.id.b6)
    public void iconStart() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_autorenew_black_24dp)
                .show();
    }

    @OnLongClick(R.id.b6)
    public boolean iconStartStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.IconStart).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b7)
    public void iconEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconEnd(R.drawable.ic_autorenew_black_24dp)
                .show();
    }


    @OnLongClick(R.id.b7)
    public boolean iconEndStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.IconEnd).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b8)
    public void iconStartEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_autorenew_black_24dp)
                .iconEnd(R.drawable.ic_autorenew_black_24dp)
                .show();
    }


    @OnLongClick(R.id.b8)
    public boolean iconStartEndStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.IconStartEnd).show();
        return true;
    }

    //--------------------------------------------------


    @OnClick(R.id.b9)
    public void coloredStroke() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, redColor)
                .show();
    }

    @OnLongClick(R.id.b9)
    public boolean coloredStrokeStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredStroke).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b10)
    public void allStyles() {
        new StyleableToast.Builder(this)
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
                .textSize(18)
                .show();
    }


    @OnLongClick(R.id.b10)
    public boolean allStylesStyles() {
        StyleableToast.makeText(this, toastMsg, R.style.AllStyles).show();
        return true;
    }
}
