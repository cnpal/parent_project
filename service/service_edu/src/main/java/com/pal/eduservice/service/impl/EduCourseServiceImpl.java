package com.pal.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pal.eduservice.entity.EduCourse;
import com.pal.eduservice.entity.EduCourseDescription;
import com.pal.eduservice.entity.vo.CourseInfoVo;
import com.pal.eduservice.entity.vo.CoursePublishVo;
import com.pal.eduservice.mapper.EduCourseMapper;
import com.pal.eduservice.service.EduChapterService;
import com.pal.eduservice.service.EduCourseDescriptionService;
import com.pal.eduservice.service.EduCourseService;
import com.pal.eduservice.service.EduVideoService;
import com.pal.servicebase.exceptionhadler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pal
 * @since 2020-10-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    //注入小节和章节service
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduCourseService courseService;
    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1、向课程表里添加课程基本信息
        //courseInfoVo转换为eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse); // 返回值是影响行数，就是返回添加了几条数据
        //如果没有成功添加数据就抛出异常
        if(insert <= 0) {
            //添加课程信息失败
            throw new MyException(20001, "添加课程信息失败");
        }

        //获取添加之后的课程id
        String cid = eduCourse.getId();

        //2、向课程简介表edu_course_description添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置课程描述的id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }
    //根据课程查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new MyException(20001, "修改课程信息失败");
        }

        //修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }


    // 根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //1.根据课程id删除小结
        videoService.removeVideoByCourseId(courseId);
        // 2、根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        // 3、根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        // 4、根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);

        if(result == 0){
            throw new MyException(20001, "删除失败");
        }
    }

}
