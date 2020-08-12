package com.wcq.jackson;

import java.util.HashMap;
import java.util.Map;

public class TestJackson {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");

        String value = JSONUtils.toJSONString(map);
        System.out.println(value);
        Map<String, String> map1 = JSONUtils.parseMap(value);
        System.out.println(map1.get("2"));
    }
}
