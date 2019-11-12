package com.wcq.quasar.quasarDemo;

import co.paralleluniverse.fibers.SuspendExecution;

import java.util.HashMap;
import java.util.Map;

public abstract class Service {
    int fiberId;

    String serviceName;
    Port port;
    Map<Integer, PortFiber> callBacks = new HashMap<>();

    public Service(String serviceName, Port port){
        this.serviceName = serviceName;
        this.port = port;
    }

    public void call(Call call){
        // call
        if(call.type == 1){
            PortFiber fiber = new PortFiber(fiberId++, this);
            fiber.send(call);

        //callReturn
        }else if(call.type == 2){
            PortFiber fiber = callBacks.get(call.toFiber);
            fiber.send(call);

        }
    }

    public abstract void doSome1(Call call, PortFiber portFiber) throws InterruptedException, SuspendExecution;
    public abstract Call returnSome2(Call call, PortFiber portFiber);

    public Object getWait(Call call, PortFiber portFiber) throws InterruptedException, SuspendExecution {
        callBacks.put(portFiber.fiberId, portFiber);
        port.addCall(call);
        Call callResult = portFiber.channel.receive();
//        try {
//            callResult = portFiber.channel.receive();
//        } catch (SuspendExecution suspendExecution) {
//            suspendExecution.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return callResult;
    }

    public void returns(Call call) {
        call.type = 2;
        port.addCall(call);
    }
}
