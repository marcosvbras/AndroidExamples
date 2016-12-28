package com.example.marcos.androidexamples.app.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.fragment.HelloFragment1;
import com.example.marcos.androidexamples.app.fragment.HelloFragment2;

public class HelloWorldFragmentActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HelloFragment2 fragment2;
    private HelloFragment1 fragment1;
    private final String FRAGMENT_TAG = "fragTag";
    public static final String TEXT_KEY = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world_fragment);
        LoadComponents();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void LoadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_first_fragment).setOnClickListener(onShowFirstFragmentButtonClick());
        findViewById(R.id.button_second_fragment).setOnClickListener(onShowSecondFragmentButtonClick());
    }

    private View.OnClickListener onShowFirstFragmentButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1 = (HelloFragment1)fragmentManager.findFragmentById(R.id.frag1);
                fragment1.setText(getResources().getString(R.string.hello_blank_fragment_id));
            }
        };
    }

    private View.OnClickListener onShowSecondFragmentButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment2 = (HelloFragment2)fragmentManager.findFragmentByTag(FRAGMENT_TAG);

                if(fragment2 == null) {
                    fragment2 = new HelloFragment2();
                    Bundle bundle = new Bundle();
                    bundle.putString(TEXT_KEY, getBaseContext().getResources().getString(R.string.hello_blank_fragment_tag));
                    fragment2.setArguments(bundle);
                    fragmentTransaction.add(R.id.fragmentContainer, fragment2, FRAGMENT_TAG);
                    fragmentTransaction.commit();
                }
            }
        };
    }
}
