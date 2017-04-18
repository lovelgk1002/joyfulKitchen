package com.app.joyfulkitchen.activity.homeChild;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/4/4.
 */

public class HomePointerImg extends Handler {
    private Context context;

    /*称转动参数开始*/
    private ImageView needleView;  //指针图片
    private Timer timer;  //时间

    private double finalWeight = 2500.0;//最终显示的重量、蓝牙接收的数据

    private float degree = 0.0f;  //记录指针旋转
    private double allWeight = 5000.0 ;

    private double allDegree = 172.0 ;
    private double finaldegree = 0.0; //记录最终指针的偏转度数
	/*称转动参数结束*/


    public HomePointerImg(Context context, ImageView needleView, double finalWeight){
        this.context = context ;
        this.needleView = needleView ;
        this.finalWeight =finalWeight ;
    }

    public void run(){

        finaldegree = finalWeight * allDegree /allWeight ;//设置转动的角度
        // 开始转动
        timer = new Timer();
        // 设置每一秒转动一下
        timer.schedule(new NeedleTask(), 0, 100);
    }
    /**
     * 秤盘转动handler开始
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {  //设置仪表盘指针转动动画
            //仪表盘最大是172度，这个是自己测出来的
            if (degree >= finaldegree) {
                timer.cancel();
            }
            degree += 2.0f;
            RotateAnimation animation = new RotateAnimation(degree,
                    degree, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1000);
            animation.setFillAfter(true);
            needleView.startAnimation(animation);
        }
    };
    private class NeedleTask extends TimerTask {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
    /**
     * 秤盘转动handler结束
     */
}
