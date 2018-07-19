package com.androidexamples.app.flows.handlers

import android.graphics.Bitmap
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.interfaces.ImageDownloadListener
import com.androidexamples.app.utils.DownloadImageTask

class AsyncTaskActivity : AppCompatActivity(), ImageDownloadListener {

    // Views
    private var progressBar: ProgressBar? = null
    private var downloadImageTask: DownloadImageTask? = null
    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    private var imageView4: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        findViewById<View>(R.id.button_start).setOnClickListener(onStartButtonClick())
        findViewById<View>(R.id.button_stop).setOnClickListener(onStopButtonClick())
        imageView1 = findViewById<View>(R.id.imageView1) as ImageView
        imageView2 = findViewById<View>(R.id.imageView2) as ImageView
        imageView3 = findViewById<View>(R.id.imageView3) as ImageView
        imageView4 = findViewById<View>(R.id.imageView4) as ImageView
    }

    override fun onResume() {
        super.onResume()
        newTask()
    }

    private fun onStopButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (downloadImageTask!!.status == AsyncTask.Status.PENDING)
                Toast.makeText(baseContext, resources.getString(R.string.task_not_started), Toast.LENGTH_SHORT).show()
            else if (downloadImageTask!!.status == AsyncTask.Status.RUNNING)
                downloadImageTask!!.cancel(true)
            else
                Toast.makeText(baseContext, resources.getString(R.string.task_already_finished), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onStartButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            if (downloadImageTask!!.status == AsyncTask.Status.PENDING) {
                downloadImageTask!!.execute(URL1, URL2, URL3, URL4)
            } else if (downloadImageTask!!.status == AsyncTask.Status.FINISHED) {
                newTask()
                downloadImageTask!!.execute(URL1, URL2, URL3, URL4)
            } else
                Toast.makeText(baseContext, resources.getString(R.string.task_already_started), Toast.LENGTH_SHORT).show()
        }
    }

    private fun newTask() {
        downloadImageTask = DownloadImageTask(progressBar, this, this)
        imageView1!!.setImageBitmap(null)
        imageView2!!.setImageBitmap(null)
        imageView3!!.setImageBitmap(null)
        imageView4!!.setImageBitmap(null)
        progressBar!!.max = 100
        progressBar!!.progress = 0
    }

    override fun onDownloadFinish(listBitmap: List<Bitmap>) {
        if (listBitmap != null) {
            imageView1!!.setImageBitmap(listBitmap[0])
            imageView2!!.setImageBitmap(listBitmap[1])
            imageView3!!.setImageBitmap(listBitmap[2])
            imageView4!!.setImageBitmap(listBitmap[3])
        } else {
            Toast.makeText(this, resources.getString(R.string.failed_download), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        // Another objects
        private val URL1 = "http://noragami-anime.net/index/img/bg_blu-ray01.jpg"
        private val URL2 = "http://noragami-anime.net/index/img/bg_blu-ray02.jpg"
        private val URL3 = "http://noragami-anime.net/index/img/bg_blu-ray03.jpg"
        private val URL4 = "http://noragami-anime.net/index/img/bg_blu-ray04.jpg"
    }
}
