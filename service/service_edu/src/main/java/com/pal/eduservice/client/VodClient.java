package com.pal.eduservice.client;

import com.pal.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-vod")
public interface VodClient {
    //定义调用的方法路径
    // 根据视频id删除阿里云中视频

    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    //PathVariable一定要指定参数名称，否则会出错
    public R removeAlyVideo(@PathVariable("id") String id);
}
