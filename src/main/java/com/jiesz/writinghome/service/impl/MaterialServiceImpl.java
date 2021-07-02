package com.jiesz.writinghome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiesz.writinghome.entity.Material;
import com.jiesz.writinghome.mapper.MaterialMapper;
import com.jiesz.writinghome.service.IMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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


}
