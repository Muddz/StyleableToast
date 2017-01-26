package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void Toaster(View v) {
        StyleableToast st;

        switch (v.getId()) {
            case R.id.button1:

                st = new StyleableToast(this, "Updating profile", Toast.LENGTH_SHORT);
                st.setBackgroundColor(Color.parseColor("#ff5a5f"));
                st.setTextColor(Color.WHITE);
                st.setIcon(R.drawable.ic_autorenew_black_24dp);
                st.spinIconAnimation();
                st.setAlpha(StyleableToast.MAX_VISIBILTY);
                st.show();

                break;


            case R.id.button2:


                st = new StyleableToast(this, "Hello World!", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#5ab2ff"));
                st.setToastStroke(3, Color.parseColor("#0065bf"));
                st.setBoldText();
                st.setIcon(R.drawable.ic_error_black_24dp);
                st.setAlpha(StyleableToast.MAX_VISIBILTY);
                st.show();
                break;

            case R.id.button3:

                st = new StyleableToast(this, "Battery needs charge!", Toast.LENGTH_LONG);
                st.setIcon(R.drawable.ic_battery_20_black_24dp);
                st.setBackgroundColor(Color.DKGRAY);

                st.show();

                break;

            case R.id.button4:

                st = new StyleableToast(this.getApplicationContext(), "PHONE IS OVERWHEATING!", Toast.LENGTH_LONG);
                st.setCornerRadius(5);
                st.setBackgroundColor(Color.parseColor("#db1411"));
                st.setTextColor(Color.BLACK);
                st.setBoldText();
                st.show();

                break;

            case R.id.button5:

                st = new StyleableToast(this, "Saving profile", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#645abc"));
                st.setTextColor(Color.WHITE);
                st.setCornerRadius(35);
                st.show();

                break;

            case R.id.button6:

                st = new StyleableToast(this, "Logging out", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#242148"));
                st.setTextColor(Color.WHITE);
                st.setCornerRadius(3);
                st.show();
                break;
        }


    }
}
