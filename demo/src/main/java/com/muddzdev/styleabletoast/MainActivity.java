package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.lang.reflect.Type;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

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
                StyleableToast.makeText(this, "Uploading image", R.style.allStyles).show();
                break;

            case R.id.button2:
                StyleableToast.makeText(this, "Image saved", Toast.LENGTH_LONG, R.style.style2).show();
                break;

            case R.id.button3:

                new StyleableToast
                        .Builder(this)
                        .text("Turn off flight mode")
                        .iconResLeft(R.drawable.ic_airplanemode_inactive_black_24dp)
                        .backgroundColor(Color.parseColor("#865aff"))
                        .show();

                break;

            case R.id.button4:

                new StyleableToast
                        .Builder(this)
                        .text("Updating profile")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#ff5a5f"))
                        .iconResLeft(R.drawable.ic_autorenew_black_24dp)
                        .show();


                break;

            case R.id.button5:
                int strokeWidth2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, getResources().getDisplayMetrics());
                new StyleableToast
                        .Builder(this)
                        .text("Milk added to list")
                        .backgroundColor(Color.parseColor("#74ba82"))
                        .show();
                break;

            case R.id.button6:

                int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                int color = Color.parseColor("#c9c9c9");

                new StyleableToast
                        .Builder(this)
                        .text("Picture saved to gallery")
                        .textColor(Color.parseColor("#fc2d3e"))
                        .textBold()
                        .stroke(width,color)
                        .typeface(Typeface.createFromAsset(getAssets(), "fonts/dosis.otf"))
                            .backgroundColor(Color.parseColor("#dddddd"))
                        .show();
                break;
        }

        if (styleableToast != null) {
            styleableToast.show();
            styleableToast = null;
        }
    }
}
