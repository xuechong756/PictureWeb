package com.yshow.pic.model.net.baidu;

import java.util.List;

public class Pagebean {
	private String allPages;
	private List<Contentlist> contentlist;
	private String currentPage;
	public String getAllPages() {
		return allPages;
	}
	public void setAllPages(String allPages) {
		this.allPages = allPages;
	}
	public List<Contentlist> getContentlist() {
		return contentlist;
	}
	public void setContentlist(List<Contentlist> contentlist) {
		this.contentlist = contentlist;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
}
