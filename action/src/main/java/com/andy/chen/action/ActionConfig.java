package com.andy.chen.action;

public class ActionConfig {
    private int actionId;
    private String actionName;
    private ActionConfig nextAction;

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setNextAction(ActionConfig nextAction) {
        this.nextAction = nextAction;
    }

    public ActionConfig getNextAction() {
        return nextAction;
    }

    public int getActionId() {
        return actionId;
    }

    public String getActionName() {
        return actionName;
    }

    @Override
    public String toString() {
        return "ActionConfig{" +
                "actionId=" + actionId +
                ", actionName='" + actionName + '\'' +
                ", nextAction=" + nextAction +
                '}';
    }
}
