package com.hpms.kjjx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.kjjx.entity.HPKJ_KJGRMX;
import com.hpms.kjjx.entity.HPKJ_KJXMMX;
import com.hpms.kjjx.entity.HPKJ_KJXMPF;
import com.hpms.kjjx.entity.HPKJ_KJXMZD;
import com.hpms.service.BaseSer;
import com.hpms.yljx.vo.Combobox;

public interface KjxmmxSer extends BaseSer<HPKJ_KJXMMX> {

	public Map<String,Object> selectAll(int page, int rows,HashMap<String,Object> map);
	
//	public List<HPKJ_KJXMZD> selectXmzd();
	
	public List<BPE_JX001> selectNd();
	
	public List<HPKJ_KJXMZD> selectzd(String param,Map<String,Object> map);
	public List<HPKJ_KJXMPF> selectSm(String param,Map<String,Object> map);
	
	public List<HPKJ_KJXMZD> selectxmmc();
	
	public String delete(String xmid);
	public String insert(HPKJ_KJXMMX kjxmmx);
	public String updateTable(HPKJ_KJXMMX kjxmmx);
	
	public void insertGrmx(List<HPKJ_KJGRMX> grmxList); 
	
	public void deleteGrmx(String xmid);
	
	public void updateGrmx(List<HPKJ_KJGRMX> grmxList);
	
	public void deleteBysmbm(String smbm);
	
	public HSSFWorkbook export(String title,String cols,List<Object> data) throws Exception;
	public List<Object> exportList(Map<String, Object> map);
	
	public List<Combobox> queryExportExcel();
}
