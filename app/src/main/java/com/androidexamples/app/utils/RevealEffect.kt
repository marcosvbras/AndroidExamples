package com.androidexamples.app.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils

/**
 * Created by marcos on 01/01/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
object RevealEffect {

    @JvmOverloads
    fun show(view: View, animDuration: Long, listener: Animator.AnimatorListener? = null) {
        // Centro da view
        val cx = (view.left + view.right) / 2
        val cy = (view.top + view.bottom) / 2

        // Define o arco para a animação
        val finalRadius = Math.max(view.width, view.height)

        // Cria a animação
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())

        if (listener != null) {
            anim.addListener(listener)
        } else {
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    //view.setVisibility(View.VISIBLE);
                }
            })
        }

        // Inicia a animação
        view.visibility = View.VISIBLE
        anim.duration = animDuration
        anim.start()
    }

    @JvmOverloads
    fun hide(view: View, animDuration: Long, listener: Animator.AnimatorListener? = null) {
        // Centro da view
        val cx = (view.left + view.right) / 2
        val cy = (view.top + view.bottom) / 2

        // Define o arco para a animação
        val initialRadius = view.width

        // Cria a animação
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius.toFloat(), 0f)

        // Quando a animação terminar, esconde a view
        if (listener == null) {
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.INVISIBLE
                }
            })
        } else {
            anim.addListener(listener)
        }

        // Inicia a animação
        anim.duration = animDuration
        anim.start()
    }
}
