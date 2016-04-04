package org.echolong.gitlearn;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;

public class ViewSystemActivity extends Activity {

    private Button gestureBtn;
    private GestureDetector detector;
    private TextView textView;
    private TextView transitionTxt;
    private String TAG = ViewSystemActivity.this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_system);
        detector = new GestureDetector(ViewSystemActivity.this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                animatorTest();
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        gestureBtn = (Button)findViewById(R.id.gesuture_btn);
        textView = (TextView)findViewById(R.id.test_txt);
        transitionTxt = (TextView)findViewById(R.id.transtion_txt);
        gestureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        gestureBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                boolean ontouch = detector.onTouchEvent(event);
//                return ontouch;
//            }
//        });
        gestureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorTest();
            }
        });
        int touchSlop = getTouchSlop();
        Log.i(TAG,"touchSlop_"+touchSlop);
    }

    /**获取最小滑动距离,大于这个值则为移动
     *
     * @return
     */
    private int getTouchSlop(){
        return ViewConfiguration.get(this).getScaledTouchSlop();
    }

    private void animatorTest(){
        final int startx = 0;
        final int deleax = 100;
        Log.i(TAG,"animator");
        ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                Log.i(TAG,"fraction_"+fraction);
                textView.scrollTo(startx+(int) (deleax*fraction),0);
            }
        });
        animator.start();
        TransitionDrawable transitionDrawable = (TransitionDrawable) transitionTxt.getBackground();
        transitionDrawable.startTransition(1000);

    }
}
