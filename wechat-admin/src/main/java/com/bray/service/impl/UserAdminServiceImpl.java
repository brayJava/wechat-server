package com.bray.service.impl;

import com.bray.mapper.WyUserMapper;
import com.bray.model.Vo.LoginModelVo;
import com.bray.model.WyUser;
import com.bray.model.WyUserExample;
import com.bray.service.IUserAdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author:wuzhiyuan
 * @Description: 登入服务
 * @Date: Created in 14:32 2019/1/28
 * @Modified By:
 */
@Service
public class UserAdminServiceImpl implements IUserAdminService {

    @Resource
    private WyUserMapper wyUserMapper;

    @Override
    public List<WyUser> login(LoginModelVo loginModelVo) {
         WyUserExample wyUserExample = new WyUserExample();
            wyUserExample.createCriteria().andUsernameEqualTo(loginModelVo.getUsername())
                    .andPasswordEqualTo(loginModelVo.getPassword());
         List<WyUser> wyUsers = wyUserMapper.selectByExample(wyUserExample);
        return wyUsers;
    }
}
