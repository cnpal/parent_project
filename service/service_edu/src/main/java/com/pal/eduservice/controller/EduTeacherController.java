package com.pal.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pal.commonutils.R;
import com.pal.eduservice.entity.EduTeacher;
import com.pal.eduservice.entity.vo.TeacherQuery;
import com.pal.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author pal
 * @since 2020-08-31
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    //  1.  查询所有
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAll() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("item", list);
    }

    //    2.逻辑删除
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //    3.分页查询讲师的方法
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页
        //在调用方法的时候，底层会进行封装，将我们分页的所有数据封装到pageTeacher对象里
        teacherService.page(pageTeacher, null);

        //获取总记录数
        long total = pageTeacher.getTotal();

        //每页数据的list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        //        两种返回结果的方式
//        1、
//        Map map = new HashMap();
//        map.put("total", total);
//        map.put("rows", records);
//        return R.ok().data(map);
//        2、
        return R.ok().data("total",total).data("rows",records);
    }
    //4、条件查询带分页的方法
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构造条件，多条件组合查询
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空就拼接条件。有点类似mybatis里学的动态sql
        String name = teacherQuery.getName();
        String end = teacherQuery.getEnd();
        String begin = teacherQuery.getBegin();
        Integer level = teacherQuery.getLevel();
        //如果名字不为空，进行模糊查询
        if(!StringUtils.isEmpty(name)){ wrapper.like("name", name); }
        if(!StringUtils.isEmpty(end)){ wrapper.le("gmt_modified", end); }
        if(!StringUtils.isEmpty(begin)){ wrapper.ge("gmt_create", begin); }
        if(!StringUtils.isEmpty(level)){ wrapper.eq("level", level); }

        //按时间排序，让新加的数据显示在第一行
        wrapper.orderByDesc("gmt_create");

        //调用方法
        teacherService.page(pageTeacher, wrapper);

        //获取总记录数
        long total = pageTeacher.getTotal();

        //每页数据的list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    //5.添加讲师接口的方法
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //6、根据讲师id进行查询
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    //7、讲师修改
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

