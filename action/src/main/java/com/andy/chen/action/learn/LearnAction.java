package com.andy.chen.action.learn;

import android.content.Context;

import com.andy.chen.action.ActionType;
import com.andy.chen.action.BaseAction;
import com.andy.chen.action.IActionListener;
import com.andy.chen.learn.ILearnListener;
import com.andy.chen.learn.LearnBean;

public class LearnAction extends BaseAction<LearnBean> implements ILearnListener {
    /**
     * @param actionType   当前动作类型
     * @param isRootAction 当前动作是否为顶部起始动作
     * @param nextAction   当前动作执行结束后对应的下一个动作
     * @param listener     动作的监听回调
     */
    public LearnAction(ActionType actionType, boolean isRootAction, BaseAction nextAction, IActionListener listener) {
        super(actionType, isRootAction, nextAction, listener);
    }

    @Override
    public void initAction(Object context, BaseAction parentAction) {

    }

    @Override
    public boolean isRootAction() {
        return false;
    }

    @Override
    public void doAction(boolean isRootAction, Object input, BaseAction parentAction) {

    }

    @Override
    public void doNextAction(BaseAction parentAction) {

    }

    @Override
    public BaseAction releaseAction(Context context) {
        return null;
    }

    @Override
    public void prepareLearn() {

    }

    @Override
    public void startLearn() {

    }

    @Override
    public void starting() {

    }

    @Override
    public void learnFail() {

    }

    @Override
    public void learnEnd() {

    }


}
