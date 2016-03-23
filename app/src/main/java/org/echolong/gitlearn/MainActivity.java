package org.echolong.gitlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.echolong.gitlearn.mathView.MatchViewActivity;
import org.echolong.gitlearn.missView.MissViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainBtn = (Button) findViewById(R.id.mainBtn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MatchViewActivity.class);
                startActivity(intent);
            }
        });
        Button missBtn = (Button) findViewById(R.id.missBtn);
        missBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MissViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
