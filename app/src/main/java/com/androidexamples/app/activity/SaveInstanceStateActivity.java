package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.androidexamples.app.R;
import com.androidexamples.app.util.Constants;

public class SaveInstanceStateActivity extends AppCompatActivity {

    // Views
    private Button button;

    // Outros objetos
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_instance_state);
        loadComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setText(String.valueOf(count));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_COUNT, count);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null)
            count = savedInstanceState.getInt(Constants.KEY_COUNT, count);
        else
            count = 0;

    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = (Button)findViewById(R.id.button_send_message_handler);
        button.setOnClickListener(onButtonClick());
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText(String.valueOf(++count));
            }
        };
    }
}
