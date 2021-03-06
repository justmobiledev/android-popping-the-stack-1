package com.justmobiledev.androidpoppingstack1;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyActivity extends BaseActivity {

    TextView taskInfoTextView, textViewActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        taskInfoTextView = findViewById(R.id.textview_task_info);
        textViewActivityId = findViewById(R.id.textview_activity_id);

        // Get info from intent
        Intent intent = getIntent();
        int activityIndex = intent.getIntExtra(Constants.KEY_ACTIVITY_INDEX, 0);
        int taskIndex = intent.getIntExtra(Constants.KEY_TASK_INDEX, 0);
        String activityName = "Activity "+activityIndex+", Task "+taskIndex;
        setTitle(activityName);

        textViewActivityId.setText(activityName);

        // Start  next Activity
        activityIndex = --activityIndex;
        if (activityIndex > 0){
            Intent nextIntent = new Intent(this, MyActivity.class);
            nextIntent.putExtra(Constants.KEY_ACTIVITY_INDEX, activityIndex);
            nextIntent.putExtra(Constants.KEY_TASK_INDEX, taskIndex);
            nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(nextIntent);
        }

        // Add clear button listener
        Button clearTopButton = findViewById(R.id.button_clear_top);
        clearTopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popStackFlag = true;

                Intent intent = new Intent(MyActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                // Finish current activity
                finish();
            }
        });

        showTasks();

    }

    private void showTasks(){
        StringBuilder builder = new StringBuilder();

        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(100);
        for (int i=0; i < taskInfo.size(); i++){
            String topActivityName = taskInfo.get(i).topActivity.getClassName();
            String baseActivityName = taskInfo.get(i).baseActivity.getClassName();
            builder.append("Task: "+i+"\n");
            builder.append("Top Activity: "+topActivityName+"\n");
            builder.append("Base Activity: "+baseActivityName+"\n");
            builder.append("-------------\n");
        }
        taskInfoTextView.setText(builder.toString());
    }
}
