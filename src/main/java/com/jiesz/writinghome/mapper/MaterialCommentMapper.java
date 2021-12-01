package com.jiesz.writinghome.mapper;

import com.jiesz.writinghome.entity.MaterialComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
public interface MaterialCommentMapper extends BaseMapper<MaterialComment> {
    /**
     * 获取列表
     */
    List<MaterialComment> list(MaterialComment materialComment);

    /**
     * 获取总数
     */
    Integer count(MaterialComment materialComment);
}
