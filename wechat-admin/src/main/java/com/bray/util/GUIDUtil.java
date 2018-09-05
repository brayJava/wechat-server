package com.bray.util;

import java.util.UUID;

/**
 * @author:wuzhiyuan
 * @description: 生成uuid随机数
 * @date: Created in 23:09 2018/1/27
 * @modified By:
 */
public class GUIDUtil {

    public static String buildMd5GUID() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replace("-","");
    }
}
