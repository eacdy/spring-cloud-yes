package com.itmuch.yes.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext
     *
     * @return applicationContext
     */
    private static ApplicationContext getApplicationContext() {
        return ApplicationContextProvider.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name bean的名称
     * @return bean
     */
    public static Object getBean(String name) {
        return ApplicationContextProvider.getApplicationContext()
                .getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz class
     * @param <T>   T
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return ApplicationContextProvider.getApplicationContext()
                .getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  bean的名称
     * @param clazz bean的class
     * @param <T>   T
     * @return bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return ApplicationContextProvider.getApplicationContext()
                .getBean(name, clazz);
    }
}