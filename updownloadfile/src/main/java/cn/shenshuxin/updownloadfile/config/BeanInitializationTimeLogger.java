package cn.shenshuxin.updownloadfile.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class BeanInitializationTimeLogger implements BeanPostProcessor, CommandLineRunner {

    private final Map<String, Long> startTimeMap = new TreeMap<>();

    private final Map<Long, List<String>> map = new TreeMap<>(Collections.reverseOrder());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        startTimeMap.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Long startTime = startTimeMap.get(beanName);
        if (startTime != null) {
            long endTime = System.currentTimeMillis();
            long c = endTime - startTime;
            map.compute(c, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(beanName);
                return v;
            });

            log.debug("Bean '{}' initialized in {} ms", beanName, c);
        }
        return bean;
    }

    @Override
    public void run(String... args) {
        map.forEach((k, v) -> {
            log.debug("Bean initialization time: {} ms,size:{}, list:{}", k,v.size(), v);
        });
    }
}