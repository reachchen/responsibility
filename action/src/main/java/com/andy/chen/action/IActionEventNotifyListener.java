package com.andy.chen.action;

public interface IActionEventNotifyListener {
    /**
     * 动作开始执行
     * @param action
     */
    boolean onActionStart(BaseAction action);

    /**
     * 动作执行完成
     * @param action
     */
    boolean onActionEnd(BaseAction action);
    /**
     * 流程执行结束
     * @param action
     */
    boolean onActionFinish(BaseAction action);

    /**
     * 流程取消
     * @param action
     */
    boolean onActionCancel(BaseAction action);

    /**
     * 动作初始化完成
     * @param success
     * @param action
     */
    boolean onActionInitResult(boolean success, BaseAction action);

}
