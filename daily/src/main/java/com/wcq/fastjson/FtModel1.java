package com.wcq.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Getter
//@Setter
@JSONType(serialzeFilters={FtPropertyFilter.class})
public class FtModel1 {

    private int a;
    private String b;
    private long c;
    private long d;
    private String e;
    private String g;
    private String u;
    private List<FtModel3> list = new ArrayList<>();
    private Map<Integer, FtModel3> map = new HashMap<>();

//    @JSONField(serializeUsing = FtSerializer.class)
    private FtModel2 m = new FtModel2();

    @JSONField(name = "aa")
    public int getA() {
        return a;
    }
    @JSONField(name = "aa")
    public void setA(int a) {
        this.a = a;
    }
    @JSONField(name = "bb")
    public String getB() {
        return b;
    }
    @JSONField(name = "bb")
    public void setB(String b) {
        this.b = b;
    }
    @JSONField(name = "cc")
    public long getC() {
        return c;
    }
    @JSONField(name = "cc")
    public void setC(long c) {
        this.c = c;
    }
    @JSONField(name = "dd")
    public long getD() {
        return d;
    }
    @JSONField(name = "dd")
    public void setD(long d) {
        this.d = d;
    }
    @JSONField(name = "ee")
    public String getE() {
        return e;
    }
    @JSONField(name = "ee")
    public void setE(String e) {
        this.e = e;
    }
    @JSONField(name = "gg")
    public String getG() {
        return g;
    }
    @JSONField(name = "gg")
    public void setG(String g) {
        this.g = g;
    }
    @JSONField(name = "uu")
    public String getU() {
        return u;
    }
    @JSONField(name = "uu")
    public void setU(String u) {
        this.u = u;
    }

    @JSONField(name = "mm")
    public FtModel2 getM() {
        return m;
    }
    @JSONField(name = "mm")
    public void setM(FtModel2 m) {
        this.m = m;
    }

    @JSONField(name = "li")
    public List<FtModel3> getList() {
        return list;
    }
    @JSONField(name = "li")
    public void setList(List<FtModel3> list) {
        this.list = list;
    }
    @JSONField(name = "mp")
    public Map<Integer, FtModel3> getMap() {
        return map;
    }
    @JSONField(name = "mp")
    public void setMap(Map<Integer, FtModel3> map) {
        this.map = map;
    }
}
