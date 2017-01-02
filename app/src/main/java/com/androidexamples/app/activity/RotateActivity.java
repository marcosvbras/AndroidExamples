package com.androidexamples.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidexamples.app.R;

public class RotateActivity extends AppCompatActivity {

    // Views
    private ImageView imageView;
    private EditText editTextAngle;
    private Button buttonReverse;
    private RadioGroup radioGroup;

    // Another objects
    private float fromDegress;
    private int angle;
    private int pivotXType;
    private float pivotXValue;
    private int pivotYType;
    private float pivotYValue;
    private boolean rotated = false;
    private Animation animationRotate;
    private Animation animationReverse;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView)findViewById(R.id.imageView);
        editTextAngle = (EditText)findViewById(R.id.edit_text_angle);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(onRadioButtonCheck());
        buttonReverse = (Button) findViewById(R.id.button_rotate);
        buttonReverse.setOnClickListener(onRotateButtonClick());

        pivotXType = Animation.RELATIVE_TO_SELF;
        pivotXValue = 0.5f;
        pivotYType = Animation.RELATIVE_TO_SELF;
        pivotYValue = 0.5f;
        fromDegress = 0;
    }

    private void rotateViaXml() {
        animationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_relative_to_self);
        animationReverse = AnimationUtils.loadAnimation(this, R.anim.reverse_rotate_relative_to_self);
        animation = rotated ? animationReverse : animationRotate;
        imageView.setAnimation(animation);
    }

    private RadioGroup.OnCheckedChangeListener onRadioButtonCheck() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButtonSelf) {
                    pivotXType = Animation.RELATIVE_TO_SELF;
                    pivotYType = Animation.RELATIVE_TO_SELF;
                } else {
                    pivotXType = Animation.RELATIVE_TO_PARENT;
                    pivotYType = Animation.RELATIVE_TO_PARENT;
                }
            }
        };
    }

    private View.OnClickListener onRotateButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rotated) {
                    if(!editTextAngle.getText().toString().equals("")) {
                        angle = Integer.parseInt(editTextAngle.getText().toString());

                        if(angle > 0) {
                            animationRotate = new RotateAnimation(fromDegress, angle, pivotXType, pivotXValue, pivotYType, pivotYValue);
                            animationReverse = new RotateAnimation(angle, 0, pivotXType, pivotXValue, pivotYType, pivotYValue);
                            animation = animationRotate;
                            rotated = !rotated;
                            buttonReverse.setText(getResources().getString(R.string.reverse));
                            editTextAngle.setEnabled(false);
                            animation.setDuration(1000);
                            animation.setFillAfter(true);
                            imageView.startAnimation(animation);
                        } else {
                            editTextAngle.setError(getResources().getString(R.string.value_more_zero));
                        }
                    } else {
                        editTextAngle.setError(getResources().getString(R.string.value_more_zero));
                    }
                } else {
                    animation = animationReverse;
                    rotated = !rotated;
                    buttonReverse.setText(getResources().getString(R.string.rotate));
                    editTextAngle.setEnabled(true);
                    animation.setDuration(1000);
                    animation.setFillAfter(true);
                    imageView.startAnimation(animation);
                }
            }
        };
    }
}
