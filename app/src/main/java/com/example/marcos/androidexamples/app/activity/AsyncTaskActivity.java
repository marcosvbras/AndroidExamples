package com.example.marcos.androidexamples.app.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.interfaces.ImageDownloadListener;
import com.example.marcos.androidexamples.app.util.DownloadImageTask;

public class AsyncTaskActivity extends AppCompatActivity implements ImageDownloadListener {

    // Views
    private ProgressBar progressBar;
    private DownloadImageTask downloadImageTask;

    // Another objects
    private static final String URL = "http://noragami-anime.net/index/img/bg_blu-ray01.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        findViewById(R.id.button_start).setOnClickListener(onStartButtonClick());
        findViewById(R.id.button_stop).setOnClickListener(onStopButtonClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadImageTask = new DownloadImageTask(progressBar, URL, this);
    }

    private View.OnClickListener onStopButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downloadImageTask.getStatus() == AsyncTask.Status.PENDING)
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.task_not_started), Toast.LENGTH_SHORT).show();
                else if(downloadImageTask.getStatus() == AsyncTask.Status.RUNNING)
                    downloadImageTask.cancel(true);
                else
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.task_already_finished), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener onStartButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downloadImageTask.getStatus() == AsyncTask.Status.PENDING) {
                    downloadImageTask.execute();
                } else if(downloadImageTask.getStatus() == AsyncTask.Status.FINISHED) {
                    newTask();
                    downloadImageTask.execute();
                } else
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.task_already_started), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void newTask() {
        downloadImageTask = new DownloadImageTask(progressBar, URL, this);
        ((ImageView)findViewById(R.id.imageView)).setImageBitmap(null);
    }

    @Override
    public void onDownloadFinish(Bitmap bitmap) {
        if(bitmap != null) {
            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bitmap);
//            bitmap.recycle();
        } else {
            Toast.makeText(this, getResources().getString(R.string.failed_download), Toast.LENGTH_SHORT).show();
        }
    }
}
