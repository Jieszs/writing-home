package com.jiesz.writinghome.service;

import com.jiesz.writinghome.entity.MaterialComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */

public interface IMaterialCommentService extends IService<MaterialComment> {
    /**
     * 获取列表
     */
    List<MaterialComment> list(MaterialComment materialComment);

    /**
     * 获取总数
     */
    Integer count(MaterialComment materialComment);
}
