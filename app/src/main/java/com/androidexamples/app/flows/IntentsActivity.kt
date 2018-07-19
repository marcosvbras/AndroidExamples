package com.androidexamples.app.flows

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.androidexamples.app.R
import com.androidexamples.app.utils.Agenda
import com.androidexamples.app.utils.AlertUtils
import com.androidexamples.app.utils.Constants
import com.androidexamples.app.utils.PermissionUtils

class IntentsActivity : AppCompatActivity() {

    private var newIntent: Intent? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)
        loadComponents()
    }

    private fun loadComponents() {
        setSupportActionBar(findViewById<View>(R.id.top_toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.button_call_number).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_dial_number).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_maps_address).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_maps_lat_long).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_maps_route).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_open_web_site).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_pick_contact).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_pick_image).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_pick_video).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_send_email).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_send_sms).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_share).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_take_photo).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_take_video).setOnClickListener(onButtonClick())
        findViewById<View>(R.id.button_view_all_contacts).setOnClickListener(onButtonClick())

        // Solicita as permissões
        val permissoes = arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA)
        PermissionUtils.validate(this, 0, *permissoes)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish()
                return
            }
        }
        // Se chegou aqui está OK :-)
    }

    private fun alertAndFinish() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.")
        // Add the buttons
        builder.setPositiveButton("OK") { dialog, id -> finish() }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == Constants.REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
                AlertUtils.showToastImageView(bitmap, this)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else if (requestCode == Constants.REQUEST_CODE_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            val bundle = data.extras

            if (bundle != null) {
                val bitmap = bundle.get("data") as Bitmap
                AlertUtils.showToastImageView(bitmap, this)
            }
        } else if (requestCode == Constants.REQUEST_CODE_TAKE_VIDEO && resultCode == Activity.RESULT_OK) {

        } else if (requestCode == Constants.REQUEST_CODE_PICK_CONTACT && resultCode == Activity.RESULT_OK) {
            val uri = data.data
            val contact = Agenda.getContact(this, uri)
            Toast.makeText(this, contact!!.name + " selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onButtonClick(): View.OnClickListener {
        return View.OnClickListener { v ->
            when (v.id) {
                R.id.button_call_number -> {
                    uri = Uri.parse("tel:12345678")
                    newIntent = Intent(Intent.ACTION_CALL, uri)
                    startActivity(newIntent)
                }
                R.id.button_dial_number -> {
                    uri = Uri.parse("tel:12345678")
                    newIntent = Intent(Intent.ACTION_DIAL, uri)
                    startActivity(newIntent)
                }
                R.id.button_maps_address -> {
                    uri = Uri.parse("geo:0,0?q=Praça Sete de Setembro, s/n - Centro, Belo Horizonte - MG")
                    newIntent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(newIntent)
                }
                R.id.button_maps_lat_long -> {
                    uri = Uri.parse("geo:-19.9341634,-43.9401459")
                    newIntent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(newIntent)
                }
                R.id.button_maps_route -> {
                    uri = Uri.parse("https://www.google.com/maps/dir/Pra%C3%A7a+Sete+de+Setembro+-+Centro,+Belo+Horizonte+-+State+of+Minas+Gerais,+Brazil/Pra%C3%A7a+da+Liberdade,+Belo+Horizonte+-+State+of+Minas+Gerais,+Brazil/@-19.9255455,-43.9449,15z/data=!3m1!4b1!4m13!4m12!1m5!1m1!1s0xa699fb595bab33:0xca642ca01121d880!2m2!1d-43.9386291!2d-19.9191248!1m5!1m1!1s0xa699dc33844e8d:0x25c3ee8734f393f9!2m2!1d-43.9379572!2d-19.9341685")
                    newIntent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(newIntent)
                }
                R.id.button_open_web_site -> {
                    uri = Uri.parse("https://github.com/")
                    newIntent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(newIntent)
                }
                R.id.button_pick_contact -> {
                    uri = Uri.parse("content://com.android.contacts/contacts")
                    newIntent = Intent(Intent.ACTION_PICK, uri)
                    startActivityForResult(newIntent, Constants.REQUEST_CODE_PICK_CONTACT)
                }
                R.id.button_pick_image -> {
                    newIntent = Intent()
                    newIntent!!.type = "image/*"
                    newIntent!!.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(Intent.createChooser(newIntent, resources.getString(R.string.select_image)), Constants.REQUEST_CODE_PICK_IMAGE)
                }
                R.id.button_pick_video -> {
                    newIntent!!.type = "video/*"
                    newIntent!!.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(Intent.createChooser(newIntent, resources.getString(R.string.select_video)), Constants.REQUEST_CODE_PICK_VIDEO)
                }
                R.id.button_send_email -> {
                    newIntent = Intent(Intent.ACTION_SEND)
                    newIntent!!.putExtra(Intent.EXTRA_SUBJECT, "E-mail title")
                    newIntent!!.putExtra(Intent.EXTRA_TEXT, "Hello Intent")
                    newIntent!!.putExtra(Intent.EXTRA_EMAIL, "email@email.xyz")
                    newIntent!!.type = "message/rfc822"
                    startActivity(newIntent)
                }
                R.id.button_send_sms -> {
                    uri = Uri.parse("sms:12345678")
                    newIntent = Intent(Intent.ACTION_SENDTO, uri)
                    newIntent!!.putExtra("sms_body", "Hello")
                    startActivity(newIntent)
                }
                R.id.button_share -> {
                    newIntent = Intent(Intent.ACTION_SEND)
                    newIntent!!.type = "text/plain"
                    newIntent!!.putExtra(Intent.EXTRA_SUBJECT, "Sharing")
                    newIntent!!.putExtra(Intent.EXTRA_TEXT, "Uga Uga")
                    startActivity(newIntent)
                }
                R.id.button_take_photo -> {
                    newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(newIntent, Constants.REQUEST_CODE_TAKE_PHOTO)
                }
                R.id.button_take_video -> {
                    newIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                    startActivityForResult(newIntent, Constants.REQUEST_CODE_TAKE_VIDEO)
                }
                R.id.button_view_all_contacts -> {
                    uri = Uri.parse("content://com.android.contacts/contacts")
                    newIntent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(newIntent)
                }
            }
        }
    }
}
