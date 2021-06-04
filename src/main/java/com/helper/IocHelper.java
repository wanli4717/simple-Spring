package com.helper;

import com.annotation.Inject;
import com.utils.CollectionUtil;
import com.utils.ReflectionUtil;
import com.utils.ArrayUtil;


import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Handler;

public class IocHelper {

    static {
        // 获取所有的Bean类与Bean实例之间的映射关系（简称Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历Bean Map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类定义的所有成员变量（简称Bean Field）
                Field[] beanFileds = beanClass.getDeclaredFields();

                if (ArrayUtil.isNotEmpty(beanFileds)) {
                    // 遍历 Bean Filed
                    for (Field beanField : beanFileds) {
                        // 判断当前Bean Field是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 在 Bean Map中获取Bean Field对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                // 通过反射初始化 BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
    
}
