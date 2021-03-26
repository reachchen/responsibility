package com.andy.chen.drink;
/**
*
* @github https://github.com/reachchen
* @author welcome
* @time  3/12/21 1:57 PM
*/
public class DrinkBean {

    public String teaName;
    public String who;
    public String state;

    public DrinkBean(String teaName, String who, String state) {
        this.teaName = teaName;
        this.who = who;
        this.state = state;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String time) {
        this.who = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
