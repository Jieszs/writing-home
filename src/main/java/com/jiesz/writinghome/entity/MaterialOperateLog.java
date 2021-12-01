package com.jiesz.writinghome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 素材操作日志
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("material_operate_log")
@ApiModel(value = "MaterialOperateLog对象", description = "素材操作日志")
public class MaterialOperateLog extends Model<MaterialOperateLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "logId", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty(value = "素材id")
    @TableField("materialId")
    private Integer materialId;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "操作类型（1-点赞 2-取消点赞 3-收藏  ）")
    @TableField("operateType")
    private Integer operateType;


    @ApiModelProperty(value = "创建时间")
    @TableField("insertTime")
    private LocalDateTime insertTime;

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
