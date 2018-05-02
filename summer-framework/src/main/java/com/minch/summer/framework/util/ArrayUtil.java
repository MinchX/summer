package com.minch.summer.framework.util;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/4/28
 * Time: 16:37
 */
public class ArrayUtil {

    public static boolean isNotEmpty(Object[] objArray) {
        if (objArray!=null&&objArray.length>0){
            return true;
        }
        return false;
    }
}
