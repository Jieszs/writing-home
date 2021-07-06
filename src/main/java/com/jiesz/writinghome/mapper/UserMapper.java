package com.jiesz.writinghome.mapper;

import com.jiesz.writinghome.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-06
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取列表
     */
    List<User> list(User user);

    /**
     * 获取总数
     */
    Integer count(User user);
}
