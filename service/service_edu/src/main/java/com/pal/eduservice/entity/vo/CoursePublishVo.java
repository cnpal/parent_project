package com.pal.eduservice.entity.vo;

import lombok.Data;

/**
 * @Author pal
 * @Date Created in 2020/10/5 20:48
 * @Version: 1.0
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
