package com.pal.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pal.eduservice.entity.EduCourse;
import com.pal.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author pal
 * @since 2020-10-03
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

//    CourseWebVo getBaseCourseInfo(String courseId);
}
