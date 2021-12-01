package com.jiesz.writinghome.service.impl;

import com.jiesz.writinghome.entity.MaterialComment;
import com.jiesz.writinghome.mapper.MaterialCommentMapper;
import com.jiesz.writinghome.service.IMaterialCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialCommentServiceImpl extends ServiceImpl<MaterialCommentMapper, MaterialComment> implements IMaterialCommentService {

    @Resource
    private MaterialCommentMapper materialCommentMapper;

    /**
     * 获取列表
     */
    @Override
    public List<MaterialComment> list(MaterialComment materialComment) {
        return materialCommentMapper.list(materialComment);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(MaterialComment materialComment) {
        return materialCommentMapper.count(materialComment);
    }


}
