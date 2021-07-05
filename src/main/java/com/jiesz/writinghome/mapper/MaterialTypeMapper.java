package com.jiesz.writinghome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiesz.writinghome.entity.MaterialType;

import java.util.List;

/**
 * <p>
 * 素材分类 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
public interface MaterialTypeMapper extends BaseMapper<MaterialType> {
    /**
     * 获取列表
     */
    List<MaterialType> list(MaterialType materialType);

    /**
     * 获取总数
     */
    Integer count(MaterialType materialType);

    Integer getMaxOrderId(Integer parentId, Integer userId);
}
