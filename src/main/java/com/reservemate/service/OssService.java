package com.reservemate.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OssService {

    /**
     * 上传文件到 OSS，并返回可访问的 URL
     */
    String uploadFile(MultipartFile file);

    /**
     * 列出指定前缀下的所有文件 URL
     */
    List<String> listFileUrls();
}
