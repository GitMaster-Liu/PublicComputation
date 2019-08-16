package com.goku.coreui.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goku.coreui.sys.model.Lesson;
import com.goku.coreui.sys.model.User;
import com.goku.coreui.sys.service.LessonService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lqh
 * @since 2018-10-24
 */
@Controller
@RequestMapping("/lesson")
public class LessonController {
	@Autowired
	LessonService lessonService;

	
	@RequestMapping("/getLesson")
	@ResponseBody
	public List<Lesson> getLesson(Model model) {
		User user = (User) SecurityUtils.getSubject().getSession()
				.getAttribute(SecurityUtils.getSubject().getPrincipal());
		List<Lesson> lessonlist = lessonService.getLessonById(user.getId());
		model.addAttribute("lessonlist", lessonlist);//返回所有课程名称
		return lessonlist;
	}
	
	public static JSONArray LessonToJson(List<Lesson> lessonList) {
		JSONArray jsonArray = new JSONArray();
		if (lessonList != null && lessonList.size() > 0 ) {
				for (Lesson lesson : lessonList) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", lesson.getId());
					jsonObject.put("name", lesson.getName()+"-"+lesson.getLessontime());
					jsonArray.add(jsonObject);
				}
			}
 
		return jsonArray;
	}
	
	@RequestMapping(value = "/getDownloadLesson")
	public @ResponseBody Map<String, Object> getUsersInfo(HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession()
				.getAttribute(SecurityUtils.getSubject().getPrincipal());
		List<Lesson> lesson = lessonService.getLessonById(user.getId());
		JSONArray lessonlist = LessonToJson(lesson);
 
		if (lessonlist != null) {
 
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 0);
			jsonObject.put("msg", "");
			jsonObject.put("count", lessonlist.size());
			jsonObject.put("data", lessonlist);
			return jsonObject;
		}
		return null;
	}

}
