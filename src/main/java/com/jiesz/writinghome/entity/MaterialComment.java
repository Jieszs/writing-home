package com.jiesz.writinghome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 评论
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
@TableName("material_comment")
@ApiModel(value = "MaterialComment对象", description = "评论")
public class MaterialComment extends Model<MaterialComment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "commentId", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "素材id")
    @TableField("materialId")
    private Integer materialId;

    @ApiModelProperty(value = "评论")
    @TableField("comment")
    private String comment;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "接收用户id")
    @TableField("receiver")
    private Integer receiver;

    @ApiModelProperty(value = "被评论的id,（可以素材，也可以是评论）")
    @TableField("commentedId")
    private Integer commentedId;

    @ApiModelProperty(value = "创建时间")
    @TableField("insertTime")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "1-评论素材 2-回复评论")
    @TableField("type")
    private Integer type;

    @TableField("state")
    /**
     *  逻辑删除字段 1正常0删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean state;
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
        return this.commentId;
    }

}
