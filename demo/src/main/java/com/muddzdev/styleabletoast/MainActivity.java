package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.os.Bundle;
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

//                new StyleableToast
//                        .Builder(this)
//                        .text("Turn off fly mode")
//                        .textColor(Color.WHITE)
//                        .backgroundColor(Color.parseColor("#63b175"))
//                        .show();

                StyleableToast.makeText(this, "Turn off fly mode", Toast.LENGTH_LONG, R.style.StyleableToast).show();
                break;

            case R.id.button2:

                new StyleableToast
                        .Builder(this)
                        .text("السلام عليكم")
                        .stroke(1,Color.YELLOW)
                        .textColor(Color.parseColor("#f2be93"))
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

                //Nodes pink

//                new StyleableToast
//                        .Builder(this)
//                        .text("Turn off fly mode")
//                        .textColor(Color.WHITE)
//                        .backgroundColor(Color.parseColor("#ff4d79"))
//                        .show();


                //Soft red:

                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#eb4a59"))
                        .show();

                break;

            case R.id.button4:

                //Facebook blue
//                new StyleableToast
//                        .Builder(this)
//                        .text("Turn off fly mode")
//                        .textColor(Color.WHITE)
//                        .backgroundColor(Color.parseColor("#4267b2"))
//                        .show();


                //Twitter blue
                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#1da1f2"))
                        .show();



                break;

            case R.id.button5:


                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
                        .textColor(Color.parseColor("#87deed"))
                        .backgroundColor(Color.parseColor("#3f4347"))
                        .show();
                break;

            case R.id.button6:
                new StyleableToast
                        .Builder(this)
                        .text("Turn off fly mode")
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
