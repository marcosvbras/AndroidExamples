package com.androidexamples.app.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidexamples.app.R;
import com.androidexamples.app.interfaces.ImageDownloadListener;
import com.androidexamples.app.utils.DownloadImageTask;

import java.util.List;

public class AsyncTaskActivity extends AppCompatActivity implements ImageDownloadListener {

    // Views
    private ProgressBar progressBar;
    private DownloadImageTask downloadImageTask;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    // Another objects
    private static final String URL1 = "http://noragami-anime.net/index/img/bg_blu-ray01.jpg";
    private static final String URL2 = "http://noragami-anime.net/index/img/bg_blu-ray02.jpg";
    private static final String URL3 = "http://noragami-anime.net/index/img/bg_blu-ray03.jpg";
    private static final String URL4 = "http://noragami-anime.net/index/img/bg_blu-ray04.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        findViewById(R.id.button_start).setOnClickListener(onStartButtonClick());
        findViewById(R.id.button_stop).setOnClickListener(onStopButtonClick());
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        newTask();
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
                    downloadImageTask.execute(URL1, URL2, URL3, URL4);
                } else if(downloadImageTask.getStatus() == AsyncTask.Status.FINISHED) {
                    newTask();
                    downloadImageTask.execute(URL1, URL2, URL3, URL4);
                } else
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.task_already_started), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void newTask() {
        downloadImageTask = new DownloadImageTask(progressBar, this, this);
        imageView1.setImageBitmap(null);
        imageView2.setImageBitmap(null);
        imageView3.setImageBitmap(null);
        imageView4.setImageBitmap(null);
        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    @Override
    public void onDownloadFinish(List<Bitmap> listBitmap) {
        if(listBitmap != null) {
            imageView1.setImageBitmap(listBitmap.get(0));
            imageView2.setImageBitmap(listBitmap.get(1));
            imageView3.setImageBitmap(listBitmap.get(2));
            imageView4.setImageBitmap(listBitmap.get(3));
        } else {
            Toast.makeText(this, getResources().getString(R.string.failed_download), Toast.LENGTH_SHORT).show();
        }
    }
}
