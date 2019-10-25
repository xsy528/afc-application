/* 
 * 日期：2011-2-23
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.msutil.enums;


import com.insigma.commons.dic.Definition;
import com.insigma.commons.dic.annotation.Dic;
import com.insigma.commons.dic.annotation.DicItem;

/**
 * 创建时间 2011-2-23 下午07:14:07<br>
 * 描述：<br>
 * Ticket:
 * 
 * @author DingLuofeng
 */
@Dic(overClass = AFCCmdResultType.class)
public class AFCCmdResultType extends Definition {
	
    private static AFCCmdResultType instance = new AFCCmdResultType();

    public static AFCCmdResultType getInstance() {
        return instance;
    }

    @DicItem(name = "发送成功")
    public static Integer SEND_SUCCESSFUL = 0x0000;

    @DicItem(name = "发送失败")
    public static Integer SEND_FAILED = 0x0001;
    
    @DicItem(name = "下层节点应答超时")
    public static Integer LOWER_NODE_RESP_TIMEOUT = 0x0006;

    @DicItem(name = "通讯连接中断")
    public static Integer COMMUNICATION_DISCONNECTION = 0x00FF;

}
