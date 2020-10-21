package com.pal.eduservice.entity.chapter;

import lombok.Data;

/**
 * @Author pal
 * @Date Created in 2020/10/4 16:54
 * @Version: 1.0
 */
@Data
public class VideoVo {
    private String id;
    private String title;

    // 视频id
    private String videoSourceId;
}
