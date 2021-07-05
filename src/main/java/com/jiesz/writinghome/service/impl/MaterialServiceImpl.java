package com.jiesz.writinghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiesz.writinghome.entity.Material;
import com.jiesz.writinghome.entity.MaterialTypeRela;
import com.jiesz.writinghome.mapper.MaterialMapper;
import com.jiesz.writinghome.service.IMaterialService;
import com.jiesz.writinghome.service.IMaterialTypeRelaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 素材 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private IMaterialTypeRelaService materialTypeRelaService;

    /**
     * 获取列表
     */
    @Override
    public List<Material> list(Material material) {
        return materialMapper.list(material);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(Material material) {
        return materialMapper.count(material);
    }

    @Override
    public Boolean insert(Material material) {
        boolean result = material.insert();
        List<MaterialTypeRela> materialTypeRelas = convertByIds(material.getTypeIds(), material.getMaterialId());
        materialTypeRelaService.saveBatch(materialTypeRelas);
        return result;
    }

    @Override
    public Boolean update(Material material) {
        boolean result = material.updateById();
        List<MaterialTypeRela> materialTypeRelas = convertByIds(material.getTypeIds(), material.getMaterialId());
        MaterialTypeRela condition = new MaterialTypeRela();
        condition.setMaterialId(material.getMaterialId());
        materialTypeRelaService.remove(new QueryWrapper<>(condition));
        materialTypeRelaService.saveBatch(materialTypeRelas);
        return result;
    }

    private List<MaterialTypeRela> convertByIds(List<Integer> typeIds, int materialId) {
        List<MaterialTypeRela> materialTypeRelas =
                typeIds.stream().map(t -> {
                    MaterialTypeRela rela = new MaterialTypeRela();
                    rela.setMaterialId(materialId);
                    rela.setTypeId(t);
                    return rela;
                }).collect(Collectors.toList());
        return materialTypeRelas;
    }
}
