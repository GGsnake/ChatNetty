package com.webscoket.webscoket.bean.dto;

import com.alibaba.druid.util.StringUtils;
import com.webscoket.webscoket.model.User;

public class UserDto extends User {
    public Boolean ifPwdNull() {
        if (StringUtils.isEmpty(getPassword())) {
            return true;

        }

        return false;
    }
    public Boolean ifPhoneNull() {
        if (StringUtils.isEmpty(getPhone())) {
            return true;
        }
        return false;
    }
}
