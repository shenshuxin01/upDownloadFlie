package cn.shenshuxin.updownloadfile.contr;

import cn.shenshuxin.updownloadfile.config.MyAutowired;
import cn.shenshuxin.updownloadfile.mybatismapper.MapperA;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
}
