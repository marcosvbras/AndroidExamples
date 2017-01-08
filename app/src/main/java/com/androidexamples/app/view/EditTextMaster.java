package com.androidexamples.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by marcos on 06/01/2017.
 */

public class EditTextMaster extends EditText {

    public EditTextMaster(Context context) {
        super(context);
    }

    public EditTextMaster(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextMaster(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isRequiredFieldCompleted(String errorMessage) {
        if(!super.getText().toString().trim().equals("")) {
            return true;
        } else {
            super.setError(errorMessage);
            super.requestFocus();
            return false;
        }
    }

    public boolean isNotRequiredFieldCompleted(int length, String errorMessage) {
        if(super.getText().toString().trim().length() == length) {
            return true;
        } else {
            super.setError(errorMessage);
            super.requestFocus();
            return false;
        }
    }
}
