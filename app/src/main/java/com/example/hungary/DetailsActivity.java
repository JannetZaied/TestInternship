package com.example.hungary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;




import static com.example.hungary.MainActivity.static_meeting;

public class DetailsActivity extends AppCompatActivity {

    WebView wb ;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        wb = findViewById(R.id.wbs);

        wb.setWebViewClient(new WebViewClient());


// all the websettings are used here  so that the link will be loaded correctly
        WebSettings ws = wb.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        ws.setAppCacheEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wb.loadUrl(static_meeting.getExternal_link());



    }


    @Override
    public void onBackPressed() {
        if (wb.canGoBack()){
            wb.goBack();
        }else
            super.onBackPressed();

    }
}
