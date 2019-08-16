package com.goku.coreui.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.goku.coreui.sys.model.KnowledgeStatistic;
import com.goku.coreui.sys.model.StudentAccuracyStatistic;
import com.goku.coreui.sys.model.StudentKnowledgeStatistic;
import com.goku.coreui.sys.model.User;
import com.goku.coreui.sys.service.LessonMemberService;
import com.goku.coreui.sys.service.LessonService;
import com.goku.coreui.sys.service.UserService;

@Controller
public class ExcelUtil {
	@Autowired
	LessonService lessonService;
	@Autowired
	LessonMemberService lessonMemberService;
	@Autowired
	UserService userService;

	/**
	 * 导出该老师的某几门课的学生列表答题统计到Excel
	 */
	public static XSSFWorkbook getLessonStatistic(List<StudentAccuracyStatistic> lessonStatistic, XSSFWorkbook wb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet();

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		XSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = wb.createCellStyle();
		// 声明列对象
		XSSFCell cell = null;

		cteateCell(wb, row, (short) 0, "姓名");
		cteateCell(wb, row, (short) 1, "学校");
		cteateCell(wb, row, (short) 2, "学院");
		cteateCell(wb, row, (short) 3, "专业");
		cteateCell(wb, row, (short) 4, "班级");
		cteateCell(wb, row, (short) 5, "学号");
		cteateCell(wb, row, (short) 6, "答题数");
		cteateCell(wb, row, (short) 7, "正确数");
		cteateCell(wb, row, (short) 8, "正确率(%)");

		// 创建内容
		for (int i = 0; i < lessonStatistic.size(); i++) {
			row = sheet.createRow(i + 1);
			// 将内容按顺序赋给对应的列对象
			cteateCell(wb, row, (short) 0, lessonStatistic.get(i).getStudentName());
			cteateCell(wb, row, (short) 1, lessonStatistic.get(i).getSchool());
			cteateCell(wb, row, (short) 2, lessonStatistic.get(i).getInstitute());
			cteateCell(wb, row, (short) 3, lessonStatistic.get(i).getMajor());
			cteateCell(wb, row, (short) 4, lessonStatistic.get(i).getClassName());
			cteateCell(wb, row, (short) 5, lessonStatistic.get(i).getNumber());
			cteateCell(wb, row, (short) 6, lessonStatistic.get(i).getQuestionCount());
			cteateCell(wb, row, (short) 7, lessonStatistic.get(i).getCorrectCount());
			cteateCell(wb, row, (short) 8, lessonStatistic.get(i).getAccuracy());
		}
		return wb;
	}

	/**
	 * 导出某个学生某一门课的答题统计到Excel
	 */
	public static XSSFWorkbook getOneStudentKnowledge(List<StudentKnowledgeStatistic> studentKnowledgeList,
			User userInfo, XSSFWorkbook wb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet();

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		XSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = wb.createCellStyle();
		// 声明列对象
		XSSFCell cell = null;

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

		// 创建内容
		for (int i = 0; i < studentKnowledgeList.size(); i++) {
			row = sheet.createRow(i + 1);
			// 将内容按顺序赋给对应的列对象
			cteateCell(wb, row, (short) 0, userInfo.getName());
			cteateCell(wb, row, (short) 1, userInfo.getSchool());
			cteateCell(wb, row, (short) 2, userInfo.getInstitute());
			cteateCell(wb, row, (short) 3, userInfo.getMajor());
			cteateCell(wb, row, (short) 4, userInfo.getClassName());
			cteateCell(wb, row, (short) 5, userInfo.getNumber());
			cteateCell(wb, row, (short) 6, userInfo.getPhone());
			cteateCell(wb, row, (short) 7, studentKnowledgeList.get(i).getKnowledge());
			Date date = studentKnowledgeList.get(i).getCreateTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String dateTime = df.format(date);
			cteateCell(wb, row, (short) 8, dateTime);
			cteateCell(wb, row, (short) 9, studentKnowledgeList.get(i).getQuestionCount());
			cteateCell(wb, row, (short) 10, studentKnowledgeList.get(i).getAnswerCount());
			cteateCell(wb, row, (short) 11, studentKnowledgeList.get(i).getCorrectCount());
			cteateCell(wb, row, (short) 12, studentKnowledgeList.get(i).getAccuracy());
		}
		return wb;
	}

