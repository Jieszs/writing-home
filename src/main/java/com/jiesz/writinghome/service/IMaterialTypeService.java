package com.jiesz.writinghome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiesz.writinghome.entity.MaterialType;

import java.util.List;

/**
 * <p>
 * 素材分类 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */

public interface IMaterialTypeService extends IService<MaterialType> {
    /**
     * 获取列表
     */
    List<MaterialType> list(MaterialType materialType);

    /**
     * 获取总数
     */
    Integer count(MaterialType materialType);

    /**
     * 获取树
     */
    List<MaterialType> getTree(MaterialType materialType);

    /**
     * 添加时判重
     */
    boolean existTypeName(String typeName, Integer userId);

    /**
     * 修改时判重
     */
    boolean existTypeName(Integer typeId, String typeName, Integer userId);

    /**
     * 上移
     *
     * @param materialType
     * @return
     */
    boolean up(MaterialType materialType);

    /**
     * 下移
     *
     * @param materialType
     * @return
     */
    boolean down(MaterialType materialType);

    /**
     * 获取当前最大值
     *
     * @param parentId
     * @param userId
     * @return
     */
    Integer getMaxOrderId(Integer parentId, Integer userId);

    void delete(MaterialType condition);

    boolean validateTypeIds(List<Integer> typeIds, Integer userId);
}
