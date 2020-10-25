package com.pal.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.pal.commonutils.R;
import com.pal.servicebase.exceptionhadler.MyException;
import com.pal.vod.service.Vodservice;
import com.pal.vod.utils.ConstantVodUtils;
import com.pal.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author pal
 * @Date Created in 2020/10/21 16:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private Vodservice vodservice;
    //上传视频到阿里云
    @PostMapping("/uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){
//        返回上传视频id
        String videoId = vodservice.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }
    // 根据视频id删除阿里云中视频
    @DeleteMapping("/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        try{
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001, "删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList")List<String> videoIdList){
        vodservice.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }
}
