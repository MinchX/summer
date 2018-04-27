package com.minch.summer.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/4/26
 * Time: 14:03
 */
public class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e){
            logger.error("新建实例失败!",e);
            throw new RuntimeException();
        }
        return instance;
    }

    public static Object invokeMethod(Object object, Method method,Object... args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object,args);
        }catch (Exception e){
            logger.error("调用方法失败！",e);
            throw new RuntimeException();
        }
        return result;
    }

    public static void setField(Object object, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(object,value);
        }catch (Exception e){
            logger.error("成员变量设值失败！",e);
            throw new RuntimeException();
        }
    }

}
