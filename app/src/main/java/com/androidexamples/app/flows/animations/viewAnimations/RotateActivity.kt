package com.androidexamples.app.flows.animations.viewAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup

import com.androidexamples.app.R

class RotateActivity : AppCompatActivity() {

    // Views
    private var imageView: ImageView? = null
    private var editTextAngle: EditText? = null
    private var buttonReverse: Button? = null
    private var radioGroup: RadioGroup? = null

    // Another objects
    private var fromDegress: Float = 0.toFloat()
    private var angle: Int = 0
    private var pivotXType: Int = 0
    private var pivotXValue: Float = 0.toFloat()
    private var pivotYType: Int = 0
    private var pivotYValue: Float = 0.toFloat()
    private var rotated = false
    private var animationRotate: Animation? = null
    private var animationReverse: Animation? = null
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        editTextAngle = findViewById<View>(R.id.edit_text_angle) as EditText
        radioGroup = findViewById<View>(R.id.radioGroup) as RadioGroup
        radioGroup!!.setOnCheckedChangeListener(onRadioButtonCheck())
        buttonReverse = findViewById<View>(R.id.button_rotate) as Button
        buttonReverse!!.setOnClickListener(onRotateButtonClick())

        pivotXType = Animation.RELATIVE_TO_SELF
        pivotXValue = 0.5f
        pivotYType = Animation.RELATIVE_TO_SELF
        pivotYValue = 0.5f
        fromDegress = 0f
    }

    private fun rotateViaXml() {
        animationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_relative_to_self)
        animationReverse = AnimationUtils.loadAnimation(this, R.anim.reverse_rotate_relative_to_self)
        animation = if (rotated) animationReverse else animationRotate
        imageView!!.animation = animation
    }

    private fun onRadioButtonCheck(): RadioGroup.OnCheckedChangeListener {
        return RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonSelf) {
                pivotXType = Animation.RELATIVE_TO_SELF
                pivotYType = Animation.RELATIVE_TO_SELF
            } else {
                pivotXType = Animation.RELATIVE_TO_PARENT
                pivotYType = Animation.RELATIVE_TO_PARENT
            }
        }
    }

    private fun onRotateButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (!rotated) {
                if (editTextAngle!!.text.toString() != "") {
                    angle = Integer.parseInt(editTextAngle!!.text.toString())

                    if (angle > 0) {
                        animationRotate = RotateAnimation(fromDegress, angle.toFloat(), pivotXType, pivotXValue, pivotYType, pivotYValue)
                        animationReverse = RotateAnimation(angle.toFloat(), 0f, pivotXType, pivotXValue, pivotYType, pivotYValue)
                        animation = animationRotate
                        rotated = !rotated
                        buttonReverse!!.text = resources.getString(R.string.reverse)
                        editTextAngle!!.isEnabled = false
                        animation!!.duration = 1000
                        animation!!.fillAfter = true
                        imageView!!.startAnimation(animation)
                    } else {
                        editTextAngle!!.error = resources.getString(R.string.value_more_zero)
                    }
                } else {
                    editTextAngle!!.error = resources.getString(R.string.value_more_zero)
                }
            } else {
                animation = animationReverse
                rotated = !rotated
                buttonReverse!!.text = resources.getString(R.string.rotate)
                editTextAngle!!.isEnabled = true
                animation!!.duration = 1000
                animation!!.fillAfter = true
                imageView!!.startAnimation(animation)
            }
        }
    }
}
