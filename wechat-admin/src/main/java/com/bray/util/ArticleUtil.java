package com.bray.util;

import com.bray.dto.ConstFinal;
import com.bray.model.Vo.ArticleModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.Collection;
import java.util.Map;

/**
 * @Author:wuzhiyuan
 * @Description: 文章工具类
 * @Date: Created in 12:22 2018/9/5
 * @Modified By:
 */
@Slf4j
public class ArticleUtil {
    /**
     * 图片上传本地
     * @param httpServletRequest
     * @param articleId
     */
    public static String uploadFileTransfer(MultipartHttpServletRequest httpServletRequest, String articleId) {
        MultipartHttpServletRequest multipartHttpServletRequest = httpServletRequest;
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        Collection<MultipartFile> files = fileMap.values();
        long millis = Clock.systemDefaultZone().millis();
        String relative_path = "";
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            //默认png图片
            String imageSuffix = ".png";
            if(!StringUtils.isEmpty(originalFilename)) {
                imageSuffix= originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
            }
            //图片相对路径
            relative_path = new StringBuilder().append(articleId).append(File.separator).append(millis).append(imageSuffix).toString();
            //绝对路径根目录
            String absolute_path = new StringBuilder().append(ConstFinal.LINUX_ROOT_PATH).append(File.separator).append(articleId).append(File.separator).toString();
            //全路径
            String real_path = absolute_path + millis+imageSuffix;

            File f = new File(absolute_path);
            if(!f.exists()) {
                f.mkdirs();
            }
            try {
                log.info("--------图片路径{}",real_path);
                file.transferTo(new File(real_path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return relative_path;
    }
    /**
     * 检验空值
     * @param articleModelVo
     * @return
     */
    public static boolean verifyEmpty(ArticleModelVo articleModelVo) {
        return StringUtils.isEmpty(articleModelVo.getTitle())
                || StringUtils.isEmpty(articleModelVo.getBgmUrl())
                || StringUtils.isEmpty(articleModelVo.getShareTitle())
                || StringUtils.isEmpty(articleModelVo.getShareDecribe())
                || StringUtils.isEmpty(articleModelVo.getAuthor());
    }
}
