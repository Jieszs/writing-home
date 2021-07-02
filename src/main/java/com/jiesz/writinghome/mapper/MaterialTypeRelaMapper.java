package com.jiesz.writinghome.mapper;

import com.jiesz.writinghome.entity.MaterialTypeRela;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 素材分类关系 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
public interface MaterialTypeRelaMapper extends BaseMapper<MaterialTypeRela> {
    /**
     * 获取列表
     */
    List<MaterialTypeRela> list(MaterialTypeRela materialTypeRela);

    /**
     * 获取总数
     */
    Integer count(MaterialTypeRela materialTypeRela);
}