	/**
	 * 导出某个学生某一门课的答题统计到Excel TEST
	 */
	public static XSSFWorkbook getOneStudentKnowledgeTest(List<StudentAccuracyStatistic> lessonMemberList,
			XSSFWorkbook wb) {
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}

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

		return wb;
	}

	public static XSSFWorkbook CreateContent(List<StudentKnowledgeStatistic> studentKnowledgeList, User userInfo,
			 XSSFSheet sheet, XSSFRow row, XSSFWorkbook wb,int count) {

		// 创建内容
		for (int i = 0; i < studentKnowledgeList.size(); i++) {
			row = sheet.createRow(i + 1 + count * studentKnowledgeList.size());
			// 将内容按顺序赋给对应的列对象
			cteateCell(wb, row, (short) 0, userInfo.getName());
			cteateCell(wb, row, (short) 1, userInfo.getSchool());
			cteateCell(wb, row, (short) 2, userInfo.getInstitute());
			cteateCell(wb, row, (short) 3, userInfo.getMajor());
			cteateCell(wb, row, (short) 4, userInfo.getClassName());
			cteateCell(wb, row, (short) 5, userInfo.getNumber());
			cteateCell(wb, row, (short) 6, userInfo.getPhone());
			cteateCell(wb, row, (short) 7, studentKnowledgeList.get(i).getKnowledge());
			Date date = studentKnowledgeList.get(i).getCreateTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String dateTime = df.format(date);
			cteateCell(wb, row, (short) 8, dateTime);
			cteateCell(wb, row, (short) 9, studentKnowledgeList.get(i).getQuestionCount());
			cteateCell(wb, row, (short) 10, studentKnowledgeList.get(i).getAnswerCount());
			cteateCell(wb, row, (short) 11, studentKnowledgeList.get(i).getCorrectCount());
			cteateCell(wb, row, (short) 12, studentKnowledgeList.get(i).getAccuracy());
		}

		return wb;
	}

	/**
	 * 导出该老师的某几门课的各知识点答题统计到Excel
	 */
	public static XSSFWorkbook getLessonKnowledgeStatistic(List<KnowledgeStatistic> lessonKnowledgeStatistic,
			XSSFWorkbook wb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet();

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		XSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = wb.createCellStyle();
		// 声明列对象
		XSSFCell cell = null;

		cteateCell(wb, row, (short) 0, "知识点");
		cteateCell(wb, row, (short) 1, "创建时间");
		cteateCell(wb, row, (short) 2, "应答人数");
		cteateCell(wb, row, (short) 3, "实答人数");
		cteateCell(wb, row, (short) 4, "正确数");
		cteateCell(wb, row, (short) 5, "正确率(%)");

		// 创建内容
		for (int i = 0; i < lessonKnowledgeStatistic.size(); i++) {
			row = sheet.createRow(i + 1);
			// 将内容按顺序赋给对应的列对象
			cteateCell(wb, row, (short) 0, lessonKnowledgeStatistic.get(i).getKnowledge());
			Date date = lessonKnowledgeStatistic.get(i).getQuestionCreateTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String dateTime = df.format(date);
			cteateCell(wb, row, (short) 1, dateTime);
			cteateCell(wb, row, (short) 2, lessonKnowledgeStatistic.get(i).getStudentCount());
			cteateCell(wb, row, (short) 3, lessonKnowledgeStatistic.get(i).getAnswerCount());
			cteateCell(wb, row, (short) 4, lessonKnowledgeStatistic.get(i).getCorrectCount());
			cteateCell(wb, row, (short) 5, lessonKnowledgeStatistic.get(i).getAccuracy());
		}
		return wb;
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
