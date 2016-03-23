package org.echolong.gitlearn.mathView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import org.echolong.echolib.matchView.MatchTextView;
import org.echolong.gitlearn.R;


public class MatchViewActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private MatchTextView mMatchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchview_main);

        mMatchTextView = (MatchTextView) findViewById(R.id.mMatchTextView);
        mSeekBar = (SeekBar) findViewById(R.id.mSeekBar);
        mSeekBar.setProgress(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMatchTextView.setProgress(progress * 1f / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchDialog matchDialog = new MatchDialog();
                getSupportFragmentManager().beginTransaction().add(matchDialog, "matchDialog").commit();
            }
        });
    }

}
