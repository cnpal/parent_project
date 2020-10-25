package com.pal.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pal.eduservice.client.VodClient;
import com.pal.eduservice.entity.EduVideo;
import com.pal.eduservice.mapper.EduVideoMapper;
import com.pal.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author pal
 * @since 2020-10-03
 */
@Service

public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
//    1.根据课程id删除小结
    @Override
    public void removeVideoByCourseId(String courseId) {
        //根据课程id查询课程所有的视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");//查询指定的列
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        List<String> videoIdList = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            if(StringUtils.isEmpty(eduVideo.getVideoSourceId()))
            videoIdList.add(eduVideo.getVideoSourceId());
        }
        //根据多个视频的id删除视频
        if (videoIdList.size()>0){
            vodClient.deleteBatch(videoIdList);
        }
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
