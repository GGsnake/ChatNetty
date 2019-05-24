package com.webscoket.webscoket.dao;

import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.model.UserBind;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("user")
public interface UserBindDao extends BaseMapper<UserBind> {
    // JDK 1.8 可不加@Param  但java编译的时候开启-parameters选项
    List<User> selectUserByName(@Param("id") String id);


    void getUserListPageQuery(PageQuery query);
}
