package com.androidexamples.app.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.androidexamples.app.R;
import com.androidexamples.app.utils.Constants;

public class HandlerMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_message);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_send_message_handler).setOnClickListener(onSendMessageHandlerButtonClick());
        findViewById(R.id.button_send_message_runnable).setOnClickListener(onSendMessageRunnableButtonClick());
    }

    private View.OnClickListener onSendMessageRunnableButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.message_arrived), Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        };
    }

    private View.OnClickListener onSendMessageHandlerButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler testHandler = new TestHandler();
                Message message = new Message();
                message.what = Constants.TEST_MESSAGE;
                testHandler.sendMessageDelayed(message, 3000);
            }
        };
    }

    public class TestHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            // O atributo message.what permite identificar a mensagem
            switch(message.what) {
                case Constants.TEST_MESSAGE:
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.message_arrived), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
