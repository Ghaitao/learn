package com.ght.learn.ffs.web.controller.code;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import framework.chart.captcha.CaptchaGenerator;
import framework.core.utils.StringUtils;

/**
 * 验证码，条形码和二维码的生成
 * @author ght
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController {
	
	 @Resource(name = "KaptchaCaptchaGenerator", type = CaptchaGenerator.class)
	 private CaptchaGenerator captchaGenerator;

	 @RequestMapping(value = "/captcha.do", method = { RequestMethod.POST, RequestMethod.GET })
	 public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpg");
		response.setHeader("Cache-Control", "no-cache,no-store");
		response.setDateHeader("Expires", System.currentTimeMillis() - 3600 * 4);
		String captchaCode = this.captchaGenerator.generateCaptcha(response.getOutputStream());
		addCaptchaCode(captchaCode, request);
	 }
	    
	 public static void addCaptchaCode(String captchaCode, HttpServletRequest request) {
		request.getSession(true).setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, captchaCode);
	 }
	//校检验证码
	public static boolean isMatchedCaptchaCode(String captchaCode, HttpServletRequest request) {
		if (StringUtils.isEmpty(captchaCode)) {
			    return false;
		}
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			    return false;
		}
		String sessionCaptchaCode = (String) httpSession.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		httpSession.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		return StringUtils.safeEqualsIgnoreCase(sessionCaptchaCode, captchaCode);
	}
}
