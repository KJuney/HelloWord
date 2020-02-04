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
 * ����FTP����
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
	 * @param ip Զ��IP
	 * @param user Զ���û���
	 * @param password Զ���û���Ӧ������
	 */
	public MiniFtp(String ip,String user,String password){
		this.remoteIp = ip;
		this.remoteUser = user;
		this.remotePass = password;
	}
	
	
	/**
	 * �ϴ��ļ�
	 * @param srcFilePath �����ļ�����·��
	 * @param toFilePath Ŀ��Ŀ¼·��
	 * @param toFileName �ļ���
	 * @throws IOException
	 */
	public void uploadFile(String srcFilePath, String toFilePath, String toFileName) throws IOException{
		FTPClient ftpClient = new FTPClient();
		try {
			logger.info("remoteIp:"+remoteIp);
			logger.info("remoteUser:"+remoteUser);
			logger.info("remotePass:"+remotePass);
			logger.info("���ӿ�ʼ");
			ftpClient.connect(remoteIp);
			logger.info("���ӽ���");
			logger.info("��¼��ʼ");
			ftpClient.login(remoteUser, remotePass);
			logger.info("��¼����");
		//	ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType( FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
//			srcFilePath = new String(srcFilePath.getBytes("GBK"),"ISO-8859-1");
			InputStream iStream = new FileInputStream(srcFilePath);
			ftpClient.changeWorkingDirectory(toFilePath);
			ftpClient.storeFile(toFileName, iStream);

			iStream.close();
			logger.info("����");
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
	 * �ϴ��ļ�
	 * @param fileData �ļ�����
	 * @param toFilePath Ŀ��Ŀ¼·��
	 * @param toFileName �ļ���
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
//				logger.error("��¼ftp������ʧ�ܣ�");
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
//			logger.error("�ϴ�����������ftp������ʧ�ܣ�", e);
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
			logger.info("�쳣��",e);
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
	 * ����sftp������
	 * 
	 * @param host
	 *            ����
	 * @param port
	 *            �˿�
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
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
	 * �ϴ��ļ�
	 * 
	 * @param directory
	 *            �ϴ���Ŀ¼
	 * @param uploadFile
	 *            Ҫ�ϴ����ļ�
	 * @param sftp
	 */
	public static void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			System.out.println("directory = "+directory);
			System.out.println("uploadFile = "+uploadFile);
			try {
			    sftp.cd(directory);
			   }catch(SftpException e){
				System.out.println("Զ�̷���Ŀ¼�����ڣ��ļ��У�"+directory+"��������");
//			    logger.info("Զ�̷���Ŀ¼�����ڣ��ļ��У�"+directory+"��������",e);
			    sftp.mkdir(directory);
			    sftp.cd(directory);
			   }
			File file = new File(uploadFile);
			if(!file.exists()){
				file = new File(directory+File.separator+uploadFile);
			}
			System.out.println("---------���Կ�ʼ------");
			sftp.put(new FileInputStream(file), file.getName());
			System.out.println("���ԡ�����������");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * �ϴ��ļ�
	 * @param host IP��ַ
	 * @param port �˿ں�
	 * @param username �û���
	 * @param password �û�����
	 * @param directory �ϴ���Ŀ¼
	 * @param uploadFile ��ҪҪ�ϴ����ļ�
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
				System.out.println("Զ�̷���Ŀ¼�����ڣ��ļ��У�"+directory+"��������");
//			    logger.info("Զ�̷���Ŀ¼�����ڣ��ļ��У�"+directory+"��������",e);
			    sftp.mkdir(directory);
			    sftp.cd(directory);
			   }
			File file = new File(uploadFile);
			if(!file.exists()){
				file = new File(directory+File.separator+uploadFile);
			}
			sftp.put(new FileInputStream(file), file.getName());
			System.out.println("���ԡ�����������");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			channel.disconnect();
			sshSession.disconnect();
		}
	}
	
}
