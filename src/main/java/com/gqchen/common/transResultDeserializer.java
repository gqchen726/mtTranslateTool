package com.gqchen.common;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.gqchen.bean.Trans_Result;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 反序列化（JSON -> Trans_Result）
 */
public class transResultDeserializer implements ObjectDeserializer {
    @Override
    public Trans_Result deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

        Trans_Result trans_result = null;
        final List<Trans_Result> trans_results = parser.parseArray(Trans_Result.class);
        for(Trans_Result trans_result1 : trans_results) {
            trans_result = trans_result1;
        }

        return trans_result;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
