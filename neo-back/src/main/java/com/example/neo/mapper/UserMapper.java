package com.example.neo.mapper;

import com.example.neo.entity.CompanyInfo;
import com.example.neo.model.IChangePassword;
import com.example.neo.entity.User;
import com.example.neo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUserMobile(@Param("mobile") String mobile);

    User findByUserId(@Param("userId") String userId);

    Role findRoleByUserId(@Param("roleId") int roleId);

    void createUser(User user);

    void changePassword(@Param("pwd") IChangePassword pwd, @Param("userId") String userId);
}
