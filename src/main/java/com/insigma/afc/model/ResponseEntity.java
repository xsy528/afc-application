/* 
 * 日期：2019年9月11日
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 * 
 */
package com.insigma.afc.model;

import java.io.Serializable;

import com.insigma.afc.consts.ReturnCode;

import io.swagger.annotations.ApiModel;

/**
 * 
 * Ticket: 统一接口返回对象
 * @author  lianchuanjie
 *
 * @param <T>
 */
@ApiModel(value = "统一接口返回对象", description = "所有接口只要请求成功，均返回此模型的JSON格式字符串，code属性表示请求结果码，msg属性是对结果码的描述，data表示业务数据")
public class ResponseEntity<T> implements Serializable {
	private static final long serialVersionUID = -2448927203496809072L;

	private int code = ReturnCode.SUCCESS.getCode();
	private String message = ReturnCode.SUCCESS.getMessage();
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
