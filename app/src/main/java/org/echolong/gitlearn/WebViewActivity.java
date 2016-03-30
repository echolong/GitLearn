package org.echolong.gitlearn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setDownloadListener(new MyWebViewDownLoadListener());

//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                //页面加载完毕
//                super.onPageFinished(view, url);
////                if (pbLoading != null && pbLoading.getVisibility() == View.VISIBLE) {
////                    pbLoading.setVisibility(View.GONE);//进度条不可见
////                }
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                //页面开始加载
//                super.onPageStarted(view, url, favicon);
////                if (pbLoading != null && pbLoading.getVisibility() == View.INVISIBLE) {
////                    pbLoading.setVisibility(View.VISIBLE);//进度条可见
////                }
//            }
//
//        });

        webView.getSettings().setJavaScriptEnabled(true);//设置支持脚本
        webView.getSettings().setBuiltInZoomControls(true);// 设置支持缩放
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常

        webView.loadUrl("http://www.ichachong.cn/appdown.html");
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Log.i("tag", "url=" + url);
            Log.i("tag", "userAgent=" + userAgent);
            Log.i("tag", "contentDisposition=" + contentDisposition);
            Log.i("tag", "mimetype=" + mimetype);
            Log.i("tag", "contentLength=" + contentLength);
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }
}
