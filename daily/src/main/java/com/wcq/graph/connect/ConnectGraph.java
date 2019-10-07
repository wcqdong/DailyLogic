package com.wcq.graph.connect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 邻接图
 */
public class ConnectGraph<V> {
    Map<String, Set<Element>> graph = new HashMap();
    Map<String, Element> nodes = new HashMap();

    ConnectGraph(){

    }

    void addNode(String name, V v){
        if(nodes.containsKey(name)){
            return;
        }
        nodes.put(name, new Element(name, v));
        graph.put(name, new HashSet<Element>());

    }

    void connectEach(String from, String to){
        Set<Element> fromSet = graph.get(from);
        Set<Element> toSet = graph.get(to);

        Element eFrom = nodes.get(from);
        Element eTo = nodes.get(to);

        fromSet.add(eTo);
        toSet.add(eFrom);
    }

    void connect(String from, String to){
        Set<Element> fromSet = graph.get(from);

        Element eTo = nodes.get(to);

        fromSet.add(eTo);
    }

    void visit(String name){
        for (Element e : graph.get(name)) {
            System.out.println(e.name);
        }
    }

    void foreach(){

        for (Map.Entry<String, Set<Element>> en : graph.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(en.getKey());
            for (Element e : en.getValue()) {
                sb.append("->").append(e.name);
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        ConnectGraph<String> graph = new ConnectGraph<String>();
        graph.addNode("a", "av");
        graph.addNode("b", "av");
        graph.addNode("c", "av");
        graph.addNode("d", "av");
        graph.addNode("e", "av");

        graph.connect("a", "b");
        graph.connect("a", "c");
        graph.connect("b", "c");
        graph.connect("b", "c");
        graph.connect("e", "c");

        graph.foreach();


    }




}

class Element<V>{
    String name;
    V v;

    Element(String name, V v){
        this.name = name;
        this.v = v;
    }

}
