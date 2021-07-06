package com.jiesz.writinghome.service.impl;

import com.jiesz.writinghome.entity.User;
import com.jiesz.writinghome.mapper.UserMapper;
import com.jiesz.writinghome.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取列表
     */
    @Override
    public List<User> list(User user) {
        return userMapper.list(user);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(User user) {
        return userMapper.count(user);
    }


}
