package com.wcq.kotlin

fun Any?.toString() : String{
    if(this == null){
        return "null"
    }
    return toString()
}
fun main(args : Array<String>){
    var a = null
    var b : Int = 2
    println(a.toString())
    println(b.toString())
}
