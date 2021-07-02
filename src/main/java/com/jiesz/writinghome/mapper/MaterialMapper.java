package com.jiesz.writinghome.mapper;

import com.jiesz.writinghome.entity.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 素材 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
public interface MaterialMapper extends BaseMapper<Material> {
    /**
     * 获取列表
     */
    List<Material> list(Material material);

    /**
     * 获取总数
     */
    Integer count(Material material);
}
