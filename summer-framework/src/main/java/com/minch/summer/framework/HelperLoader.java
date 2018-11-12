package com.minch.summer.framework;

import com.minch.summer.framework.helper.*;
import com.minch.summer.framework.util.ClassUtil;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/5/2
 * Time: 16:05
 */
public class HelperLoader {

    public static void  init(){
        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,AopHelper.class,IocHelper.class,ControllerHelper.class};
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }

}
