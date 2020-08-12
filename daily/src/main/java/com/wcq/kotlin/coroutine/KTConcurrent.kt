package com.wcq.kotlin.coroutine

import com.wcq.kotlin.coroutine.Test.deal
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

fun batch(list : MutableList<Int>) : MutableList<String>{
    var result = ArrayList<String>()
    runBlocking (Dispatchers.Unconfined){
        println("${Thread.currentThread().name}" + " 开始执行")
        var jobList = ArrayList<Deferred<String>>()
        for (i in list){
            var deferred = GlobalScope.async (Dispatchers.Default){
                println("${Thread.currentThread().name}" + " work")
                return@async Test.deal(i)
            }

            jobList.add(deferred)
        }

        for (j in jobList){
            var v = j.await()
            result.add(v)
        }
    }


    return result
}

fun CoroutineScope.counterActor() = actor<Int>{

}


fun batch1(list: MutableList<Int>): MutableList<String> {
    var result = ArrayList<String>()
    for (i in list) {
//        result.add(deal(i))
        result.add(deal(i))
    }
    return result
}

//fun deal(i: Int): String {
//    return Thread.currentThread().name + "  " + i + "result"
//}
