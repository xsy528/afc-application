/* 
 * 日期：2019年9月11日
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 * 
 */
package com.insigma.afc.consts;

import org.springframework.util.StringUtils;

/**
 * 
 * Ticket: 业务返回码
 * @author  lianchuanjie
 *
 */
public enum ReturnCode {
	/**
	 * 请求成功
	 */
	SUCCESS(0, "请求成功"),
	/**
	 * 请求失败
	 */
	FAIL(1, "请求失败"),
	/**
	 * 参数错误
	 */
	PARA_ERROR(2, "请传入必填参数");

	private int code;
	private String message;

	private ReturnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return getMessage(null);
	}

	public String getMessage(String paraName) {
		String retMsg = "";
		if (!StringUtils.isEmpty(paraName)) {
			retMsg = message + ", 参数：" + paraName;
		} else {
			retMsg = message;
		}
		return retMsg;
	}
}
