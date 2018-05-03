package com.minch.summer.framework.bean;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/5/2
 * Time: 16:18
 */
public class View {

    private String path;

    private Map<String,Object> model;

    public View(String path){
        this.path = path;
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
