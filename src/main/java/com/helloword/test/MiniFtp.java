/**
 * 
 */
package com.helloword.test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


/**
 * 简易FTP程序
 * 
 * @author <a href="mailto:lihz2@asiainfo-linkage.com">FluteD</a>
 * @version 2011-8-11
 */
public class MiniFtp {

	private static Log logger = LogFactory.getLog(MiniFtp.class);
	private String remoteIp = "";

	private String remoteUser = "";

	private String remotePass = "";
	
	/**
	 * 
	 * @param ip 远端IP
	 * @param user 远端用户端
	 * @param password 远端用户对应的密码
	 */
	public MiniFtp(String ip,String user,String password){
		this.remoteIp = ip;
		this.remoteUser = user;
		this.remotePass = password;
	}
	
	
	/**
	 * 上传文件
	 * @param srcFilePath 本地文件绝对路径
	 * @param toFilePath 目标目录路径
	 * @param toFileName 文件名
	 * @throws IOException
	 */
	public void uploadFile(String srcFilePath, String toFilePath, String toFileName) throws IOException{
		FTPClient ftpClient = new FTPClient();
		try {
			logger.info("remoteIp:"+remoteIp);
			logger.info("remoteUser:"+remoteUser);
			logger.info("remotePass:"+remotePass);
			logger.info("连接开始");
			ftpClient.connect(remoteIp);
			logger.info("连接结束");
			logger.info("登录开始");
			ftpClient.login(remoteUser, remotePass);
			logger.info("登录结束");
		//	ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType( FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
//			srcFilePath = new String(srcFilePath.getBytes("GBK"),"ISO-8859-1");
			InputStream iStream = new FileInputStream(srcFilePath);
			ftpClient.changeWorkingDirectory(toFilePath);
			ftpClient.storeFile(toFileName, iStream);

			iStream.close();
			logger.info("结束");
		} catch (IOException e) {
			throw e;
		} finally {
			if (ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		}
	}
	
	/**
	 * 上传文件
	 * @param fileData 文件数据
	 * @param toFilePath 目标目录路径
	 * @param toFileName 文件名
	 * @throws IOException
	 */
	public void uploadFile(byte[] fileData, String toFilePath, String toFileName) throws IOException{
		FTPClient ftpClient = new FTPClient();
//		try {
//			
//			ftpClient.setControlEncoding("utf-8");
//			int port = 21;
//			ftpClient.connect(remoteIp, port);
//			
//			ftpClient.login(remoteUser, remotePass);
//			int replyCode = ftpClient.getReplyCode();
//			ftpClient.enterLocalPassiveMode();
//			if (!FTPReply.isPositiveCompletion(replyCode)) {
//				logger.error("登录ftp服务器失败！");
//				ftpClient.disconnect();
//			}
//			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//			InputStream inputStream = null;
//			try {
//				logger.info("filename:" + toFileName);
//				inputStream = new FileInputStream(new File("/app/toptea-itsm-ljdj/toptea", toFileName));
//				//ftpClient.changeWorkingDirectory(toFilePath);
//				boolean flag = ftpClient.storeFile(toFileName,
//						inputStream);
//				logger.info("flag************************"+flag);
//				inputStream.close();
//			} finally {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//			}
//			
//			
//			
////			ByteArrayInputStream iStream = new ByteArrayInputStream(fileData);
////			boolean flag = ftpClient.storeFile(toFileName,	iStream);
////			logger.info("flag************************"+flag);
////			iStream.close();
//		} catch (Exception e) {
//			logger.error("上传附件到集团ftp服务器失败！", e);
//		} finally {
//		if (ftpClient != null) {
//			ftpClient.logout();
//			ftpClient.disconnect();
//		}
//		}
		
		try {
			ftpClient.connect(remoteIp);
			ftpClient.login(remoteUser, remotePass);
			ftpClient.setFileType( FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
			ByteArrayInputStream iStream = new ByteArrayInputStream(fileData);
			boolean directory = ftpClient.changeWorkingDirectory(toFilePath);
			logger.info("directory = "+directory);
			boolean flag = ftpClient.storeFile(toFileName, iStream);
			logger.info("flag = "+flag);
			logger.info("flag*******************"+flag);
			iStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("异常：",e);
			throw e;
		} finally {
			if (ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		}
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getRemotePass() {
		return remotePass;
	}

	public void setRemotePass(String remotePass) {
		this.remotePass = remotePass;
	}
	
	public static void main(String[] args) {
		
/*		MiniFtp ftp = new MiniFtp("10.255.39.39", "smopt","smopt@2018");
		File file = new File("E:/itsm_num1.js");
		
		 byte[] buffer = null;
	        try
	        {
	            FileInputStream fis = new FileInputStream(file);
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            byte[] b = new byte[1024];
	            int n;
	            while ((n = fis.read(b)) != -1)
	            {
	                bos.write(b, 0, n);
	            }
	            fis.close();
	            bos.close();
	            buffer = bos.toByteArray();
	            ftp.uploadFile(buffer, "/toptdata/smopt/data/","itsm_num1.js");
	        }
	        catch (FileNotFoundException e)
	        {
	            e.printStackTrace();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }*/
		
		
		String host = "10.255.39.39";
		int port = 21;
		String username = "smopt";
		String password = "smopt@2018";
		String directory = "/toptdata/smopt/data/";
		String uploadFile = "E:/itsm_num1.js";
		ChannelSftp sftp = connect(host, port, username, password);
		upload(directory, uploadFile, sftp);
		try {
			sftp.cd(directory);
			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public static ChannelSftp connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}
	
	
/*	public static void upload(String directory, String uploadFile,String host) {
		ChannelSftp sftp = connect(host, FTP_PORT, FTP_USER, FTP_PASSWORD);
		upload(directory, uploadFile, sftp);
	}*/
	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public static void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			System.out.println("directory = "+directory);
			System.out.println("uploadFile = "+uploadFile);
			try {
			    sftp.cd(directory);
			   }catch(SftpException e){
				System.out.println("远程服务目录不存在：文件夹："+directory+"将被创建");
//			    logger.info("远程服务目录不存在：文件夹："+directory+"将被创建",e);
			    sftp.mkdir(directory);
			    sftp.cd(directory);
			   }
			File file = new File(uploadFile);
			if(!file.exists()){
				file = new File(directory+File.separator+uploadFile);
			}
			System.out.println("---------测试开始------");
			sftp.put(new FileInputStream(file), file.getName());
			System.out.println("测试。。。。结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 上传文件
	 * @param host IP地址
	 * @param port 端口号
	 * @param username 用户名
	 * @param password 用户密码
	 * @param directory 上传的目录
	 * @param uploadFile 需要要上传的文件
	 * 
	 */
	public static void sftpUpload(String host, int port, String username,String password,
			String directory, String uploadFile) {
		ChannelSftp sftp = null;
		Session sshSession = null;
		Channel channel = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
			
			System.out.println("directory = "+directory);
			System.out.println("uploadFile = "+uploadFile);
			try {
			    sftp.cd(directory);
			   }catch(SftpException e){
				System.out.println("远程服务目录不存在：文件夹："+directory+"将被创建");
//			    logger.info("远程服务目录不存在：文件夹："+directory+"将被创建",e);
			    sftp.mkdir(directory);
			    sftp.cd(directory);
			   }
			File file = new File(uploadFile);
			if(!file.exists()){
				file = new File(directory+File.separator+uploadFile);
			}
			sftp.put(new FileInputStream(file), file.getName());
			System.out.println("测试。。。。结束");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			channel.disconnect();
			sshSession.disconnect();
		}
	}
	
}
