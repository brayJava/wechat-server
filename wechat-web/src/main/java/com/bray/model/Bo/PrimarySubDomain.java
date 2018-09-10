package com.bray.model.Bo;

import com.bray.model.WyDomain;
import com.bray.model.WySubdomain;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 主域名子域名类
 * @Date: Created in 15:45 2018/8/28
 * @Modified By:
 */
@Builder
@Data
public class PrimarySubDomain  implements Serializable{

    private WyDomain wyDomain;

    private List<WySubdomain> wySubdomainList;
}
