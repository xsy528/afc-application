/* 
 * 日期：2011-10-13
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.rmi;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Ticket: <br/>
 * 
 * @author 邱昌进(qiuchangjin@zdwxjd.com)
 */
public class CmdHandlerResult implements Serializable {
	private static final long serialVersionUID = -8567713340204042946L;

	public CmdHandlerResult() {

	}

	public boolean isOK = true;

	public Serializable returnValue;

	/**
	 * rmi接口返回的描述信息：一般是错误信息描述
	 */
	public List<String> messages = new ArrayList<String>();

	/**
	 * rmi接口返回的结果信息，对象须序列化
	 */
	protected Object data;

	public String getResultMessage() {
		return StringUtils.join(messages, "\n");
	}

	public boolean hasMessage() {
		return !messages.isEmpty();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
