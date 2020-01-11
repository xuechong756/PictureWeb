package com.yshow.pic.model.local;

public class ParamType {
	private Integer type;
	private Integer page;
	private Integer items;
	
	public ParamType(String type, String page, String items) throws Exception
	{
		this.type = Integer.parseInt(type);
		this.page = Integer.parseInt(page);
		this.items = Integer.parseInt(items);
	}
	public Integer getType() {
		return type;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getItems() {
		return items;
	}
}
