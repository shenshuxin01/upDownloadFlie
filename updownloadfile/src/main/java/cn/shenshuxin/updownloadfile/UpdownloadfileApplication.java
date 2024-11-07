package cn.shenshuxin.updownloadfile;

import cn.shenshuxin.updownloadfile.config.SsxStartupStep;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;

@SpringBootApplication
public class UpdownloadfileApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext run = SpringApplication.run(UpdownloadfileApplication.class, args);
//		ApplicationStartup applicationStartup = run.getApplicationStartup();
//		run.getBean(SsxStartupStep.class);
//		applicationStartup.start("");

		SpringApplication springApplication = new SpringApplication(new Class<?>[]{UpdownloadfileApplication.class});
		springApplication.setApplicationStartup(new SsxStartupStep());
		springApplication.setApplicationStartup(null);
		springApplication.run(args);
	}

}
