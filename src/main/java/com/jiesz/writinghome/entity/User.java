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
 * 用户信息
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
@ApiModel(value = "User对象", description = "用户信息")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @TableField("state")
    /**
     *  逻辑删除字段 1正常0删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean state;

    @ApiModelProperty(value = "创建时间")
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
        return this.userId;
    }

}
