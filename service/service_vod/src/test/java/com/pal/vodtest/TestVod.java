package com.pal.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @Author pal
 * @Date Created in 2020/10/21 15:09
 * @Version: 1.0
 */
public class TestVod {
    public static void main(String[] args) throws Exception {
        getPlayAuth();
    }
    // 根据视频id获取视频播放地址
    public static void getPlayUrl () throws Exception {
        // 1、根据视频id获取视频的播放地址
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GANiAjUgRPjAAzT1n8a", "P8HIOB1bc3ibNOJL8UwfASriKOnX19");

        // 2、创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        // 3、向request对象里设置视频id
        request.setVideoId("4069d21c560645e6ba93afaecbcdcfad");

        // 4、调用初始化对象里面的方法传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
    // 根据视频id获取视频播放凭证
    public static void getPlayAuth () throws Exception {
        // 1、根据视频id获取视频播放凭证
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GDfHSLGr5BHF4Hwrjy2", "nR7QRJTL88oD7j3lOsrExBNyB5apwe");

        // 2、创建获取视频地址request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        // 3、向request对象里设置视频id
        request.setVideoId("4069d21c560645e6ba93afaecbcdcfad");

        // 4、调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playauth:" + response.getPlayAuth());
    }
}
