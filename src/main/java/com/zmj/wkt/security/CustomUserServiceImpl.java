package com.zmj.wkt.security;

import com.zmj.wkt.entity.Bs_permission;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.mapper.Bs_permissionMapper;
import com.zmj.wkt.mapper.Bs_personMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"ALL", "AlibabaServiceOrDaoClassShouldEndWithImpl"})
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    Bs_personMapper bs_personMapper;

    @Autowired
    Bs_permissionMapper bs_permissionMapper;

    @Autowired
    MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username:"+username);
        myInvocationSecurityMetadataSourceService.loadResourceDefine();
        //获取用户对象
        Bs_person user = bs_personMapper.findByName(username);
        if (user != null) {
            //获取该用户权限列表
            List<Bs_permission> permissions =bs_permissionMapper.findAllByPersonId(user.getClientID());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //遍历权限列表
            for (Bs_permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUserName(), user.getPersonPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User: " + username + " do not exist!");
        }
    }

}
