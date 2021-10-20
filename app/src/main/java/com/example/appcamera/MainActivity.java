package com.example.appcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnCamera;
    private ImageView iv;
    private String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (Button) findViewById(R.id.button);
        iv = (ImageView) findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });


    }

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent,1);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgB = (Bitmap) extras.get("data");

            iv.setImageBitmap(imgB);
            File path = getApplicationContext().getFilesDir();
            File foto = new File(path,"foto.jpg");

            try {
                FileOutputStream os = new FileOutputStream(foto);
                imgB.compress(Bitmap.CompressFormat.JPEG,100,os);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }






}