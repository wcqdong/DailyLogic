package com.wcq.quasar.quasarDemo;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Port extends Thread{

    Node node;

    private String portName;

    private Queue<Call> calls= new ConcurrentLinkedDeque<>();
    private List<Call> pulseCalls= new ArrayList<>();

    private Map<String, Service> services = new HashMap<>();
//    private Map<String, PortFiber> fibers = new HashMap<>();

    public Port(Node node, String portName){
        this.node = node;
        this.portName = portName;
    }
    @Override
    public void run() {
        while (true) {

            pulseCallAffirm();

            pulseCall();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void pulseCall() {
        pulseCalls.forEach(call -> {
            Service service = services.get(call.toService);
            if(service == null){
                System.out.println(portName + "下service不存在 " + call.toService);
                return;
            }
            service.call(call);
        });
        pulseCalls.clear();
    }

    private void pulseCallAffirm() {
        // 本心跳要执行的call
        Call call = null;
        while ((call = calls.poll()) != null) {
            pulseCalls.add(call);
        }
    }

//    public void createFiber(String fiberName){
//        fiberName = this.portName + "_" + fiberName;
//        PortFiber fiber = new PortFiber(fiberName, this);
//        fibers.put(fiberName, fiber);
//    }

    public void sendCall(Call call){
        System.out.println(portName + "收到一条call消息");
        calls.add(call);
    }

    public void returns(Call call, int i) {
        Call callReturn = call.createReturn();
    }

    public void addCall(Call call) {
        node.ports.get(call.toPort).sendCall(call);
    }

    public void addService(Service service) {
        services.put(service.serviceName, service);
    }
}
