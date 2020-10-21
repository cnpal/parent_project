package com.pal.oss.controller;

import com.pal.commonutils.R;
import com.pal.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author pal
 * @Date Created in 2020/9/28 20:51
 * @Version: 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法，MultipartFile获取上传文件
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url", url);
    }
}