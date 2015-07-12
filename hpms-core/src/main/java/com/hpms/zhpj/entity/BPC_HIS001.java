package com.hpms.zhpj.entity;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月12日-下午4:33:41
 *
 * 类名： HIS接口数据查询类
 *
 * 描述: 用于存放从BPC_HIS001表中查询的数据
 *
 */
public class BPC_HIS001 {
	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String bh;
	private Integer nd;
	private Integer yd;
	private String xmbm;
	private String xmmc;
	private Double sjz;
	private String dxlx;
	private String dxmc;
	private String dxbm;
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
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
	public String getXmbm() {
		return xmbm;
	}
	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public Double getSjz() {
		return sjz;
	}
	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}
	public String getDxlx() {
		return dxlx;
	}
	public void setDxlx(String dxlx) {
		this.dxlx = dxlx;
	}
	public String getDxmc() {
		return dxmc;
	}
	public void setDxmc(String dxmc) {
		this.dxmc = dxmc;
	}
	public String getDxbm() {
		return dxbm;
	}
	public void setDxbm(String dxbm) {
		this.dxbm = dxbm;
	}
	
}
