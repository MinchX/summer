package com.minch.summer.framework.helper;

import com.minch.summer.framework.annotation.Inject;
import com.minch.summer.framework.util.ArrayUtil;
import com.minch.summer.framework.util.CollectionUtil;
import com.minch.summer.framework.util.ReflectionUtil;

import java.util.Arrays;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/4/28
 * Time: 15:05
 */
public class IocHelper {

    static {
        //获取所有bean的class和object的对应关系
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)){

            beanMap.forEach((beanClass,beanObject)->{
                if (ArrayUtil.isNotEmpty(beanClass.getDeclaredFields())){
                    Arrays.stream(beanClass.getDeclaredFields()).forEach(beanField->{
                        if (beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldinstance = beanMap.get(beanFieldClass);
                            if (beanFieldinstance != null) {
                                ReflectionUtil.setField(beanObject,beanField,beanFieldinstance);
                            }
                        }
                    });
                }
            });
/**
            for (Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)){
                    for (Field beanField:beanFields){
                        if (beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
 **/
        }
    }

}
