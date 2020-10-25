package com.pal.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Vodservice {
    //上传 视频到阿里云
    String uploadVideoAly(MultipartFile file);
//删除多个阿里云视频
    void removeMoreAlyVideo(List<String> videoIdList);
}
