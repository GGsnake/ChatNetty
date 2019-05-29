package com.webscoket.webscoket.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.model.UserBind;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserBindDao extends BaseMapper<UserBind> {
    @Select(" select a.id,a.age,\n" +
            "                    a.roleId,\n" +
            "                     a.name,\n" +
            "                 \n" +
            "                     a.userName,\n" +
            "                    a.signature from user  a inner join userbind  b on b.uid=#{uid} and a.id=b.pid ")
    List<User> selectUserByName(String uid);
}
