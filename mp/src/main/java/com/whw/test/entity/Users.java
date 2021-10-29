package com.whw.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author whw
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
@ApiModel(value="Users对象", description="用户")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户账号")
    @TableField("user_account")
    private String userAccount;

    @ApiModelProperty(value = "用户密码")
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(value = "员工姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户手机")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(value = "用户邮箱")
    @TableField("user_email")
    private String userEmail;

    @TableField("user_enable")
    private Boolean userEnable;

    @ApiModelProperty(value = "是否登录过")
    @TableField("user_logged")
    private Boolean userLogged;

    @ApiModelProperty(value = "上次登陆时间")
    @TableField("last_login_time")
    private Date lastLoginTime;

    @ApiModelProperty(value = "上次登陆学校ID")
    @TableField("last_login_school_id")
    private Integer lastLoginSchoolId;

    @ApiModelProperty(value = "上次登陆平台")
    @TableField("last_user_type")
    private String lastUserType;

    @ApiModelProperty(value = "创建该用户的学校ID")
    @TableField("create_school_id")
    private Integer createSchoolId;

    @ApiModelProperty(value = "创建用户ID")
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Integer createUserId;

    @ApiModelProperty(value = "更新用户ID")
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
