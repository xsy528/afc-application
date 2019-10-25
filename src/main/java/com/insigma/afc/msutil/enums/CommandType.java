package com.insigma.afc.msutil.enums;

import com.insigma.commons.dic.Definition;
import com.insigma.commons.dic.annotation.DicItem;

public class CommandType extends Definition {

	public static CommandType commandType = new CommandType();

	public static CommandType getInstance() {
		return commandType;
	}

	@DicItem
	public static Integer CMD_SYNC_INFO = 0x01;

	@DicItem
	public static Integer CMD_UPLOAD_FILE = 0x02;

	@DicItem
	public static Integer CMD_CHANGE_MODE = 0x03;

	@DicItem
	public static Integer CMD_TIME_SYNC = 0x04;

	@DicItem
	public static Integer CMD_QUERY_MODE = 0x05;

	@DicItem
	public static Integer CMD_BROADCAST_MODE = 0x06;

	@DicItem
	public static Integer CMD_SYNC_MAP = 0x9999;

	@DicItem
	public static Integer CMD_EXPORT_MAP = 0x1111;

	@DicItem
	public static Integer CMD_UPLOAD_MAP = 0x2222;

	@DicItem
	public static Integer CMD_CLEAR_TICHET_BOX = 0x9998;

	@DicItem
	public static Integer CMD_EQU_CTRL = 0x3000;

	@DicItem
	public static Integer CMD_TCM_MESSAGE = 0x3006;

	@DicItem
	public static Integer CMD_UD_ASK = 0x3011;

	@DicItem
	public static Integer CMD_QUERY_TVM_MONEY_TICKET_BOX = 0x4003;

	@DicItem
	public static Integer CMD_QUERY_BOM_AGM_TICKET_BOX = 0x4004;

	@DicItem
	public static Integer CMD_BOTHWAY_GATE_ONLY = 0x5000;

	@DicItem
	public static Integer CMD_QUERY_DEVICE_STATUS = 0x4000;

	@DicItem
	public static Integer CMD_WAKE_ON_LAN = 0x4005;
	
	@DicItem
	public static Integer CMD_ONLINE_AUTHENTIC = 0x4006;

	/** ========== 参数相关(参数字典不需要被覆盖，所以定义成final) ======================== **/
	@DicItem(name = "新建草稿版本")
	public static final Integer CMD_EOD_CREATEDRAF = 0x0100;

	@DicItem(name = "删除草稿版本")
	public static final Integer CMD_EOD_DELETE = 0x0101;

	@DicItem(name = "版本转换")
	public static final Integer CMD_EOD_VERSION_SWITCH = 0x0102;

	@DicItem(name = "参数同步")
	public static final Integer CMD_EOD_VERSION_SYNC = 0x0103;

	@DicItem(name = "参数实时查询")
	public static final Integer CMD_EOD_VERSION_QUERY = 0x0104;

	@DicItem(name = "EOD参数发布")
	public static final Integer CMD_EOD_PUBLISH = 0x0105;

	@DicItem(name = "EOD参数导入")
	public static final Integer CMD_EOD_IMPORT = 0x0106;

	@DicItem(name = "TP参数版本")
	public static final Integer CMD_QUERY_TP_VERSION = 0x0107;

	@DicItem(name = "运营参数发布")
	public static final Integer CMD_OPT_PUBLISH = 0x0110;

	@DicItem(name = "软件同步")
	public static final Integer CMD_SOFT_SYNC = 0x0120;

	@DicItem(name = "软件导入")
	public static final Integer CMD_SOFT_IMPORT = 0x0121;

	@DicItem(name = "软件发布")
	public static final Integer CMD_SOFT_PUBLISH = 0x0124;

	@DicItem(name = "软件实时查询")
	public static final Integer CMD_SOFT_VERSION_QUERY = 0x8020;

    @DicItem(name = "过闸设置")
    public static final Integer QR_LOCK_SET = 0x8031;

    @DicItem(name = "过闸查询")
    public static final Integer QR_LOCK_QUERY = 0x8032;

	/** ========== 审计和结算文件导入(字典不需要被覆盖，所以定义成final) ======================== **/
	@DicItem(name = "审计文件导入")
	public static final Integer AUDIT_FILE_INPORT = 0x0122;

	@DicItem(name = "结算文件导入")
	public static final Integer SETTLE_FILE_INPORT = 0x0123;

	/** ========== 库存(字典不需要被覆盖，所以定义成final) ======================== **/
	@DicItem(name = "库存查询")
	public static final Integer TICKET_STOCK_QUERY = 0x0201;

	@DicItem(name = "库存调配")
	public static final Integer TICKET_STOCK_ALLOC = 0x0202;

	@DicItem(name = "库存调配确认")
	public static final Integer TICKET_STOCK_ALLOC_COMFIRM = 0x0203;

	@DicItem(name = "库存盘点")
	public static final Integer TICKET_STOCK_INVENTORY = 0x0204;
}
