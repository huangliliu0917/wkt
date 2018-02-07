package com.zmj.wkt.service;

import com.baomidou.mybatisplus.service.IService;
import com.zmj.wkt.entity.Bs_role;
import com.zmj.wkt.entity.Bs_role_person;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
public interface Bs_role_personService extends IService<Bs_role_person> {
    /**
     * 获取用户权限
     * @param ClientID
     * @return
     */
    public List<Bs_role> getUserRole(String ClientID);
}
