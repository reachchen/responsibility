package com.andy.chen.action;

public enum ActionType {
    CITY_CARD(0,"市民卡动作"),//市民卡
    ID_CARD(1,"身份证动作"),//身份证
    TEXT_CARD(2,"手输身份证动作"),//手动输入身份证
    QRCODE(3,"扫健康码动作"),//扫描健康码二维码
    FACE_COMP(4,"人证比对动作"),//人证比对
    FACE_DETECT(5,"人脸检测动作"),//人脸检测
    TEMP(6,"测温动作"),//测温
    QUERY_HEATH(7,"查询健康码动作");//网络查询健康码
    public String actionName;
    public int actionId;
    ActionType(int id, String name){
        this.actionId = id;
        this.actionName = name;
    }

}
