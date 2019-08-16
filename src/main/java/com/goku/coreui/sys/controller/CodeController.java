package com.goku.coreui.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goku.coreui.sys.model.StudentAccuracyStatistic;
import com.goku.coreui.sys.model.StudentKnowledgeStatistic;
import com.goku.coreui.sys.model.User;
import com.goku.coreui.sys.service.LessonMemberService;
import com.goku.coreui.sys.service.LessonService;
import com.goku.coreui.sys.service.UserService;
import com.goku.coreui.sys.util.ExcelUtil;



import com.goku.coreui.sys.util.FileUtil;  //文件工具类
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/code")
public class CodeController {

	@RequestMapping("/compile")
	public String compile(Model model) {
		return "code/compile";
	}
	@RequestMapping("/ftpdownload")
	public String ftpdownload(Model model) {
		return "code/ftpdownload";
	}
	@RequestMapping("/ftpaddress")
	public String ftpaddress(Model model) {
		return "code/ftpaddress";
	}
	
	//处理文件上传
    @RequestMapping(value="/uploadimg", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
     
        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字

        //文件存放路径
        String filePath = "D:\\111\\";
        
        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
       try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }

       // 返回图片的存放路径
        return filePath;
    }
}