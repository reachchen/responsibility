package com.andy.chen.sleep;

public class Sleep {

    public static String TAG = Sleep.class.getSimpleName();

    public static ISleepListener mListener;

    public static SleepBean prepareSleep(ISleepListener listener){
            mListener = listener;
            return new SleepBean("21:00","me","1");
    }

    public static SleepBean startSleep(){
          mListener.startSleep();
          return new SleepBean("21:00","me","2");
    }

    public static SleepBean isSleeping(){
        mListener.isSleeping();
        return new SleepBean("21:00","me","3");
    }

    public static SleepBean sleepEnd(){
        return new SleepBean("21:00","me","4");
    }
}
