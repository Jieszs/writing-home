package com.jiesz.writinghome.mapper;

import com.jiesz.writinghome.entity.MaterialOperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 素材操作日志 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
public interface MaterialOperateLogMapper extends BaseMapper<MaterialOperateLog> {
    /**
     * 获取列表
     */
    List<MaterialOperateLog> list(MaterialOperateLog materialOperateLog);

    /**
     * 获取总数
     */
    Integer count(MaterialOperateLog materialOperateLog);
}
