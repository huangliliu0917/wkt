package com.zmj.wkt.service;

import com.baomidou.mybatisplus.service.IService;
import com.zmj.wkt.entity.Acc_person;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.entity.Bs_person_goods_list;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 客户个人信息表 服务类
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
public interface Bs_personService extends IService<Bs_person> {
    public Bs_person findByName(String name) ;
    public String registered(Bs_person bs_person, String registerWay);
    public Boolean updatePersonInfo(Bs_person bs_person);
    Bs_person findByWXOpenID(String WXOpenID);
    /**
     * 新增人员加入角色，入参为人员ID和角色名
     * @param roleName
     * @return
     */
    public boolean addPersonAsRoleName(String ClientID, String roleName);
    /**
     * 添加用户账户
     * @param ClientID
     * @return
     */
    public boolean addAccPerson(String ClientID);

    Bs_person findByClientID(String ClientID);
}
