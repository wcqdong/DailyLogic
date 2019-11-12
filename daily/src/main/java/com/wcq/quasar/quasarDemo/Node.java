package com.wcq.quasar.quasarDemo;

import java.util.HashMap;
import java.util.Map;

public class Node {
    Map<String, Port> ports = new HashMap<>();

    Port createPort(String portName){
        Port port = new Port(this, portName);
        port.start();
        ports.put(portName, port);
        return port;
    }
}
