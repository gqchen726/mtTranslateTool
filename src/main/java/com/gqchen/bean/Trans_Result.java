package com.gqchen.bean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class Trans_Result {
    @JSONField(name = "dst")
    private String dst;
    @JSONField(name = "src")
    private String src;




    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Trans_Result{" +
                "dst='" + dst + '\'' +
                ", src='" + src + '\'' +
                '}';
    }


}
