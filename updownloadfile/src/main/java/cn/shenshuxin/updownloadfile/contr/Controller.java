package cn.shenshuxin.updownloadfile.contr;

import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
