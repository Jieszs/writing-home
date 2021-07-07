package com.jiesz.writinghome.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 素材分类
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("material_type")
@ApiModel(value = "MaterialType对象", description = "素材分类")
public class MaterialType extends Model<MaterialType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "typeId", type = IdType.AUTO)
    private Integer typeId;

    @ApiModelProperty(value = "分类名称")
    @TableField("typeName")
    private String typeName;

    @ApiModelProperty(value = "父亲id")
    @TableField(value = "parentId")
    private Integer parentId;

    @ApiModelProperty(value = "排序值，小的在前")
    @TableField("orderId")
    private Integer orderId;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "userId")
    @JsonIgnore
    private Integer userId;

    @ApiModelProperty(value = "插入时间")
    @TableField(value = "insertTime")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updateTime")
    private LocalDateTime updateTime;
    /**
     * 非数据库字段
     */
    //孩子
    @TableField(exist = false)
    private List<MaterialType> children;

    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

}
