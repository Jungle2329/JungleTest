package com.zy.commonlibrary.example;

/**
 * Created by Jungle on 2018/7/27 0027.
 * 使用静态内部类创建懒汉式单例模式
 * @desc TODO
 */

public class SingleInstance {
    //创建静态内部类，静态内部类在外部类被加载的时候被创建一次
    private static class SingleInstanceInner {
        //静态内部类里创建单例
        private static SingleInstance ourInstance = new SingleInstance();
    }
    //防止被创建对象
    private SingleInstance() {

    }
    //延迟加载，按需创建
    public static SingleInstance getInstance() {
        return SingleInstanceInner.ourInstance;
    }
}
