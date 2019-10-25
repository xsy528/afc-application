package com.insigma.afc.consts.dic;

/*
 * 日期：2010-8-12
 *
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */

import com.insigma.commons.dic.Definition;
import com.insigma.commons.dic.annotation.Dic;
import com.insigma.commons.dic.annotation.DicItem;

/**
 * 产品级ftp文件类型<br/>
 * Ticket: <br/>
 *
 * @author 邱昌进(qiuchangjin@zdwxjd.com)
 */
@Dic(overClass = AFCFTPFileType.class)
public class AFCFTPFileType extends Definition {

    private static AFCFTPFileType instance = new AFCFTPFileType();

    public static AFCFTPFileType getInstance() {
        return instance;
    }

    // 自定义类型， 文件导入类型定义,工作台使用
    @DicItem(name = "文件导入")
    public static Integer IMPORT = 0x99;

    // 自定义类型， EOD参数的资源文件
    @DicItem(name = "EOD参数的资源文件")
    public static Integer EOD_RES_FILE = 0x98;

    // 自定义类型，测试参数
    @DicItem(name = "EOD测试参数")
    public static Integer EOD_TEST = 0x71;

    @DicItem(name = "local")
    public static Integer local = 0x95;

    // EOD
    @DicItem(name = "EOD")
    public static Integer EOD = 0x7;

    // 黑灰名单
    @DicItem(name = "黑灰名单")
    public static Integer BLK = 0x8;

    // 模式履历
    @DicItem(name = "模式履历")
    public static Integer WAIVER = 0x9;

    // 权限
    @DicItem(name = "权限")
    public static Integer AUTH = 0x12;

    // 车站地图
    @DicItem(name = "车站地图文件")
    public static Integer STATION_MAP = 0x13;

    @DicItem(name = "设备软件")
    public static Integer SOFT_DEVICE = 0xA0;

    @DicItem(name = "TP软件")
    public static Integer SOFT_TP = 0xA1;

    @DicItem(name = "审计文件")
    public static Integer AUDIT_FILE = 0x0A;

    @DicItem(name = "结算文件")
    public static Integer SETTLE_FILE = 0x0B;

    @DicItem(name = "库存文件")
    public static Integer STOCK_FILE = 0x0C;

    @DicItem(name = "报表文件")
    public static Integer REPORT_FILE = 0x77;

}
