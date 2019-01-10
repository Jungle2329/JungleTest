package com.zy.jungletest.model


/**
 * Created by Jungle on 2018/10/19 0019.
 * @desc TODO
 */
open class Enemy(level: Int){

    var hp = 100
    var mp = 100
    var att = 100
    var age = 0
    var level = 100

    constructor(level: Int, age: Int) : this(level) {
        println(level)
        this.age = age
    }

    constructor(level: Int, age: Int, name: String) : this(level) {
        println(level)
        println(age)
        println(name)
        this.age = age
    }

    init {
        this.level = level
    }

    fun test() {
        println(level)
        age
    }

}