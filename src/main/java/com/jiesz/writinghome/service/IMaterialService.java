package com.jiesz.writinghome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiesz.writinghome.entity.Material;

import java.util.List;

/**
 * <p>
 * 素材 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */

public interface IMaterialService extends IService<Material> {
    /**
     * 获取列表
     */
    List<Material> list(Material material);

    /**
     * 获取总数
     */
    Integer count(Material material);


    Boolean insert(Material material);

    Boolean update(Material material);
}
