package com.reservemate.mapper;

import com.reservemate.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select user_id from reservemate.user where email = #{email}")
    Long selectByEmail(String email);

    @Insert("INSERT INTO reservemate.user (username, email, password, phone, created_at, updated_at) " +
            "VALUES (#{username}, #{email}, #{password}, #{phone}, #{createdAt}, #{updatedAt})")
    void save(User user);

    @Select("select password from reservemate.user where email = #{email}")
    String getPasswordByEmail(String email);

    @Select("select user_id from reservemate.user where email = #{email}")
    Long selectIdByEmail(String email);
}
