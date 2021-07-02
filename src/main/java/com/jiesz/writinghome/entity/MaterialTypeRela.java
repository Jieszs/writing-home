package com.jiesz.writinghome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * <p>
 * 素材分类关系
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
@TableName("material_type_rela")
@ApiModel(value = "MaterialTypeRela对象", description = "素材分类关系")
public class MaterialTypeRela extends Model<MaterialTypeRela> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "素材id")
    @TableField("materialId")
    private Integer materialId;

    @ApiModelProperty(value = "类型id")
    @TableField("typeId")
    private Integer typeId;

    @ApiModelProperty(value = "创建时间")
    @TableField("insertTime")
    private LocalDateTime insertTime;

    /**
     * 非数据库字段
     */

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
