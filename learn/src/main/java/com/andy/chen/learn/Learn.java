package com.andy.chen.learn;

import android.util.Log;

public class Learn {

    public static String TAG = Learn.class.getSimpleName();

    public static ILearnListener  mListener;


    public static LearnBean prepareLearn(ILearnListener listener){
        mListener = listener;
        Log.i(TAG,"准备学习");
        return new LearnBean("编程","me","1");
    }

    public static LearnBean startLearn(){
        Log.i(TAG,"开始学习");
        return new LearnBean("编程","me","2");
    }

    public static LearnBean starting(){
        Log.i(TAG,"正在学习");
        return  new LearnBean("编程","me","3");
    }

    public static LearnBean startEnd(){
        Log.i(TAG,"结束学习");
        return new LearnBean("编程","me","4");
    }

}
