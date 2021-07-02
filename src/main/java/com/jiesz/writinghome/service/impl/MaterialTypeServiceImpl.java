package com.jiesz.writinghome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiesz.writinghome.entity.MaterialType;
import com.jiesz.writinghome.mapper.MaterialTypeMapper;
import com.jiesz.writinghome.service.IMaterialTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 素材分类 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, MaterialType> implements IMaterialTypeService {

    @Resource
    private MaterialTypeMapper materialTypeMapper;

    /**
     * 获取列表
     */
    @Override
    public List<MaterialType> list(MaterialType materialType) {
        return materialTypeMapper.list(materialType);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(MaterialType materialType) {
        return materialTypeMapper.count(materialType);
    }

    /**
     * 添加时判重
     */
    @Override
    public boolean existTypeName(String typeName) {
        MaterialType condition = MaterialType.builder()
                .typeName(typeName)
                .build();
        List<MaterialType> list = list(condition);
        if (!CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
    }

    /**
     * 修改时判重
     */
    @Override
    public boolean existTypeName(Integer typeId, String typeName) {
        MaterialType condition = MaterialType.builder()
                .typeName(typeName)
                .build();
        List<MaterialType> list = list(condition);
        if (!CollectionUtils.isEmpty(list) && !list.get(0).getTypeId().equals(typeId)) {
            return true;
        }
        return false;
    }

    /**
     * 获取树
     */
    @Override
    public List<MaterialType> getTree(MaterialType materialType) {
        List<MaterialType> all = list(materialType);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        List<MaterialType> tree = new ArrayList<>();
        for (MaterialType one : all) {
            if (one.getParentId() == 0) {
                setChildren(one, all);
                tree.add(one);
            }
        }
        return tree;
    }

    private void setChildren(MaterialType materialType, List<MaterialType> all) {
        for (MaterialType one : all) {
            if (one.getParentId().equals(materialType.getTypeId())) {
                setChildren(one, all);
                if (materialType.getChildren() != null) {
                    materialType.getChildren().add(one);
                } else {
                    List<MaterialType> list = new ArrayList<>();
                    list.add(one);
                    materialType.setChildren(list);
                }
            }
        }
    }


}
