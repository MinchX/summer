package com.minch.summer.framework.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/4/28
 * Time: 16:32
 */
public class CollectionUtil {
    public static boolean isNotEmpty(Map<Class<?>, Object> beanMap) {
        if (beanMap!=null&&!beanMap.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Collection<Class<?>> collection) {
        if (collection!=null&&collection.size()>0){
            return true;
        }
        return false;
    }
}
