package com.example.marcos.androidexamples.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.marcos.androidexamples.R;

import java.io.InputStream;
import java.net.URL;

public class ViewsLayoutActivity extends AppCompatActivity {

    // Views

    private Button buttonGetImage;
    private ProgressDialog progressDialog;
    private ImageView imageView;

    // Outros objetos
    private final String URL_ANIME = "https://static.hummingbird.me/anime/poster_images/000/003/021/large/bYWVjy9.jpg?1419353531";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_layout);
        LoadComponents();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_webview).setOnClickListener(onWebViewButtonClick());
        findViewById(R.id.button_gridview).setOnClickListener(onGridViewButtonClick());
        findViewById(R.id.button_xml_selectors).setOnClickListener(onXmlSelectorsClick());
        findViewById(R.id.button_drawing_shapes).setOnClickListener(onDrawingShapesButtonClick());
        findViewById(R.id.button_image_switcher).setOnClickListener(onImageSwitcherButtonClick());
        findViewById(R.id.button_recycler_view).setOnClickListener(onRecyclerViewButtonClick());
        findViewById(R.id.button_spinner_complete_check).setOnClickListener(onSpinnerCompleteCheckButtonClick());

        // Button
        buttonGetImage = (Button)findViewById(R.id.button_get_image);
        buttonGetImage.setOnClickListener(onButtonGetImageClick());
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    private View.OnClickListener onSpinnerCompleteCheckButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SpinnerCompleteCheckActivity.class));
            }
        };
    }

    private View.OnClickListener onRecyclerViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RecyclerViewActivityListener.class));
            }
        };
    }

    private View.OnClickListener onImageSwitcherButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ImageSwitcherActivity.class));
            }
        };
    }

    private View.OnClickListener onDrawingShapesButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), DrawingShapesActivity.class));
            }
        };
    }

    private View.OnClickListener onWebViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), WebViewActivity.class));
            }
        };
    }

    private View.OnClickListener onGridViewButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), GridViewActivity.class));
            }
        };
    }

    private View.OnClickListener onXmlSelectorsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), XmlSelectorsActivity.class));
            }
        };
    }

    private View.OnClickListener onButtonGetImageClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage(URL_ANIME);
            }
        };
    }

    private void downloadImage(final String urlImg) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.dialog_download);
        progressDialog.setMessage(getResources().getString(R.string.dialog_wait));
        progressDialog.setIndeterminate(true);

        //progressDialog.show();

        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlImg);
                    InputStream inputStream = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    showImage(bitmap);
                } catch (Exception ex) {

                }
            }
        }.run();
    }

    private void showImage(final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //progressDialog.dismiss();
                imageView.setImageBitmap(bitmap);
                bitmap.recycle();
            }
        });
    }
}
