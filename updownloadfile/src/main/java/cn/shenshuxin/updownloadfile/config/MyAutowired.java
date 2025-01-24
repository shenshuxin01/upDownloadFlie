package cn.shenshuxin.updownloadfile.config;

import cn.shenshuxin.updownloadfile.UpdownloadfileApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyAutowired implements InitializingBean,ApplicationContextAware{

    UpdownloadfileApplication main;
    @Autowired
    private void setMain(UpdownloadfileApplication updownloadfileApplication){
        main= updownloadfileApplication;
    }

    @PostConstruct
    private void p(){
        System.out.println("post");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }
   public   Environment environment;
    /**
     * 实例化bean的时候调用这个方法
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        environment = applicationContext.getEnvironment();
//        Object xxx = applicationContext.getBean("xxx");
//        System.out.println("获取到的env和xxx"+environment+xxx);
    }
}
