package com.javaclimb.controller;

import com.javaclimb.util.ReturnData;
import com.javaclimb.util.UUIDUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 上传
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping(value = "uploadImg",method = {RequestMethod.POST})
    public ReturnData uploadImg(@PathVariable(value="file") MultipartFile file, HttpServletRequest request){
        String str = file.getOriginalFilename();
        String name = UUIDUtil.getUUID()+str.substring(str.lastIndexOf("."));
        String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+name;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnData.fail("上传失败");
        }
        return ReturnData.success("upload/"+name);
    }
}
