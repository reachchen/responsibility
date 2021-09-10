package com.andy.chen.drink;

public interface IDrinkListener {

    /**
     * 喝茶失败
     */
    void drinkFail();

    /**
     * 开始喝茶
     */
    void startDrink();

    /**
     * 正在喝茶
     */
    void isDrinking();

    /**
     * 喝茶结束
     */
    void drinkEnd();


}
