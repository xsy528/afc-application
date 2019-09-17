package com.insigma.afc.datasource;


import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重写setPassword方法，新增数据库密码解密。
 */
public class EncryptedDataSource extends BasicDataSource {
	private static final Logger logger = LoggerFactory.getLogger(EncryptedDataSource.class);

	@Override
	public void setPassword(String password) {
		String depcrytedPassword = null;
		try {
			depcrytedPassword = DESUtil.decrypt(password);
		} catch (Exception e) {
			logger.error("数据库密码解密失败", e);
		}
		super.setPassword(depcrytedPassword);
	}
}
