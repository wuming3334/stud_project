package com.itwu.Controller.impl;

import com.itwu.Utils.AliyunOSSOperator;
import com.itwu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController

public class UploadController {
    @Autowired
    AliyunOSSOperator aliyunOSSOperator;
    /**
     *注:此方案为保存到本地磁盘的方案
     * 上传文件前提:具备前端三要素 : 1.表单提交方式:post 2.enctype="multipart/form-data" 3.input标签的type="file"
     * 服务器端方面:形参需要为MultipartFile 且形参名称必须与input标签的name属性一致
     *上传的文件回保存在服务器端的临时文件夹中
     */
    /*@PostMapping("/upload")
    public Result upload(String name, String age, MultipartFile file) throws Exception {
        log.info("接收参数: {}, {}, {}", name, age, file);
        //获取上传的文件名
        String originalFilename = file.getOriginalFilename();
        */
    /**
     * 使用uuid和substring方法截取后缀合成新文件名
     */
    /*
        String newFileName =  UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存文件到本地服务器磁盘
        file.transferTo(new File("D:\\Develop_Tool\\javase-basic-code\\web-ai-code\\Image_server" , newFileName ));
        return Result.success();
    }*/

    /**
     *
     * 注:此方案为保存到阿里云oss的方案
     * 需要返回一个 url,这个url是保存在阿里云oss中的图片访问路径
     * 返回访问路径便于前端访问展示
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {

        //获取上传的文件名
        String originalFilename = file.getOriginalFilename();
        log.info("文件上传:  {}", originalFilename);
       String url = aliyunOSSOperator.upload(file.getBytes(), originalFilename);
       log.info("文件上传oss 的url: {}", url);
        //保存文件到本地服务器磁盘
        return Result.success(url);
    }
}
