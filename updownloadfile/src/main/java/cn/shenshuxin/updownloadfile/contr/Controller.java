package cn.shenshuxin.updownloadfile.contr;

import cn.shenshuxin.updownloadfile.config.MyAutowired;
import cn.shenshuxin.updownloadfile.mybatismapper.MapperA;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Properties;

@Slf4j
@RestController
public class Controller {

    @Value("${ssx.up.path}")
    private String upPath;

    @PostMapping(value = "/up",consumes = "multipart/form-data")
    public String  upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("上传文件:{}", file.getOriginalFilename());
        File dest = new File(upPath + file.getOriginalFilename());
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
        return "OK";
    }

    @Autowired
    MyAutowired myAutowired;
    @GetMapping("/test")
    public String test(){
        Class<? extends MyAutowired> aClass = myAutowired.getClass();
        return "test";
    }

    @Autowired
    MapperA mapperA;

    @GetMapping("/mybatis")
    public String mybatis(@RequestParam(defaultValue = "defV") String some, HttpServletRequest request) {
        String r = mapperA.querySomeThingForTest(some);

        return r;
    }

    @GetMapping("/original/mybatis")
    public String originalmybatis(@RequestParam(defaultValue = "defV") String some, HttpServletRequest request) throws IOException {
        try (InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml")){
            List<String> list = List.of("spring.datasource.url", "spring.datasource.username", "spring.datasource.password");
            Properties properties = new Properties();
            list.forEach(l->{
                properties.setProperty(l, myAutowired.environment.getProperty(l));
            });

            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream, properties);
            try (SqlSession sqlSession = build.openSession()) {
                MapperA mapper = sqlSession.getMapper(MapperA.class);
                String s = mapper.selectBlog(some);
                return s;
            }
        }
    }
}
