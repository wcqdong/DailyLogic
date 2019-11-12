package com.wcq.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

public class FtDeserializer implements ObjectDeserializer {

    @Override
    public FtModel2 deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        FtModel2 ft = new FtModel2();
        Class targetClass = FtModel2.class;

        parser.parseObject(targetClass);
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
