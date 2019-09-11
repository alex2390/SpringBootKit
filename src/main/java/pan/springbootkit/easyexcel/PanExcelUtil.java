package pan.springbootkit.easyexcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Excel util
 *
 * Created by panzhangbao on 2019-09-11 08:57:56
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class PanExcelUtil {

    /**
     * 读取 Excel(多个 sheet)
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        for (Sheet sheet : reader.getSheets()) {
            if (rowModel != null) {
                sheet.setClazz(rowModel.getClass());
            }
            reader.read(sheet);
        }
        return excelListener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo) {
        return readExcel(excel, rowModel, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo,
										 int headLineNum) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel.getClass()));

        return excelListener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     */
    public static void writeExcel(HttpServletResponse response,
								  List<? extends BaseRowModel> list,
								  String fileName,
								  String sheetName) {
    	/**
    	 * 参数合法性校验
    	 */
    	if (CollectionUtils.isEmpty(list)) {
    		return;
		}

    	// sheet
    	Sheet sheet = new Sheet(1, 0, list.get(0).getClass());
		if (StringUtils.isNotBlank(sheetName)) {
			sheet.setSheetName(sheetName);
		}


        ExcelWriter writer = new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        writer.write(list, sheet);
        writer.finish();

        /**
         * 删除本地文件
         */
		deleteLocalFile(fileName);
	}

    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     */
    public static ExcelWriterFactroy writeExcelWithSheets(HttpServletResponse response,
														  List<? extends BaseRowModel> list,
														  String fileName,
														  String sheetName) {
		/**
		 * 参数合法性校验
		 */
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		// sheet
		Sheet sheet = new Sheet(1, 0, list.get(0).getClass());
		if (StringUtils.isNotBlank(sheetName)) {
			sheet.setSheetName(sheetName);
		}

        ExcelWriterFactroy writer = new ExcelWriterFactroy(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        writer.write(list, sheet);

		/**
		 * 删除本地文件
		 */
		deleteLocalFile(fileName);

        return writer;
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        // 创建本地文件
        String filePath = fileName + ".xlsx";
        File dbfFile = new File(filePath);
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                dbfFile.createNewFile();
            }
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "filename=" + fileName);
			response.setCharacterEncoding("UTF-8");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new ExcelException("创建文件失败！");
        }
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) {
    	/**
    	 * 参数合法性校验
    	 */
    	if (null == excel || null == excelListener) {
    		return null;
		}

        String filename = excel.getOriginalFilename();
        if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
            throw new ExcelException("文件格式错误！");
        }
        try {
			InputStream inputStream = new BufferedInputStream(excel.getInputStream());

            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

	/**
	 * 删除本地根目录下文件
	 *
	 * @param fileName
	 * @return java.lang.Boolean
	 * @date 2019-09-11 14:50
	 * @author panzhangbao
	 */
	private static Boolean deleteLocalFile(String fileName) {
		StringBuilder filePath = new StringBuilder(System.getProperty("user.dir"))
				.append("/")
				.append(fileName)
				.append(".xlsx");

		File file = new File(filePath.toString());
		if (file.exists()) {
			return file.delete();
		}

		return false;
	}
}
