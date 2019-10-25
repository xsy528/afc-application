/* 
 * 日期：2011-8-31
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.msutil.enums;

import com.insigma.commons.dic.Definition;
import com.insigma.commons.dic.annotation.Dic;
import com.insigma.commons.dic.annotation.DicItem;

/**
 * Ticket:成都MS连接类型
 * 
 * @author chenhangwen
 */
@Dic(overClass = CDMSConnectionType.class)
public class CDMSConnectionType extends Definition {
	private static CDMSConnectionType instance = new CDMSConnectionType();

	public static CDMSConnectionType getInstance() {
		return instance;
	}

	@DicItem(name = "主连接")
	public static Short Master = 1;

	@DicItem(name = "备连接")
	public static Short Back = 2;

}
