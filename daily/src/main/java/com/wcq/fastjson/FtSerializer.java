package com.wcq.fastjson;

import com.alibaba.fastjson.serializer.*;

import java.io.IOException;
import java.lang.reflect.Type;

public class FtSerializer implements ObjectSerializer {
//    public FtSerializer(Class<?> beanType) {
//        super(beanType);
//    }
//
//    public FtSerializer(SerializeBeanInfo beanInfo) {
//        super((beanInfo));
//    }


    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        //原来的值code
        FtModel2 strVal = (FtModel2)object;
//        System.out.println(strVal);
        //通过code查找相关名称，测试先写死值
        out.write('{');


        String id="i";
        out.writeString(id);
        out.write(':');
        out.writeInt(strVal.getId());
        out.write(",");
        out.write("n");
        out.write(':');
        out.writeString(strVal.getName() == null ? "" : strVal.getName());

        ObjectSerializer preS = serializer.getObjectWriter(FtModel3.class);
//        preS.write(serializer, strVal.getM3(), null, null, 0);

        out.write('}');
    }
}
