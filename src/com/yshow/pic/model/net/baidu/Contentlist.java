package com.yshow.pic.model.net.baidu;

import java.util.List;

public class Contentlist {
	private String title;
	private List<ResultPic> list;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ResultPic> getList() {
		return list;
	}
	public void setList(List<ResultPic> list) {
		this.list = list;
	}
}