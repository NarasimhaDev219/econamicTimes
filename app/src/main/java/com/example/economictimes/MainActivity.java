package com.example.economictimes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView click_text_id,server_text_id;
    WebView webView_id;
    AwesomeValidation awesomeValidation;
    String video_url = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";
    VideoView video_view_id;
    String video_db;
    DatabaseHelper databaseHelper;
    ModelClass modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click_text_id = findViewById(R.id.click_text_id);
        video_view_id  = findViewById(R.id.video_view_id);
        server_text_id  = findViewById(R.id.server_text_id);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        databaseHelper = new DatabaseHelper(this);

        click_text_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper.addData(new ModelClass(video_url));
                System.out.println("=====++==========1"+video_url);
                System.out.println("=====++==========2"+databaseHelper);

                getData();
            }
        });
    }

    public void getData(){
        List<ModelClass> details=null;
        details=databaseHelper.getAllInfo();
        if (details.size()!=0){
            for (int i=0;i<details.size();i++){
                modelClass=details.get(i);
                video_db = modelClass.getText_url();
                video_view_id.setVideoPath(String.valueOf(video_db));
                video_view_id.setMediaController(new MediaController(this));
                video_view_id.requestFocus();
                video_view_id.start();
                server_text_id.setVisibility(View.GONE);
                video_view_id.setVisibility(View.VISIBLE);
                System.out.println("=====++==========5"+video_db);
            }
        }
    }
}
