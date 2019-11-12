package com.wcq.fastjson;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
//@JSONType(serialzeFilters={FtNameFilter.class, FtPropertyFilter.class})
public class FtModel3 implements Cloneable {

    private int a;
    private String b;
    private long c;
}
