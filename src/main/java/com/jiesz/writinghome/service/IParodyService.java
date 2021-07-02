package com.jiesz.writinghome.service;

import com.jiesz.writinghome.entity.Parody;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 仿写 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */

public interface IParodyService extends IService<Parody> {
    /**
     * 获取列表
     */
    List<Parody> list(Parody parody);

    /**
     * 获取总数
     */
    Integer count(Parody parody);
}
