package com.example.neo.security;

import com.example.neo.mybatis.model.NeoFunctions;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.service.NeoFunctionsService;
import com.example.neo.service.NeoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/9/29 13:47
 */
@Service
public class NeoUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private NeoUserService neoUserService;
    @Autowired
    private NeoFunctionsService neoFunctionsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NeoUser user = neoUserService.findUserByMobile(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        List<NeoFunctions> functions = neoFunctionsService.getFunctionsByMobile(username);
        return new JwtUserDetails(user.getMobile(), user.getPassword(), user.getIsLocked() ? 1 : 0, functions);
    }
}
