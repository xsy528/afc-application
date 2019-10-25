/**
 *
 */
package com.insigma.afc.ftp;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fenghong
 */
public class FtpUtil {

	private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

	private final static String File_Separator = "/";

	public static boolean uploadFile(FtpInfo ftpInfo, File uploadFile) {
		try {
			if (uploadFile == null) {
				return false;
			}
			if (uploadFile.isFile()) {
				return uploadFile(ftpInfo.getHost(), ftpInfo.getUser(), ftpInfo.getPass(), ftpInfo.getPort(),
						ftpInfo.getWorkDir(), uploadFile);
			} else {
				return uploadFiles(ftpInfo.getHost(), ftpInfo.getUser(), ftpInfo.getPass(), ftpInfo.getPort(),
						ftpInfo.getWorkDir(), uploadFile);
			}
		} catch (Exception e) {
			logger.error("上传文件：" + (uploadFile == null ? "Null" : uploadFile.getAbsolutePath()) + "失败", e);
			return false;
		}
	}

	public static boolean uploadFile(FtpInfo ftpInfo, String[] uploadFiles) {
		try {
			for (String path : uploadFiles) {
				uploadFile(ftpInfo, new File(path));
			}
			return true;
		} catch (Exception e) {
			logger.error("上传文件：" + StringUtils.join(uploadFiles, ",") + "失败", e);
			return false;
		}
	}

	private String basePath = "D:/cd/temp";

	public static Map<String,Boolean> uploadFiles(FTPClient ftpClient, List<MultipartFile> uploadFiles, boolean isDisconnect) {
		Map<String,Boolean> result = new HashMap<>();
		if (ftpClient == null) {
			logger.error("文件上传链接异常");
			return null;
		}
		for (MultipartFile file : uploadFiles) {
			InputStream fis = null;
			String bakName = null;
			boolean isUpload = false;
			String[] split = file.getOriginalFilename().split("/");
			String fileName = split[split.length-1];
			try {

				//先备份同名文件
				boolean isBak = ftpClient.rename(UTFToISO8859(fileName), UTFToISO8859(fileName + ".temp"));
				if (isBak) {
					bakName = fileName + ".temp";
				}

				fis = file.getInputStream();
				logger.debug("文件名称：" + fileName);
				String tempName = fileName + ".lck";

				boolean isStore = ftpClient.storeFile(UTFToISO8859(tempName), fis);
				if (isStore) {
					boolean isRename = ftpClient.rename(UTFToISO8859(tempName), UTFToISO8859(fileName));
					if (!isRename) {
						ftpClient.deleteFile(UTFToISO8859(tempName));
					}
				} else {
					ftpClient.deleteFile(UTFToISO8859(tempName));
					result.put(fileName,false);
					continue;
				}
				logger.debug("上传文件本地端口：" + ftpClient.getLocalPort());
				logger.debug("上传文件远程端口：" + ftpClient.getRemotePort());
				logger.debug("文件：'" + fileName + "' 上传成功");
				isUpload = true;
			} catch (IOException e) {
				logger.error("上传文件：" + StringUtils.join(uploadFiles, ",") + "失败", e);
			} finally {
				try {

					if (!isUpload && bakName != null) {
						ftpClient.rename(UTFToISO8859(bakName), UTFToISO8859(fileName));
					}
					if (isUpload && bakName != null) {
						ftpClient.deleteFile(UTFToISO8859(bakName));
					}
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					logger.error(fileName+">>>上传失败！");
					result.put(fileName,false);
				}

			}
			result.put(fileName,true);
		}

			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				logger.error("关闭FTP连接失败！");
			}

