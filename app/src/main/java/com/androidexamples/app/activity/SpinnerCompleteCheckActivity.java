package com.androidexamples.app.activity;

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

import com.androidexamples.app.R;
import com.androidexamples.app.domain.SimpleItem;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCompleteCheckActivity extends AppCompatActivity {

    // Views
    private AutoCompleteTextView autoCompleteTextView;
    private CheckBox check1, check2, check3, check4, check5;
    private Spinner spinner;

    // Outros Objetos
    private List<SimpleItem> listSimpleItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_complete_check);
        loadComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
        ArrayAdapter<SimpleItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listSimpleItems);
        spinner.setAdapter(adapter);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.clearFocus();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoComplete);
        autoCompleteTextView.setOnItemClickListener(onAutoCompleteTextViewItemClick());
        check1 = (CheckBox)findViewById(R.id.checkbox1);
        check1.setOnClickListener(onCheckStar());
        check2 = (CheckBox)findViewById(R.id.checkbox2);
        check2.setOnClickListener(onCheckStar());
        check3 = (CheckBox)findViewById(R.id.checkbox3);
        check3.setOnClickListener(onCheckStar());
        check4 = (CheckBox)findViewById(R.id.checkbox4);
        check4.setOnClickListener(onCheckStar());
        check5 = (CheckBox)findViewById(R.id.checkbox5);
        check5.setOnClickListener(onCheckStar());
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(onSpinnerItemSelected());
    }

    private void populateList() {
        listSimpleItems = new ArrayList<>();
        listSimpleItems.add(new SimpleItem("Arryn", R.drawable.arryn));
        listSimpleItems.add(new SimpleItem("Baratheon", R.drawable.baratheon));
        listSimpleItems.add(new SimpleItem("Greyjoy", R.drawable.greyjoy));
        listSimpleItems.add(new SimpleItem("Lannister", R.drawable.lannister));
        listSimpleItems.add(new SimpleItem("Martell", R.drawable.martell));
        listSimpleItems.add(new SimpleItem("Stark", R.drawable.stark));
        listSimpleItems.add(new SimpleItem("Targaryan", R.drawable.targaryen));
        listSimpleItems.add(new SimpleItem("Tully", R.drawable.tully));
        listSimpleItems.add(new SimpleItem("Tyrell", R.drawable.tyrell));
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

    private AdapterView.OnItemClickListener onAutoCompleteTextViewItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SimpleItem simpleItem = (SimpleItem) adapterView.getSelectedItem();
                Toast.makeText(getBaseContext(), simpleItem.getName() + " selected!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private AdapterView.OnItemSelectedListener onSpinnerItemSelected() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SimpleItem simpleItem = (SimpleItem) adapterView.getSelectedItem();
                Toast.makeText(getBaseContext(), simpleItem.getName() + " selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
    }
}
