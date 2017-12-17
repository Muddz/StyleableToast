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
                StyleableToast.makeText(this, "Uploading image", R.style.empty).show();
                break;

            case R.id.button2:
                StyleableToast.makeText(this, "Image saved", Toast.LENGTH_LONG, R.style.style2).show();
                break;

            case R.id.button3:

                new StyleableToast
                        .Builder(this)
                        .text("Shopping cart updated")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#ff4d79"))
                        .show();

                break;

            case R.id.button4:

                int strokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, getResources().getDisplayMetrics());

                new StyleableToast
                        .Builder(this)
                        .text("5 Tasks completed")
                        .textColor(Color.WHITE)
                        .solidBackground()
                        .stroke(strokeWidth, Color.parseColor("#50627c"))
                        .typeface(Typeface.createFromAsset(getAssets(), "fonts/arvo.ttf"))
                        .backgroundColor(Color.parseColor("#586d8b"))
                        .show();


                break;

            case R.id.button5:
                int strokeWidth2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, getResources().getDisplayMetrics());
                new StyleableToast
                        .Builder(this)
                        .text("Donation sent")
                        .stroke(strokeWidth2, Color.parseColor("#6441a4"))
                        .textColor(Color.parseColor("#6441a4"))
                        .backgroundColor(Color.parseColor("#e8e8e8"))
                        .show();
                break;

            case R.id.button6:

                new StyleableToast
                        .Builder(this)
                        .iconResLeft(R.drawable.ic_cloud_upload_black_24dp)
                        .text("Uploading")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#6441a4"))
                        .show();
                break;
        }

        if (styleableToast != null) {
            styleableToast.show();
            styleableToast = null;
        }
    }
}
