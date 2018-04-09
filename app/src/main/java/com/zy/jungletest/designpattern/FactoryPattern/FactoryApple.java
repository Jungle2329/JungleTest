package com.zy.jungletest.designpattern.FactoryPattern;

import com.zy.jungletest.designpattern.SimpleFactoryPattern.Phone;

/**
 * Created by Jungle on 2018/3/19.
 */

public class FactoryApple extends Factory {
    @Override
    public Phone production() {
        return new Apple();
    }
}
