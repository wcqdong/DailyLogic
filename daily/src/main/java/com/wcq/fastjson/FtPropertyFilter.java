package com.wcq.fastjson;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class FtPropertyFilter implements PropertyFilter {
    @Override
    public boolean apply(Object object, String name, Object value) {
        if(value instanceof Integer && (Integer)value == 0){
            return false;
        }
        return true;
    }
}
