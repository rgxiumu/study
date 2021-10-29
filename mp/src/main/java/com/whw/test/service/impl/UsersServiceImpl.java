package com.whw.test.service.impl;

import com.whw.test.entity.Users;
import com.whw.test.mapper.UsersMapper;
import com.whw.test.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author whw
 * @since 2020-11-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
