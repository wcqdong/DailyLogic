package com.wcq.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Getter
//@Setter
@JSONType(serialzeFilters={FtPropertyFilter.class})
public class FtModel2 implements Serializable {

    private int id;
    private String name;
    private long sid;
//    private FtModel3 m3 = new FtModel3();


    @JSONField(name="id1")
    public int getId() {
        return id;
    }
    @JSONField(name="id1")
    public void setId(int id) {
        this.id = id;
    }
    @JSONField(name="name1")
    public String getName() {
        return name;
    }
    @JSONField(name="name1")
    public void setName(String name) {
        this.name = name;
    }
    @JSONField(name="sid1")
    public long getSid() {
        return sid;
    }
    @JSONField(name="sid1")
    public void setSid(long sid) {
        this.sid = sid;
    }
}
