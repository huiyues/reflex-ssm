package com.heiye.reflex.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2020/5/4
 * @Time: 11:39
 * Description: No Description
 */
public class ReflexParent {

    private int code;
    private List<ReflexModel> nextCode;

    @Override
    public String toString() {
        return "ReflexParent{" +
                "code=" + code +
                ", nextCode=" + nextCode +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ReflexModel> getNextCode() {
        return nextCode;
    }

    public void setNextCode(List<ReflexModel> nextCode) {
        this.nextCode = nextCode;
    }
}
