package com.andy.chen.action;

public enum  UIModelType {
    TYPE_STUDENT(0,"暑假模式"),
    TYPE_WORKER(1,"开学模式");
    public String modelName;
    public int modelId;
    UIModelType(int id,String name){
        this.modelId = id;
        this.modelName = name;
    }

    public static UIModelType getTypeById(int modelId){
        if(modelId == TYPE_STUDENT.modelId){
            return TYPE_STUDENT;
        }else if(modelId == TYPE_WORKER.modelId){
            return TYPE_WORKER;
        }else{
            return null;
        }
    }
}
