package com.minch.summer.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/5/3
 * Time: 14:50
 */
public class JsonUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("json生成失败！",e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json,Class<T> cls){
        T result;
        try {
            result = OBJECT_MAPPER.readValue(json,cls);
        } catch (Exception e) {
            logger.error("json转object失败！",e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
