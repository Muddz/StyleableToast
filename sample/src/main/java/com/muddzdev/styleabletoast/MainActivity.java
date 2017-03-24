package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    private StyleableToast st, stBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        stBuilder = new StyleableToast
//                .Builder(this, "Turn off fly mode")
//                .withBackgroundColor(Color.RED)
//                .withTextColor(Color.WHITE)
//                .withBoldText()
//                .build();

    }


    public void Toaster(View v) {
        switch (v.getId()) {
            case R.id.button1:

                st = new StyleableToast(this, "Updating profile", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#ff5a5f"));
                st.setTextColor(Color.WHITE);
                st.setIcon(R.drawable.ic_autorenew_black_24dp);
                st.spinIcon();
                st.setMaxAlpha();
                st.show();

                break;

            case R.id.button2:

//                stBuilder.show();

                StyleableToast st = new StyleableToast(this, "Turn off fly mode", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#865aff"));
                st.setIcon(R.drawable.ic_airplanemode_inactive_black_24dp);
                st.setMaxAlpha();
                st.show();


                break;

            case R.id.button3:

                st = new StyleableToast(this, "Profile saved", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#3b5998"));
                st.setMaxAlpha();
                st.setTextFont(Typeface.createFromAsset(getAssets(), "fonts/dosis.otf"));
                st.show();

                break;

            case R.id.button4:

                st = new StyleableToast(this.getApplicationContext(), "PHONE IS OVERHEATING!", Toast.LENGTH_LONG);
                st.setCornerRadius(5);
                st.setBackgroundColor(Color.BLACK);
                st.setTextColor(Color.RED);
                st.setBoldText();
                st.show();

                break;

            case R.id.button5:

                StyleableToast.makeText(this, "Picture saved in gallery", Toast.LENGTH_LONG, R.style.StyleableToast).show();

                break;

            case R.id.button6:

                st = new StyleableToast(this, "Wrong password/username", Toast.LENGTH_LONG);
                st.setBackgroundColor(Color.parseColor("#2187c6"));
                st.setBoldText();
                st.setTextColor(Color.WHITE);
                st.setCornerRadius(7);
                st.show();
                break;
        }
    }
}
