package com.jiesz.writinghome.mapper;

import com.jiesz.writinghome.entity.Parody;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 仿写 Mapper 接口
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
public interface ParodyMapper extends BaseMapper<Parody> {
    /**
     * 获取列表
     */
    List<Parody> list(Parody parody);

    /**
     * 获取总数
     */
    Integer count(Parody parody);
}
