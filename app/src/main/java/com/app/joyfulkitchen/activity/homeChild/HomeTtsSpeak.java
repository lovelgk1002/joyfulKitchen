package com.app.joyfulkitchen.activity.homeChild;

import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

/**
 * Created by Administrator on 2017/4/13.
 */

public class HomeTtsSpeak implements TextToSpeech.OnInitListener {


    private static final int REQ_TTS_STATUS_CHECK = 0;
    private static final String TAG = "TTS Demo";
    private TextToSpeech mTts;

    private String speakStr = null;//语音播报内容

    public HomeTtsSpeak(){

    }

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
}
