package com.andy.chen.sleep;

public interface ISleepListener {

    /**
     * 睡觉失败
     */
    void sleepFail();

    /**
     * 睡觉成功
     */
    void sleepSuccess();
}
