package com.zy.commonlibrary.example;

/**
 * Created by Jungle on 2018/7/27 0027.
 * 恶汉式单例模式
 * @desc TODO
 */

public class SingleInstance2 {
    //在类被加载的时候创建对象，保证了线程安全，实例唯一，但是没有懒汉式的按需加载
    private static SingleInstance2 ourInstance = new SingleInstance2();

    private SingleInstance2() {

    }

    public static SingleInstance2 getInstance() {
        return ourInstance;
    }

}
