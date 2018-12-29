package com.justmobiledev.androidpoppingstack1;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public static boolean popStackFlag = false;

    @Override
    protected void onResume() {
        super.onResume();

        if (popStackFlag){
            finish();
        }
    }
}
