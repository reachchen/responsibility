package com.andy.chen.learn;

public interface ILearnListener {


    /**
     * 准备学习
     */
    void prepareLearn();

    /**
     * 开始学习
     */
    void startLearn();

    /**
     * 正在学习中
     */
    void starting();


    /**
     * 学习失败
     */
    void learnFail();

    /**
     * 学习完成
     */
    void learnEnd();

}
