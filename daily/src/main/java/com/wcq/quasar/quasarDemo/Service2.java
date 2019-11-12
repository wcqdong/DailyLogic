package com.wcq.quasar.quasarDemo;

public class Service2 extends Service{

    public Service2(String serviceName, Port port) {
        super(serviceName, port);
    }

    @Override
    public void doSome1(Call call, PortFiber portFiber) {

    }

    @Override
    public Call returnSome2(Call call, PortFiber portFiber) {
        System.out.println(serviceName + " 执行方法returnSome2");
        Call result = call.createReturn();
        return result;

    }
}
