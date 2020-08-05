package com.javaclimb.util;

import java.io.File;

public class FileUtils {
    public static final String FileUnit_B = "B";
    public static final String FileUnit_K = "K";
    public static final String FileUnit_M = "M";
    public static final String FileUnit_G = "G";

    /**
     * 判断文件大小
     *
     * @param len  文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if (FileUnit_B.equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if (FileUnit_K.equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if (FileUnit_M.equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if (FileUnit_G.equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }

    public static boolean deleteFile(String path) {
        return new File(path).delete();
    }
}
