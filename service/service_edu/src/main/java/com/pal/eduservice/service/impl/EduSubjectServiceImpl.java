package com.pal.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pal.eduservice.entity.EduSubject;
import com.pal.eduservice.entity.excel.SubjectData;
import com.pal.eduservice.entity.subject.OneSubject;
import com.pal.eduservice.entity.subject.TwoSubject;
import com.pal.eduservice.listener.SubjectExcelListener;
import com.pal.eduservice.mapper.EduSubjectMapper;
import com.pal.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author pal
 * @since 2020-09-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
//       1. 查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", 0);
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapperOne);
//        2.查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", 0);
        List<EduSubject> twoSubjects = baseMapper.selectList(wrapperTwo);
        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3.封装一级分类
        //List<EduSubject>转换为List<OneSubject>
        //做法：查询出来所有的一级分类list集合进行比那里，得到每一个一级分类对象，
        //获取每一个一级分类对象值，封装到要求的list集合里面List<OneSubject> finalSubjectList
        for (EduSubject eduSubject : oneSubjects) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);
            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历查询所有的二级分类
            //创建list集合，封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for (EduSubject eduSubject1 : twoSubjects) {
                //判断二级分类的parentid和一级分类的id是否一样
                if (eduSubject1.getParentId().equals(eduSubject.getId())) {
                    //4、封装二级分类
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject1, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }
//        封装二级分类

        return finalSubjectList;
    }
}
