package com.pal.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author pal
 * @Date Created in 2020/10/8 15:54
 * @Version: 1.0
 */

@Data
public class CourseQuery implements Serializable {
    private String title;
    private String status;
}
