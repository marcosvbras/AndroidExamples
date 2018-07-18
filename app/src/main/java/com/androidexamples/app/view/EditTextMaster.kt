package com.androidexamples.app.view

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

/**
 * Created by marcos on 06/01/2017.
 */

class EditTextMaster : EditText {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun isRequiredFieldCompleted(errorMessage: String): Boolean {
        if (super.getText().toString().trim { it <= ' ' } != "") {
            return true
        } else {
            super.setError(errorMessage)
            super.requestFocus()
            return false
        }
    }

    fun isNotRequiredFieldCompleted(length: Int, errorMessage: String): Boolean {
        if (super.getText().toString().trim { it <= ' ' }.length == length) {
            return true
        } else {
            super.setError(errorMessage)
            super.requestFocus()
            return false
        }
    }
}
