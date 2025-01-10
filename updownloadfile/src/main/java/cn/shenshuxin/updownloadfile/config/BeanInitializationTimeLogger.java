package cn.shenshuxin.updownloadfile.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BeanInitializationTimeLogger implements BeanPostProcessor {

    private final Map<String, Long> startTimeMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        startTimeMap.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Long startTime = startTimeMap.remove(beanName);
        if (startTime != null) {
            long endTime = System.currentTimeMillis();
            log.info("Bean '{}' initialized in {} ms", beanName, endTime - startTime);
        }
        return bean;
    }
}