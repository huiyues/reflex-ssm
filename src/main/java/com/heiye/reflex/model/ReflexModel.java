package com.heiye.reflex.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2020/5/3
 * @Time: 11:05
 * Description: No Description
 */
public class ReflexModel {

    private int code;
    private String methodName;

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ReflexModel{" +
                "code=" + code +
                ", methodName='" + methodName + '\'' +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
