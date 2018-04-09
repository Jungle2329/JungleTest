package com.zy.jungletest.designpattern.SimpleFactoryPattern;

/**
 * Created by Jungle on 2018/3/19.
 */

public class Factory {

    public static Phone getPhone(String string) throws Exception {
        switch (string) {
            case "mi":
                return new Mi();
            case "apple":
                return new Apple();
            case "nokia":
                return new Nokia();
            default:
               throw new Exception(new NullPointerException("没东西"));
        }
    }
}
