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
                        .textColor(Color.BLACK)
                        .duration(Toast.LENGTH_LONG)
                        .backgroundColor(ContextCompat.getColor(this, R.color.purple))
                        .iconResLeft(R.drawable.ic_airplanemode_inactive_black_24dp)
                        .show();

//                StyleableToast.makeText(this, "Turn off fly mode", Toast.LENGTH_LONG, R.style.StyleableToast).show();
                break;

            case R.id.button2:

                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .backgroundColor(ContextCompat.getColor(this, R.color.purple))
                        .show();

//                StyleableToast styleableToasst = new StyleableToast
//                        .Builder(this)
//                        .text("Turn off fly mode")
//                        .textColor(Color.WHITE)
//                        .duration(Toast.LENGTH_LONG)
//                        .backgroundColor(ContextCompat.getColor(this, R.color.purple))
//                        .icon(R.drawable.ic_overheating)
//                        .build();
//                styleableToasst.show();


//                styleableToast = new StyleableToast
//                        .Builder(this)
//                        .text("New update available")
//                        .textColor(Color.WHITE)
//                        .icon(R.drawable.ic_file_download)
//                        .backgroundColor(Color.parseColor("#23ad33"))
//                        .build();
                break;

            case R.id.button3:

//                StyleableToast styleableToastt = new StyleableToast
//                        .Builder(this)
//                        .text("Turn off fly mode")
//                        .textColor(Color.WHITE)
//                        .duration(Toast.LENGTH_LONG)
//                        .backgroundColor(ContextCompat.getColor(this, R.color.purple))
//                        .icon(R.drawable.ic_file_download)
//                        .build();
//                styleableToastt.show();

//                Toast.makeText(this, "Turn off fly mode", Toast.LENGTH_SHORT).show();
//
//                styleableToast = new StyleableToast
//                        .Builder(this)
//                        .duration(Toast.LENGTH_LONG)
//                        .text("Your collection has been updated")
//                        .textColor(Color.WHITE)
//                        .typeface(Typeface.createFromAsset(getAssets(), "fonts/dosis.otf"))
//                        .backgroundColor(Color.parseColor("#cc3784"))
//                        .build();
                break;

            case R.id.button4:
//                styleableToast = new StyleableToast
//                        .Builder(this)
//                        .text("Thank you for your health donation!")
//                        .textColor(Color.parseColor("#6063b2"))
////                        .stroke(2)
//                        .duration(Toast.LENGTH_LONG)
////                        .strokeColor(Color.parseColor("#989ad1"))
//                        .backgroundColor(Color.WHITE)
//                        .build();

                break;

            case R.id.button5:
//                styleableToast = new StyleableToast
//                        .Builder(this)
//                        .icon(R.drawable.ic_overheating)
//                        .text("Phone is overheating!")
//                        .textBold()
//                        .textColor(Color.parseColor("#FFDA44"))
//                        .cornerRadius(5)
//                        .build();
                break;

            case R.id.button6:
//                styleableToast = new StyleableToast
//                        .Builder(this)
//                        .duration(Toast.LENGTH_LONG)
//                        .icon(R.drawable.ic_autorenew_black_24dp)
//                        .spinIcon()
//                        .text("Downloading your information")
//                        .textColor(Color.WHITE)
//                        .backgroundColor(Color.parseColor("#184c6d"))
//                        .build();
                break;
        }

        if (styleableToast != null) {
            styleableToast.show();
            styleableToast = null;
        }
    }
}
