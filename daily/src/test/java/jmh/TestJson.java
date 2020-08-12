package jmh;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonParser;
import com.wcq.jackson.JSONUtils;
import lombok.Getter;
import lombok.Setter;
import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@Measurement(iterations = 3)
public class TestJson {

    private static String str = "{\"sc\":\"werf\",\"sd\":\"werf\",\"sg\":\"werf\",\"sa\":\"werf\"}";

    public static void main(String[] args) throws RunnerException {
        ParserConfig.getGlobalInstance().setSafeMode(true);
        System.out.println("设置成功");
        Options options = new OptionsBuilder().include(TestJson.class.getName()).build();
        new Runner(options).run();
    }

    @Benchmark
    public void testFastJsonToString(){
        JSONObject jo = new JSONObject();
        jo.put("sd", "werf");
        jo.put("sg", "werf");
        jo.put("sc", "werf");
        jo.put("sa", "werf");

        JSON.toJSONString(jo, SerializerFeature.DisableCircularReferenceDetect);


    }

    @Benchmark
    @Test
    @Ignore
    public void testJackJsonToString(){
        Map<String, String> jo = new HashMap<>();
            jo.put("sd", "werf");
            jo.put("sg", "werf");
            jo.put("sc", "werf");
            jo.put("sa", "werf");
        JSONUtils.toJSONString(jo);
    }

    @Benchmark
    public void testFastJsonParse(){
        JSON.parseObject(str);
    }

    @Benchmark
    public void testJackJsonParse(){
        JSONUtils.parseMap(str);
    }


    static Student st = new Student();
    static String stStr = "{\"name\":\"deasdf\",\"desc\":\"eedf\",\"age\":4,\"level\":6}";

    @Benchmark
    @Test
    public void testJackJsonParseClass(){
        JSONUtils.parse(stStr, Student.class);
//        JSONUtils.toJSONString(st);
    }

    @Benchmark
    @Test
    public void testFastJsonParseClass(){
        JSON.parseObject(stStr, Student.class);
    }

    @Benchmark
    @Test
    public void testJackJsonClassToString(){
        JSONUtils.toJSONString(st);
//        JSONUtils.toJSONString(st);
    }

    @Benchmark
    @Test
    public void testFastJsonClassToString(){
        JSON.toJSONString(st);
    }

}

@Getter
@Setter
class Student{
    private String name = "deasdf";
    private String desc = "eedf";
    private int age = 4;
    private int level = 6;

}
