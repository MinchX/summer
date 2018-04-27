package com.minch.summer.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Minch
 * Date: 2018/4/25
 * Time: 16:46
 */
public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    public Properties loadProps(String propsPath){
        Properties properties = new Properties();

        InputStream inputStream = null;
        try {
            if (StringUtil.isEmpty(propsPath)){
                throw new IllegalAccessException();
            }
        }
        properties.load(new FileInputStream(new File(path)));
        return properties;
    }

}
