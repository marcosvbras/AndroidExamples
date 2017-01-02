package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
    }

    private void loadComponents() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Click events
        findViewById(R.id.button_animations).setOnClickListener(onButtonClick());
        findViewById(R.id.button_another_views).setOnClickListener(onButtonClick());
        findViewById(R.id.button_fragments).setOnClickListener(onButtonClick());
        findViewById(R.id.button_gestures).setOnClickListener(onButtonClick());
        findViewById(R.id.button_save_instance_state).setOnClickListener(onButtonClick());
        findViewById(R.id.button_view_pager).setOnClickListener(onButtonClick());
        findViewById(R.id.button_toolbar).setOnClickListener(onButtonClick());
        findViewById(R.id.button_handler_async_task).setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_animations:
                        startActivity(new Intent(getBaseContext(), AnimationsActivity.class));
                        break;
                    case R.id.button_another_views:
                        startActivity(new Intent(getBaseContext(), AnotherViewsActivity.class));
                        break;
                    case R.id.button_fragments:
                        startActivity(new Intent(getBaseContext(), FragmentsActivity.class));
                        break;
                    case R.id.button_gestures:
                        startActivity(new Intent(getBaseContext(), GesturesActivity.class));
                        break;
                    case R.id.button_handler_async_task:
                        startActivity(new Intent(getBaseContext(), HandlerAsyncTaskActivity.class));
                        break;
                    case R.id.button_save_instance_state:
                        startActivity(new Intent(getBaseContext(), SaveInstanceStateActivity.class));
                        break;
                    case R.id.button_toolbar:
                        startActivity(new Intent(getBaseContext(), ToolbarActivity.class));
                        break;
                    case R.id.button_view_pager:
                        startActivity(new Intent(getBaseContext(), ViewPagerActivity.class));
                        break;
                }
            }
        };
    }
}
