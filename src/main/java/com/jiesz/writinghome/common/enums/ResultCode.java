package com.jiesz.writinghome.common.enums;



public enum ResultCode   {
	/**
	 * 公共返回码: 0-99
	 */
	// 操作成功
	SUCCESS_CODE(0, "操作成功"),
	// 操作警告
	WARNING_CODE(1, "操作警告"),

	/**
	 * 请求参数错误: 10开头
	 */
	// 请求参数格式错误
	PARAM_VALID_ERROR(1001, "请求参数格式错误"),
	// 请求参数不能为空
	PARAM_EMPTY_ERROR(1002, "请求参数不能为空"),
	// 请求参数类型有误
	PARAM_TYPE_ERROR(1003, "请求参数类型有误"),
	// 请求参数不全
	PARAM_NOT_COMPLETE_ERROR(1004, "请求参数不全"),
	// 请求方法不支持
	REQUEST_METHOD_NOT_SUPPORT(1005, "请求方法不支持"),

	/**
	 * 接口错误: 20开头
	 */
	// 系统内部接口调用异常
	INTERFACE_INNER_INVOKE_ERROR(2001, "系统内部接口调用异常"),
	// 系统外部接口调用异常
	INTERFACE_OUTER_INVOKE_ERROR(2002, "系统外部接口调用异常"),
	// 接口禁止访问
	INTERFACE_FORBIDDEN(2003, "接口禁止访问"),
	// 接口地址无效
	INTERFACE_ADDRESS_INVALID(2004, "接口地址无效"),
	// 接口请求超时
	INTERFACE_REQUEST_TIMEOUT(2005, "接口请求超时"),

	/**
	 * 数据错误: 30开头
	 */
	// 数据未找到
	DATA_NOT_FOUND(3001, "数据未找到"),
	// 数据有误
	DATA_IS_WRONG(3002, "数据有误"),
	// 数据已存在
	DATA_ALREADY_EXISTED(3003, "数据已存在"),

	/**
	 * 系统错误: 40开头
	 */
	// 系统内部错误
	SYSTEM_INNER_ERROR(4001, "系统内部错误"),

	/**
	 * 权限错误: 50开头
	 */
	// 没有访问权限
	PERMISSION_NO_ACCESS(5001, "没有访问权限");


	private Integer code;
	private String desc;

	ResultCode(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}
}
