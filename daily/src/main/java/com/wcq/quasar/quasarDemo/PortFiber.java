package com.wcq.quasar.quasarDemo;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;

public class PortFiber implements SuspendableRunnable {
    final Channel<Call> channel = Channels.newChannel(0);
    Fiber fiber;
    Service service;
    int fiberId;

    public PortFiber(int fiberId, Service service){
        this.fiberId = fiberId;
        this.service = service;
        fiber = new Fiber(this);
        fiber.start();
    }

    @Override
    public void run() throws SuspendExecution, InterruptedException {
        Call call = channel.receive();
        if(call.methodId == 1){
            service.doSome1(call, this);
        }

        if(call.methodId == 2){
            Call callResult = service.returnSome2(call, this);
            if(callResult == null){
                return;
            }
            service.returns(callResult);
        }
    }

    public void send(Call call) {
        try {
            channel.send(call);
        } catch (SuspendExecution suspendExecution) {
            suspendExecution.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Object getWait(Call call) throws SuspendExecution, InterruptedException {
        Object obj = service.getWait(call, this);
//        try {
//            obj = service.getWait(call, this);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (SuspendExecution suspendExecution) {
//            suspendExecution.printStackTrace();
//        }

        return obj;
    }
}
