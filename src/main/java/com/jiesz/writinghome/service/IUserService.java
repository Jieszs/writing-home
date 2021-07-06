package com.jiesz.writinghome.service;

import com.jiesz.writinghome.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-06
 */

public interface IUserService extends IService<User> {
    /**
     * 获取列表
     */
    List<User> list(User user);

    /**
     * 获取总数
     */
    Integer count(User user);
}
