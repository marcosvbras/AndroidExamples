package com.androidexamples.app.activity

import android.graphics.Bitmap
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.utils.ImageHelper

class ReDownloadImage : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private val handler = Handler()
    private var imageView: ImageView? = null
    private var count: Int = 0

    private val runnableDownload: Runnable
        get() = Runnable { downloadImage() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_download_image)
        loadComponents()
        count = 0
        downloadImage()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
    }

    private fun downloadImage() {
        imageView!!.setImageBitmap(null)
        progressBar!!.visibility = View.VISIBLE

        object : Thread() {
            override fun run() {
                val bitmap = ImageHelper.downloadBitmap(URL)

                atualizaImagem(bitmap)
                // Agenda o download novamente (daqui a 2 seg)
                handler.postDelayed(runnableDownload, 2000)
            }
        }.start()
    }

    private fun atualizaImagem(imagem: Bitmap?) {
        runOnUiThread {
            Toast.makeText(baseContext, resources.getString(R.string.image_downloaded)
                    + " " + ++count + " times", Toast.LENGTH_SHORT).show()
            progressBar!!.visibility = View.INVISIBLE
            imageView!!.setImageBitmap(imagem)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancela o runnable ao sair da activity
        handler.removeCallbacksAndMessages(null)
    }

    companion object {

        private val URL = "http://noragami-anime.net/index/img/bg_blu-ray01.jpg"
    }
}
