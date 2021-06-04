package com;

import com.annotation.Controller;
import com.helper.*;
import com.utils.ClassUtil;

public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(), false);
        }
    }
}

