package com.goku.coreui.sys.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goku.coreui.sys.mapper.UserMapper;
import com.goku.coreui.sys.model.User;


@Controller
public class HomeController implements ErrorController {
	@Autowired
    UserMapper usermapper;

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password, Model model) {
		String passwordmd5 = new Md5Hash(password).toString();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(phone, passwordmd5);
		try {
			subject.login(token);
			return "redirect:/home";
		} catch (UnknownAccountException e) {
			model.addAttribute("error", "账号不存在!");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("error", "账号密码错误!");
			return "login";
		} catch (AuthenticationException e) {
			model.addAttribute("error", "登录异常!请联系管理员!");
			return "login";
		} catch (Exception e) {
			model.addAttribute("error", "系统异常!");
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
			return "login";
		} catch (SessionException ise) {
			return "500";
		} catch (Exception e) {
			return "500";
		}
	}

	@RequestMapping("/")
	public String defaultviews() {
		return "login";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		User user = (User) SecurityUtils.getSubject().getSession()
				.getAttribute(SecurityUtils.getSubject().getPrincipal());
		model.addAttribute("user", user);
		return "homepage";
	}

	@RequestMapping("/404")
	public String Unauthorized() {
		return "404";
	}

	@RequestMapping("/error")
	public String error() {
		return "500";
	}

	public String getErrorPath() {
		return "/error";
	}
}
