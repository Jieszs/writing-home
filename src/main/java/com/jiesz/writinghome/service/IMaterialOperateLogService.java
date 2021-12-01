package com.jiesz.writinghome.service;

import com.jiesz.writinghome.entity.MaterialOperateLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 素材操作日志 服务类
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */

public interface IMaterialOperateLogService extends IService<MaterialOperateLog> {
    /**
     * 获取列表
     */
    List<MaterialOperateLog> list(MaterialOperateLog materialOperateLog);

    /**
     * 获取总数
     */
    Integer count(MaterialOperateLog materialOperateLog);
}
