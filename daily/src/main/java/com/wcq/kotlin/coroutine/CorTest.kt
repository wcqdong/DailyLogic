package com.wcq.kotlin.coroutine
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

val channel = Channel<String>()

suspend fun getToken(): String {
//    delay(100)
//    println("getToken start ${System.currentTimeMillis()}")
    var result = channel.receive()
    var result1 = channel.receive()
//    println("getToken end ${result} ${System.currentTimeMillis()}")

    println("getToken 开始执行${result}  ${result1}，${Thread.currentThread().name}")
    return result
}

suspend fun sendToken() {
    delay(500)
    channel.send("sendMsg")
}

suspend fun getResponse(token: String): String {
    delay(500)
    channel.send("1235")
    channel.send("333")
//    channel.close()
    println("getResponse 开始执行，${Thread.currentThread().name}")

    return "response"
}

fun setText(response: String) {
    println("setText 执行，${Thread.currentThread().name}")
}



fun main(args : Array<String>) {

    GlobalScope.launch (Dispatchers.Unconfined){
        println("协程 开始执行，${Thread.currentThread().name}")
        var token = GlobalScope.async(Dispatchers.Unconfined) {
            return@async getToken()
        }

        var response = GlobalScope.async(Dispatchers.Unconfined) {
            return@async getResponse("1")
        }

        setText("2")
    }

    println("end!!")
    Thread.sleep(2000)

    // 运行代码
//    var job : Job = GlobalScope.launch{
////        print("协程 开始执行，时间:  ${System.currentTimeMillis()}")
////        val token = getToken()
////        val response = getResponse(token)
////        setText(response)
//        print(11)
//    }
//    Thread.sleep(200)

//    GlobalScope.launch {
//        delay(1000L)
//        print("Coroutines")
//    }
//    print("Hello, ")
//    runBlocking {
//        delay(2000L)
//
//    }


}

