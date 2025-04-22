package com.reservemate.controller;

import com.reservemate.pojo.vo.Result;
import com.reservemate.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    /** 上传单个文件 */
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> upload(@RequestPart("file") MultipartFile file) {
        System.out.println("saassa");
        if (file == null || file.isEmpty()) {
            return Result.failure("未检测到上传文件");
        }
        String url = ossService.uploadFile(file);
        return Result.success(url);
    }

    /** 列出所有已上传文件 URL */
    @GetMapping("/list")
    public Result<List<String>> list() {
        List<String> urls = ossService.listFileUrls();
        return Result.success(urls);
    }
}
