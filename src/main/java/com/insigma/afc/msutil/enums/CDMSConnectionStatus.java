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
 * Ticket:成都MS连接状态
 * 
 * @author chenhangwen
 */
@Dic(overClass = CDMSConnectionStatus.class)
public class CDMSConnectionStatus extends Definition {
	private static CDMSConnectionStatus instance = new CDMSConnectionStatus();

	public static CDMSConnectionStatus getInstance() {
		return instance;
	}

	@DicItem(name = "连接")
	public static Short Connect = 1;

	@DicItem(name = "断开")
	public static Short Disconnect = 2;

}
