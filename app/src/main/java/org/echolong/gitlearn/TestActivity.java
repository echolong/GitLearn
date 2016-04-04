package org.echolong.gitlearn;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("Tag","主线程");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable,5000);
        Looper looper = getMainLooper();
        looper.getThread();
    }
}
