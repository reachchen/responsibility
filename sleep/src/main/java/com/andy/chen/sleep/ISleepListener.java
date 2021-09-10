package com.andy.chen.sleep;

public interface ISleepListener {


    /**
     *准备睡觉
     */
    void prepareSleep(ISleepListener listener);

    /**
     * 开始睡觉
     */
    void startSleep();

    /**
     * 正在睡觉
     */
    void isSleeping();

    /**
     * 睡觉失败
     */
    void sleepFail();

    /**
     * 睡觉成功
     */
    void sleepEnd();
}
