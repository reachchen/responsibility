package com.andy.chen.eat;

import android.util.Log;

public class Eat {

    public static String TAG = Eat.class.getSimpleName();

    public static IEatListener  mListener;

    public static EatBean prepareEat(IEatListener listener){
        mListener = listener;
        Log.i(TAG,"prepareEat()--");
        return new EatBean("鸡腿","","1");
    }

    public static EatBean startEat(){
        mListener.startEat();
        return new EatBean("鸡腿","","2");
    }

    public static EatBean isEating(){
        mListener.isEating();
        Log.i(TAG,"isEating()--");
        return new EatBean("鸡腿","","3");
    }

    public static EatBean eatEnd(){
        mListener.eatEnd();
        Log.i(TAG,"eatEnd()--");
        return new EatBean("鸡腿","","4");
    }

    public static EatBean eatFail(){
        mListener.eatFail();
        Log.i(TAG,"eatFail()--");
        return new EatBean("鸡腿","","5");
    }


}
