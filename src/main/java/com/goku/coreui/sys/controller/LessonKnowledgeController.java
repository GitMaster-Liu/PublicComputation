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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.goku.coreui.sys.model.KnowledgeStatistic;
import com.goku.coreui.sys.service.LessonKnowledgeService;
import com.goku.coreui.sys.service.LessonService;
import com.goku.coreui.sys.util.ExcelUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lqh
 * @since 2018-10-24
 */
@Controller
@RequestMapping("/knowledge")
public class LessonKnowledgeController {
	@Autowired
	LessonKnowledgeService lessonKnowledgeService;
	@Autowired
	LessonService lessonService;

	@RequestMapping("/lessonknowledge")
	public String Knowledeg(Model model) {
		return "knowledgepage/knowledge";
	}

	@RequestMapping("/{lessonId}")
	public @ResponseBody Map<String, Object> getLessonMember(HttpServletRequest request, Model model,
			@PathVariable("lessonId") String lessonId,@RequestParam int page, @RequestParam int limit) {
		int startRow = (page - 1) * limit;//该页要显示的数据起始行
		int count=lessonKnowledgeService.getKnowledge(lessonId).size();
		List<KnowledgeStatistic> lessonKnowledgeList = lessonKnowledgeService.getKnowledgeForPage(lessonId,startRow,limit);
		JSONArray knowledgelist = LessonKnowledgeToJson(lessonKnowledgeList);

		if (knowledgelist != null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 0);
			jsonObject.put("msg", "");
			jsonObject.put("count", count);
			jsonObject.put("data", knowledgelist);
			return jsonObject;
		}
		return null;
	}

	// 下载该课程所有知识点的答题统计
	@RequestMapping(value = "/downloadLessonStatistic/{lessonId}/{lessonName}")
	@ResponseBody
	public String downloadLessonKnowledgeStatistic(Model model, HttpServletResponse response,
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
				List<KnowledgeStatistic> lessonKnowledgeStatistic = lessonKnowledgeService.getKnowledge(lessonId);
				XSSFWorkbook wb = ExcelUtil.getLessonKnowledgeStatistic(lessonKnowledgeStatistic, null);
				String filename = path + lessonNameList[i] + "-知识点列表" + ".xlsx";
				fileNames.add(filename);

				FileOutputStream output = new FileOutputStream("d:\\i课堂报表下载\\" + lessonName + "-知识点列表" + ".xlsx");
				response.reset();
				response.setContentType("application/msexcel");// 设置生成的文件类型
				response.setCharacterEncoding("UTF-8");// 设置文件头编码方式和文件名
				response.setHeader("Content-Disposition", "attachment");
				try {
					wb.write(output);
				} catch (IOException e) {
					e.printStackTrace();
				}
				output.close();
			}
		}
		// 导出压缩文件全路径
		String zipFilePath = path + "知识点列表压缩包" + ".zip";
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

	public static JSONArray LessonKnowledgeToJson(List<KnowledgeStatistic> lessonKnowledgeList) {
		JSONArray jsonArray = new JSONArray();
		if (lessonKnowledgeList != null && lessonKnowledgeList.size() > 0) {
			for (KnowledgeStatistic knowledgelist : lessonKnowledgeList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("knowledge", knowledgelist.getKnowledge());
				Date date = knowledgelist.getQuestionCreateTime();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String dateTime = df.format(date); // Formats a Date into a date/time string.
				jsonObject.put("questionCreateTime", dateTime);
				jsonObject.put("studentCount", knowledgelist.getStudentCount());
				jsonObject.put("answerCount", knowledgelist.getAnswerCount());
				jsonObject.put("correctCount", knowledgelist.getCorrectCount());
				jsonObject.put("accuracy", knowledgelist.getAccuracy());
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
}
