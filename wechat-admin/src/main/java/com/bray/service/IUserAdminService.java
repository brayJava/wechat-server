package com.bray.service;

import com.bray.model.Vo.LoginModelVo;
import com.bray.model.WyUser;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 用户服务
 * @Date: Created in 14:30 2019/1/28
 * @Modified By:
 */
public interface  IUserAdminService {
    /**
     * 登入校验
     * @param loginModelVo
     * @return
     */
    List<WyUser> login(LoginModelVo loginModelVo);
}
