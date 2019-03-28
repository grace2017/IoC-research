package com.qimingnan.core;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;

public class JSONApplicationContext extends BeanFactoryImpl {

    private String jsonFile;

    public JSONApplicationContext(String jsonFile) {
        this.jsonFile = jsonFile;

        loadFile();
    }

    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonFile);

        byte[] data = new byte[1024];

        try {
            int readSize = is.read(data);

            registerBeans(JSON.parseArray(new String(data)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
