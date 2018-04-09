package com.zy.jungletest.designpattern.SingleInstance;

/**
 * 单例模式，懒汉式线程安全版本，最好用的版本
 * 使用synchronized枷锁避免线程安全问题
 *
 * Created by Jungle on 2018/3/19.
 */

public class SingleInstanceExample1 {

    private static SingleInstanceExample1 instance = null;

    private SingleInstanceExample1() {

    }

    public static SingleInstanceExample1 getInstance() {
        if (instance == null) {//避免加锁开销
            synchronized (SingleInstanceExample1.class) {//给创建实例的这个操作加锁
                if (instance == null) {//单例
                    instance = new SingleInstanceExample1();
                }
            }
        }

        return instance;
    }


}
