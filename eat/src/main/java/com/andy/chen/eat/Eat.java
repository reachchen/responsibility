package com.andy.chen.eat;

public class Eat {

    public static IEatListener  mListener;

    public static EatBean prepareEat(IEatListener listener){
        mListener = listener;
        return new EatBean("鸡腿","","1");
    }

    public static EatBean isEating(){
        mListener.isEating();
        return new EatBean("鸡腿","","2");
    }

    public static EatBean eatEnd(){
        mListener.eatEnd();
        return new EatBean("鸡腿","","3");
    }

    public static EatBean eatFail(){
        mListener.eatFail();
        return new EatBean("鸡腿","","4");
    }


}
