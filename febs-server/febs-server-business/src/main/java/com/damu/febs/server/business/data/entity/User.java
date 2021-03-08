package com.damu.febs.server.business.data.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
	@TableId(value = "ID", type = IdType.AUTO)
	private int id;
	@TableField("user_name")
	private String userName;
	@TableField("phone")
	private String phone;
	@TableField("password")
	private String password;
	@TableField("salt")
	private String salt;
	@TableField("head")
	private String head;
	@TableField("login_count")
	private int loginCount;
	@TableField("register_date")
	private Date registerDate;
	@TableField("last_login_date")
	private Date lastLoginDate;
}
