package com.reservemate.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.OSSObjectSummary;
import com.reservemate.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OssServiceImpl implements OssService {

    private final OSS ossClient;
    private final String bucketName;
    private final String fileHost;
    private final String endpoint;

    public OssServiceImpl(OSS ossClient,
                          @Value("${aliyun.oss.bucketName}") String bucketName,
                          @Value("${aliyun.oss.fileHost}") String fileHost,
                          @Value("${aliyun.oss.endpoint}") String endpoint) {
        this.ossClient = ossClient;
        this.bucketName = bucketName;
        this.fileHost = fileHost;
        this.endpoint = endpoint;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // 构造 objectKey: uploads/2025/04/19/uuid.ext
            String datePath = "reserve_mate/image";
            String original = file.getOriginalFilename();
            String ext = original != null && original.contains(".")
                    ? original.substring(original.lastIndexOf('.'))
                    : "";
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String objectName = datePath + "/" + uuid + ext;

            // 上传
            ossClient.putObject(bucketName, objectName, file.getInputStream());

            // 构造外链 URL
            return String.format("https://%s.%s/%s", bucketName, endpoint, objectName);
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    @Override
    public List<String> listFileUrls() {
        // 列出 fileHost 下所有文件
        ObjectListing listing = ossClient.listObjects(bucketName, fileHost + "/");
        return listing.getObjectSummaries().stream()
                .map(OSSObjectSummary::getKey)
                .map(key -> String.format("https://%s.%s/%s", bucketName, endpoint, key))
                .collect(Collectors.toList());
    }
}
