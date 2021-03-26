package com.andy.chen.action;

public class BaseActionResult<T> {
    private T result;//动作执行的结果数据
    private String message;
    private BaseAction action;//当前结果所对应的动作
    public BaseActionResult(BaseAction action){
        this.action = action;
    }

    public BaseAction getAction() {
        return action;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
