package com.pal.eduservice.service;

import com.pal.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pal.eduservice.entity.vo.CourseInfoVo;
import com.pal.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pal
 * @since 2020-10-03
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);
}
