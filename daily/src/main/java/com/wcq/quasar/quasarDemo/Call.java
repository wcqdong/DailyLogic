package com.wcq.quasar.quasarDemo;

import java.util.Map;

public class Call {
    String fromPort;
    String fromService;
    int fromFiber;

    String toPort;
    String toService;
    int toFiber;
    int methodId;
    Map<String, Object> param;

    int type;

    int p;


    public Call createReturn() {
        Call callTo = new Call();
        callTo.type = 2;
        callTo.toPort = this.fromPort;
        callTo.toService = this.fromService;
        callTo.toFiber = this.fromFiber;

        callTo.fromPort = this.toPort;
        callTo.fromService = this.toService;
        return callTo;
    }
}
