package com.thaiduong.qrcodegenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private EditText generateEditText;
    private ImageView QRImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateEditText = findViewById(R.id.generateEditText);
        QRImageView = findViewById(R.id.QRImageView);
    }

    @SuppressLint("ShowToast")
    public void onGenerateButtonPressed(View view) {
        if (generateEditText.getText().toString().length() == 0) {
            Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            final String url = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + generateEditText.getText().toString();
            Picasso.get().load(url).into(QRImageView);
        }
    }
}
