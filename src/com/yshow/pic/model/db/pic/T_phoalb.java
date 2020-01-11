package com.yshow.pic.model.db.pic;

public class T_phoalb {
    private Integer palId;

    private Integer palPcoid;

    private String palName;

    private String palBigurl;

    private String palMidurl;

    private String palSmlurl;

    public Integer getPalId() {
        return palId;
    }

    public void setPalId(Integer palId) {
        this.palId = palId;
    }

    public Integer getPalPcoid() {
        return palPcoid;
    }

    public void setPalPcoid(Integer palPcoid) {
        this.palPcoid = palPcoid;
    }

    public String getPalName() {
        return palName;
    }

    public void setPalName(String palName) {
        this.palName = palName == null ? null : palName.trim();
    }

    public String getPalBigurl() {
        return palBigurl;
    }

    public void setPalBigurl(String palBigurl) {
        this.palBigurl = palBigurl == null ? null : palBigurl.trim();
    }

    public String getPalMidurl() {
        return palMidurl;
    }

    public void setPalMidurl(String palMidurl) {
        this.palMidurl = palMidurl == null ? null : palMidurl.trim();
    }

    public String getPalSmlurl() {
        return palSmlurl;
    }

    public void setPalSmlurl(String palSmlurl) {
        this.palSmlurl = palSmlurl == null ? null : palSmlurl.trim();
    }
}