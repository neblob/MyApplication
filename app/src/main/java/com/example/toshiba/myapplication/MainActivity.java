package com.example.toshiba.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private static final int TAKE_PICTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCapture = (Button) findViewById(R.id.capture_button);
        btnCapture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
            ImageView image = (ImageView) findViewById(R.id.image);

            if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK ) {
            Bitmap captureImage = (Bitmap)
                                    data.getExtras().get("data");
            image.setImageBitmap(captureImage);
            }
    }
}