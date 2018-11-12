package com.minch.summer.framework.helper;

import com.minch.summer.framework.aop.annotation.Aspect;
import com.minch.summer.framework.proxy.AspectProxy;
import com.minch.summer.framework.proxy.Proxy;
import com.minch.summer.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/11/8
 * Time: 16:02
 */
public final class AopHelper {

    static {
        try {
            //获取代理配置类和代理目标类之间的关系
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();

            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            //根据代理目标类和代理list创建代理bean
            targetMap.forEach((k,v)->{
                Object proxy = ProxyManager.createProxy(k,v);
                BeanHelper.setBean(k,proxy);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation!=null&&!annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<>();
        //获取aspect代理配置类
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        //获取aspectproxy的子类，并且带aspect注解，根据aspect注解找到切面注解，根据切面注解找到代理的目标类
        proxyClassSet.forEach(cls->{
            if (cls.isAnnotationPresent(Aspect.class)){
                Aspect aspect = cls.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = null;
                try {
                    targetClassSet = createTargetClassSet(aspect);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                proxyMap.put(cls,targetClassSet);
            }
        });
        return proxyMap;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<>();
        //将代理类和代理目标转换成代理目标类和代理对象
        proxyMap.forEach((proxyClass,targetClassSet)->{
            targetClassSet.forEach(targetClass->{
                Proxy proxy = null;
                try {
                    proxy = (Proxy) proxyClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }
            });
        });
        return targetMap;
    }

}
