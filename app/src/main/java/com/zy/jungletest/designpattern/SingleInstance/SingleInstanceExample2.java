package com.zy.jungletest.designpattern.SingleInstance;

/**
 * // 调用过程说明：
 // 1. 外部调用类的newInstance()
 // 2. 自动调用Singleton2.ourInstance
 // 2.1 此时单例类Singleton2得到初始化
 // 2.2 而该类在装载 & 被初始化时，会初始化它的静态域，从而创建单例；
 // 2.3 由于是静态域，因此只会JVM只会加载1遍，Java虚拟机保证了线程安全性
 // 3. 最终只创建1个单例
 * Created by Jungle on 2018/3/19.
 */

public class SingleInstanceExample2 {
    // 1. 创建静态内部类
    private static class SingleInstanceExampleInner {
        // 在静态内部类里创建单例
        private static SingleInstanceExample2 instance = new SingleInstanceExample2();
    }

    // 私有构造函数
    private SingleInstanceExample2() {

    }

    // 延迟加载、按需创建
    public static SingleInstanceExample2 getInstance() {
        return SingleInstanceExampleInner.instance;
    }
}
