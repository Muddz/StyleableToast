package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    private StyleableToast styleableToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Toaster(View v) {
        switch (v.getId()) {
            case R.id.button1:

                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.parseColor("#feda44"))
                        .backgroundColor(Color.parseColor("#06301a"))
                        .show();

//                StyleableToast.makeText(this, "Turn off fly mode", Toast.LENGTH_LONG, R.style.StyleableToast).show();
                break;

            case R.id.button2:

                new StyleableToast
                        .Builder(this)

                        .text("Turn off fly mode")
                        .textColor(Color.parseColor("#e7ba95"))
                        .backgroundColor(Color.parseColor("#721C47"))
                        .show();


//                styleableToast = new StyleableToast
//                        .Builder(this)
//                        .text("New update available")
//                        .textColor(Color.WHITE)
//                        .icon(R.drawable.ic_file_download)
//                        .backgroundColor(Color.parseColor("#23ad33"))
//                        .build();
                break;

            case R.id.button3:

                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#FF5A5F"))
                        .show();
                break;

            case R.id.button4:
                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#3575d3"))
                        .show();

                break;

            case R.id.button5:




                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#ff4d79"))
                        .show();
                break;

            case R.id.button6:
                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.BLUE)
                        .show();
                break;
        }

        if (styleableToast != null) {
            styleableToast.show();
            styleableToast = null;
        }
    }
}
