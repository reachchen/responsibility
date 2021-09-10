package com.andy.chen.eat;

public class EatBean {

    public String foodName;
    public String who;
    public String state;

    public EatBean(String foodName,String who,String state){
        this.foodName = foodName;
        this.who = who;
        this.state = state;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
