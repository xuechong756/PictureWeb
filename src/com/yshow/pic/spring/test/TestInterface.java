package com.yshow.pic.spring.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yshow.pic.server.picload.LoadPictuer;
import com.yshow.pic.utils.JsonUtil;
import com.yshow.pic.utils.ResponseUtils;

@Controller
@RequestMapping("/test")
public class TestInterface {

	// http://localhost:8080//PictureWeb/test/t1.do
	@RequestMapping(value = "/t1", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {

		new LoadPictuer().requestPic();
		ResponseUtils.outToHtml(response, JsonUtil.toJson(""));
	}

}