			return result;
	}

	public static boolean uploadFile(FtpInfo ftpInfo, List<String> uploadFiles) {
		try {
			for (String path : uploadFiles) {
				uploadFile(ftpInfo, new File(path));
			}
			return true;
		} catch (Exception e) {
			logger.error("上传文件：" + StringUtils.join(uploadFiles, ",") + "失败", e);
			return false;
		}
	}


	/**
	 * 
	 * @param ftpInfo
	 * @param remoteFile 远程文件名
	 * @param localFile  本地文件名
	 * @return
	 */
	public static boolean downloadFile(FtpInfo ftpInfo, String remoteFile, String localFile) {
		if (ftpInfo == null) {
			return false;
		}
		return downloadFile(ftpInfo.getHost(), ftpInfo.getUser(), ftpInfo.getPass(), ftpInfo.getPort(),
				ftpInfo.getWorkDir(), remoteFile, localFile);
	}

	/**
	 * 获得FTP连接，如果连接异常，返回NULL；
	 * 
	 * @param host
	 * @param user
	 * @param pwd
	 * @param remoteDirtemp
	 * @return
	 * @throws IOException
	 */
	public static FTPClient getFTPConnection(String host, String user, String pwd, int port, String remoteDirtemp)
			throws IOException {
		String remoteDir = null;
		if (remoteDirtemp != null) {
			remoteDir = UTFToISO8859(remoteDirtemp);
		}
		FTPClient fc = new FTPClient();
		try {
			logger.debug("链接FTP服务器：" + host + " 用户名：" + user + " 密码：" + pwd + " port: " + port);
			fc.connect(host, port);
			if (!fc.login(user, pwd)) {
				logger.error("FTP 连接到服务器 " + host + ": 用户名或密码错误");
				throw new IOException("FTP 连接到服务器 " + host + ": 用户名或密码错误。");
			}
			initFtp(fc, null);
			if (remoteDir != null && remoteDir.trim().length() > 0) {
				String[] remoteDirPaths = remoteDir.split("/");
				for (String remoteDirPath : remoteDirPaths) {
					if (!fc.changeWorkingDirectory(remoteDirPath)) {
						logger.error("FTP 连接到服务器 " + host + ": 切换工作目录（" + remoteDirPath + "）出错");
						boolean result = fc.makeDirectory(remoteDirPath);
						if (result) {
							fc.changeWorkingDirectory(remoteDirPath);
							logger.debug("工作目录不存在，新建目录成功");
						} else {
							logger.warn("工作目录不存在，新建目录失败");
							return null;
						}
					}
				}
			}
		} catch (IOException e) {
			logger.error("初始化FTP链接异常", e);
			throw e;
		}
		return fc;
	}

	/**
	 * 获取远程FTP目录的所有文件
	 * 
	 * @return the host
	 * @throws IOException
	 */
