package com.qimingnan.core;


import com.qimingnan.annotation.Component;
import com.qimingnan.annotation.ComponentScan;

import java.io.File;

/**
 *  优化：
 *      如何提供包扫描速度
 *
 */
public class AnnotationApplicationContext extends BeanFactoryImpl {

    private Class configClass;

    public AnnotationApplicationContext(Class appConfigClass) {
        this.configClass = appConfigClass;

        try {
            componentScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void componentScan() throws Exception {
        // 检测是否被@ComponentScan修饰
        ComponentScan componentScan = (ComponentScan) this.configClass.getAnnotation(ComponentScan.class);
        if (null == componentScan) {
            throw new RuntimeException("配置文件未检测到@ComponentScan");
        }

        // 获取扫描路径
        String value = componentScan.value();
        if (null == value || 0 == value.length()) {
            throw new RuntimeException("@ComponentScan的value不得为空");
        }

        // 计算出指定扫描路径的绝对路径
        String classesPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = value.replace('.', File.separatorChar);
        String completePath = classesPath + path;

        File file = new File(completePath);

        if (null == file && !file.exists()) {
            throw new RuntimeException("不存在的目录: " + completePath);
        }

        if (!file.isDirectory()) {
            throw new RuntimeException("不是一个目录: " + completePath);
        }

        // 开始扫描
        for (File fileItem: file.listFiles()) {
            if (fileItem.isDirectory()) {
                for (File fileItem2: fileItem.listFiles()) {
                    getAllComponent(fileItem2, classesPath);
                }
            } else if (fileItem.isFile()) {
                getAllComponent(fileItem, classesPath);
            } else {
                // 不是文件、目录
            }
        }
    }

    private void getAllComponent(File fileItem, String classesPath) {
        String className = fileItem.getPath().replace(classesPath, "");
        className = className.replace(File.separatorChar, '.');
        className = className.replace(".class", "");

        // 获取Class对象
        Class clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (null == clazz) {
            System.out.println("不合法的class文件: " + fileItem);

            return;
        }

        // 获取每个对象的注解，如果被@Component修饰就存储
        if (null != clazz.getAnnotation(Component.class)) {
            registerBeans(fileItem.getName().replace(".class", "").toLowerCase(), className);
        }
    }
}
