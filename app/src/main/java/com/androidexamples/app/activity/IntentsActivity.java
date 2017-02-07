package com.androidexamples.app.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.androidexamples.app.R;
import com.androidexamples.app.utils.Agenda;
import com.androidexamples.app.utils.AlertUtils;
import com.androidexamples.app.utils.Constants;
import com.androidexamples.app.utils.Contact;
import com.androidexamples.app.utils.PermissionUtils;

import java.io.IOException;

public class IntentsActivity extends AppCompatActivity {

    private Intent intent;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
        loadComponents();
    }

    private void loadComponents() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_call_number).setOnClickListener(onButtonClick());
        findViewById(R.id.button_dial_number).setOnClickListener(onButtonClick());
        findViewById(R.id.button_maps_address).setOnClickListener(onButtonClick());
        findViewById(R.id.button_maps_lat_long).setOnClickListener(onButtonClick());
        findViewById(R.id.button_maps_route).setOnClickListener(onButtonClick());
        findViewById(R.id.button_open_web_site).setOnClickListener(onButtonClick());
        findViewById(R.id.button_pick_contact).setOnClickListener(onButtonClick());
        findViewById(R.id.button_pick_image).setOnClickListener(onButtonClick());
        findViewById(R.id.button_pick_video).setOnClickListener(onButtonClick());
        findViewById(R.id.button_send_email).setOnClickListener(onButtonClick());
        findViewById(R.id.button_send_sms).setOnClickListener(onButtonClick());
        findViewById(R.id.button_share).setOnClickListener(onButtonClick());
        findViewById(R.id.button_take_photo).setOnClickListener(onButtonClick());
        findViewById(R.id.button_take_video).setOnClickListener(onButtonClick());
        findViewById(R.id.button_view_all_contacts).setOnClickListener(onButtonClick());
        
        // Solicita as permissões
        String[] permissoes = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CAMERA
        };
        PermissionUtils.validate(this, 0, permissoes);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish();
                return;
            }
        }
        // Se chegou aqui está OK :-)
    }

    private void alertAndFinish() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.");
        // Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                AlertUtils.showToastImageView(bitmap, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(requestCode == Constants.REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();

            if(bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");
                AlertUtils.showToastImageView(bitmap, this);
            }
        } else if(requestCode == Constants.REQUEST_CODE_TAKE_VIDEO && resultCode == RESULT_OK) {

        } else if(requestCode == Constants.REQUEST_CODE_PICK_CONTACT && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Contact contact = Agenda.getContact(this, uri);
            Toast.makeText(this, contact.getName() + " selected", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.button_call_number:
                        uri = Uri.parse("tel:12345678");
                        intent = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intent);
                        break;
                    case R.id.button_dial_number:
                        uri = Uri.parse("tel:12345678");
                        intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                        break;
                    case R.id.button_maps_address:
                        uri = Uri.parse("geo:0,0?q=Praça Sete de Setembro, s/n - Centro, Belo Horizonte - MG");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.button_maps_lat_long:
                        uri = Uri.parse("geo:-19.9341634,-43.9401459");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.button_maps_route:
                        uri = Uri.parse("https://www.google.com/maps/dir/Pra%C3%A7a+Sete+de+Setembro+-+Centro,+Belo+Horizonte+-+State+of+Minas+Gerais,+Brazil/Pra%C3%A7a+da+Liberdade,+Belo+Horizonte+-+State+of+Minas+Gerais,+Brazil/@-19.9255455,-43.9449,15z/data=!3m1!4b1!4m13!4m12!1m5!1m1!1s0xa699fb595bab33:0xca642ca01121d880!2m2!1d-43.9386291!2d-19.9191248!1m5!1m1!1s0xa699dc33844e8d:0x25c3ee8734f393f9!2m2!1d-43.9379572!2d-19.9341685");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.button_open_web_site:
                        uri = Uri.parse("https://github.com/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.button_pick_contact:
                        uri = Uri.parse("content://com.android.contacts/contacts");
                        intent = new Intent(Intent.ACTION_PICK, uri);
                        startActivityForResult(intent, Constants.REQUEST_CODE_PICK_CONTACT);
                        break;
                    case R.id.button_pick_image:
                        intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_image)), Constants.REQUEST_CODE_PICK_IMAGE);
                        break;
                    case R.id.button_pick_video:
                        intent.setType("video/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_video)), Constants.REQUEST_CODE_PICK_VIDEO);
                        break;
                    case R.id.button_send_email:
                        intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT, "E-mail title");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hello Intent");
                        intent.putExtra(Intent.EXTRA_EMAIL, "email@email.xyz");
                        intent.setType("message/rfc822");
                        startActivity(intent);
                        break;
                    case R.id.button_send_sms:
                        uri = Uri.parse("sms:12345678");
                        intent = new Intent(Intent.ACTION_SENDTO, uri);
                        intent.putExtra("sms_body", "Hello");
                        startActivity(intent);
                        break;
                    case R.id.button_share:
                        intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing");
                        intent.putExtra(Intent.EXTRA_TEXT, "Uga Uga");
                        startActivity(intent);
                        break;
                    case R.id.button_take_photo:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, Constants.REQUEST_CODE_TAKE_PHOTO);
                        break;
                    case R.id.button_take_video:
                        intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivityForResult(intent, Constants.REQUEST_CODE_TAKE_VIDEO);
                        break;
                    case R.id.button_view_all_contacts:
                        uri = Uri.parse("content://com.android.contacts/contacts");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                }
            }
        };
    }
}
