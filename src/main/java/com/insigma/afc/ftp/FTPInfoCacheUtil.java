package com.insigma.afc.ftp;

/**
 * @Author by Xinshao,
 * @Email xingshaoya@unittec.com,
 * @Time on 2019/8/28 11:26.
 * @Ticket :
 */
public class FTPInfoCacheUtil {
    private static FTPInfoCacheUtil instance = new FTPInfoCacheUtil();

    public static FTPInfoCacheUtil getInstance(){
        return  instance;
    }

    private static FTPInfoManager infoManager;

    public  FTPInfoManager getInfoManager() {
        return infoManager;
    }

    public void setInfoManager(FTPInfoManager infoManager) {
        FTPInfoCacheUtil.infoManager = infoManager;
    }

   

}
