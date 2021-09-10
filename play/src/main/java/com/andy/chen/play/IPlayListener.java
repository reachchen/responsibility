package com.andy.chen.play;

public interface IPlayListener {


    /**
     * 准备打篮球
     */
    void preparePlay();

    /**
     * 开始打篮球
     */
    void startPlay();

    /**
     * 正在打篮球
     */
    void isPlaying();

    /**
     * 打篮球失败
     */
    void playFail();

    /**
     * 打篮球结束
     */
    void playEnd();
}
