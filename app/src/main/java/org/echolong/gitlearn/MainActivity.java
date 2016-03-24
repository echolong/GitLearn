package org.echolong.gitlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.echolong.gitlearn.emoji.EmojiActivity;
import org.echolong.gitlearn.mathView.MatchViewActivity;
import org.echolong.gitlearn.missView.MissViewActivity;
import org.echolong.otherfacelib.OtherFaceActivity;

public class MainActivity extends AppCompatActivity {

    private ListView funcList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] strings = new String[]{
                "MatchViewActivity",
                "MissViewActivity",
                "EmojiActivity",
                "OtherFaceActivity"
        };
        funcList = (ListView)findViewById(R.id.func_list);
        funcList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,strings));
        funcList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(MainActivity.this,MatchViewActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this,MissViewActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, EmojiActivity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, OtherFaceActivity.class);
                }
                startActivity(intent);
            }
        });

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
