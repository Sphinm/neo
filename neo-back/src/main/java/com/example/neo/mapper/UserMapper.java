package com.example.neo.mapper;

import com.example.neo.entity.params.IChangePassword;
import com.example.neo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUserMobile(@Param("mobile") String mobile);

    User findByUserId(@Param("userId") String userId);

    void createUser(User user);

    void changePassword(@Param("pwd") IChangePassword pwd, @Param("userId") String userId);
}
