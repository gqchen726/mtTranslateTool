package com.gqchen.bean;

public class MethodReference {
    private String test;

    public MethodReference(String test) {
        this.test = test;
    }


    public MethodReference() {
    }

    @Override
    public String toString() {
        return "MethodReference{" +
                "test='" + test + '\'' +
                '}';
    }
}
