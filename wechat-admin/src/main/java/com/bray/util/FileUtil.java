package com.bray.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author:wuzhiyuan
 * @Description: 文件工具类
 * @Date: Created in 14:23 2018/10/9
 * @Modified By:
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        FileOutputStream out = null;
        try {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            out = new FileOutputStream(filePath+File.separator+fileName);
            out.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

}
