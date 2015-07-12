package com.hpms.jjfp.entity;

import java.sql.Timestamp;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class BPE_JX001 {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String jxbh; //绩效编号
	private Integer jxnd; //绩效年度
	private String jxqy;//绩效区域
	private String jxlx;//字典
	private String zqlx;//字典
	private Double jxdj;//单价
	private String remark;//备注
	private String creator;//创建人
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp createat;//创建时间
	private String modifor;//修改人
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Timestamp modifyat;//修改时间
	private String status;//状态
	public String getJxbh() {
		return jxbh;
	}
	public void setJxbh(String jxbh) {
		this.jxbh = jxbh;
	}
	public Integer getJxnd() {
		return jxnd;
	}
	public void setJxnd(Integer jxnd) {
		this.jxnd = jxnd;
	}
	public String getJxqy() {
		return jxqy;
	}
	public void setJxqy(String jxqy) {
		this.jxqy = jxqy;
	}
	public String getJxlx() {
		return jxlx;
	}
	public void setJxlx(String jxlx) {
		this.jxlx = jxlx;
	}
	public String getZqlx() {
		return zqlx;
	}
	public void setZqlx(String zqlx) {
		this.zqlx = zqlx;
	}
	public Double getJxdj() {
		return jxdj;
	}
	public void setJxdj(Double jxdj) {
		this.jxdj = jxdj;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateat() {
		return createat;
	}
	public void setCreateat(Timestamp createat) {
		this.createat = createat;
	}
	public String getModifor() {
		return modifor;
	}
	public void setModifor(String modifor) {
		this.modifor = modifor;
	}
	public Timestamp getModifyat() {
		return modifyat;
	}
	public void setModifyat(Timestamp modifyat) {
		this.modifyat = modifyat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
