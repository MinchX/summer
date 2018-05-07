package com.minch.summer.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/5/3
 * Time: 11:22
 */
public class StreamUtil {

    private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static String getString(ServletInputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            logger.error("获取字符串失败",e);
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
