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
@RequestMapping("/lessonMember")
public class LessonMemberController {
	@Autowired
	LessonService lessonService;
	@Autowired
	LessonMemberService lessonMemberService;
	@Autowired
	UserService userService;

	@RequestMapping("/lessonmember")
	public String LesssoMember(Model model) {
		return "lessonmember/lessonmember";
	}
	@RequestMapping("/test")
	public String test(Model model) {
		return "lessonmember/test";
	}

	@RequestMapping("/knowledge")
	public String LessonMemberKnowledge(Model model) {
		return "lessonmember/knowledge";
	}

	
	@RequestMapping(value = "/{lessonId}")
	public @ResponseBody Map<String, Object> getLessonMember(HttpServletRequest request, Model model,
			@PathVariable("lessonId") String lessonId,@RequestParam int page, @RequestParam int limit) {
		int startRow = (page - 1) * limit;//该页数据起始行
		int count=lessonMemberService.getMemberByLessonId(lessonId).size();
		List<StudentAccuracyStatistic> lessonMemberList = lessonMemberService.getMemberByLessonId(lessonId,startRow,limit);
		
		JSONArray memberlist = LessonMemberToJson(lessonMemberList);

		if (memberlist != null) {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 0);
			jsonObject.put("msg", "");
			jsonObject.put("count",count);
			jsonObject.put("data", memberlist);
			return jsonObject;
		}
		return null;
	}

	@RequestMapping(value = "/{lessonId}/{memberId}")
	public @ResponseBody Map<String, Object> getKnowledgeOfStudentByLessonId(HttpServletRequest request, Model model,
			@PathVariable("lessonId") String lessonId, @PathVariable("memberId") String memberId) {
		List<StudentKnowledgeStatistic> studentKnowledgeList = lessonMemberService
				.getKnowledgeOfStudentByLessonId(lessonId, memberId);
		JSONArray knowledgeList = StudentKnowledgeToJson(studentKnowledgeList);

		if (knowledgeList != null) {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 0);
			jsonObject.put("msg", "");
			jsonObject.put("count", studentKnowledgeList.size());
			jsonObject.put("data", knowledgeList);
			return jsonObject;
		}
		return null;
	}

	// 下载该课程所有学生的答题统计 （多文件压缩）
	@RequestMapping(value = "/downloadLessonStatistic/{lessonId}/{lessonName}")
	@ResponseBody
	public String downloadLessonStatistic(Model model, HttpServletResponse response,
			@PathVariable("lessonId") String lessonId, @PathVariable("lessonName") String lessonName)
			throws IOException {
		String[] lessonIdList = lessonId.split("!");
		String[] lessonNameList = lessonName.split("!");

		List<String> fileNames = new ArrayList<String>();

		String path = "d:\\i课堂报表下载" + File.separator;
		// 如果不存在,创建文件夹
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		int length = lessonIdList.length;
		for (int i = 0; i < length; i++) {
			if (!lessonIdList[i].equals("")) {
				lessonId = lessonIdList[i];
				lessonName = lessonNameList[i];
				List<StudentAccuracyStatistic> lessonStatistic = lessonMemberService.getMemberByLessonId(lessonId);

				XSSFWorkbook wb = ExcelUtil.getLessonStatistic(lessonStatistic, null);
				String filename = path + lessonNameList[i] + "-学生列表" + ".xlsx";

				fileNames.add(filename);

				FileOutputStream output = new FileOutputStream("d:\\i课堂报表下载\\" + lessonName + "-学生列表" + ".xlsx");
				response.reset();
				response.setContentType("application/msexcel");// 设置生成的文件类型
				response.setCharacterEncoding("UTF-8");// 设置文件头编码方式和文件名
				response.setHeader("Content-Disposition", "attachment");
				wb.write(output);
				output.close();
			}
		}

		// 导出压缩文件全路径
		String zipFilePath = path + "学生列表压缩包" + ".zip";
		// 导出zip
		File zip = new File(zipFilePath);
		// 将excel文件生成压缩文件
		File srcfile[] = new File[fileNames.size()];
		for (int j = 0, n1 = fileNames.size(); j < n1; j++) {
			srcfile[j] = new File(fileNames.get(j));
		}
		ZipFiles(srcfile, zip);
		response.setContentType("application/zip");
		
		//response.setHeader("Location", zip.getName());
		response.setHeader("Content-Disposition",
				"attachment; filename=" + URLEncoder.encode(zip.getName(), "UTF-8"));
		OutputStream outputStream = response.getOutputStream();
		InputStream inputStream = new FileInputStream(zipFilePath);
		byte[] buffer = new byte[1024];
		int i = -1;
		while ((i = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, i);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		outputStream = null;
		try {
			delAllFile(path); // 删除完里面所有内容
			path = path.toString();
			java.io.File myFilePath = new java.io.File(path);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 下载该课程所有学生的按知识点排序的答题统计 （多文件压缩）
	@RequestMapping(value = "/downloadKnowledgeStatistic/{lessonId}/{lessonName}")
	@ResponseBody
	public String downloadKnowledgeStatistic(Model model, HttpServletResponse response,
			@PathVariable("lessonId") String lessonId, @PathVariable("lessonName") String lessonName)
			throws IOException {
		String[] lessonIdList = lessonId.split("!");
		String[] lessonNameList = lessonName.split("!");

		List<String> fileNames = new ArrayList<String>();

		String path = "d:\\i课堂报表下载" + File.separator;
		// 如果不存在,创建文件夹
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		int lessonIdLength = lessonIdList.length;
		for (int i = 0; i < lessonIdLength; i++) {
			if (!lessonIdList[i].equals("")) {
				lessonId = lessonIdList[i];
				lessonName = lessonNameList[i];
				List<StudentAccuracyStatistic> lessonMemberList = lessonMemberService.getMemberByLessonId(lessonId);

				XSSFWorkbook wb = new XSSFWorkbook();
				// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
				XSSFSheet sheet = wb.createSheet();

				// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
				XSSFRow row = sheet.createRow(0);

				cteateCell(wb, row, (short) 0, "姓名");
				cteateCell(wb, row, (short) 1, "学校");
				cteateCell(wb, row, (short) 2, "学院");
				cteateCell(wb, row, (short) 3, "专业");
				cteateCell(wb, row, (short) 4, "班级");
				cteateCell(wb, row, (short) 5, "学号");
				cteateCell(wb, row, (short) 6, "手机号");
				cteateCell(wb, row, (short) 7, "知识点");
				cteateCell(wb, row, (short) 8, "创建时间");
				cteateCell(wb, row, (short) 9, "应答题数");
				cteateCell(wb, row, (short) 10, "实答题数");
				cteateCell(wb, row, (short) 11, "正确数");
				cteateCell(wb, row, (short) 12, "正确率(%)");

				int lessonMemberLength = lessonMemberList.size();

				for (int j = 0; j < lessonMemberLength; j++) {
					User userInfo = userService.getUserByMemberId(lessonMemberList.get(j).getMemberID());
					List<StudentKnowledgeStatistic> studentKnowledgeList = lessonMemberService
							.getKnowledgeOfStudentByLessonId(lessonId, lessonMemberList.get(j).getMemberID());
					wb = ExcelUtil.CreateContent(studentKnowledgeList, userInfo, sheet, row, wb, j);
				}

				String filename = path + lessonNameList[i] + "-学生知识点列表" + ".xlsx";

				fileNames.add(filename);

				FileOutputStream output = new FileOutputStream("d:\\i课堂报表下载\\" + lessonName + "-学生知识点列表" + ".xlsx");
				response.reset();
				response.setContentType("application/msexcel");// 设置生成的文件类型
				response.setCharacterEncoding("UTF-8");// 设置文件头编码方式和文件名
				response.setHeader("Content-Disposition", "attachment");
				wb.write(output);
				output.close();
			}
		}

		// 导出压缩文件全路径
		String zipFilePath = path + "学生知识点列表压缩包" + ".zip";
		// 导出zip
		File zip = new File(zipFilePath);
		// 将excel文件生成压缩文件
		File srcfile[] = new File[fileNames.size()];
		for (int j = 0, n1 = fileNames.size(); j < n1; j++) {
			srcfile[j] = new File(fileNames.get(j));
		}
		ZipFiles(srcfile, zip);
		response.setContentType("application/zip");
		response.setHeader("Location", zip.getName());
		response.setHeader("Content-Disposition",
				"attachment; filename=" + URLEncoder.encode(zip.getName(), "UTF-8"));
		OutputStream outputStream = response.getOutputStream();
		InputStream inputStream = new FileInputStream(zipFilePath);
		byte[] buffer = new byte[1024];
		int i = -1;
		while ((i = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, i);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		outputStream = null;
		try {
			delAllFile(path); // 删除完里面所有内容
			path = path.toString();
			java.io.File myFilePath = new java.io.File(path);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public static JSONArray LessonMemberToJson(List<StudentAccuracyStatistic> lessonMemberList) {
		JSONArray jsonArray = new JSONArray();
		if (lessonMemberList != null && lessonMemberList.size() > 0) {
			for (StudentAccuracyStatistic lessonMember : lessonMemberList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("memberId", lessonMember.getMemberID());
				jsonObject.put("studentName", lessonMember.getStudentName());
				jsonObject.put("school", lessonMember.getSchool());
				jsonObject.put("institute", lessonMember.getInstitute());
				jsonObject.put("major", lessonMember.getMajor());
				jsonObject.put("className", lessonMember.getClassName());
				jsonObject.put("number", lessonMember.getNumber());
				jsonObject.put("questionCount", lessonMember.getQuestionCount());
				jsonObject.put("correctCount", lessonMember.getCorrectCount());
				jsonObject.put("accuracy", lessonMember.getAccuracy());
				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	public static JSONArray StudentKnowledgeToJson(List<StudentKnowledgeStatistic> studentKnowledgeList) {
		JSONArray jsonArray = new JSONArray();
		if (studentKnowledgeList != null && studentKnowledgeList.size() > 0) {
			for (StudentKnowledgeStatistic knowledgeList : studentKnowledgeList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("memberID", knowledgeList.getMemberID());
				jsonObject.put("knowledge", knowledgeList.getKnowledge());
				Date date = knowledgeList.getCreateTime();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String dateTime = df.format(date); // Formats a Date into a date/time string.
				jsonObject.put("createTime", dateTime);
				jsonObject.put("questionCount", knowledgeList.getQuestionCount());
				jsonObject.put("answerCount", knowledgeList.getAnswerCount());
				jsonObject.put("correctCount", knowledgeList.getCorrectCount());
				jsonObject.put("accuracy", knowledgeList.getAccuracy());
				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	// 压缩文件
	public void ZipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里的文件
				flag = true;
			}
		}
		return flag;

	}

	private static void cteateCell(XSSFWorkbook wb, XSSFRow row, short col, String val) {
		XSSFCell cell = row.createCell(col);

		Pattern pattern = Pattern.compile("[0-9]{1,3}");
		Matcher isNum = pattern.matcher(val);
		if (isNum.matches() && val != "") {
			cell.setCellValue(Double.valueOf(val).doubleValue());
		} else {
			cell.setCellValue(val);
		}
	}
}
