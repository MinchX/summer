package com.minch.summer.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/5/3
 * Time: 11:21
 */
public class CodecUtil {

    private final static Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    public static String encodeURL(String sourceStr){
        String target;
        try {
            target = URLEncoder.encode(sourceStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("URL编码失败！",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeURL(String sourceStr) {
        String target;
        try {
            target = URLDecoder.decode(sourceStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("解析url编码失败！",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
