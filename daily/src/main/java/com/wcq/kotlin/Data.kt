package com.wcq.kotlin

data class User(val name : String){
    fun copy() : User{
        return User("bb")
    }
}