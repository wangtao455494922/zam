package com.wjt.zam.modules.sys.mapper;

import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.modules.sys.model.User;

public interface UserMapper extends BaseMapper<User, Long>{

	User findByUsername(String username);
}
