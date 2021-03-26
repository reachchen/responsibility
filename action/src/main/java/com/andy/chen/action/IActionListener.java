package com.andy.chen.action;


public interface IActionListener {

    /**
     * 动作开始执行
     * @param action
     */
    void onActionStart(BaseAction action);

    /**
     * 动作执行完成
     * @param action
     */
    void onActionEnd(BaseAction action);
    /**
     * 流程执行结束
     * @param action
     */
    void onActionFinish(BaseAction action);

    /**
     * 流程取消
     * @param action
     */
    void onActionCancel(BaseAction action);

    /**
     * 动作初始化完成
     * @param success
     * @param action
     */
    void onActionInitResult(boolean success,BaseAction action);

//    /**
//     * 判断动作是否取消
//     * @return
//     */
//    boolean isActionCancel();

    /**
     * 是否有动作正在执行
     * @return
     */
    boolean hasActionWorking();
}
