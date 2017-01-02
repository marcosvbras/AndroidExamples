package com.androidexamples.app.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidexamples.app.R;
import com.androidexamples.app.util.ImageHelper;

import java.io.IOException;

public class ReDownloadImage extends AppCompatActivity {

    private static final String URL = "http://noragami-anime.net/index/img/bg_blu-ray01.jpg";
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private ImageView imageView;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_download_image);
        loadComponents();
        count = 0;
        downloadImage();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void downloadImage() {
        imageView.setImageBitmap(null);
        progressBar.setVisibility(View.VISIBLE);

        new Thread() {
            @Override
            public void run() {
                final Bitmap bitmap = ImageHelper.downloadBitmap(URL);

                atualizaImagem(bitmap);
                // Agenda o download novamente (daqui a 2 seg)
                handler.postDelayed(getRunnableDownload(), 2000);
            }
        }.start();
    }

    private Runnable getRunnableDownload() {
        return new Runnable() {
            @Override
            public void run() {
                downloadImage();
            }
        };
    }

    private void atualizaImagem(final Bitmap imagem) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.image_downloaded)
                        + " " + (++count) + " times", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageBitmap(imagem);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancela o runnable ao sair da activity
        handler.removeCallbacksAndMessages(null);
    }
}
