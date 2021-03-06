package com.bean;

import java.lang.reflect.Method;

public class Handler {
    // Controller
    private Class<?> controllerClass;

    // Action 方法
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass(){
        return controllerClass;
    }

    public Method getActionMethod(){
        return actionMethod;
    }
}
