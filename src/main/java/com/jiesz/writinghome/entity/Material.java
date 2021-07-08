package com.jiesz.writinghome.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 素材
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
@TableName("material")
@ApiModel(value = "Material对象", description = "素材")
public class Material extends Model<Material> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "materialId", type = IdType.AUTO)
    private Integer materialId;

    @ApiModelProperty(value = "素材内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "userId")
    @JsonIgnore
    private Integer userId;

    @TableField("state")
    /**
     *  逻辑删除字段 1正常0删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean state;

    @ApiModelProperty(value = "来源")
    @TableField("source")
    private String source;

    @ApiModelProperty(value = "插入时间")
    @TableField(value = "insertTime")
    private String insertTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updateTime")
    private String updateTime;

    @ApiModelProperty(value = "素材分类名称")
    @TableField(exist = false)
    private List<String> typeNames;

    @ApiModelProperty(value = "仿写列表")
    @TableField(exist = false)
    private List<String> parodies;
    /**
     * 非数据库字段
     */
    @JsonIgnore
    @TableField(exist = false)
    private Integer limit;
    @JsonIgnore
    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    @JsonIgnore
    private List<Integer> typeIds;


    @Override
    protected Serializable pkVal() {
        return this.materialId;
    }

}
