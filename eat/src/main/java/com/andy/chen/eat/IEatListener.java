package com.andy.chen.eat;

public interface IEatListener {

    /**
     * 开始吃饭
     */
    void startEat();

    /**
     * 正在吃饭
     */
    void isEating();

    /**
     * 吃饭失败
     */
    void eatFail();

    /**
     * 吃饭结束
     */
    void eatEnd();


}
