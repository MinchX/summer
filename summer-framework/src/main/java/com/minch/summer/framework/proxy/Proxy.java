package com.minch.summer.framework.proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/11/7
 * Time: 22:33
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
