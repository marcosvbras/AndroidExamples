package com.androidexamples.app.flows.handlers

import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.utils.Constants

class HandlerMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_message)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_send_message_handler).setOnClickListener(onSendMessageHandlerButtonClick())
        findViewById<View>(R.id.button_send_message_runnable).setOnClickListener(onSendMessageRunnableButtonClick())
    }

    private fun onSendMessageRunnableButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            val handler = Handler()
            handler.postDelayed({ Toast.makeText(baseContext, resources.getString(R.string.message_arrived), Toast.LENGTH_SHORT).show() }, 3000)
        }
    }

    private fun onSendMessageHandlerButtonClick(): View.OnClickListener {
        return View.OnClickListener {
            val testHandler = TestHandler()
            val message = Message()
            message.what = Constants.TEST_MESSAGE
            testHandler.sendMessageDelayed(message, 3000)
        }
    }

    inner class TestHandler : Handler() {
        override fun handleMessage(message: Message) {
            // O atributo message.what permite identificar a mensagem
            when (message.what) {
                Constants.TEST_MESSAGE -> Toast.makeText(baseContext, resources.getString(R.string.message_arrived), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
