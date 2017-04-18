package com.app.joyfulkitchen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.app.joyfulkitchen.activity.homeChild.HomePointerImg;
import com.app.joyfulkitchen.activity.menuchild.MenuCarousel;

import java.text.DecimalFormat;
import java.util.Locale;


public class FragmentHome extends Fragment implements TextToSpeech.OnInitListener{

	/*************转盘参数****************/
	private ImageView needleView;  //指针图片
	private double finalWeight = 2500.0;//最终显示的重量、蓝牙接收的数据

	/******************单选radioGroup按钮********************/
	private RadioGroup rg ;
	private RadioButton g,oz,ml,tael,lb;



	ImageView menu_classfiy;//头部图标


	/*****************顶部搜索框参数*******************/
	private TextView looktv ;//文本框
	private String lookStr;//文本框显示的内容

	/*顶部搜索框变量*/
	private LinearLayout home_edt_ly;
	private LinearLayout home_tv_ly;
	private EditText home_search ;//搜索框
	private ImageView home_title_set;//搜索按钮

	private ImageView search_img;//搜索图片

	/******************顶部搜索框参数结束***************************/


	/*食物选择*/
	private String food=null;//当前称量的食物
	private TextView changetv ;//显示当前食物tv



	/**************************语音播报参数****************************/
	private EditText inputText = null;//测试用，模拟蓝牙接收的数据
	private Button speakBtn = null;

	private static final int REQ_TTS_STATUS_CHECK = 0;
	private static final String TAG = "TTS Demo";
	private TextToSpeech mTts;
	private CheckBox checkbox;

	private String speakStr = null;//语音播报内容

