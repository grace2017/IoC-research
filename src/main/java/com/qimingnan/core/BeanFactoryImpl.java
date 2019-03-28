package com.qimingnan.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {

    private Map<String, String> beanMap = new HashMap<String, String>();

    public Object getBean(String name) {
        String className = beanMap.get(name);
        if (null == className) {
            return null;
        }

        try {
            Class clazz = Thread.currentThread().getContextClassLoader().loadClass(className);

            return new DynamicProxy(clazz).getProxyObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object getBean2(String name) {



        return null;
    }

    public void registerBeans(JSONArray array) {
        for (Object data: array) {
            JSONObject jsonObject = (JSONObject) data;

            beanMap.put(jsonObject.getString("name"), jsonObject.getString("class"));
        }
    }

    public Map<String, String> getBeans() {
        return beanMap;
    }
}
