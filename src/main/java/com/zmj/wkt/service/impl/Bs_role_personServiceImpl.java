package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zmj.wkt.entity.Bs_role;
import com.zmj.wkt.entity.Bs_role_person;
import com.zmj.wkt.mapper.Bs_roleMapper;
import com.zmj.wkt.mapper.Bs_role_personMapper;
import com.zmj.wkt.service.Bs_role_personService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Bs_role_personServiceImpl extends ServiceImpl<Bs_role_personMapper, Bs_role_person> implements Bs_role_personService {

    @Autowired
    Bs_role_personMapper bs_role_personMapper;

    @Autowired
    Bs_roleMapper bs_roleMapper;

    @Override
    public boolean addPersonAsRoleName(String ClientID, String roleName) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_role());
        List<Bs_role> selectList = bs_roleMapper.selectList(entityWrapper.where("name={0}", roleName));
        Bs_role_person bs_role_person = new Bs_role_person();
        bs_role_person.setPerson_id(ClientID);
        bs_role_person.setRole_id(selectList.get(0).getId());
        bs_role_personMapper.insert(bs_role_person);
        return false;
    }
}
