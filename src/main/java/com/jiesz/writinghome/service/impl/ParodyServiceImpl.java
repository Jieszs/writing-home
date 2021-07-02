package com.jiesz.writinghome.service.impl;

import com.jiesz.writinghome.entity.Parody;
import com.jiesz.writinghome.mapper.ParodyMapper;
import com.jiesz.writinghome.service.IParodyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 仿写 服务实现类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParodyServiceImpl extends ServiceImpl<ParodyMapper, Parody> implements IParodyService {

    @Resource
    private ParodyMapper parodyMapper;

    /**
     * 获取列表
     */
    @Override
    public List<Parody> list(Parody parody) {
        return parodyMapper.list(parody);
    }

    /**
     * 获取总数
     */
    @Override
    public Integer count(Parody parody) {
        return parodyMapper.count(parody);
    }


}
