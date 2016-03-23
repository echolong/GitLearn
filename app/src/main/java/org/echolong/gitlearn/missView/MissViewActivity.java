package org.echolong.gitlearn.missView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import org.echolong.echolib.missView.MissView;
import org.echolong.gitlearn.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MissViewActivity extends Activity {
    private final String IMAGE_NAME = "night.jpg";
    MissView mMissview;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss_view);
        initImage();//将图片从assets考到SD卡
        mMissview = (MissView) findViewById(R.id.missview);
        mMissview.initPicture(path);
    }

    private void initImage() {
        try {
            path = getSDPath() + "/" + IMAGE_NAME;
            File image = new File(path);
            if (image.exists()) {
                return;
            }
            assetsDataToSD(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assetsDataToSD(String fileName) throws IOException {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(fileName);
        myInput = this.getAssets().open(IMAGE_NAME);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist)      //如果SD卡存在，则获取跟目录
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
