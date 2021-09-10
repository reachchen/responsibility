package com.andy.chen.action;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.andy.chen.action.drink.DrinkAction;
import com.andy.chen.action.eat.EatAction;
import com.andy.chen.action.learn.LearnAction;
import com.andy.chen.action.play.PlayAction;
import com.andy.chen.action.sleep.SleepAction;
import com.andy.chen.action.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ActionControl implements IActionListener {
    private static final String TAG = ActionControl.class.getSimpleName();
    private volatile static ActionControl control;
    private BaseAction runningRootAction;//正在执行的流程所对应的顶部动作
    private BaseAction runningAction;//正在执行的动作
    private IActionEventNotifyListener notifyListener;
    private UIModelBean uiModelBean;
    private boolean enableAction = true;
    private String actionJsonFileName = "action.txt";
    private ConcurrentHashMap<String,BaseAction> modelMap = new ConcurrentHashMap<>();

    public static ActionControl getControl() {
        if (control == null) {
            synchronized (ActionControl.class) {
                if (control == null) {
                    control = new ActionControl();
                }
            }
        }
        return control;
    }

    public void setActionEventNotifyListener(IActionEventNotifyListener iActionListener) {
        this.notifyListener = iActionListener;
    }


    /**
     *
     * @param actionJsonFileName
     */
    public void init(Context context,String actionJsonFileName) throws Exception {
        Log.d(TAG, "init: "+actionJsonFileName);
        if(TextUtils.isEmpty(actionJsonFileName)){
            throw new Exception("配置文件不能为空");
        }else {
            this.actionJsonFileName = actionJsonFileName;
            if(uiModelBean == null){
                uiModelBean = readJSONObjectFromFile(context,actionJsonFileName);
            }
        }
    }

    /**
     * 将JSON文件转换成对象
     *
     * @throws FileNotFoundException
     */
    private UIModelBean readJSONObjectFromFile(Context context,String fileName) {
        UIModelBean uiModelBean = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(fileName);//读配置文件
//            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            try {
                String readLine = bufferedReader.readLine();
                while (!TextUtils.isEmpty(readLine)){
                    stringBuffer.append(readLine);
                    readLine = bufferedReader.readLine();
                }
                Log.d(TAG, "readJSONObjectFromFile: "+stringBuffer.toString());
                uiModelBean = GsonUtil.json2Object(stringBuffer.toString(), TypeToken.get(UIModelBean.class).getType());
                if(uiModelBean != null){
                    Log.d(TAG, "readJSONObjectFromFile: uiModelBean="+ GsonUtil.object2Json(uiModelBean,TypeToken.get(UIModelBean.class).getType()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return uiModelBean;
        }
    }
    /**
     * 编辑动作链路
     * @param context
     */
    public void initActionNode(Context context,boolean enableTemp,UIModelType uiModelType){
//        createUIModelBean(context);
        Log.d(TAG, "initModel: "+uiModelType.modelName+" enableTemp="+enableTemp);
        if(uiModelBean == null){
            uiModelBean = readJSONObjectFromFile(context,actionJsonFileName);
        }
        if(uiModelBean != null){
            List<UIModelConfig> uiModelConfigs = uiModelBean.getUiModelConfigs();
            if(uiModelConfigs != null && !uiModelConfigs.isEmpty()){
                Log.d(TAG, "initModel: uiModelConfigs.size="+uiModelConfigs.size());
                for(UIModelConfig uiModelConfig : uiModelConfigs){
                    if(uiModelConfig != null ){
                        Log.d(TAG, "initModel: uiModelConfig.getModelId()="+uiModelConfig.getModelId()+" uiModelType.modelId="+uiModelType.modelId);
                        if(uiModelConfig.getModelId() == uiModelType.modelId){
                            List<ActionConfig> actionModels = uiModelConfig.getActionModels();
                            if(actionModels != null && !actionModels.isEmpty()){
                                Log.d(TAG, "initModel: actionModels size="+actionModels.size());
                                for(ActionConfig actionConfig : actionModels){
                                    Log.d(TAG, "initModel: rootAction actionConfig name="+actionConfig.getActionName());
                                    String actionName = actionConfig.getActionName();
                                    if(!modelMap.contains(actionConfig.getActionName())){
                                        BaseAction rootAction = createAction(actionConfig.getActionId(),true,null);
                                        actionConfig = actionConfig.getNextAction();
                                        BaseAction parentAction = rootAction;
                                        while (actionConfig!= null){
                                            BaseAction action = createAction(actionConfig.getActionId(),false,null);
                                            if(parentAction != null){
                                                parentAction.setNextAction(action);
                                            }
                                            parentAction = action;
                                            actionConfig = actionConfig.getNextAction();
                                        }
                                        Log.i(TAG,"=modelMap.put=="+actionName);
                                        modelMap.put(actionName,rootAction);
                                    }
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }

    }



    /**
     *  获取当前配置文件里的所有UI模式
     */
    public UIModelBean getAllUIModel(Context context){
        if (uiModelBean == null) {
            if(!TextUtils.isEmpty(actionJsonFileName)){
                uiModelBean = readJSONObjectFromFile(context,actionJsonFileName);
            }
        }
        return uiModelBean;

    }

    /**
     * 创建动作实例
     * @param actionId
     * @param isRootAction
     * @param nextAction
     * @return
     */
    private BaseAction createAction(int actionId,boolean isRootAction,BaseAction nextAction){
        BaseAction action = null;
        Log.i("ActionControl","==createAction=="+actionId);
        switch (actionId){

            case 1://吃饭动作
                action = new EatAction(ActionType.EAT,isRootAction,nextAction,this);
                break;
            case 2://喝茶动作
                action = new DrinkAction(ActionType.DRINK,isRootAction,nextAction,this);
                break;
            case 3://玩耍动作
                action = new PlayAction(ActionType.PLAY,isRootAction,nextAction,this);
                break;
            case 4://睡觉动作
                action = new SleepAction(ActionType.SLEEP,isRootAction,nextAction,this);
                break;
            case 5://学习动作
                action = new LearnAction(ActionType.LEARN,isRootAction,nextAction,this);
                break;
        }
        return action;
    }


    /**
     * 获取某条链路下的某个动作
     * @param actionType
     * @param rootAction
     * @return
     */
    public BaseAction getActionByType(ActionType actionType,BaseAction rootAction){
        if(rootAction!= null){
            if(rootAction.getActionType().actionId == actionType.actionId){
                return rootAction;
            } else {
                return getActionByType(actionType,rootAction.nextAction);
            }
        }else {
            return null;
        }
    }


    /**
     * 初始化动作链
     * @param activity
     */
    public void initAction(Activity activity , UIModelType uiModelType , boolean enableTemp ) {
        initActionNode(activity,enableTemp,uiModelType);
        if(!modelMap.isEmpty()){
            Set<Map.Entry<String, BaseAction>> entries = modelMap.entrySet();
            Iterator<Map.Entry<String, BaseAction>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, BaseAction> next = iterator.next();
                BaseAction action = next.getValue();
                String actionName = next.getKey();
                Log.d(TAG, "=====初始化动作链开始: actionName="+actionName+"=====");
                action.initAction(activity,null);
                Log.d(TAG, "=====初始化动作链结束: actionName="+actionName+"=====");
            }
        }
    }

    /**
     * 取消正在执行的流程
     * @param context
     */
    public void cancelAction(Context context) {
        Log.d(TAG, "cancelAction: ");
        if(runningAction!= null){
            runningAction.setActionCancel(true);
        }
        if(runningRootAction != null){
            runningRootAction.cancelAction(null);
        }
        if(runningAction!= null){
            onActionCancel(runningAction);
        }else if(notifyListener != null){
            boolean finish = notifyListener.onActionCancel(null);
            finishAction();
        }
//        runningRootAction = null;
    }

    /**
     * 主动结束流程，并重置
     */
    public void finishAction(){
        if(runningRootAction != null){
            Log.d(TAG, "finishAction: ");
//            runningRootAction.cancelAction(null);
            runningRootAction.resetAction();
        }
        runningAction = null;
        runningRootAction = null;
    }

    /**
     * 进程推出时销毁资源
     * @param context
     */
    public void onDestroy(Context context) {
        Log.d(TAG, "onDestroy: modelMap size="+modelMap.size());
        cancelAction(context);
        setActionEventNotifyListener(null);
        if(!modelMap.isEmpty()){
            Set<Map.Entry<String, BaseAction>> entries = modelMap.entrySet();
            Iterator<Map.Entry<String, BaseAction>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, BaseAction> next = iterator.next();
                BaseAction action = next.getValue();
                String actionName = next.getKey();
                Log.d(TAG, "onDestroy: actionName="+actionName);
                releaseAction(action,context);
//                BaseAction baseAction = action.releaseAction(context);

            }
        }
        modelMap.clear();
    }
    private void releaseAction(BaseAction action ,Context context){
        if(action != null){
            Log.d(TAG, "releaseAction: "+action.getActionName());
            BaseAction baseAction = action.releaseAction(context);
            releaseAction(baseAction,context);
        }
    }

    @Override
    public void onActionStart(BaseAction action) {
        Log.d(TAG, "onActionStart: "+action.getActionName());
        //TODO 通知UI
        if(action!= null && action.isRootAction()){
            Log.d(TAG, "=====流程开始 onActionStart: actionName="+action.getActionName()+"=====");
            runningRootAction = action;
        }
        runningAction = action;
        if(notifyListener != null){
            notifyListener.onActionStart(action);
        }
    }

    @Override
    public void onActionEnd(BaseAction action) {
        Log.d(TAG, "onActionEnd: "+action.getActionName());
        //TODO 通知UI
        if(notifyListener != null){
            notifyListener.onActionEnd(action);
        }
    }

    @Override
    public void onActionFinish(BaseAction action) {
        //TODO 通知UI
        if(notifyListener != null){
            boolean finish = notifyListener.onActionFinish(action);
            Log.d(TAG, "=====流程结束 onActionFinish: "+finish+" actionName="+action.getActionName()+"=====");
            if(finish){//上层可能不会立即结束流程，而是倒计时显示UI
                finishAction();
            }
//            finishAction();
        }else {
            finishAction();
        }
    }

    @Override
    public void onActionCancel(BaseAction action) {
        if(notifyListener != null){
            boolean finish = notifyListener.onActionCancel(action);
            Log.d(TAG, "=====流程结束 onActionCancel: "+finish+" actionName="+action.getActionName()+"=====");
            if(finish){//上层可能不会立即结束流程，而是倒计时显示UI
                finishAction();
            }
        }else {
            finishAction();
        }
    }

    @Override
    public void onActionInitResult(boolean success, BaseAction action) {
        Log.d(TAG, "onActionInitResult: "+success+" actionName="+action.getActionName());
        //TODO 通知UI
        if(notifyListener != null){
            notifyListener.onActionInitResult(success,action);
        }
    }

    public void setEnableAction(boolean enableAction) {
        Log.d(TAG, "setEnableAction: "+enableAction);
        this.enableAction = enableAction;
    }

    @Override
    public boolean hasActionWorking() {
        Log.d(TAG, "hasActionWorking: enableAction="+enableAction);
        if(!enableAction){
            return true;
        }
        boolean hasActionWork = runningRootAction == null ? false : true;
        Log.d(TAG, "hasActionWorking: hasActionWork="+hasActionWork);
        return hasActionWork;
    }
}
