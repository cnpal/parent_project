package com.pal.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author pal
 * @Date Created in 2020/10/4 16:53
 * @Version: 1.0
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    //章节里表示小节
    List<VideoVo> children = new ArrayList<>();
}