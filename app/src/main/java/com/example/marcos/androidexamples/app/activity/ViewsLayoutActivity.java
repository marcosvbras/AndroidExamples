package com.example.marcos.androidexamples.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

import java.io.InputStream;
import java.net.URL;

public class ViewsLayoutActivity extends AppCompatActivity {

    // Views
    private AutoCompleteTextView autoCompleteTextView;
    private CheckBox check1, check2, check3, check4, check5;
    private Spinner spinner;
    private Button buttonGetImage;
    private ProgressDialog progressDialog;
    private ImageView imageView;

    // Outros objetos
    private String[] planets = { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune" };
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

        // Auto Complete
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoComplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, planets);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(onAutoCompleteClick());
        autoCompleteTextView.clearFocus();

        // CheckBox
        check1 = (CheckBox)findViewById(R.id.checkbox1);
        check2 = (CheckBox)findViewById(R.id.checkbox2);
        check3 = (CheckBox)findViewById(R.id.checkbox3);
        check4 = (CheckBox)findViewById(R.id.checkbox4);
        check5 = (CheckBox)findViewById(R.id.checkbox5);
        check1.setOnClickListener(onCheckStar());
        check2.setOnClickListener(onCheckStar());
        check3.setOnClickListener(onCheckStar());
        check4.setOnClickListener(onCheckStar());
        check5.setOnClickListener(onCheckStar());

        // Spinner
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(onSpinnerItemSelected());

        // Button
        buttonGetImage = (Button)findViewById(R.id.button_get_image);
        buttonGetImage.setOnClickListener(onButtonGetImageClick());
        imageView = (ImageView)findViewById(R.id.imageView);
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
                    Toast.makeText(getBaseContext(), "katiau", Toast.LENGTH_SHORT).show();
                    URL url = new URL(urlImg);
                    InputStream inputStream = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    showImage(bitmap);
                    Toast.makeText(getBaseContext(), "katiuga", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {

                }
            }
        }.run();
    }

    private void showImage(final Bitmap bitmap) {
        Toast.makeText(getBaseContext(), "dentro", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "mais dentro", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                imageView.setImageBitmap(bitmap);
                bitmap.recycle();
            }
        });
    }

    private AdapterView.OnItemSelectedListener onSpinnerItemSelected() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = (String)adapterView.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), item + " selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
    }

    private View.OnClickListener onCheckStar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox)view;

                if(checkBox.getId() == R.id.checkbox1) {
                    check1.setChecked(true);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                } else if(checkBox.getId() == R.id.checkbox2) {
                    check1.setChecked(true);
                    check2.setChecked(true);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                } else if(checkBox.getId() == R.id.checkbox3) {
                    check1.setChecked(true);
                    check2.setChecked(true);
                    check3.setChecked(true);
                    check4.setChecked(false);
                    check5.setChecked(false);
                } else if(checkBox.getId() == R.id.checkbox4) {
                    check1.setChecked(true);
                    check2.setChecked(true);
                    check3.setChecked(true);
                    check4.setChecked(true);
                    check5.setChecked(false);
                } else if(checkBox.getId() == R.id.checkbox5) {
                    check1.setChecked(true);
                    check2.setChecked(true);
                    check3.setChecked(true);
                    check4.setChecked(true);
                    check5.setChecked(true);
                }
            }
        };
    }

    private AdapterView.OnItemClickListener onAutoCompleteClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), item + " selected!", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
