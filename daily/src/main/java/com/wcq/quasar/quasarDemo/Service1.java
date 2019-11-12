package com.wcq.quasar.quasarDemo;

import co.paralleluniverse.fibers.SuspendExecution;

public class Service1 extends Service{

    public Service1(String serviceName, Port port) {
        super(serviceName, port);
    }

    @Override
    public void doSome1(Call call, PortFiber portFiber) throws InterruptedException, SuspendExecution {
        long time = System.currentTimeMillis();
        System.out.println(serviceName + " 执行方法doSome1 +++++++++++++");
        Call callTo = new Call();
        callTo.type = 1;
        callTo.toPort = "port2";
        callTo.toService = "service2";
        callTo.methodId = 2;

        callTo.fromPort = "port1";
        callTo.fromService = "service1";
        callTo.fromFiber = portFiber.fiberId;


        Object returnObj = portFiber.getWait(callTo);

        System.out.println(serviceName + "的doSome1方法执行完毕 +++++++++++");
        System.out.println(System.currentTimeMillis() - time);
    }

    @Override
    public Call returnSome2(Call call, PortFiber portFiber) {
        System.out.println(serviceName + " 执行方法returnSome2 -------------");

        return null;
    }
}
