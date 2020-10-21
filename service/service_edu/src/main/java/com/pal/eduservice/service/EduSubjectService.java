package com.pal.eduservice.service;

import com.pal.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pal.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author pal
 * @since 2020-09-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
