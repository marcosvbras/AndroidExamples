package com.androidexamples.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidexamples.app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));

        // Click events
        findViewById(R.id.button_actionbar_toolbar).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_animations).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_another_views).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_broadcast_receiver).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_fragments).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_gestures).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_handler_async_task).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_intents).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_navigation_drawer).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_notifications).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_palette).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_preference_screen).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_save_instance_state).setOnClickListener(onButtonClickListener());
        findViewById(R.id.button_simple_view_pager).setOnClickListener(onButtonClickListener());
    }

    private View.OnClickListener onButtonClickListener() {
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
                    case R.id.button_broadcast_receiver:
                        startActivity(new Intent(getBaseContext(), BroadcastReceiverActivity.class));
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
                    case R.id.button_intents:
                        startActivity(new Intent(getBaseContext(), IntentsActivity.class));
                        break;
                    case R.id.button_save_instance_state:
                        startActivity(new Intent(getBaseContext(), SaveInstanceStateActivity.class));
                        break;
                    case R.id.button_actionbar_toolbar:
                        startActivity(new Intent(getBaseContext(), ActionBarToolbarActivity.class));
                        break;
                    case R.id.button_simple_view_pager:
                        startActivity(new Intent(getBaseContext(), ViewPagerActivity.class));
                        break;
                    case R.id.button_palette:
                        startActivity(new Intent(getBaseContext(), PaletteActivity.class));
                        break;
                    case R.id.button_navigation_drawer:
                        startActivity(new Intent(getBaseContext(), NavigationDrawerActivity.class));
                        break;
                    case R.id.button_notifications:
                        startActivity(new Intent(getBaseContext(), NotificationsActivity.class));
                        break;
                    case R.id.button_preference_screen:
                        startActivity(new Intent(getBaseContext(), PreferenceScreenActivity.class));
                        break;
                }
            }
        };
    }
}
