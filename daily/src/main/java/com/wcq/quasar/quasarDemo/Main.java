package com.wcq.quasar.quasarDemo;

public class Main {
    public static void main(String[] args) {
        Node node = new Node();
        Port port1 = node.createPort("port1");
        Service service1 = new Service1("service1", port1);
        port1.addService(service1);

        Port port2 = node.createPort("port2");
        Service service2 = new Service2("service2", port2);
        port2.addService(service2);


        Call callTo = new Call();
        callTo.type = 1;
        callTo.toPort = "port1";
        callTo.toService = "service1";
        callTo.methodId = 1;

        port1.sendCall(callTo);

        Call callTo1 = new Call();
        callTo1.type = 1;
        callTo1.toPort = "port1";
        callTo1.toService = "service1";
        callTo1.methodId = 2;
        port1.sendCall(callTo1);

    }
}