//	public static List<String> getRemoteFileList(String host, String user, String pwd, int port, String workDir)
//			throws IOException {
//		List<String> listFileName = new ArrayList<String>();
//
//		FTPClient fc = getFTPConnection(host, user, pwd, port, workDir);
//		if (fc == null) {
//			logger.warn("获取ftp连接失败");
//			return listFileName;
//		}
//		FTPFile[] files = null;
//		try {
//			files = fc.listFiles();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fc.isConnected()) {
//					fc.disconnect();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		for (FTPFile ftpfile : files) {
//			if (!ftpfile.isDirectory()) {
//				listFileName.add(ISO8859ToUTF(ftpfile.getName()));
//			} else {
//				// TODO 如果是文件夹
//			}
//		}
//		// 打印文件名称
//		// for (String fileName : listFileName) {
//		// logger.info("远程文件名称：" + fileName);
//		// }
//		// logger.info("远程目录： " + workDir + " 文件数：" + listFileName.size());
//		return listFileName;
//	}

	public static boolean downloadFiles(FtpInfo ftpInfo, String localPathName, FilenameFilter filter) {
		if (ftpInfo == null) {
			return false;
		}
		return downloadFiles(ftpInfo.getHost(), ftpInfo.getUser(), ftpInfo.getPass(), ftpInfo.getPort(),
				FTP.BINARY_FILE_TYPE, ftpInfo.getWorkDir(), localPathName, filter);
	}

	public static boolean downloadFiles(String server, String userName, String passWord, Integer port, Integer fileType,
			String remoteDirectoryName, String localAbsoluteDirectoryName, FilenameFilter filter) {
		String downDesc = "下载多个文件:ftp://" + userName + "@" + passWord + ":" + server + ":" + port + File.separator
				+ (remoteDirectoryName == null ? "" : remoteDirectoryName + File.separator) + "到本地文件夹："
				+ localAbsoluteDirectoryName;
		if (filter != null) {
			downDesc += ",下载文件名过滤器：" + filter;
		}
		logger.debug(downDesc);
		// if (remoteDirectoryName == null) {
		// logger.info("remoteDirectoryName 为空");
		// return false;
		// }

		FTPClient ftpClient = new FTPClient();
		try {
			if (port == null) {
				ftpClient.connect(server);
			} else {
				ftpClient.connect(server, port);
			}
			ftpClient.login(userName, passWord);
			initFtp(ftpClient, fileType);
			FTPFile[] files = null;
			if (null == remoteDirectoryName || remoteDirectoryName.trim().equals("")) {
				logger.debug("文件夹：" + remoteDirectoryName + " 为空");
				// files = ftpClient.listFiles();
			} else if (!ftpClient.changeWorkingDirectory(remoteDirectoryName)) {
				logger.warn("changeWorkingDirectory()方法执行失败");
				return false;
			}
			if (localAbsoluteDirectoryName == null) {
				logger.debug("localeDirectoryName 为空");
				return false;
			}
			// if (null != remoteDirectoryName) {
			files = ftpClient.listFiles();
			// }

			if (files.length == 0) {
				logger.warn("目录中不存在文件，无需进行下载");
				return false;
			}

			boolean isDownloadSuccess = true;

			final File dir = new File(remoteDirectoryName);
			for (FTPFile ftpFile : files) {
				if (filter != null) {
					if (!filter.accept(dir, ftpFile.getName())) {
						logger.debug("过滤器，不下载FTP文件：" + ftpFile.getName());
						continue;
					}
				}
				logger.debug("name:" + ftpFile.getName() + " File:" + ftpFile.isFile());
				// handle the file which is regular file

				isDownloadSuccess = downloadFile(ftpFile, ftpClient, localAbsoluteDirectoryName);
			}

			return isDownloadSuccess;
		} catch (IOException e) {
			logger.error("文件下载失败，\n" + e);
			throw new RuntimeException("文件下载失败。");
			//			return false;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (Exception e) {
				return false;
			}
		}
	}

	private static boolean downloadFile(FTPFile ftpFile, FTPClient ftpClient, String localAbsoluteDirectoryName)
			throws IOException {
		final String pathName = new String(ftpFile.getName().getBytes("iso-8859-1"), "UTF-8");

		if (ftpFile.isFile()) {
			String localFileName = localAbsoluteDirectoryName + File.separator + pathName;
			File localFile = new File(localFileName);
			if (!localFile.exists()) {
				logger.debug("parentFile:" + localFile.getParentFile().toString());
				if (!localFile.getParentFile().exists()) {
					if (!localFile.getParentFile().mkdirs()) {
						logger.warn("创建文件 " + localFileName + " 失败");
						return false;
					} else {
						localFile.createNewFile();
						logger.debug("创建文件本地文件 " + localFileName + " 成功");
					}
				} else {
					localFile.createNewFile();
					logger.debug("创建文件本地文件 " + localFileName + " 成功");
				}
			}
			if (!retrieveFile(ftpClient, ftpFile, localFile)) {
				logger.warn("下载文件" + pathName + "到本地失败");
				return false;
			}
			logger.debug("下载文件" + pathName + "成功");
		} else if (ftpFile.isDirectory()) {

			ftpClient.changeWorkingDirectory(pathName);
			FTPFile[] list = ftpClient.listFiles();
			for (FTPFile ftpFile2 : list) {
				downloadFile(ftpFile2, ftpClient,
						localAbsoluteDirectoryName + File.separator + pathName + File.separator);
			}
			ftpClient.changeToParentDirectory();

		}

		return true;
	}

	/**
	 * 下载文件，将远程目录中的文件下载到本地目录中
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP登录用户名
	 * @param passWord
	 *            FTP登录密码
	 * @param remoteDirectoryName
	 *            FTP工作目录,为空时默认下载用户名对应的目录下的所有文件
	 * @param localAbsoluteDirectoryName
	 *            本地工作目录绝对路径
	 * @return 下载成功与否
	 */
	public static boolean downloadFiles(String server, String userName, String passWord, Integer port, Integer fileType,
			String remoteDirectoryName, String localAbsoluteDirectoryName) {
		return downloadFiles(server, userName, passWord, port, fileType, remoteDirectoryName,
				localAbsoluteDirectoryName, null);

	}

	private static boolean retrieveFile(FTPClient ftpClient, FTPFile ftpFile, File localFile) throws IOException {
		FileOutputStream fos = null;
		boolean retrieveFile = false;
		try {
			fos = new FileOutputStream(localFile);
			retrieveFile = ftpClient.retrieveFile(ftpFile.getName(), fos);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return retrieveFile;
	}

	/**
	 * 下载文件
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP登录用户名
	 * @param passWord
	 *            FTP登录密码
	 * @param directoryName
	 *            FTP工作目录
	 * @param remoteFileName
	 *            远程文件名
	 * @param localFileName
	 *            本地文件全名
	 * @return 下载是否成功
	 */
	public static boolean downloadFile(String server, String userName, String passWord, Integer port, Integer fileType,
			String directoryName, String remoteFileName, String localFileName) {
		logger.debug("下载文件:ftp://" + userName + ":" + passWord + "@" + server + ":" + port + "/"
				+ (directoryName == null ? "" : directoryName + File.separator) + remoteFileName + "到本地文件："
				+ localFileName);
		if (remoteFileName == null) {
			logger.debug("remoteFileName 为空");
			return false;
		}
		FileOutputStream fos = null;
		FTPClient ftpClient = new FTPClient();
		File file = null;
		boolean result = false;
		try {
			if (port == null) {
				ftpClient.connect(server);
			} else {
				ftpClient.connect(server, port);
			}
			ftpClient.login(userName, passWord);
			initFtp(ftpClient, fileType);

			if (directoryName == null || directoryName.trim().equals("")) {
				logger.debug("不需要切换FTP工作目录");
				// return false;
			} else if (!ftpClient.changeWorkingDirectory(directoryName)) {
				logger.warn("切换FTP目录" + directoryName + "时失败");
				return false;
			}
			if (localFileName == null) {
				logger.debug("localFileName 为空");
				localFileName = remoteFileName;
			}
			file = new File(localFileName);
			if (!file.exists()) {
				logger.debug("localFileName的ParentFile: " + file.getParentFile().toString());
				if (!file.getParentFile().exists()) {
					if (!file.getParentFile().mkdirs()) {
						logger.warn("创建文件 " + localFileName + " 失败");
						return false;
					} else {
						file.createNewFile();
						logger.debug("创建文件本地文件 " + localFileName + " 成功");
					}
				} else {
					file.createNewFile();
					logger.debug("创建文件本地文件 " + localFileName + " 成功");
				}
			}
			fos = new FileOutputStream(file);
			result = ftpClient.retrieveFile(remoteFileName, fos);
			return result;
		} catch (IOException e) {
			logger.error("ftp下载文件失败", e);
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.flush();
					fos.close();
				}
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (Exception e) {
				logger.error("ftp下载文件失败", e);
				return false;
			}
			if (!result) {
				logger.debug("下载本地文件 " + file + " 失败。删除失败文件" + (file.delete() ? "成功" : "失败"));
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP登录用户名
	 * @param passWord
	 *            FTP登录密码
	 * @param directoryName
	 *            FTP工作目录
	 * @param remoteFileName
	 *            远程文件名
	 * @param localFileName
	 *            本地文件名
	 * @return 下载是否成功
	 */
	public static boolean downloadFile(String server, String userName, String passWord, int port, String directoryName,
			String remoteFileName, String localFileName) {
		return downloadFile(server, userName, passWord, port, null, directoryName, remoteFileName, localFileName);
	}

	/**
	 * 上传文件
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP用户名
	 * @param passWord
	 *            FTP密码
	 * @param remoteDir
	 *            远程工作目录
	 * @param file
	 *            上传文件（本地文件全路径）
	 * @param isDisconnect
	 *            是否为常连接
	 * @return
	 * @throws IOException
	 */
	public static boolean uploadFile(String server, String userName, String passWord, int port, String remoteDir,
			File file, boolean isDisconnect) throws IOException {
		FTPClient fc = getFTPConnection(server, userName, passWord, port, remoteDir);
		uploadFile(file, fc, isDisconnect);
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP用户名
	 * @param passWord
	 *            FTP密码
	 * @param remoteDir
	 *            远程工作目录
	 * @param file
	 *            上传文件（本地文件全路径）
	 * @return
	 * @throws IOException
	 */
	public static boolean uploadFile(String server, String userName, String passWord, int port, String remoteDir,
			File file) throws IOException {
		FTPClient fc = getFTPConnection(server, userName, passWord, port, remoteDir);
		uploadFile(file, fc);
		return true;
	}

	/**
	 * @param file
	 *            要上传的本地文件
	 * @param ftpClient
	 *            FTP连接客户端
	 * @return
	 * @throws IOException
	 */
	private static void uploadFile(File file, FTPClient ftpClient) throws IOException {
		uploadFile(file, ftpClient, false);
	}

	/**
	 * @param file
	 *            要上传的本地文件
	 * @param ftpClient
	 *            FTP连接客户端
	 * @param isDisconnect
	 *            是否常连接
	 * @return
	 * @throws IOException
	 */
	public static void uploadFile(File file, FTPClient ftpClient, boolean isDisconnect) throws IOException {
		if (ftpClient == null) {
			logger.error("文件上传链接异常");
			throw new IOException("文件上传链接异常。");
		}
		InputStream fis = null;
		String bakName = null;
		boolean isUpload = false;
		try {
			//先备份同名文件
			boolean isBak = ftpClient.rename(UTFToISO8859(file.getName()), UTFToISO8859(file.getName() + ".temp"));
			if (isBak) {
				bakName = file.getName() + ".temp";
			}
			fis = new FileInputStream(file);
			logger.debug("文件名称：" + file.getName());
			String tempName = file.getName() + ".lck";

			boolean isStore = ftpClient.storeFile(UTFToISO8859(tempName), fis);
			if (isStore) {
				boolean isRename = ftpClient.rename(UTFToISO8859(tempName), UTFToISO8859(file.getName()));
				if (!isRename) {
					ftpClient.deleteFile(UTFToISO8859(tempName));
				}
			} else {
				ftpClient.deleteFile(UTFToISO8859(tempName));
				throw new IOException("文件" + file.getName() + "上传失败。");
			}
			logger.debug("上传文件本地端口：" + ftpClient.getLocalPort());
			logger.debug("上传文件远程端口：" + ftpClient.getRemotePort());
			logger.debug("文件：'" + file.getName() + "' 上传成功");
			isUpload = true;
		} finally {
			if (isUpload && bakName != null) {
				ftpClient.deleteFile(UTFToISO8859(bakName));
			}
			if (!isUpload && bakName != null) {
				ftpClient.rename(UTFToISO8859(bakName), UTFToISO8859(file.getName()));
			}
			if (fis != null) {
				try {
					fis.close();
					if (isDisconnect) {
						ftpClient.disconnect();
					}
				} catch (IOException e) {
					logger.error("关闭文件流 异常", e);
				}
			}

		}
	}

	/**
	 * 上传文件（只上传文件，不处理目录和子目录下的文件）
	 * 
	 * @return
	 */
	public static Map<String, Boolean> uploadFiles(FTPClient ftpClient , List<File> files) {
		Map<String, Boolean> result = new HashMap<>();

		int count = 0;
		String fileName = null;
		for (File file : files) {
			try {

				fileName = file.getName();
				uploadFile(file, ftpClient);
				count++;
				result.put(fileName,true);
			} catch (Exception e) {
			logger.error("文件: " + fileName + " 上传异常", e);
			result.put(fileName,false);
			}
		}
			if (ftpClient != null) {
				try {
					if (ftpClient.isConnected()) {
						ftpClient.disconnect();
					}
				} catch (IOException e) {
					logger.error("断开FTP客户端链接异常", e);
				}
		}
		logger.debug("上传文件总 " + files.size() + " 成功 " + count + " 文件");
		return result;
	}

	/**
	 * 上传文件(包括子目录文件，如果远程子目录不存在则新建目录)
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP用户名
	 * @param passWord
	 *            FTP密码
	 * @param remoteDir
	 *            远程工作目录
	 * @param path
	 *            上传文件目录
	 * @return
	 */
	public static boolean uploadFiles(String server, String userName, String passWord, int port, String remoteDir,
			File path) {
		String remoteDirTemp = remoteDir;
		int count = 0;
		// boolean uploaded=false;
		String fileName = null;
		FTPClient ftpClient = null;
		File[] files = path.listFiles();
		try {
			ftpClient = getFTPConnection(server, userName, passWord, port, remoteDir);
			for (File file : files) {
				if (file.isFile()) {
					fileName = file.getName();
					uploadFile(file, ftpClient);
					count++;
				} else {
					remoteDir = remoteDir == null ? file.getName() : remoteDir + File_Separator + file.getName();
					uploadFiles(server, userName, passWord, port, remoteDir, file);
					remoteDir = remoteDirTemp;
				}
			}
		} catch (Exception e) {
			logger.error("文件: " + fileName + " 上传异常", e);
			return false;
		} finally {
			if (ftpClient != null) {
				try {
					if (ftpClient.isConnected()) {
						ftpClient.disconnect();
					}
				} catch (IOException e) {
					logger.error("断开FTP客户端链接异常", e);
				}
			}
		}
		logger.debug("上传文件总 " + files.length + " 成功 " + count + " 文件");
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP用户名
	 * @param passWord
	 *            FTP密码
	 * @param fileName
	 *            要上传的文件名
	 * @param directoryName
	 *            远处目录
	 * @param targetName
	 *            要保存到服务器的文件名
	 * @return 上传是否成功
	 */
	public static boolean uploadFile(String server, String userName, String passWord, String fileName,
			String directoryName, String targetName) {
		if (fileName == null) {
			return false;
		}
		FileInputStream fis = null;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server);
			ftpClient.login(userName, passWord);
			initFtp(ftpClient, null);
			if (directoryName != null && directoryName.trim().length() > 0
					&& !ftpClient.changeWorkingDirectory(directoryName)) {
				logger.error("工作目录切换失败");
				return false;
			}
			fis = new FileInputStream(fileName);
			if (targetName == null) {
				targetName = new File(fileName).getName();
			}
			return ftpClient.storeFile(UTFToISO8859(targetName), fis);

		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (null != fis) {
					fis.close();
				}
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				return false;
			}
		}
	}

	/**
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP用户名
	 * @param passWord
	 *            FTP密码
	 * @param fileName
	 *            要上传的文件绝对路径和文件名
	 * @param directoryName
	 *            ftp工作目录
	 * @param subDirectoryName
	 *            ftp工作子目录
	 * @param targetName
	 *            保存到服务器的文件名
	 * @return 上传是否成功
	 */
	public static boolean uploadFileWithDirectory(String server, String userName, String passWord, String fileName,
			String directoryName, String subDirectoryName, String targetName) {

		String remoteDir = UTFToISO8859(directoryName);
		if (fileName == null) {
			logger.warn("导入文件: " + targetName + "不存在");
			return false;
		}
		FileInputStream fis = null;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server);
			ftpClient.login(userName, passWord);
			initFtp(ftpClient, null);
			if (directoryName != null && directoryName.trim().length() > 0
					&& !ftpClient.changeWorkingDirectory(directoryName)) {

				boolean result = ftpClient.makeDirectory(remoteDir);
				if (result) {
					ftpClient.changeWorkingDirectory(remoteDir);
					logger.debug("工作目录不存在，新建目录成功");
				} else {
					logger.warn("工作目录不存在，新建目录失败");
					return false;
				}
			}
			logger.info("切换工作目录" + directoryName + "成功");

			if (subDirectoryName != null && subDirectoryName.trim().length() > 0) {
				boolean isExsit = isExsitDirectory(ftpClient, subDirectoryName);
				if (!isExsit) {
					if (ftpClient.makeDirectory(subDirectoryName)) {
						subDirectoryName = subDirectoryName.trim();
						logger.debug("工作子目录不存在，新建目录成功");
					} else {
						logger.warn("工作子目录不存在，新建目录失败");
						return false;
					}
				}
			}
			fis = new FileInputStream(fileName);
			if (targetName == null) {
				targetName = new File(fileName).getName();
			}
			final String remoteFile = (null == subDirectoryName || subDirectoryName.length() == 0 ? ""
					: subDirectoryName + File_Separator) + targetName;
			boolean storeFile = ftpClient.storeFile(UTFToISO8859(remoteFile), fis);
			if (!storeFile) {
				logger.error("上传文件: " + targetName + " 保存失败");
				return false;
			} else {
				logger.debug("上传文件成功");
				return true;
			}
		} catch (IOException e) {
			logger.error("导入文件 " + targetName + " 失败");
			return false;
		} finally {
			try {
				if (null != fis) {
					fis.close();
				}
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				logger.error("关闭FTP连接异常");
				return false;
			}
		}
	}

	private static boolean isExsitDirectory(FTPClient ftpClient, String subDirectoryName) throws IOException {
		FTPFile[] listFiles = ftpClient.listFiles();
		for (FTPFile file : listFiles) {
			if (file.isDirectory() && file.getName().equals(subDirectoryName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 上传文件 并且抛出FTP具体出错原因
	 * 
	 * @param server
	 *            FTP服务器地址
	 * @param userName
	 *            FTP用户名
	 * @param passWord
	 *            FTP密码
	 * @param fileName
	 *            要上传的文件绝对路径和文件名
	 * @param directoryName
	 *            远处目录
	 * @param targetName
	 *            要保存到服务器的文件名
	 * @return 上传是否成功 author zhengshuquan
	 */
	public static String uploadFileAndThrowException(String server, String userName, String passWord, String fileName,
			String directoryName, String targetName) {
		String ftpLog = null;
		String remoteDir = UTFToISO8859(directoryName);
		if (fileName == null) {
			ftpLog = "导入文件: " + targetName + "不存在。";
			return ftpLog;
			// throw new RuntimeException(ftpLog);
		}
		FileInputStream fis = null;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server);
			ftpClient.login(userName, passWord);
			initFtp(ftpClient, null);
			if (directoryName != null && directoryName.trim().length() > 0
					&& !ftpClient.changeWorkingDirectory(directoryName)) {
				// logger.error("工作目录切换失败。");

				boolean result = ftpClient.makeDirectory(remoteDir);
				if (result) {
					ftpClient.changeWorkingDirectory(remoteDir);
					logger.debug("工作目录不存在，新建目录成功");
				} else {
					logger.warn("工作目录不存在，新建目录失败");
					ftpLog = "工作目录不存在，新建目录失败。";
					return ftpLog;
				}
			}
			fis = new FileInputStream(fileName);
			if (targetName == null) {
				targetName = new File(fileName).getName();
			}
			boolean storeFile = ftpClient.storeFile(UTFToISO8859(targetName), fis);
			if (!storeFile) {
				ftpLog = "导入文件: " + targetName + " 保存失败。";
				return ftpLog;
			} else {
				ftpLog = "导入文件成功。";
				return ftpLog;
				// throw new RuntimeException(ftpLog);
			}

		} catch (IOException e) {
			ftpLog = "导入文件 " + targetName + " 失败。";
			return ftpLog;
			// throw new RuntimeException(ftpLog, e);
		} finally {
			try {
				if (null != fis) {
					fis.close();
				}
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				ftpLog += "  关闭FTP连接异常。";
				return ftpLog;
				// throw new RuntimeException(ftpLog, e);
			}
		}
	}

	/**
	 * 转码[UTF-8 -> ISO-8859-1]
	 * 
	 * @param obj
	 * @return
	 */
	public static String UTFToISO8859(Object obj) {
		try {
			if (obj == null) {
				return "";
			} else {
				return new String(obj.toString().getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 转码[ ISO-8859-1->UTF-8 ]
	 * 
	 * @param obj
	 * @return
	 */
	public static String ISO8859ToUTF(Object obj) {
		try {
			if (obj == null) {
				return "";
			} else {
				return new String(obj.toString().getBytes("iso-8859-1"), "UTF-8");
			}
		} catch (Exception e) {
			return "";
		}
	}

	private static void initFtp(FTPClient ftpClient, Integer fileType) {
		try {
			if (fileType == null) {
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			} else {
				ftpClient.setFileType(fileType);
			}
			if (System.getProperty("enterLocalPassiveMode") != null
					&& !System.getProperty("enterLocalPassiveMode").equals("")) {
				boolean enterLocalPassiveMode = Boolean.valueOf(System.getProperty("enterLocalPassiveMode"));
				if (enterLocalPassiveMode) {
					ftpClient.enterLocalPassiveMode();
				}
			}

			if (System.getProperty("dataTimeout") != null && !System.getProperty("dataTimeout").equals("")) {
				ftpClient.setDataTimeout(Integer.valueOf(System.getProperty("dataTimeout")));
			} else {
				ftpClient.setDataTimeout(60000);
			}

			if (System.getProperty("connectTimeout") != null && !System.getProperty("connectTimeout").equals("")) {
				ftpClient.setConnectTimeout(Integer.valueOf(System.getProperty("connectTimeout")));
			} else {
				ftpClient.setConnectTimeout(15000);
			}

		} catch (Exception e) {
			logger.error("FTP初始化异常", e);
			throw new RuntimeException(e);
		}
	}
}
