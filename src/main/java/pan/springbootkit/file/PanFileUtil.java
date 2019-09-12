package pan.springbootkit.file;import org.apache.commons.lang3.StringUtils;import org.springframework.web.multipart.MultipartFile;import pan.springbootkit.utils.http.PanHttpUtil;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.*;/** * 文件 util * * Created by panzhangbao on 1/13/18. * Copyright © 2017年 panzhangbao. All rights reserved. */public class PanFileUtil {	/**	 * 本地存储文件目录	 */	private static final String uploadFilePath = "/Users/panzhangbao/Documents/";	/**	 * 本地下载文件目录	 */	private static final String downloadFilePath = "/Users/panzhangbao/Downloads/";	/**	 * 本地服务器下载	 *	 * @param	 * @return void	 * @date 2019-09-12 17:41	 * @author panzhangbao	 */	public static void localServerDownload() {		HttpServletRequest request = PanHttpUtil.getRequest();		HttpServletResponse response = PanHttpUtil.getResponse();		String fileUrlPath = request.getParameter("fileUrlPath");		if (StringUtils.isBlank(fileUrlPath)) {			return;		}		File file = new File(fileUrlPath);		DataInputStream in = null;		OutputStream out = null;		try {			response.reset();// 清空输出流			response.setCharacterEncoding("UTF-8");			// 防止中文乱码			String fileName = new String(file.getName().getBytes("gbk"), "iso8859-1");			// 强制下载不打开			response.setContentType("application/force-download");			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);						// 打开文件//			response.setHeader("Content-disposition", "inline;attachment; filename=" + fileName);//			// 定义输出类型//			response.setContentType("image/png");						// 输入流：本地文件路径			in = new DataInputStream(new FileInputStream(file));			// 输出流			out = response.getOutputStream();			// 输出文件			int bytes = 0;			byte[] bufferOut = new byte[1024];			while ((bytes = in.read(bufferOut)) != -1) {				out.write(bufferOut, 0, bytes);			}		} catch(Exception e){			e.printStackTrace();			response.reset();			try {				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8");				String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";				writer.write(data);				writer.close();			} catch (IOException e1) {				e1.printStackTrace();			}		}finally {			if(null != in) {				try {					in.close();				} catch (IOException e) {					e.printStackTrace();				}			}			if(null != out) {				try {					out.close();				} catch (IOException e) {					e.printStackTrace();				}			}		}	}	/**	 * 本地服务器上传文件	 *	 * @param multipartFile	 * @return	 */	public static String localServerUploadFile(MultipartFile multipartFile){    	/**    	 * 参数合法性校验    	 */    	if (null == multipartFile ||  multipartFile.getSize() <= 0) {    		return null;		}//		  String fileName = file.getOriginalFilename();   // 例如：wolf.jpg//        String contentType = file.getContentType(); // 例如：image/png//        String filePath = request.getSession().getServletContext().getRealPath("uploadFile/");    // 服务器真实路径//        String filePath = "/Users/slcf666/Desktop/";    // 上传后的文件存储路径 - 这里暂时写的路径为 本地桌面		// 存储文件地址		String fileUrlPath = uploadFilePath + multipartFile.getOriginalFilename();        File file = new File(uploadFilePath);        if(!file.exists()){            file.mkdirs();        }		FileOutputStream fos;		try {			fos = new FileOutputStream(fileUrlPath);			fos.write(multipartFile.getBytes());			fos.flush();			fos.close();		} catch (FileNotFoundException e) {			e.printStackTrace();		} catch (IOException e) {			e.printStackTrace();		}		/**		 * 封装返回文件地址		 */		StringBuilder result = new StringBuilder(PanHttpUtil.getRequest().getRequestURL())				.append("?fileUrlPath=")				.append(fileUrlPath);		return result.toString();	}}