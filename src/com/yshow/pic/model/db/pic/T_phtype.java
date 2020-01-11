package com.yshow.pic.model.db.pic;

import java.util.ArrayList;
import java.util.List;

public class T_phtype {
    private Integer hptpId;
    private String hptpName;
    private String hptpSort;
    private List<T_phoatlas> tplasList = new ArrayList<>();

	public List<T_phoatlas> getTplasList() {
		return tplasList;
	}

	public void setTplasList(List<T_phoatlas> tplasList) {
		this.tplasList = tplasList;
	}

    public Integer getHptpId() {
        return hptpId;
    }

    public void setHptpId(Integer hptpId) {
        this.hptpId = hptpId;
    }

    public String getHptpName() {
        return hptpName;
    }

    public void setHptpName(String hptpName) {
        this.hptpName = hptpName == null ? null : hptpName.trim();
    }

	public String getHptpSort() {
		return hptpSort;
	}

	public void setHptpSort(String hptpSort) {
		this.hptpSort = hptpSort;
	}
}