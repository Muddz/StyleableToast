package com.muddzdev.styleabletoast.demo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muddzdev.styleabletoast.StyleableToast;
import com.muddzdev.styleabletoast.demo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    String toastMsg = "Hello World!";
    int redColor = Color.parseColor("#FF5A5F");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(this);

    }

    public void coloredBackground() {
        StyleableToast.Builder st = new StyleableToast.Builder(this)
                .text(toastMsg)
                .backgroundColor(redColor)
                .build();

        st.show();
    }

    public boolean coloredBackgroundXML() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredBackground).show();
        return true;
    }


    public void coloredText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(redColor)
                .show();
    }

    public boolean coloredTextXML() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredText).show();
        return true;
    }


    public void coloredBoldText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(redColor)
                .textBold()
                .show();
    }

    public boolean coloredBoldTextXML() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredBoldText).show();
        return true;
    }


    public void customFont() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .font(R.font.dosis)
                .show();
    }

    public boolean customFontXML() {
        StyleableToast.makeText(this, toastMsg, R.style.CustomFont).show();
        return true;
    }

    public void cornerRadius() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .cornerRadius(5)
                .show();
    }

    public boolean cornerRadiusXML() {
        StyleableToast.makeText(this, toastMsg, R.style.CornerRadius5Dp).show();
        return true;
    }

    public void iconStart() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(getIcon())
                .show();
    }

    public boolean iconStartXML() {
        StyleableToast.makeText(this, toastMsg, R.style.IconStart).show();
        return true;
    }


    public void iconEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconEnd(getIcon())
                .show();
    }

    public boolean iconEndXML() {
        StyleableToast.makeText(this, toastMsg, R.style.IconEnd).show();
        return true;
    }


    public void iconStartEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(getIcon())
                .iconEnd(getIcon())
                .show();
    }

    public boolean iconStartEndXML() {
        StyleableToast.makeText(this, toastMsg, R.style.IconStartEnd).show();
        return true;
    }

    public void coloredStroke() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, redColor)
                .show();
    }

    public boolean coloredStrokeXML() {
        StyleableToast.makeText(this, toastMsg, R.style.ColoredStroke).show();
        return true;
    }

    public void allStyles() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, Color.BLACK)
                .backgroundColor(Color.WHITE)
                .solidBackground()
                .textColor(Color.RED)
                .textBold()
                .font(R.font.dosis)
                .iconStart(getIcon())
                .iconEnd(getIcon())
                .cornerRadius(12)
                .textSize(18)
                .show();
    }

    public boolean allStylesXML() {
        StyleableToast.makeText(this, toastMsg, R.style.AllStyles).show();
        return true;
    }

    public int getIcon() {
        if (android.os.Build.VERSION.SDK_INT >= 27) {
            return R.drawable.ic_autorenew_black_24dp;
        } else {
            return R.drawable.ic_autorenew_white_24dp;
        }
    }

}
