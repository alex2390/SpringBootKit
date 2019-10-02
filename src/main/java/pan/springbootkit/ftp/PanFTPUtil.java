//package pan.springbootkit.ftp;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTPReply;
//import sun.misc.BASE64Encoder;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
///**
// * Created by panzhangbao on 2019-09-24 17:51:44
// * Copyright © 2019 panzhangbao. All rights reserved.
// */
//@Slf4j
//public class PanFTPUtil {
//	/**
//	 * ftp 服务器地址
//	 */
//	private static String url = "101.132.70.78";
//
//	/**
//	 * 用户名
//	 */
//	private static String userName = "root";
//
//	/**
//	 * 密码
//	 */
//	private static String password = "Fedora2018";
//
//	/**
//	 * 本地文件目录
//	 */
//	private static String localDir = "/Users/panzhangbao/Desktop";
//
//	/**
//	 * 远程存储地址
//	 */
//	private static String remoteDir = "/opt/admin";
//
//	private static FTPClient connectFtpServer(){
//		FTPClient ftpClient = new FTPClient();
//		ftpClient.setConnectTimeout(1000*30);//设置连接超时时间
//		ftpClient.setControlEncoding("utf-8");//设置ftp字符集
//		ftpClient.enterLocalPassiveMode();//设置被动模式，文件传输端口设置
//		try {
//			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件传输模式为二进制，可以保证传输的内容不会被改变
//			ftpClient.connect(url);
//			ftpClient.login(userName,password);
//			int replyCode = ftpClient.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(replyCode)){
//				log.error("connect ftp {} failed",url);
//				ftpClient.disconnect();
//				return null;
//			}
//			log.info("replyCode==========={}",replyCode);
//		} catch (IOException e) {
//			log.error("connect fail ------->>>{}",e.getCause());
//			return null;
//		}
//		return ftpClient;
//	}
//
//	/**
//	 *
//	 * @param inputStream 待上传文件的输入流
//	 * @param originName 文件保存时的名字
//	 */
//	public static void uploadFile(InputStream inputStream, String originName){
//		FTPClient ftpClient = connectFtpServer();
//		if (ftpClient == null){
//			return;
//		}
//
//		try {
//			// 进入到文件保存的目录
//			ftpClient.changeWorkingDirectory(remoteDir);
//			Boolean isSuccess = ftpClient.storeFile(originName,inputStream);//保存文件
//			if (!isSuccess){
//				log.error("上传失败");
//			}
//			log.info("上传成功！",originName);
//			ftpClient.logout();
//		} catch (IOException e) {
//			log.error("上传失败！",originName);
//		}finally {
//			if (ftpClient.isConnected()){
//				try {
//					ftpClient.disconnect();
//				} catch (IOException e) {
//					log.error("disconnect fail ------->>>{}",e.getCause());
//				}
//			}
//		}
//	}
//
//	/**
//	 *  读ftp上的文件，并将其转换成base64
//	 * @param remoteFileName ftp服务器上的文件名
//	 * @return
//	 */
//	public static String readFileToBase64(String remoteFileName){
//		FTPClient ftpClient = connectFtpServer();
//		if (ftpClient == null){
//			return null;
//		}
//
//		String base64 = "";
//		InputStream inputStream = null;
//
//		try {
//			ftpClient.changeWorkingDirectory(remoteDir);
//			FTPFile[] ftpFiles = ftpClient.listFiles(remoteDir);
//			Boolean flag = false;
//			//遍历当前目录下的文件，判断要读取的文件是否在当前目录下
//			for (FTPFile ftpFile : ftpFiles) {
//				if (ftpFile.getName().equals(remoteFileName)) {
//					flag = true;
//				}
//			}
//
//			if (!flag) {
//				log.error("directory：{}下没有 {}", remoteDir, remoteFileName);
//				return null;
//			}
//			//获取待读文件输入流
//			inputStream = ftpClient.retrieveFileStream(remoteDir + remoteFileName);
//
//
//			//inputStream.available() 获取返回在不阻塞的情况下能读取的字节数，正常情况是文件的大小
//			byte[] bytes = new byte[inputStream.available()];
//
//			inputStream.read(bytes);//将文件数据读到字节数组中
//			BASE64Encoder base64Encoder = new BASE64Encoder();
//			base64 = base64Encoder.encode(bytes);//将字节数组转成base64字符串
//			log.info("read file {} success",remoteFileName);
//			ftpClient.logout();
//		} catch (IOException e) {
//			log.error("read file fail ----->>>{}",e.getCause());
//			return null;
//		}finally {
//			if (ftpClient.isConnected()){
//				try {
//					ftpClient.disconnect();
//				} catch (IOException e) {
//					log.error("disconnect fail ------->>>{}",e.getCause());
//				}
//			}
//
//			if (inputStream != null){
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					log.error("inputStream close fail -------- {}",e.getCause());
//				}
//			}
//
//		}
//
//		return base64;
//	}
//
//	/**
//	 * 文件下载
//	 * @param remoteFileName ftp上的文件名
//	 * @param localFileName 本地文件名
//	 */
//	public static void download(String remoteFileName,String localFileName){
//		FTPClient ftpClient = connectFtpServer();
//		if (ftpClient == null){
//			return ;
//		}
//
//		OutputStream outputStream = null;
//
//		try {
//			ftpClient.changeWorkingDirectory(remoteDir);
//			FTPFile[] ftpFiles = ftpClient.listFiles(remoteDir);
//			Boolean flag = false;
//			//遍历当前目录下的文件，判断是否存在待下载的文件
//			for (FTPFile ftpFile:ftpFiles){
//				if (ftpFile.getName().equals(remoteFileName)){
//					flag = true;
//					break;
//				}
//			}
//
//			if (!flag){
//				log.error("directory：{}下没有 {}",remoteDir,remoteFileName);
//				return ;
//			}
//
//			outputStream = new FileOutputStream(localDir + localFileName);//创建文件输出流
//
//			Boolean isSuccess = ftpClient.retrieveFile(remoteFileName,outputStream); //下载文件
//			if (!isSuccess){
//				log.error("download file 【{}】 fail",remoteFileName);
//			}
//
//			log.info("download file success");
//			ftpClient.logout();
//		} catch (IOException e) {
//			log.error("download file 【{}】 fail ------->>>{}",remoteFileName,e.getCause());
//		}finally {
//			if (ftpClient.isConnected()){
//				try {
//					ftpClient.disconnect();
//				} catch (IOException e) {
//					log.error("disconnect fail ------->>>{}",e.getCause());
//				}
//			}
//
//			if (outputStream != null){
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					log.error("outputStream close fail ------->>>{}",e.getCause());
//				}
//			}
//		}
//	}
//
//	/**
//	 * main Method
//	 */
//	public static void main(String[] args) {
//	  download("a.txt", "bbb.txt");
//	}
//}
