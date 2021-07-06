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

/**
 * <p>
 * 仿写
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
@TableName("parody")
@ApiModel(value = "Parody对象", description = "仿写")
public class Parody extends Model<Parody> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "parodyId", type = IdType.AUTO)
    private Integer parodyId;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "素材id")
    @TableField("materialId")
    private Integer materialId;

    @ApiModelProperty(value = "用户id")
    @JsonIgnore
    @TableField(value = "userId", updateStrategy = FieldStrategy.NEVER)
    private Integer userId;

    @TableField("state")
    /**
     *  逻辑删除字段 1正常0删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean state;

    @ApiModelProperty(value = "插入时间")
    @TableField("insertTime")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updateTime")
    private LocalDateTime updateTime;
    /**
     * 非数据库字段
     */
    @JsonIgnore
    @TableField(exist = false)
    private Integer limit;
    @JsonIgnore
    @TableField(exist = false)
    private Integer offset;

    @Override
    protected Serializable pkVal() {
        return this.parodyId;
    }

}
