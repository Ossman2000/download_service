package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText url;
private Button downloadbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url=(EditText)findViewById(R.id.url);
        downloadbutton=(Button)findViewById(R.id.downloadbutton);
        downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUrl=url.getText().toString();

                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(getUrl));
                String title= URLUtil.guessFileName(getUrl,null,null);
                request.setTitle(title);
                request.setDescription("downloading file....");
                String cookie = CookieManager.getInstance().getCookie(getUrl);
                request.addRequestHeader("cookie",cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager downloadManager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                Toast.makeText(MainActivity.this,"download starting",Toast.LENGTH_SHORT).show();
            }
        });
}}