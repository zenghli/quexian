package com.javaclimb.util;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class UploadUtils {
    public static final String FILE_TYPE_IMAGE = "image";

    /**
     * 获取随机名称
     *
     * @param realName 真实名称
     * @return uuid
     */
    public static String getUUIDName(String realName) {
        //realname  可能是  1.jpg   也可能是  1
        //获取后缀名
        if (realName == null) {
            return UUID.randomUUID().toString().replace("-", "").toUpperCase();
        } else {
            int index = realName.lastIndexOf(".");
            if (index == -1) {
                return UUID.randomUUID().toString().replace("-", "").toUpperCase();
            }
            return UUID.randomUUID().toString().replace("-", "").toUpperCase() + realName.substring(index);
        }
    }

    /**
     * 获取文件真实名称
     *
     * @param name
     * @return
     */
    public static String getRealName(String name) {
        // c:/upload/1.jpg    1.jpg
        //获取最后一个"/"
        int index = name.lastIndexOf("\\");
        return name.substring(index + 1);
    }

    /**
     * 获取文件目录
     *
     * @param name 文件名称
     * @return 目录
     */
    public static String getDir(String name) {
        //任意的对象都有一个hash码，我们将hash码转换成16进制的字符串
        int i = name.hashCode();
        String hex = Integer.toHexString(i);
        int j = hex.length();
        for (int k = 0; k < 8 - j; k++) {
            hex = "0" + hex;
        }
        return "/" + hex.charAt(0) + "/" + hex.charAt(1)
                + "/" + hex.charAt(2) + "/" + hex.charAt(3)
                + "/" + hex.charAt(4) + "/" + hex.charAt(5)
                + "/" + hex.charAt(6) + "/" + hex.charAt(7);
    }

    public static ReturnData upload(ServletContext servletContext, MultipartFile multipartFile, String folderPath, int maxSize, String unit) {
        if (!FileUtils.checkFileSize(multipartFile.getSize(), maxSize, unit)) {
            return ReturnData.fail("图片太大，请上传小于" + maxSize + unit + "的图片");
        }
        try {
            //上传项
            //获取到上传项的输入流，根据输入流可以获取到图片的二进制数据
            InputStream inputStream = multipartFile.getInputStream();
            //将数据流输入到服务端的文件夹
            String realPath = servletContext.getRealPath("/WEB-INF" + folderPath);
            //在服务端创建一个空文件（服务端文件的后缀名必须和上传文件的后缀名保持一致）
            String originFileName = multipartFile.getOriginalFilename(); //原文件名
            String newFileName = getUUIDName(originFileName);//新文件名
            String dir = getDir(newFileName); //新路径
            String path = realPath + dir;
            File newDir = new File(path);
            if (!newDir.exists()) {
                boolean b = newDir.mkdirs();//创建目录
                if (!b) {
                    ReturnData.fail("创建目录失败");
                }
            }
            File finalFile = new File(newDir, newFileName);//最终拼凑成的文件的路径
            if (!finalFile.exists()) {
                boolean b = finalFile.createNewFile();
                if (!b) {
                    ReturnData.fail("创建文件失败");
                }
            }
            //建立和空文件夹对应的输出流
            FileOutputStream fileOutputStream = new FileOutputStream(finalFile);
            //将输入流中的数据刷到输出流
            //关闭流，释放资源
            IOUtils.copy(inputStream, fileOutputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
            return ReturnData.success(folderPath + dir + "/" + newFileName);
        } catch (Exception e) {
            return ReturnData.fail("上传文件异常");
        }
    }

    public static ReturnData saveFile(ServletContext servletContext, String folderPath, InputStream inputStream) {
        try {
            //将数据流输入到服务端的文件夹
            String realPath = servletContext.getRealPath("/" + folderPath);
            //在服务端创建一个空文件（服务端文件的后缀名必须和上传文件的后缀名保持一致）
            String newFileName = getUUIDName(null);//新文件名
            String dir = getDir(newFileName); //新路径
            String path = realPath + dir;
            File newDir = new File(path);
            if (!newDir.exists()) {
                boolean b = newDir.mkdirs();//创建目录
                if (!b) {
                    ReturnData.fail("创建目录失败");
                }
            }
            File finalFile = new File(newDir, newFileName);//最终拼凑成的文件的路径
            if (!finalFile.exists()) {
                boolean b = finalFile.createNewFile();
                if (!b) {
                    ReturnData.fail("创建文件失败");
                }
            }
            //建立和空文件夹对应的输出流
            FileOutputStream fileOutputStream = new FileOutputStream(finalFile);
            //将输入流中的数据刷到输出流
            //关闭流，释放资源
            IOUtils.copy(inputStream, fileOutputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
            return ReturnData.success(folderPath + dir + "/" + newFileName);
        } catch (Exception e) {
            return ReturnData.fail("上传文件异常");
        }
    }
}
