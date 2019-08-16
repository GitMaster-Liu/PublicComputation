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

@Controller
@RequestMapping("/system")
public class SystemController {

	@RequestMapping("/autodeploy")
	public String autodeploy(Model model) {
		return "system/autodeploy";
	}
	@RequestMapping("/function")
	public String function(Model model) {
		return "system/function";
	}
	@RequestMapping("/gerrit")
	public String gerrit(Model model) {
		return "system/gerrit";
	}
	@RequestMapping("/update")
	public String update(Model model) {
		return "system/update";
	}
	@RequestMapping("/update1")
	public String update1(Model model) {
		return "system/update1";
	}
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		return "system/welcome";
	}
	@RequestMapping("/addhost")
	public String addhost(Model model) {
		return "system/addhost";
	}
}