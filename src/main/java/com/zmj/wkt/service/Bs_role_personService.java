package com.zmj.wkt.service;

import com.baomidou.mybatisplus.service.IService;
import com.zmj.wkt.entity.Bs_role_person;

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
     * 新增人员加入角色，入参为人员ID和角色名
     * @param roleName
     * @return
     */
    public boolean addPersonAsRoleName(String ClientID, String roleName);
}
