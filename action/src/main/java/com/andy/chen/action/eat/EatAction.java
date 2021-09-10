package com.andy.chen.action.eat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.andy.chen.action.ActionType;
import com.andy.chen.action.BaseAction;
import com.andy.chen.action.IActionListener;
import com.andy.chen.eat.Eat;
import com.andy.chen.eat.EatBean;
import com.andy.chen.eat.IEatListener;

public class EatAction  extends BaseAction<EatBean> implements IEatListener {


    /**
     * @param actionType   当前动作类型
     * @param isRootAction 当前动作是否为顶部起始动作
     * @param nextAction   当前动作执行结束后对应的下一个动作
     * @param listener     动作的监听回调
     */
    public EatAction(ActionType actionType, boolean isRootAction, BaseAction nextAction, IActionListener listener) {
        super(actionType, isRootAction, nextAction, listener);
    }

    @Override
    public void initAction(Object context, BaseAction parentAction) {
         if(context instanceof Activity){
             EatBean eatBean = Eat.prepareEat(this);
             Log.i(logTAG(),eatBean.getState());
         }

         if(nextAction!=null){
             nextAction.initAction(context,this);
         }
    }

    @Override
    public boolean isRootAction() {
        return false;
    }

    @Override
    public void doAction(boolean isRootAction, Object input, BaseAction parentAction) {
        setParentAction(parentAction);
        Log.i("EatAction","");

        doNextAction(this);

    }

    @Override
    public void doNextAction(BaseAction parentAction) {
        Log.d(logTAG(), "doNextAction: parentAction="+parentAction);

        if(isNextActionEnable()){
            if(actionListener!= null){
                actionListener.onActionEnd(this);//告知上层当前动作执行完成
            }
            Log.i("EatAction","=========="+nextAction);
            if(nextAction != null){//执行下一个动作
                nextAction.doAction(false, result,this);
            }else if(actionListener!= null){
                actionListener.onActionFinish(this);
            }
        }else{
            Log.i(logTAG(),"下一个动作不可用");
        }
    }

    @Override
    public BaseAction releaseAction(Context context) {
        return null;
    }

    @Override
    public void startEat() {

    }

    @Override
    public void isEating() {

    }

    @Override
    public void eatFail() {

    }

    @Override
    public void eatEnd() {

    }


}
