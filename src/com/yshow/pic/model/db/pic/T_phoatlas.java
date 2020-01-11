package com.yshow.pic.model.db.pic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T_phoatlas {
    private Integer pcoId;

    private Integer pcoHptpid;

    private Byte pcoDeg;

    private Date pcoDate;

    private String pcoTitle;
    private Integer sumPic;
    private List<T_phoalb> t_phoalbList = new ArrayList<>();

    public List<T_phoalb> getT_phoalbList() {
		return t_phoalbList;
	}

	public void setT_phoalbList(List<T_phoalb> t_phoalbList) {
		this.t_phoalbList = t_phoalbList;
	}
    public Integer getPcoId() {
        return pcoId;
    }

    public void setPcoId(Integer pcoId) {
        this.pcoId = pcoId;
    }

    public Integer getPcoHptpid() {
        return pcoHptpid;
    }

    public void setPcoHptpid(Integer pcoHptpid) {
        this.pcoHptpid = pcoHptpid;
    }

    public Byte getPcoDeg() {
        return pcoDeg;
    }

    public void setPcoDeg(Byte pcoDeg) {
        this.pcoDeg = pcoDeg;
    }

    public Date getPcoDate() {
        return pcoDate;
    }

    public void setPcoDate(Date pcoDate) {
        this.pcoDate = pcoDate;
    }

    public String getPcoTitle() {
        return pcoTitle;
    }

    public void setPcoTitle(String pcoTitle) {
        this.pcoTitle = pcoTitle == null ? null : pcoTitle.trim();
    }

	public Integer getSumPic() {
		return sumPic;
	}

	public void setSumPic(Integer sumPic) {
		this.sumPic = sumPic;
	}
}