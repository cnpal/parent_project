package com.pal.vod.controller;

import com.pal.commonutils.R;
import com.pal.vod.service.Vodservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author pal
 * @Date Created in 2020/10/21 16:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    @Autowired
    private Vodservice vodservice;
    //上传视频到阿里云
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){
//        返回上传视频id
        String videoId = vodservice.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }
}
