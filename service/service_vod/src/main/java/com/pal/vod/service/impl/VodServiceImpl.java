package com.pal.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.pal.servicebase.exceptionhadler.MyException;
import com.pal.vod.service.Vodservice;
import com.pal.vod.utils.ConstantVodUtils;
import com.pal.vod.utils.InitVodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Author pal
 * @Date Created in 2020/10/21 16:23
 * @Version: 1.0
 */
@Service
public class VodServiceImpl implements Vodservice {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename(); // 上传文件原始名称
            // 上传之后显示的名称，去掉后缀名
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) {
        try{
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            String strIds = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(strIds);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001, "删除视频失败");
        }
    }
}
