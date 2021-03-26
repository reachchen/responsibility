package com.andy.chen.action.drink;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;



import com.andy.chen.action.ActionType;
import com.andy.chen.action.BaseAction;
import com.andy.chen.action.IActionListener;
import com.andy.chen.drink.Drink;
import com.andy.chen.drink.DrinkBean;
import com.andy.chen.drink.IDrinkListener;

/**
*
* @github https://github.com/reachchen 
* @author welcome
* @time  3/12/21 1:45 PM
*/
public class DrinkAction extends BaseAction<DrinkBean> implements IDrinkListener {

    /**
     * @param actionType   当前动作类型
     * @param isRootAction 当前动作是否为顶部起始动作
     * @param nextAction   当前动作执行结束后对应的下一个动作
     * @param listener     动作的监听回调
     */
    public DrinkAction(ActionType actionType, boolean isRootAction, BaseAction nextAction, IActionListener listener) {
        super(actionType, isRootAction, nextAction, listener);
    }

    @Override
    public void initAction(Object context, BaseAction parentAction) {

         if(context instanceof Activity){
            DrinkBean drinkBean = Drink.prepareDrink(this);
            Log.i(logTAG(),drinkBean.getState());
         }

         if(nextAction!=null){
            nextAction.initAction(context,this);
         }
    }

    @Override
    public boolean isRootAction() {
        return isRootAction;
    }

    @Override
    public void doAction(boolean isRootAction, Object input, BaseAction parentAction) {
        setParentAction(parentAction);
        if(isActionEnable()){
            setActionWorking(true);
        }else{
            Log.i(logTAG(),"动作不可用");
        }

    }

    @Override
    public void doNextAction(BaseAction parentAction) {
        if(isNextActionEnable()){
            if(actionListener!= null){
                actionListener.onActionEnd(this);//告知上层当前动作执行完成
            }
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
        resetAction();
        return nextAction;
    }

    @Override
    public void drinkFail() {

    }

    @Override
    public void startDrink() {
        doAction(isRootAction,null,null);
    }
}
