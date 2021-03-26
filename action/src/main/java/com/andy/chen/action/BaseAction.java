package com.andy.chen.action;

import android.content.Context;
import android.util.Log;

public abstract class BaseAction<T> {
    private static String TAG = "BaseAction";
    public volatile boolean isRootAction;//是否为顶部起始动作
    public BaseAction parentAction;//上一个动作
    public BaseAction nextAction;//下一个动作
    public ActionType actionType;//动作类型
    public IActionListener actionListener;
    public BaseActionResult<T> result;//动作执行结果
    public volatile boolean isActionWorking = false;//当前动作是否正在执行
    public volatile boolean isActionCancel = true;//当前动作是否取消
    /**
     *
     * @param actionType 当前动作类型
     * @param isRootAction 当前动作是否为顶部起始动作
     * @param nextAction 当前动作执行结束后对应的下一个动作
     * @param listener 动作的监听回调
     */
    public BaseAction(ActionType actionType, boolean isRootAction, BaseAction nextAction, IActionListener listener){
        this.actionType = actionType;
        this.actionListener = listener;
        this.nextAction = nextAction;
        this.isRootAction = isRootAction;
    }

    /**
     * 设置当前动作执行状态
     * @param working
     */
    public void setActionWorking(boolean working){
        this.isActionWorking = working;
        if(working){
            setActionCancel(false);
        }
    }

    public void setActionCancel(boolean actionCancel) {
        isActionCancel = actionCancel;
    }

    public boolean isActionCancel(){
        Log.d(logTAG(), "isActionCancel: "+"isActionWorking="+isActionWorking+" isActionCancel="+isActionCancel);
        if(isActionWorking){//正在执行的动作
            if(isActionCancel){//动作被取消了
                return true;
            }
        }
        return false;
    }

    public void setNextAction(BaseAction nextAction) {
        this.nextAction = nextAction;
    }

    /**
     * 判断当前动作是否可执行
     * @return
     */
    public boolean isActionEnable(){
        Log.d(logTAG(), "isActionEnable: isRootAction="+isRootAction+" isActionWorking="+isActionWorking+" parentAction="+parentAction);
        boolean enable = false;
        if(!isRootAction){//非顶部动作
            if(parentAction != null){//如果父动作执行了
                if(parentAction.isActionCancel()){//可能上层取消了动作,这时候就没必要继续执行了
                    enable = false;
                }else {
                    enable = true;
                }
            }else {//非顶部动作需要依赖于上一级动作执行
                enable = false;
            }
        } else {//顶部动作
            if(actionListener!= null){
                if(actionListener.hasActionWorking()){//如果有流程在执行则没必要再执行动作，顶部动作互斥关系
                    enable = false;
                }else if(isActionWorking){//如果当前动作正在执行，这时候就没必要再次执行了
                    enable = false;
                }else {
                    enable = true;
                }
            }else {
                enable = true;
            }
        }
        Log.d(logTAG(), "isActionEnable: "+enable);
        return enable;
    }

    /**
     * 判断下一个动作是否可执行
     * @return
     */
    public boolean isNextActionEnable(){
        boolean enable = false;
        Log.d(logTAG(), "isNextActionEnable: isRootAction="+isRootAction+" isActionWorking="+isActionWorking);
        if(!isRootAction){//非顶部动作
            if(parentAction != null){//如果父动作执行了
                if(parentAction.isActionCancel()){//可能上层取消了动作,这时候就没必要继续执行了
                    enable = false;
                }else {
                    enable = true;
                }
            }else {//非顶部动作需要依赖于上一级动作执行
                enable = false;
            }
        } else {//顶部动作
                if(isActionWorking){
                    if(isActionCancel()){//可能上层取消了动作,这时候就没必要继续执行下一个动作了
                        enable = false;
                    }else {
                        enable = true;
                    }
                }else {//如果当前动作没执行，也就没必要执行下一个动作了
                    enable = false;
                }
        }
        Log.d(logTAG(), "isNextActionEnable: "+enable);
        return enable;
    }
    public void setParentAction(BaseAction parentAction) {
        if(parentAction!= null){
            if(this.parentAction == null){
                this.parentAction = parentAction;
            }
        }else {
            this.parentAction = parentAction;
        }
    }

    public String logTAG(){
        return TAG+"<="+getActionName()+"=>";
    }

    /**
     * 初始化动作
     * @param context
     * @param parentAction
     */
    public abstract void initAction(Object context,BaseAction parentAction);

    /**
     * 是否为根部动作
     * @return
     */
    public abstract boolean isRootAction();

    /**
     * 执行动作
     * @param isRootAction 是否作为顶部动作执行
     * @param  input 动作的入参
     * @param parentAction 父动作
     */
    public abstract void doAction(boolean isRootAction,Object input,BaseAction parentAction);

    /**
     * 执行下一个动作
     * @param parentAction
     */
    public abstract void doNextAction(BaseAction parentAction);


    /**
     * 释放动作资源
     * @param context
     * @return 返回下一级动作
     */
    public abstract BaseAction releaseAction(Context context);

    /**
     * 取消动作
     * @param parentAction
     */
    public void cancelAction(BaseAction parentAction){
        Log.d(logTAG(), "cancelAction: ");
        setActionCancel(true);
        if(nextAction != null){
            nextAction.cancelAction(this);
        }
    }
    /**
     * 重置动作
     */
    public void resetAction(){
        Log.d(logTAG(), "resetAction: ");
        setActionCancel(true);
        setActionWorking(false);
        setParentAction(null);
        result = null;
        if(nextAction != null){
            nextAction.resetAction();
        }
    }
    public String getActionName(){
        return actionType.actionName;
    }
    public ActionType getActionType(){
        return actionType;
    }

}
