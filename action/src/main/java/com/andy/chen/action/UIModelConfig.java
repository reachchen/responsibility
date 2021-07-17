package com.andy.chen.action;

import java.util.List;

public class UIModelConfig {
    private int modelId;//UIModelType.modelId
    private String modelName;//UIModelType.modelName
    private List<ActionConfig> actionModels;//某种UI模式下支持的动作模式

    public void setActionModels(List<ActionConfig> actionModels) {
        this.actionModels = actionModels;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public List<ActionConfig> getActionModels() {
        return actionModels;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public String toString() {
        return "UIModelConfig{" +
                "modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", actionModels=" + actionModels +
                '}';
    }
}
