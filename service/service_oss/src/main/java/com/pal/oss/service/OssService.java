package com.pal.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author pal
 * @Date Created in 2020/9/28 20:52
 * @Version: 1.0
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
