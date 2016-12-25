package com.example.marcos.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;

public class SpinnerCompleteCheckActivity extends AppCompatActivity {

    // Views
    private AutoCompleteTextView autoCompleteTextView;
    private CheckBox check1, check2, check3, check4, check5;
    private Spinner spinner;

    // Outros Objetos
    private String[] planets = { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_complete_check);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
}
