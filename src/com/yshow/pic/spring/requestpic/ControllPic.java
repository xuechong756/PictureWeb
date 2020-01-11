package com.yshow.pic.spring.requestpic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.yshow.pic.server.query.pic.VersionManager;
import com.yshow.pic.utils.JsonUtil;
import com.yshow.pic.utils.ResponseUtils;

@Controller
@RequestMapping("/pic")
public class ControllPic {
	
	//http://192.168.10.25:8080//PictureWeb/pic/nav.do
	@RequestMapping(value="/nav", method=RequestMethod.POST)
	public void obatinNav(HttpServletRequest request, HttpServletResponse response){
		//获取图片分类标题
		ResponseUtils.outToHtml(response, JsonUtil.toJson(VersionManager.getNavs(request)));
	}
	
	@RequestMapping(value="/type", method=RequestMethod.POST)
	public void byTypeId(HttpServletRequest request, HttpServletResponse response){
		//根据类型获取图片封面展示
		ResponseUtils.outToHtml(response, JsonUtil.toJson(VersionManager.getTypeOver(request)));
	}
	//获取某个图片集中的全部图片
	@RequestMapping(value="/annoc", method=RequestMethod.POST)
	public void byPlasId(HttpServletRequest request, HttpServletResponse response){
		ResponseUtils.outToHtml(response, JsonUtil.toJson(VersionManager.obatinPhotos(request)));
	}
}
