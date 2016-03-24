package org.echolong.otherfacelib;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

public class OtherFaceActivity extends Activity implements OnItemClickListener {
	
	private Button btn_emotion;
	private EditText et_emotion;
	private LinearLayout ll_emotion_dashboard;
	private ViewPager vp_emotion_dashboard;

	private EmotionPagerAdapter emotionPagerGvAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_face);
		
		btn_emotion = (Button) findViewById(R.id.btn_emotion);
		et_emotion = (EditText) findViewById(R.id.et_emotion);
		ll_emotion_dashboard = (LinearLayout) findViewById(R.id.ll_emotion_dashboard);
		vp_emotion_dashboard = (ViewPager) findViewById(R.id.vp_emotion_dashboard);
		
		btn_emotion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 隐藏或显示表情面板
				ll_emotion_dashboard.setVisibility(
						ll_emotion_dashboard.getVisibility() == View.VISIBLE ? 
								View.GONE : View.VISIBLE);
			}
		});
		
		String initText = "作者是个大帅比[可爱]啊哈哈哈哈[害羞],不帅不是中国人[可怜]!!!!";
		// EditText 是 TextView 的子类,所以第二个参数可以直接使用
		et_emotion.setText(StringUtils.getEmotionContent(this, et_emotion, initText));
		
		initEmotion();
	}
	
	/**
	 *  初始化表情面板内容
	 */
	private void initEmotion() {
		// 获取屏幕宽度
		int gvWidth = DisplayUtils.getScreenWidthPixels(this);
		// 表情边距
		int spacing = DisplayUtils.dp2px(this, 8);
		// GridView中item的宽度
		int itemWidth = (gvWidth - spacing * 8) / 7;
		int gvHeight = itemWidth * 3 + spacing * 4;

		List<GridView> gvs = new ArrayList<GridView>();
		List<String> emotionNames = new ArrayList<String>();
		// 遍历所有的表情名字
		for (String emojiName : EmotionUtils.emojiMap.keySet()) {
			emotionNames.add(emojiName);
			// 每20个表情作为一组,同时添加到ViewPager对应的view集合中
			if (emotionNames.size() == 20) {
				GridView gv = createEmotionGridView(emotionNames, gvWidth, spacing, itemWidth, gvHeight);
				gvs.add(gv);
				// 添加完一组表情,重新创建一个表情名字集合
				emotionNames = new ArrayList<String>();
			}
		}

		// 检查最后是否有不足20个表情的剩余情况
		if (emotionNames.size() > 0) {
			GridView gv = createEmotionGridView(emotionNames, gvWidth, spacing, itemWidth, gvHeight);
			gvs.add(gv);
		}

		// 将多个GridView添加显示到ViewPager中
		emotionPagerGvAdapter = new EmotionPagerAdapter(gvs);
		vp_emotion_dashboard.setAdapter(emotionPagerGvAdapter);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gvWidth, gvHeight);
		vp_emotion_dashboard.setLayoutParams(params);
	}

	/**
	 * 创建显示表情的GridView
	 */
	private GridView createEmotionGridView(List<String> emotionNames, int gvWidth, int padding, int itemWidth, int gvHeight) {
		// 创建GridView
		GridView gv = new GridView(this);
		gv.setBackgroundColor(Color.rgb(233, 233, 233));
		gv.setSelector(android.R.color.transparent);
		gv.setNumColumns(7);
		gv.setPadding(padding, padding, padding, padding);
		gv.setHorizontalSpacing(padding);
		gv.setVerticalSpacing(padding);
		LayoutParams params = new LayoutParams(gvWidth, gvHeight);
		gv.setLayoutParams(params);
		// 给GridView设置表情图片
		EmotionGvAdapter adapter = new EmotionGvAdapter(this, emotionNames, itemWidth);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(this);
		return gv;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Object itemAdapter = parent.getAdapter();
		
		if (itemAdapter instanceof EmotionGvAdapter) {
			// 点击的是表情
			EmotionGvAdapter emotionGvAdapter = (EmotionGvAdapter) itemAdapter;

			if (position == emotionGvAdapter.getCount() - 1) {
				// 如果点击了最后一个回退按钮,则调用删除键事件
				et_emotion.dispatchKeyEvent(new KeyEvent(
						KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
			} else {
				// 如果点击了表情,则添加到输入框中
				String emotionName = emotionGvAdapter.getItem(position);

				// 获取当前光标位置,在指定位置上添加表情图片文本
				int curPosition = et_emotion.getSelectionStart();
				StringBuilder sb = new StringBuilder(et_emotion.getText().toString());
				sb.insert(curPosition, emotionName);

				// 特殊文字处理,将表情等转换一下
				et_emotion.setText(StringUtils.getEmotionContent(
						this, et_emotion, sb.toString()));
				
				// 将光标设置到新增完表情的右侧
				et_emotion.setSelection(curPosition + emotionName.length());
			}

		}
	}

}
