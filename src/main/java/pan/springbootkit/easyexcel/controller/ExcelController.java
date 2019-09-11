package pan.springbootkit.easyexcel.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pan.springbootkit.easyexcel.PanExcelUtil;
import pan.springbootkit.easyexcel.model.ExportInfo;
import pan.springbootkit.easyexcel.model.ImportInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel controller
 *
 * Created by panzhangbao on 2019-09-11 08:57:56
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/excel")
public class ExcelController {
    /**
     * 读取 Excel（允许多个 sheet）
     */
	@PostMapping("readExcelWithSheets")
    public Object readExcelWithSheets(MultipartFile excel) {
        return PanExcelUtil.readExcel(excel, new ImportInfo());
    }

    /**
     * 读取 Excel（指定某个 sheet）
     */
	@PostMapping("readExcel")
    public Object readExcel(MultipartFile excel, int sheetNo,
							@RequestParam(defaultValue = "1") int headLineNum) {
        return PanExcelUtil.readExcel(excel, new ImportInfo(), sheetNo, headLineNum);
    }

    /**
     * 导出 Excel（一个 sheet）
     */
	@GetMapping("writeExcel")
    public void writeExcel(HttpServletResponse response){
        List<ExportInfo> list = getList();
        String fileName = "一个 Excel 文件";
        String sheetName = "第一个 sheet";

        PanExcelUtil.writeExcel(response, list, fileName, sheetName, new ExportInfo());
    }

    /**
     * 导出 Excel（多个 sheet）
     */
	@GetMapping("writeExcelWithSheets")
    public void writeExcelWithSheets(HttpServletResponse response){
        List<ExportInfo> list = getList();
        String fileName = "一个 Excel 文件";
        String sheetName1 = "第一个 sheet";
        String sheetName2 = "第二个 sheet";
        String sheetName3 = "第三个 sheet";

        PanExcelUtil.writeExcelWithSheets(response, list, fileName, sheetName1, new ExportInfo())
                .write(list, sheetName2, new ExportInfo())
                .write(list, sheetName3, new ExportInfo())
                .finish();
    }

    private List<ExportInfo> getList() {
        List<ExportInfo> list = new ArrayList<>();
        ExportInfo model1 = new ExportInfo();
        model1.setName("howie");
        model1.setAge("19");
        model1.setAddress("123456789");
        model1.setEmail("123456789@gmail.com");
        list.add(model1);
        ExportInfo model2 = new ExportInfo();
        model2.setName("harry");
        model2.setAge("20");
        model2.setAddress("198752233");
        model2.setEmail("198752233@gmail.com");
        list.add(model2);
        return list;
    }
}
