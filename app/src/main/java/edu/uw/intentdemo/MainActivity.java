package edu.uw.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**Main**";

    public static final String EXTRA_MESSAGE = "edu.uw.intentDemo.message";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View launchButton = findViewById(R.id.btnLaunch);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Launch button pressed");

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Hello from MainActivity");
                if(intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);

                //sendBroadcast(intent)
                // receive broadcast
                //

            }
        });

    }

    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:206-685-1622"));
        startActivity(intent);

    }

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");
            ImageView img = (ImageView) findViewById(R.id.imgThumbnail);
            img.setImageBitmap(bmp);
        }

    }
}
