package com.gqchen.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.gqchen.common.transResultDeserializer;

import java.util.List;
import java.util.Map;

public class TranslateResult {
    @JSONField(name = "from")
    private String from;
    @JSONField(name = "to")
    private String to;
    @JSONField(name = "trans_result" ,deserializeUsing = transResultDeserializer.class)
    private Trans_Result trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public Trans_Result getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(Trans_Result trans_result) {
        this.trans_result = trans_result;
    }

    @Override
    public String toString() {
        return "TranslateResult{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                '}';
    }
}