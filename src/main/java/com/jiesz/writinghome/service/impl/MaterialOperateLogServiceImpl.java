package com.jiesz.writinghome.service.impl;

import com.jiesz.writinghome.entity.MaterialOperateLog;
import com.jiesz.writinghome.mapper.MaterialOperateLogMapper;
import com.jiesz.writinghome.service.IMaterialOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 素材操作日志 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialOperateLogServiceImpl extends ServiceImpl<MaterialOperateLogMapper, MaterialOperateLog> implements IMaterialOperateLogService {

    @Resource
    private MaterialOperateLogMapper materialOperateLogMapper;

    /**
     * 获取列表
     */
    @Override
    public List<MaterialOperateLog> list(MaterialOperateLog materialOperateLog) {
        return materialOperateLogMapper.list(materialOperateLog);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(MaterialOperateLog materialOperateLog) {
        return materialOperateLogMapper.count(materialOperateLog);
    }


}
