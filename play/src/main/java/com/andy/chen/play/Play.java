package com.andy.chen.play;

public class Play {

    public static String TAG = Play.class.getSimpleName();

    public static IPlayListener mListener;

    public static PlayBean preparePlay(IPlayListener listener){
        mListener = listener;
        return new PlayBean("篮球","me","1");
    }


    public static PlayBean startPlay(){
        mListener.startPlay();
        return new PlayBean("篮球","me","2");
    }


    public static PlayBean isPlaying(){
        mListener.isPlaying();
        return new PlayBean("篮球","me","3");
    }


    public static PlayBean playEnd(){
        mListener.playEnd();
        return new PlayBean("篮球","me","4");
    }


}
