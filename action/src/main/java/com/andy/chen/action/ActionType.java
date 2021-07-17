package com.andy.chen.action;

public enum ActionType {


    EAT(1,"吃东西动作"),//吃东西动作
    DRINK(2,"喝茶动作"),//喝茶
    PLAY(3,"玩耍动作"),//玩耍动作
    SLEEP(4,"睡觉动作"),//睡觉动作
    LEARN(5,"学习动作");//学习动作

    public String actionName;
    public int actionId;
    ActionType(int id, String name){
        this.actionId = id;
        this.actionName = name;
    }

}
