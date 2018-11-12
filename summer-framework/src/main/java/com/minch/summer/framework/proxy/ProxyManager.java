package com.minch.summer.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/11/8
 * Time: 14:51
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass,o,method,methodProxy,objects,proxyList).doProxyChain();
            }
        });
    }

}
