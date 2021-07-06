package com.jiesz.writinghome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiesz.writinghome.entity.MaterialTypeRela;
import com.jiesz.writinghome.mapper.MaterialTypeRelaMapper;
import com.jiesz.writinghome.service.IMaterialTypeRelaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 素材分类关系 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialTypeRelaServiceImpl extends ServiceImpl<MaterialTypeRelaMapper, MaterialTypeRela> implements IMaterialTypeRelaService {

    @Resource
    private MaterialTypeRelaMapper materialTypeRelaMapper;

    /**
     * 获取列表
     */
    @Override
    public List<MaterialTypeRela> list(MaterialTypeRela materialTypeRela) {
        return materialTypeRelaMapper.list(materialTypeRela);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(MaterialTypeRela materialTypeRela) {
        return materialTypeRelaMapper.count(materialTypeRela);
    }


}