	/**************************语音播报参数****************************/




	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.home_fragment, container, false);

		showView();//调用方法，为变量赋值

		menu_classfiy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(),MenuCarousel.class);
				startActivity(it);
			}
		});


		/*search_btn点击获取home_search的值*/
		home_title_set.setOnClickListener(new HomeClickListener());
		/*home_search_img显示文本框输入值*/
		//search_img.setOnClickListener(new HomeClickListener());


		/************称布局****************/

		lookStr = finalWeight +"g" ;

		looktv.setText(lookStr);//设置显示内容



		//指针转动
		new HomePointerImg(this.getActivity(),needleView,finalWeight).run();



		/************************************************语音播报开始*******************************************************/
		//检查TTS数据是否已经安装并且可用

		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);


		checkbox = (CheckBox)view.findViewById(R.id.checkbox);
		inputText = (EditText)view.findViewById(R.id.inputText);
		speakBtn = (Button)view.findViewById(R.id.speakBtn);


		inputText.setText(finalWeight+"");


		//模拟蓝牙数据变化
		speakBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if("".equals(inputText.getText().toString())){
					finalWeight = 0;
				}else{
					finalWeight = Double.valueOf(inputText.getText().toString());
				}
				//lookStr = finalWeight +"g" ;

				setUnit(rg.getCheckedRadioButtonId());

                looktv.setText(lookStr);
			}
		});






		//rg改变事件
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				setUnit(checkedId);
				looktv.setText(lookStr);
			}
		});

		//语音播报
		looktv.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				//指针转动
				new HomePointerImg(new FragmentHome().getActivity(),needleView,finalWeight).run();

				if(checkbox.isChecked()) {
					speakStr = lookStr;
				}else{
					speakStr = null ;
				}

				mTts.speak(speakStr, TextToSpeech.QUEUE_ADD,null);
			}

		});
		/************************************************语音播报结束*******************************************************/







		//食物切换显示
		changetv.setText(setChangetv(food));//设置显示


		return view;
	}

	/***
	 * 为变量赋值
	 */
	public void showView(){

		menu_classfiy = (ImageView) view.findViewById(R.id.home_menu);//头部图标

		home_tv_ly  =(LinearLayout) view.findViewById(R.id.home_tv_ly);//title文本框设置布局
		home_edt_ly =(LinearLayout) view.findViewById(R.id.home_edt_ly);//title文本设置布局
		//home_edt_ly.setVisibility(View.GONE);//不可见

		looktv = (TextView) view.findViewById(R.id.home_look_tv);//控件textView显示称量结果
		changetv =(TextView) view.findViewById(R.id.home_change_tv);//控件textView显示当前称量的食物
		home_search = (EditText) view.findViewById(R.id.home_search_edt);//控件EditText搜索当前称量的食物
		home_title_set = (ImageView) view.findViewById(R.id.home_title_set);//控件ImageView设置当前称量的食物
		//search_img = (ImageView) view.findViewById(R.id.home_search_img);//控件ImageViewsous搜索当前称量的食物

		needleView = (ImageView) view.findViewById(R.id.needle);//指针

		/*单选radioGroup*/

		rg = (RadioGroup)view.findViewById(R.id.home_rg);
		g = (RadioButton)view.findViewById(R.id.home_gbtn_g);//克
		oz = (RadioButton)view.findViewById(R.id.home_gbtn_oz);//安士
		ml = (RadioButton)view.findViewById(R.id.home_gbtn_ml);//毫升
		tael = (RadioButton)view.findViewById(R.id.home_gbtn_tael);//两
		lb = (RadioButton)view.findViewById(R.id.home_gbtn_lb);//英镑
	}



	/***
	 * 语音播报init
	 * @param status
	 */
	@Override
	public void onInit(int status) {
		//TTS Engine初始化完成
		if(status == TextToSpeech.SUCCESS)
		{
			int result = mTts.setLanguage(Locale.US);
			//设置发音语言

			if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
			//判断语言是否可用
			{
				Log.v(TAG, "Language is not available");
				speakStr = null;
			}
			else
			{
				mTts.speak("Welcome to JoyfulKitchen, connect bluetooth began to weigh.", TextToSpeech.QUEUE_ADD, null);
			}
		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == REQ_TTS_STATUS_CHECK)
		{
			switch (resultCode) {
				case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
					//这个返回结果表明TTS Engine可以用
				{
					mTts = new TextToSpeech(getActivity(), this);
					Log.v(TAG, "TTS Engine is installed!");

				}

				break;
				case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
					//需要的语音数据已损坏
				case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
					//缺少需要语言的语音数据
				case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
					//缺少需要语言的发音数据
				{
					//这三种情况都表明数据有错,重新下载安装需要的数据
					Log.v(TAG, "Need language stuff:"+resultCode);
					Intent dataIntent = new Intent();
					dataIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
					startActivity(dataIntent);

				}
				break;
				case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
					//检查失败
				default:
					Log.v(TAG, "Got a failure. TTS apparently not available");
					break;
			}
		}
		else
		{
			//其他Intent返回的结果
		}
	}


	@Override
	public void onPause() { super.onPause();
		if(mTts != null)
		//activity暂停时也停止TTS
		{
			mTts.stop();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//释放TTS的资源
		mTts.shutdown();
	}

    /**
     * 根据选择单位换算
     * @param checkedBtnId
     */
	public void setUnit(int checkedBtnId){
        DecimalFormat df = new DecimalFormat("#.##");
		switch (checkedBtnId) {
			case R.id.home_gbtn_g:
				lookStr = df.format(finalWeight) +"g" ;
				break;
			case R.id.home_gbtn_oz:
				lookStr = df.format(finalWeight/28.35) +"oz" ;
				break;
			case R.id.home_gbtn_ml:
				lookStr = df.format(finalWeight) +"ml" ;
				break;
			case R.id.home_gbtn_tael:
				lookStr =  df.format(finalWeight/50) +"tael" ;
				break;
			case R.id.home_gbtn_lb:
				lookStr =  df.format(finalWeight/453.6)+"lb" ;
				break;
		}
	}


	/**
	 * 设置显示当前的食物
	 * */
	public String setChangetv(String food){
		if(food == null || food.equals("")){
			return "你还没选择食物";
		}else {
			return "当前称量的食物是"+food;
		}
	}

	/**
	 * 按钮点击事件
	 */
	class HomeClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.home_title_set:
					String search_name = home_search.getText().toString();
					food = search_name ;
					changetv.setText(setChangetv(food));//设置显示
					break;
				/*case R.id.home_search_img:
					home_edt_ly.setVisibility(View.VISIBLE);
					home_tv_ly.setVisibility(View.GONE);
					break;*/
				default:
					break;
			}
		}
	}

}
