package com.justmobiledev.androidpoppingstack1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.justmobiledev.androidpoppingstack1.task2.MyActivityTask2;

public class MainActivity extends BaseActivity {

    Button oneTaskStackButton;
    Button twoTaskStackButton;

    public MainActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Uses the current task
        oneTaskStackButton = findViewById(R.id.button_one_task_stack);
        oneTaskStackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity = new Intent(MainActivity.this, MyActivity.class);
                activity.putExtra(Constants.KEY_ACTIVITY_INDEX, 3);
                activity.putExtra(Constants.KEY_TASK_INDEX, 1);
                startActivity(activity);
            }
        });

        // Creates a new task
        twoTaskStackButton = findViewById(R.id.button_two_task_stack);
        twoTaskStackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivityTask2.class);
                intent.putExtra(Constants.KEY_ACTIVITY_INDEX, 3);
                intent.putExtra(Constants.KEY_TASK_INDEX, 2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                // finish this activity to prevent back button pressed
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        popStackFlag = false;

        super.onResume();
    }
}
