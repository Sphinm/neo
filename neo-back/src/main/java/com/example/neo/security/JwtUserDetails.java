package com.example.neo.security;

import com.example.neo.mybatis.model.NeoFunctions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/9/29 15:53
 */
public class JwtUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private int isLocked;
    private List<NeoFunctions> authorities;

    JwtUserDetails(String username, String password, int isLocked, List<NeoFunctions> authorities) {
        this.username = username;
        this.password = password;
        this.isLocked = isLocked;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> functions = new ArrayList<>();
        for(NeoFunctions neoFunctions:authorities){
            functions.add(neoFunctions.getFunctionInfo());
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(functions.toString());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked==0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
