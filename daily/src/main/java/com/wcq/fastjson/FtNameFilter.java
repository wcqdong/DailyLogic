package com.wcq.fastjson;

import com.alibaba.fastjson.serializer.NameFilter;

public class FtNameFilter implements NameFilter {
    @Override
    public String process(Object source, String name, Object value) {
        if (name == null || name.length() == 0) {
            return name;
        }

//        char[] chars = name.toCharArray();
//        chars[0]= Character.toUpperCase(chars[0]);
//
//        String pascalName = new String(chars);
        if(name.equals("m")){
            return "hg";
        }
        return name;
    }
}
