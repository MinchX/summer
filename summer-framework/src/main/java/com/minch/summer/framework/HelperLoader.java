package com.minch.summer.framework;

import com.minch.summer.framework.helper.BeanHelper;
import com.minch.summer.framework.helper.ClassHelper;
import com.minch.summer.framework.helper.ControllerHelper;
import com.minch.summer.framework.helper.IocHelper;
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
        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,IocHelper.class,ControllerHelper.class};
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }

}
