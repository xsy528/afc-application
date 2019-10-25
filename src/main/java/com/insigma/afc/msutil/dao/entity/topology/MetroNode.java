package com.insigma.afc.msutil.dao.entity.topology;


import com.insigma.commons.constant.AFCNodeLevel;

import java.io.Serializable;

public interface MetroNode extends Cloneable, Serializable {

	/**
	 * 十进制节点编号：
	 * ACC: 0x00
	 * LC: 0x00-0x99
	 * SC: 0x0000-0x9999
	 * SLE: 0x0000000-0x99999999
	 */
	Long id();

	/**
	 * 获取节点等级
	 * @return ACC,LC,SC,SLE
	 */
	AFCNodeLevel level();

	/**
	 * 获取节点名称
	 * @return 节点名称
	 */
	String name();

}
