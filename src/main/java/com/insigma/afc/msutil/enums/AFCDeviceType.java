/* 
 * 日期：2010-10-13
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.msutil.enums;

import com.insigma.commons.dic.Definition;
import com.insigma.commons.dic.annotation.Dic;
import com.insigma.commons.dic.annotation.DicItem;

/**
 * 产品级设备类型 <br/>
 * Ticket: <br/>
 * 
 * @author 邱昌进(qiuchangjin@zdwxjd.com)
 */
@Dic(overClass = AFCDeviceType.class)
public class AFCDeviceType extends Definition {

	private static AFCDeviceType instance = new AFCDeviceType();

	public static AFCDeviceType getInstance() {
		return instance;
	}

	@DicItem(name = "清分中心设备", group = "CCHS", index = 2)
	public static Short CCHS = 0x01;

	@DicItem(name = "线路中心设备", group = "LC", index = 3)
	public static Short LC = 0x02;

	@DicItem(name = "车站中心设备", group = "SC")
	public static Short SC = 0x03;

	@DicItem(name = "自动检票机", group = "SLE")
	public static Short GATE = 0x04;

	@DicItem(name = "自动加值机", group = "SLE")
	public static Short AVM = 0x05;

	@DicItem(name = "自动售票机", group = "SLE")
	public static Short TVM = 0x06;

	@DicItem(name = "半自动售票机", group = "SLE")
	public static Short POST = 0x07;

	@DicItem(name = "手持式验票机", group = "PCA")
	public static Short PCA = 0x08;

	@DicItem(name = "编码分拣机", group = "E/S")
	public static Short E_S = 0x09;

	// 杭州项目使用
	@DicItem(name = "自动充值机")
	public static Short TSM = 0x0a;

	// 杭州项目使用
	@DicItem(name = "自动查询机")
	public static Short TCM = 0x0b;
	
	@DicItem(name =  "柜台式半自动售票机")
	public static Short CBOM = 0x0c;

}
