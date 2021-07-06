package com.jiesz.writinghome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiesz.writinghome.entity.MaterialTypeRela;

import java.util.List;

/**
 * <p>
 * 素材分类关系 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */

public interface IMaterialTypeRelaService extends IService<MaterialTypeRela> {
    /**
     * 获取列表
     */
    List<MaterialTypeRela> list(MaterialTypeRela materialTypeRela);

    /**
     * 获取总数
     */
    Integer count(MaterialTypeRela materialTypeRela);
}
