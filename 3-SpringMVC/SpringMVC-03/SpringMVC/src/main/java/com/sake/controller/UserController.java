package com.sake.controller;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger=org.apache.log4j.Logger.getLogger(UserController.class);

    /**
     * 传统的文件上传方式
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload1")
    public String fileUpLoad1(HttpServletRequest request) throws Exception {
        logger.debug("文件上传...");

        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        //判断该路径是否存在
        File file =new File(path);
        if (!file.exists()){
           //创建该文件夹
           file.mkdirs();
        }

        //解析request对象，获取上传文件项
        DiskFileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历
        for (FileItem fileItem : fileItems) {
            //判断fileItem对象是否是上传文件项
            if (fileItem.isFormField()){
                //是普通表单项
                logger.debug("是普通表单项..");

            }else{
                //是上传文件项
                //获取上传文件的名称
                String filename = fileItem.getName();
                //吧文件的名称设置为唯一值
                String uuid = UUID.randomUUID().toString().replace("-","");
                filename=uuid+"_"+filename;
                //完成文件上传
                fileItem.write(new File(path,filename));
                //删除临时文件
                fileItem.delete();
            }
        }
        return "success";
    }

    @RequestMapping("/fileupload2")
    public String fileUpLoad2(HttpServletRequest request,
                              //MultipartFile对象的名称必须与表单中的名称相同
                              MultipartFile upload) throws Exception{
        logger.debug("springmvc文件上传...");
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        //判断该路径是否存在
        File file =new File(path);
        if (!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }

        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //吧文件的名称设置为唯一值
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename=uuid+"_"+filename;
        logger.debug("path:"+path);
        logger.debug("filename:"+filename);
        //完成文件上传
        upload.transferTo(new File(path,filename));
        return "success";
    }

    @RequestMapping("/fileupload3")
    public String fileUpLoad3(MultipartFile upload) throws Exception{
        logger.debug("跨服务器文件上传...");

        //定义上传文件服务器路径
        String path = "http://localhost:9090/uploads/";
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //吧文件的名称设置为唯一值
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename=uuid+"_"+filename;
        logger.debug("path:"+path);
        logger.debug("filename:"+filename);

        //创建客户端对象
        Client client =Client.create();
        //和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);

        //上传文件
        webResource.put(upload.getBytes());

        //完成文件上传
        return "success";
    }

    }
