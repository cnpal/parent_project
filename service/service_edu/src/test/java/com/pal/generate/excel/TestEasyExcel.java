package com.pal.generate.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author pal
 * @Date Created in 2020/9/29 14:12
 * @Version: 1.0
 */
public class TestEasyExcel {
    public static void main(String[] args) {
//        实现excel写入操作
//        1设置写入文件地址和excel文件名称
        String filename = "E:\\test\\excel\\student.xlsx";
        //2、调用easyexcel里的方法实现写操作
        //write中有两个参数，第一个参数是文件路径的名称，第二个参数是实体类的class
        //.sheet可以指定sheet的名称，.doWrite将数据写到excel中。之后文件流会自动关闭
//        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());

        //实现excel读操作
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    //创建一个方法进行简单测试，返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for(int i  = 0; i < 10; i++){
            DemoData data = new DemoData();
            data.setSid(i);
            data.setSname("Lucy:" + i);
            list.add(data);
        }
        return list;
    }
}
