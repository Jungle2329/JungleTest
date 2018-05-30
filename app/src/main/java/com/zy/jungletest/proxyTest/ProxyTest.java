package com.zy.jungletest.proxyTest;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Jungle on 2018/5/16 0016.
 *
 * @desc TODO
 */

public class ProxyTest {

    private final ProxyInterface mProxyInterface;

    public ProxyTest() {
        mProxyInterface = new OperationProxy();
    }

    public ProxyInterface getProxy(Context context) {
        return (ProxyInterface)Proxy.newProxyInstance(context.getClassLoader(), mProxyInterface.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("eat") ) {
                    Log.i("zhangyi", "吃爽你小子");
                }
                return method.invoke(mProxyInterface, args);
            }
        });
    }

}
