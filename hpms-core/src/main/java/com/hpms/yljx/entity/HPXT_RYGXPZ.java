package  com.hpms.yljx.entity;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class HPXT_RYGXPZ {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private Integer nd;
	private Integer yd;
	private String ygbh;
	private String ksnm;
	private String xlfl;
	private String xwfl;
	private String xwzl;
	private String zyzg;
	private String zgdj;
	private String zylb;
	private String zyfw;
	private String ygxz;
	private String gwlb;
	private String gwdj;
	private String zwlb;
	private String zwzc;               
	private String ryldlb; 
	private Double ygxs; 
	private String ndgw;  
	private String cjr; 
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj; 
	private String xgr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp xgsj;   
	private String zt;
	
	@Transient
	private String userid;
	@Transient
	private String xm;
	@Transient
	private String ksmc;
	@Transient
	public String selected;
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public Integer getNd() {
		return nd;
	}
	public void setNd(Integer nd) {
		this.nd = nd;
	}
	public Integer getYd() {
		return yd;
	}
	public void setYd(Integer yd) {
		this.yd = yd;
	}
	public String getYgbh() {
		return ygbh;
	}
	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}
	public String getKsnm() {
		return ksnm;
	}
	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}
	public String getXlfl() {
		return xlfl;
	}
	public void setXlfl(String xlfl) {
		this.xlfl = xlfl;
	}
	public String getXwfl() {
		return xwfl;
	}
	public void setXwfl(String xwfl) {
		this.xwfl = xwfl;
	}
	public String getXwzl() {
		return xwzl;
	}
	public void setXwzl(String xwzl) {
		this.xwzl = xwzl;
	}
	public String getZyzg() {
		return zyzg;
	}
	public void setZyzg(String zyzg) {
		this.zyzg = zyzg;
	}
	public String getZgdj() {
		return zgdj;
	}
	public void setZgdj(String zgdj) {
		this.zgdj = zgdj;
	}
	public String getZylb() {
		return zylb;
	}
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	public String getZyfw() {
		return zyfw;
	}
	public void setZyfw(String zyfw) {
		this.zyfw = zyfw;
	}
	public String getYgxz() {
		return ygxz;
	}
	public void setYgxz(String ygxz) {
		this.ygxz = ygxz;
	}
	public String getGwlb() {
		return gwlb;
	}
	public void setGwlb(String gwlb) {
		this.gwlb = gwlb;
	}
	public String getGwdj() {
		return gwdj;
	}
	public void setGwdj(String gwdj) {
		this.gwdj = gwdj;
	}
	public String getZwlb() {
		return zwlb;
	}
	public void setZwlb(String zwlb) {
		this.zwlb = zwlb;
	}
	public String getZwzc() {
		return zwzc;
	}
	public void setZwzc(String zwzc) {
		this.zwzc = zwzc;
	}
	public String getRyldlb() {
		return ryldlb;
	}
	public void setRyldlb(String ryldlb) {
		this.ryldlb = ryldlb;
	}
	public Double getYgxs() {
		return ygxs;
	}
	public void setYgxs(Double ygxs) {
		this.ygxs = ygxs;
	}
	public String getNdgw() {
		return ndgw;
	}
	public void setNdgw(String ndgw) {
		this.ndgw = ndgw;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}
	public String getXgr() {
		return xgr;
	}
	public void setXgr(String xgr) {
		this.xgr = xgr;
	}
	public Timestamp getXgsj() {
		return xgsj;
	}
	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
}
