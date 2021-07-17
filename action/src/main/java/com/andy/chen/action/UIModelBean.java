package com.andy.chen.action;

import java.util.List;

/**
 * 通过读json文件转换成配置对象
 */
public class UIModelBean {
    private List<UIModelConfig> uiModelConfigs;

    public List<UIModelConfig> getUiModelConfigs() {
        return uiModelConfigs;
    }

    public void setUiModelConfigs(List<UIModelConfig> uiModelConfigs) {
        this.uiModelConfigs = uiModelConfigs;
    }

    @Override
    public String toString() {
        return "UIModelBean{" +
                "uiModelConfigs=" + uiModelConfigs +
                '}';
    }
}
