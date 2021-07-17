package com.andy.chen.drink;

import android.util.Log;

/**
*
* @github https://github.com/reachchen
* @author welcome
* @time  3/12/21 1:45 PM
*/
public class Drink{

    public static IDrinkListener mListener;

    public static DrinkBean prepareDrink(IDrinkListener listener){
        Log.i("DRINK","prepareDrink");
        mListener = listener;
        return new DrinkBean("红茶","","准备喝茶");
    }

    public static DrinkBean  drinking(){
        return new DrinkBean("红茶","","正在喝茶");
    }

    public static DrinkBean  drinkEnd(){
        return new DrinkBean("红茶","","喝完茶了");
    }

    public static void startDrinking(){
        mListener.startDrink();
    }


}
