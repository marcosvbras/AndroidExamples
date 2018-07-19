package com.androidexamples.app.flows.views

import android.graphics.Bitmap
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

import com.androidexamples.app.R

class WebViewSwipeRefreshActivity : AppCompatActivity() {

    // Views
    private var webView: WebView? = null
    private var progressBar: ProgressBar? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private val url = "http://www.inimex.com.br"

    // Erro ao carregar a página do WebView (endereço errado, ou de erro de conexão)
    val webViewClient: WebViewClient
        get() = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                if (!swipeRefreshLayout!!.isShown)
                    progressBar!!.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                progressBar!!.visibility = View.INVISIBLE
                swipeRefreshLayout!!.isRefreshing = false
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {}
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_swipe_refresh)
        LoadComponents()
    }

    private fun LoadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar!!.visibility = View.INVISIBLE

        webView = findViewById<View>(R.id.webView) as WebView
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = webViewClient

        swipeRefreshLayout = findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout!!.setOnRefreshListener(onRefreshLayout())
        swipeRefreshLayout!!.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3)
    }

    override fun onResume() {
        super.onResume()
        webView!!.loadUrl(url)
    }

    private fun onRefreshLayout(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener { webView!!.reload() }
    }
}
