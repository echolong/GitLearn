package org.echolong.gitlearn.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/3/29.
 */
public class AndroidMathUtil {

    private final String TAG = getClass().getSimpleName();
    private Context mContext;

    public AndroidMathUtil(Context context) {
        this.mContext = context;
    }

    public static void getMessage() {

    }

    private void hashMapTest() {
        Map map = new HashMap<>();
        map.put("zhangsan", 29);
        map.put("zhangsan", 31);
        map.put("wangwu", 122);

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            String key = (String) item.getKey();
            String value = (String) item.getValue();
            Log.d(TAG, key + "_" + value);
            key.hashCode();
            Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
        }
    }
}
