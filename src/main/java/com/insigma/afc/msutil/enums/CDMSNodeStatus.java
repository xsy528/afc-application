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
 * Ticket:成都MS节点状态
 * 
 * @author chenhangwen
 */
@Dic(overClass = CDMSNodeStatus.class)
public class CDMSNodeStatus extends Definition {
	private static CDMSNodeStatus instance = new CDMSNodeStatus();

	public static CDMSNodeStatus getInstance() {
		return instance;
	}

	@DicItem(name = "正常")
	public static Short Normal = 1;

	@DicItem(name = "宕机")
	public static Short Downtime = 2;

	@DicItem(name = "废弃")
	public static Short Discard = 3;

}
