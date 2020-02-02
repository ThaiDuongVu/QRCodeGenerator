package com.thaiduong.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText generateEditText;
    private ImageView QRImageView;

    private Vibrator vibrator;
    private final int vibratingDuration = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateEditText = findViewById(R.id.generateEditText);
        QRImageView = findViewById(R.id.QRImageView);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @SuppressLint("ShowToast")
    public void onGenerateButtonPressed(View view) {
        vibrator.vibrate(vibratingDuration);
        if (generateEditText.getText().toString().length() == 0) {
            Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            final String url = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + generateEditText.getText().toString();
            Picasso.get().load(url).into(QRImageView);
        }
    }

    public void onSaveButtonPressed(View view) {
        vibrator.vibrate(vibratingDuration);
        saveImage(QRImageView);
    }

    private void saveImage(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir("Pictures", Context.MODE_PRIVATE);
        File file = new File(directory, "qr code!" + ".jpg");

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show();

            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception!", Toast.LENGTH_SHORT).show();
        }
    }
}
