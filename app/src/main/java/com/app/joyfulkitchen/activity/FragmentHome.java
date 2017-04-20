package com.app.joyfulkitchen.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.app.joyfulkitchen.activity.homeChild.HomeChangeFood;
import com.app.joyfulkitchen.activity.homeChild.HomePointerImg;
import com.app.joyfulkitchen.activity.menuchild.MenuCarousel;
import com.app.joyfulkitchen.db.JoyfulKitDB;
import com.app.joyfulkitchen.service.BluetoothService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FragmentHome extends Fragment implements TextToSpeech.OnInitListener{

	private JoyfulKitDB joyfulKitDB;//打开首页就创建数据库
	ImageView menu_classfiy;//头部图标

	/*************转盘参数开始****************/
	private ImageView needleView;  //指针图片
	private double finalWeight = 1500.0;//最终显示的重量、蓝牙接收的数据

	//盘显示
	private TextView looktv ;//文本框
	private String lookStr;//文本框显示的内容
	/*************转盘参数结束****************/


	/******************单位换算单选radioGroup按钮********************/
	private RadioGroup rg ;
	private RadioButton g,oz,ml,tael,lb;

	/*********************************点击跳转页面*************************************/
	private Button foodNutrition ;

	/**************************语音播报参数****************************/
	private EditText inputText = null;//测试用，模拟蓝牙接收的数据
	private Button speakBtn = null;

	private static final int REQ_TTS_STATUS_CHECK = 0;
	private static final String TAG = "TTS Demo";
	private TextToSpeech mTts;
	private CheckBox checkbox;//选择是否语音

	/**************************语音播报参数****************************/

	private View view;




	/*蓝牙所需*/
	private BluetoothAdapter.LeScanCallback lazyCallback;
	private BluetoothAdapter mBluetoothAdapter;
	private String mDeviceAddress =null;
	private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList();
	private BluetoothGattCharacteristic mNotifyCharacteristic;
	private BluetoothService mBluetoothLeService;
	private static final int REQUEST_ENABLE_BT = 0;

	// 代码管理服务生命周期
	private final ServiceConnection mServiceConnection = new ServiceConnection() {

		//服务保持连接
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder service) {
			mBluetoothLeService = ((BluetoothService.LocalBinder)service).getService();
			if (!mBluetoothLeService.initialize()) {
				Log.i(TAG, "无法初始化蓝牙");
			}

			Toast.makeText(getActivity(), "连接设备", Toast.LENGTH_LONG).show();
			// 自动连接到设备成功启动初始化。
			mBluetoothLeService.connect(mDeviceAddress);
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			Log.i(TAG, "无法连接");
			mBluetoothLeService = null;
		}
	};

	//广播
	private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothService.ACTION_GATT_CONNECTED.equals(action)) {
				Toast.makeText(context, "连接成功", Toast.LENGTH_LONG).show();
			} else if (BluetoothService.ACTION_GATT_DISCONNECTED.equals(action)) {
				Toast.makeText(context, "断开连接", Toast.LENGTH_LONG).show();
			} else if (BluetoothService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
				// 显示所有用户界面上的支持服务和特色
				displayGattServices(mBluetoothLeService.getSupportedGattServices());
			} else if (BluetoothService.ACTION_DATA_AVAILABLE.equals(action)) {
				String data = intent.getStringExtra(BluetoothService.EXTRA_DATA);


				/*******蓝牙传送的数据*********/
				String jstate = intent.getStringExtra(BluetoothService.JSTATE);
				String jdata = intent.getStringExtra(BluetoothService.JDATA);

				double jdatadb = Double.valueOf(jdata);


				if(jdatadb != finalWeight){
				/*	try {
						sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/

					finalWeight = Double.valueOf(jdata);

					/*查找当前的单位，进行换算*/
					RadioButton radioButton = (RadioButton)view.findViewById(rg.getCheckedRadioButtonId());
					int checkedId = radioButton.getId();
					setUnit(checkedId);
					looktv.setText(lookStr);

					//指针转动
					new HomePointerImg(new FragmentHome().getActivity(),needleView,finalWeight).run();

				}



			}
		}
	};

	private void displayGattServices(List<BluetoothGattService> gattServices) {
		if (gattServices != null) {
			ArrayList<HashMap<String, String>> gattServiceData = new ArrayList();
			//ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList();

			mGattCharacteristics = new ArrayList();
			for (BluetoothGattService gattService : gattServices) {
				HashMap<String, String> currentServiceData = new HashMap();
				String uuid = gattService.getUuid().toString();
				if (Objects.equals(uuid, "0000fff0-0000-1000-8000-00805f9b34fb")) {
					currentServiceData.put("UUID", uuid);
					gattServiceData.add(currentServiceData);
					ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList();
					List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
					ArrayList<BluetoothGattCharacteristic> charas = new ArrayList();
					for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
						charas.add(gattCharacteristic);
						HashMap<String, String> currentCharaData = new HashMap();
						currentCharaData.put("UUID", gattCharacteristic.getUuid().toString());
						gattCharacteristicGroupData.add(currentCharaData);
					}
					mGattCharacteristics.add(charas);
					if (mGattCharacteristics != null) {
						BluetoothGattCharacteristic characteristic = (BluetoothGattCharacteristic) ((ArrayList) this.mGattCharacteristics.get(0)).get(3);
						int charaProp = characteristic.getProperties();
						if ((charaProp | 2) > 0) {
							if (mNotifyCharacteristic != null) {
								mBluetoothLeService.setCharacteristicNotification(this.mNotifyCharacteristic, false);
								mNotifyCharacteristic = null;
							}
							mBluetoothLeService.readCharacteristic(characteristic);
						}
						if ((charaProp | 16) > 0) {
							mNotifyCharacteristic = characteristic;
							mBluetoothLeService.setCharacteristicNotification(characteristic, true);
						}
					}
				}
			}
		}
	}

	//懒加载回调
	private class LazyCallback implements BluetoothAdapter.LeScanCallback {
		@Override
		public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
			//Log.i("开始查询","LazyCallback");
			String name = bluetoothDevice.getName();
			String address = bluetoothDevice.getAddress();
			if (address != null) {
				Log.i("查到的地址:", address);
				//7C:EC:79:55:63:29
			}
			if (name != null && "BIGCARE_BC301".equals(name)) {
				//String address2 = bluetoothDevice.getAddress();
				mDeviceAddress = bluetoothDevice.getAddress();
				Log.i("---查到的名字-----:", mDeviceAddress);

				mBluetoothAdapter.stopLeScan(lazyCallback);

				//Intent gattServiceIntent = new Intent(getActivity(), BluetoothService.class);

				boolean ble = getActivity().getApplicationContext().bindService(new Intent(getActivity().getApplication(), BluetoothService.class), mServiceConnection, Context.BIND_AUTO_CREATE);//绑定服务

				if (ble) {
					Log.i("绑定成功", "dd");
				} else {
					Log.i("绑定失败", "dd");
				}

				getActivity().registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

			}
		}
	}

	private static IntentFilter makeGattUpdateIntentFilter() {

		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothService.ACTION_GATT_CONNECTED);
		intentFilter.addAction(BluetoothService.ACTION_GATT_DISCONNECTED);
		intentFilter.addAction(BluetoothService.ACTION_GATT_SERVICES_DISCOVERED);
		intentFilter.addAction(BluetoothService.ACTION_DATA_AVAILABLE);
		return intentFilter;
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG,"onStart-1");

		Log.i("sssss",mDeviceAddress+"");
		if (mDeviceAddress != null) {
			Intent gattServiceIntent = new Intent(getActivity(), BluetoothService.class);
			if(mBluetoothLeService ==null)
				getActivity().bindService(gattServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);//绑定服务
			getActivity().registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG,"onResume--1");

		Log.i("onresume","什么事都没做");

		if (!mBluetoothAdapter.isEnabled()) {
			Toast.makeText(getActivity(), "打开蓝牙成功", Toast.LENGTH_LONG).show();
			Log.i("打开蓝牙成功", "BluetoothConnection");
			Intent enableBtIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}

		Toast.makeText(getActivity(),"打开蓝牙后", Toast.LENGTH_LONG).show();

		if (lazyCallback == null) {
			Log.i("lazyCallback","lazyCallback  new前");
			lazyCallback = new LazyCallback();
		}
		Log.i("lazyCallback","new 后");


		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					boolean d = mBluetoothAdapter.startLeScan(lazyCallback);
					Log.i("扫描状态：",d+"");
				}catch (Exception e){
					Log.i("异常：",e.toString());
					e.printStackTrace();
				}

			}
		}).start();

		if (mBluetoothLeService != null) {
			final boolean result = mBluetoothLeService.connect(mDeviceAddress);
			Log.i(TAG, "连接请求的结果=" + result);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG,"onAttach--1");
		//打开蓝牙
		BluetoothManager bluetoothManager =(BluetoothManager)getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
	}


	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG,"onStop--1");
		if (mDeviceAddress != null) {
			if (mBluetoothLeService.isRestricted()){
				if(mServiceConnection != null) {
					if(mBluetoothLeService != null)
						getActivity().unbindService(mServiceConnection);
				}
			}
		}

		if (mDeviceAddress != null && mGattCharacteristics != null) {
			getActivity().unregisterReceiver(mGattUpdateReceiver);
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG,"onDestroyView--1");
	}


	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG,"onDetach--1");

		mBluetoothAdapter.stopLeScan(lazyCallback);

	}






	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.home_fragment, container, false);


		//创建数据库
		joyfulKitDB = new JoyfulKitDB(getActivity());
		joyfulKitDB.getWritableDatabase();

		showView();//调用方法，为变量赋值

		menu_classfiy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(),MenuCarousel.class);
				startActivity(it);
			}
		});

		foodNutrition.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(),HomeChangeFood.class);
				startActivity(it);
			}
		});

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

		//模仿蓝牙数据变化
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
				//指针转动
				new HomePointerImg(new FragmentHome().getActivity(),needleView,finalWeight).run();

			}
		});

		//单位换算rg改变事件
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				setUnit(checkedId);
				looktv.setText(lookStr);
			}
		});




		looktv.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void afterTextChanged(Editable s) {
				if(checkbox.isChecked()) {
					mTts.speak(lookStr, TextToSpeech.QUEUE_ADD, null);
				}
			}

		});
		/************************************************语音播报结束*******************************************************/

		return view;
	}



	/***
	 * 为变量赋值
	 */
	public void showView(){

		//bluetoothtv = (TextView) view.findViewById(R.id.home_bluetooth_tv);//蓝牙数据缓存不可见

		menu_classfiy = (ImageView) view.findViewById(R.id.home_menu);//头部图标

		/*****************称控件***********************/
		looktv = (TextView) view.findViewById(R.id.home_look_tv);//控件textView显示称量结果
		needleView = (ImageView) view.findViewById(R.id.needle);//指针
		checkbox = (CheckBox)view.findViewById(R.id.checkbox);//语音是否选择语音播报

		/*单选radioGroup*/
		rg = (RadioGroup)view.findViewById(R.id.home_rg);
		g = (RadioButton)view.findViewById(R.id.home_gbtn_g);//克
		oz = (RadioButton)view.findViewById(R.id.home_gbtn_oz);//安士
		ml = (RadioButton)view.findViewById(R.id.home_gbtn_ml);//毫升
		tael = (RadioButton)view.findViewById(R.id.home_gbtn_tael);//两
		lb = (RadioButton)view.findViewById(R.id.home_gbtn_lb);//英镑


		/*点击跳转页面*/
		foodNutrition = (Button) view.findViewById(R.id.home_food_nutrition);//选择食材
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


	/*	//蓝牙
		Log.i(TAG,"onDestroy--1");

		if (mBluetoothAdapter != null) {
			mBluetoothAdapter.disable();
		}
		mBluetoothLeService = null;*/
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



}
