package com.zy.jungletest.Java8;

/**
 * Created by Jungle on 2018/9/30 0030.
 *
 * @desc TODO
 */

public class Car {

    //Supplier是jdk1.8的接口，这里和lambda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
}
