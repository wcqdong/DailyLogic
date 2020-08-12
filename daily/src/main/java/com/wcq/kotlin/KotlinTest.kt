package com.wcq.kotlin

class KotlinTest {

    var a : Int = 1

    /**
       return 整个foo方法返回
     */
    fun foo() {
        val ints = listOf(2, 1, 0, 3)
        ints.forEach {
            if (it == 0) return
            print(it)
        }
        print(11)
    }

    /**
        return@forEach return一次循环
     */
    fun foo1() {

        val ints = listOf(2, 1, 0, 3)
        // @foreach为隐式标签
        ints.forEach {
            if (it == 0) return@forEach
            print(it)
        }
        print(11)

        // 也可以自定义标签
        ints.forEach fe@{
            if (it == 0) return@fe
            print(it)
        }
        print(22)
    }

    fun vars(vararg v : Int){
        for(vv in v){
            print(vv)
        }
    }

    fun printWord(){
        println("this is kotlin")
        Main.printWord()
    }

    fun List<String>.filterValid(): List<String> {
        val result = mutableListOf<String>()
        for (s in this) {
            if (s.length < 5) {
                result.add(s)
            }
        }
        return result.toList()
    }


    @JvmName("fileterValidint")
    fun List<Int>.filterValid(): List<Int> {
        val result = mutableListOf<Int>()
        for (i in this) {
            if (i < 20) {
                result.add(i)
            }
        }
        return result.toList()
    }
}

fun main(args : Array<String>){
    val test = KotlinTest()
    test.vars(1, 2, 3)
    println()
    test.foo()
    var user : User = User("aa")
    println(user.name)
    var user1 = user.copy()
    println(user1.name)
}