package com.hpms.kjjx.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.utils.JsonUtil;
import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.kjjx.entity.HPKJ_KJGRMX;
import com.hpms.kjjx.entity.HPKJ_KJXMMX;
import com.hpms.kjjx.entity.HPKJ_KJXMPF;
import com.hpms.kjjx.entity.HPKJ_KJXMZD;
import com.hpms.kjjx.service.KjxmmxSer;
import com.hpms.util.UtilStr;
import com.hpms.yljx.entity.HPCS_HXXMZD;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.hpms.yljx.service.HxxmzdSer;
import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.service.YgzcxxSer;
import com.hpms.yljx.vo.Combobox;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("kjjx/kjxmmx")
public class Kjxmmxcontroller {

	@Autowired
	private KjxmmxSer kjSer;
	
	@Autowired
	private HxxmzdSer hSer;
	
	@Autowired
	private KszcxxSer kSer;
	
	@Autowired
	private YgzcxxSer ySer;
	
	@RequestMapping
	public String showIndex(){
		return "kjjx/kjxmxxcj";
	}
	
	@RequestMapping(value="/queryAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAll(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String xmbm = request.getParameter("xmbm");
		String xm = request.getParameter("xm");
		String ksnm = request.getParameter("ksnm");
		Map<String, Object> map = new HashMap<String ,Object>();
		if(!StringUtil.isNullAndSpace(startTime)){
			map.put("startTime",startTime);
		}
		if(!StringUtil.isNullAndSpace(endTime)){
			map.put("endTime",endTime );
		}
		if(!StringUtil.isNullAndSpace(xmbm)){
			map.put("xmbm", xmbm);
		}
		if(!StringUtil.isNullAndSpace(xm)){
			map.put("xm",xm );
		}
		if(!StringUtil.isNullAndSpace(ksnm)){
			map.put("ksnm", ksnm);
		}
		return kjSer.selectAll(page, rows, (HashMap<String, Object>) map);
	}
	
	@RequestMapping(value="/queryXmmc", method=RequestMethod.POST)
	@ResponseBody
	public Object queryXmmc(){
		List<HPCS_HXXMZD> list = hSer.selectXmmc();
		return list;
	}
	
	@RequestMapping(value="/queryKs", method=RequestMethod.POST)
	@ResponseBody
	public Object queryKs(){
		List<Combobox> list = kSer.findKS();
		return list;
	}
	
//	@RequestMapping(value="/queryXmzd", method=RequestMethod.POST)
//	@ResponseBody
//	public Object queryXmzd(){
//		List<HPKJ_KJXMZD> list = kjSer.selectXmzd();
//		return list;
//	}
	
	@RequestMapping(value="/queryNd", method=RequestMethod.POST)
	@ResponseBody
	public Object queryNd(){
		List<BPE_JX001> list = kjSer.selectNd();
		return list;
	}
	
	@RequestMapping(value="/queryKjzd", method=RequestMethod.POST)
	@ResponseBody
	public Object queryKjzd(HttpServletRequest request,HttpServletResponse response){
		List<HPKJ_KJXMZD> list =null;
		String param = request.getParameter("param");
		String xmbm = request.getParameter("xmbm");
		String lbbm = request.getParameter("lbbm");
		String jbbm = request.getParameter("jbbm");
		Map<String, Object> map = new HashMap<String,Object>();
		if(!StringUtil.isNullAndSpace(xmbm)){
			map.put("xmbm", xmbm);
		}
		if(!StringUtil.isNullAndSpace(lbbm)){
			map.put("lbbm", lbbm);
		}
		if(!StringUtil.isNullAndSpace(jbbm)){
			map.put("jbbm", jbbm);
		}
		
		if(!StringUtil.isNullAndSpace(param) && param != "sm"){
			list = kjSer.selectzd(param, map);
		}
		
		return list;
	}
	
	
	@RequestMapping(value="/queryKjsm", method=RequestMethod.POST)
	@ResponseBody
	public Object queryKjsm(HttpServletRequest request,HttpServletResponse response){
		List<HPKJ_KJXMPF> list = null;
		String param = request.getParameter("param");
		String xmbm = request.getParameter("xmbm");
		String lbbm = request.getParameter("lbbm");
		String jbbm = request.getParameter("jbbm");
		
		Map<String, Object> map = new HashMap<String,Object>();
		if(!StringUtil.isNullAndSpace(xmbm)){
			map.put("xmbm", xmbm);
		}
		if(!StringUtil.isNullAndSpace(lbbm)){
			map.put("lbbm", lbbm);
		}
		if(!StringUtil.isNullAndSpace(jbbm)){
			map.put("jbbm", jbbm);
		}
		
		if(!StringUtil.isNullAndSpace(param) && "sm".equals(param)){
			 list = kjSer.selectSm(param, map);
		}	
		
		return list;
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public Object del(HttpServletRequest request,HttpServletResponse response){
		String xmid = request.getParameter("id");
		kjSer.deleteGrmx(xmid);
		String msg = kjSer.delete(xmid);
		return msg;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public Object insert(HttpServletRequest request,HttpServletResponse response){
		String kjbm = request.getParameter("kjbm");
		String rhsj = request.getParameter("rhsj");
		String dysm = request.getParameter("dysm");
		String dysmmc = request.getParameter("dysmmc");
		String desm = request.getParameter("desm");
		String desmmc = request.getParameter("desmmc");
		String dssm = request.getParameter("dssm");
		String dssmmc = request.getParameter("dssmmc");
		String dsism = request.getParameter("dsism");
		String dsismmc = request.getParameter("dsismmc");
		String dwsm = request.getParameter("dwsm");
		String dwsmmc = request.getParameter("dwsmmc");
		String kz1 = request.getParameter("kz1");
		String kz2 = request.getParameter("kz2");
		String kz3 = request.getParameter("kz3");
		String remark = request.getParameter("remark");
		String dysmfz = request.getParameter("dysmfz");
		String desmfz = request.getParameter("desmfz");
		String dssmfz = request.getParameter("dssmfz");
		String dsismfz = request.getParameter("dsismfz");
		String dwsmfz = request.getParameter("dwsmfz");
		String dysmbm = request.getParameter("dysmbm");
		String desmbm = request.getParameter("desmbm");
		String dssmbm = request.getParameter("dssmbm");
		String dsismbm = request.getParameter("dsismbm");
		String dwsmbm = request.getParameter("dwsmbm");
		String dysmItem = request.getParameter("dysmItem");
		String desmItem = request.getParameter("desmItem");
		String dssmItem = request.getParameter("dssmItem");
		String dsismItem = request.getParameter("dsismItem");
		String dwsmItem = request.getParameter("dwsmItem");
		
		List dylist = null;
		List delist = null;
		List dslist = null;
		List dsilist = null;
		List dwlist = null;
 		HPKJ_KJXMMX kjxmmx = new HPKJ_KJXMMX();
 		if(!StringUtil.isNullAndSpace(kjbm)){
 			kjxmmx.setKjbm(kjbm);
 		}
 		if(!StringUtil.isNullAndSpace(rhsj)){
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
 			try {
				kjxmmx.setRhsj(sdf.parse(rhsj));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			kjxmmx.setRhnd(Integer.parseInt(rhsj.substring(0,4)));
 		}
 		if(!StringUtil.isNullAndSpace(dysm)){
 			kjxmmx.setDysm(dysm);
 		}
 		if(!StringUtil.isNullAndSpace(dysmmc)){
 			kjxmmx.setDysmmc(dysmmc);
 		}
 		if(!StringUtil.isNullAndSpace(desm)){
 			kjxmmx.setDesm(desm);
 		}
 		if(!StringUtil.isNullAndSpace(desmmc)){
 			kjxmmx.setDesmmc(desmmc);
 		}
 		if(!StringUtil.isNullAndSpace(dssm)){
 			kjxmmx.setDssm(dssm);
 		}
 		if(!StringUtil.isNullAndSpace(dssmmc)){
 			kjxmmx.setDssmmc(dssmmc);
 		}
 		if(!StringUtil.isNullAndSpace(dsism)){
 			kjxmmx.setDsism(dsism);
 		}
 		if(!StringUtil.isNullAndSpace(dsismmc)){
 			kjxmmx.setDsismmc(dsismmc);
 		}
 		if(!StringUtil.isNullAndSpace(dwsm)){
 			kjxmmx.setDwsm(dwsm);
 		}
 		if(!StringUtil.isNullAndSpace(dwsmmc)){
 			kjxmmx.setDwsmmc(dwsmmc);
 		}
 		if(!StringUtil.isNullAndSpace(kz1)){
 			kjxmmx.setKz1(kz1);
 		}
		if(!StringUtil.isNullAndSpace(kz2)){
		 	kjxmmx.setKz2(kz2);		
	    }
		if(!StringUtil.isNullAndSpace(kz3)){
			kjxmmx.setKz3(kz3);
		}
		if(!StringUtil.isNullAndSpace(remark)){
			kjxmmx.setRemark(remark);	
		}
		if(!StringUtil.isNullAndSpace(dysmItem)){
			 dylist = JsonUtil.json2Bean(dysmItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(desmItem)){
			 delist = JsonUtil.json2Bean(desmItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(dssmItem)){
			 dslist = JsonUtil.json2Bean(dssmItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(dsismItem)){
			 dsilist = JsonUtil.json2Bean(dsismItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(dwsmItem)){
			 dwlist = JsonUtil.json2Bean(dwsmItem, ArrayList.class);
		}
		String xmid = UUID.randomUUID().toString();
		kjxmmx.setXmid(xmid);
 		kjxmmx.setCreateat(new Date());
 		kjxmmx.setCreator("superadmin");
 		kjxmmx.setStatus("1");
		String msg = kjSer.insert(kjxmmx);
		List<HPKJ_KJGRMX> grmxList = new ArrayList<HPKJ_KJGRMX>();
		if(dylist!= null){
			for(int i=0;i<dylist.size();i++){
				Map map = (Map) dylist.get(i);
				HPKJ_KJGRMX kjgrmx1 = new HPKJ_KJGRMX();
				kjgrmx1.setSmbm(dysmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx1.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx1.setKsnm(ksnm);
				kjgrmx1.setXmid(xmid);
				kjgrmx1.setFz(Double.parseDouble(dysmfz));
				kjgrmx1.setCjr("superadmin");
				kjgrmx1.setZt("1");
				grmxList.add(kjgrmx1);
			}
		}
		if(delist != null){
			for(int i=0;i<delist.size();i++){
				Map map = (Map) delist.get(i);
				HPKJ_KJGRMX kjgrmx2 = new HPKJ_KJGRMX();
				kjgrmx2.setSmbm(desmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx2.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx2.setKsnm(ksnm);
				kjgrmx2.setXmid(xmid);
				kjgrmx2.setFz(Double.parseDouble(desmfz));
				kjgrmx2.setCjr("superadmin");
				kjgrmx2.setZt("1");
				grmxList.add(kjgrmx2);
			}
		}
		if(dslist != null){
			for(int i=0;i<dslist.size();i++){
				Map map = (Map) dslist.get(i);
				HPKJ_KJGRMX kjgrmx3 = new HPKJ_KJGRMX();
				kjgrmx3.setSmbm(dssmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx3.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx3.setKsnm(ksnm);
				kjgrmx3.setXmid(xmid);
				kjgrmx3.setFz(Double.parseDouble(dssmfz));
				kjgrmx3.setCjr("superadmin");
				kjgrmx3.setZt("1");
				grmxList.add(kjgrmx3);
			}
		}
		if(dsilist != null){
			for(int i=0;i<dsilist.size();i++){
				Map map = (Map) dsilist.get(i);
				HPKJ_KJGRMX kjgrmx4 = new HPKJ_KJGRMX();
				kjgrmx4.setSmbm(dsismbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx4.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx4.setKsnm(ksnm);
				kjgrmx4.setXmid(xmid);
				kjgrmx4.setFz(Double.parseDouble(dsismfz));
				kjgrmx4.setCjr("superadmin");
				kjgrmx4.setZt("1");
				grmxList.add(kjgrmx4);
			}
		}
		if(dwlist !=null){
			for(int i=0;i<dwlist.size();i++){
				Map map = (Map) dwlist.get(i);
				HPKJ_KJGRMX kjgrmx5 = new HPKJ_KJGRMX();
				kjgrmx5.setSmbm(dwsmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx5.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx5.setKsnm(ksnm);
				kjgrmx5.setXmid(xmid);
				kjgrmx5.setFz(Double.parseDouble(dwsmfz));
				kjgrmx5.setCjr("superadmin");
				kjgrmx5.setZt("1");
				grmxList.add(kjgrmx5);
			}
		}
		if(msg != null){
			kjSer.insertGrmx(grmxList);
		}
		return msg;
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request,HttpServletResponse response){
		String xmid = request.getParameter("xmid");
		String rhsj = request.getParameter("rhsj");
		String dysm = request.getParameter("dysm");
		String dysmmc = request.getParameter("dysmmc");
		String desm = request.getParameter("desm");
		String desmmc = request.getParameter("desmmc");
		String dssm = request.getParameter("dssm");
		String dssmmc = request.getParameter("dssmmc");
		String dsism = request.getParameter("dsism");
		String dsismmc = request.getParameter("dsismmc");
		String dwsm = request.getParameter("dwsm");
		String dwsmmc = request.getParameter("dwsmmc");
		String kz1 = request.getParameter("kz1");
		String kz2 = request.getParameter("kz2");
		String kz3 = request.getParameter("kz3");
		String remark = request.getParameter("remark");
		String dysmfz = request.getParameter("dysmfz");
		String desmfz = request.getParameter("desmfz");
		String dssmfz = request.getParameter("dssmfz");
		String dsismfz = request.getParameter("dsismfz");
		String dwsmfz = request.getParameter("dwsmfz");
		String dysmbm = request.getParameter("dysmbm");
		String desmbm = request.getParameter("desmbm");
		String dssmbm = request.getParameter("dssmbm");
		String dsismbm = request.getParameter("dsismbm");
		String dwsmbm = request.getParameter("dwsmbm");
		String dysmItem = request.getParameter("dysmItem");
		String desmItem = request.getParameter("desmItem");
		String dssmItem = request.getParameter("dssmItem");
		String dsismItem = request.getParameter("dsismItem");
		String dwsmItem = request.getParameter("dwsmItem");
		List dylist = null;
		List delist = null;
		List dslist = null;
		List dsilist = null;
		List dwlist = null;
		HPKJ_KJXMMX kjxmmx = new HPKJ_KJXMMX();
		if(!StringUtil.isNullAndSpace(xmid)){
			kjxmmx.setXmid(xmid);
		}
		if(!StringUtil.isNullAndSpace(rhsj)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			try {
				kjxmmx.setRhsj(sdf.parse(rhsj));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			kjxmmx.setRhnd(Integer.parseInt(rhsj.substring(0,4)));
 		}
		if(!StringUtil.isNullAndSpace(dysm)){
 			kjxmmx.setDysm(dysm);
 		}
 		if(!StringUtil.isNullAndSpace(dysmmc)){
 			kjxmmx.setDysmmc(dysmmc);
 		}
 		if(!StringUtil.isNullAndSpace(desm)){
 			kjxmmx.setDesm(desm);
 		}
 		if(!StringUtil.isNullAndSpace(desmmc)){
 			kjxmmx.setDesmmc(desmmc);
 		}
 		if(!StringUtil.isNullAndSpace(dssm)){
 			kjxmmx.setDssm(dssm);
 		}
 		if(!StringUtil.isNullAndSpace(dssmmc)){
 			kjxmmx.setDssmmc(dssmmc);
 		}
 		if(!StringUtil.isNullAndSpace(dsism)){
 			kjxmmx.setDsism(dsism);
 		}
 		if(!StringUtil.isNullAndSpace(dsismmc)){
 			kjxmmx.setDsismmc(dsismmc);
 		}
 		if(!StringUtil.isNullAndSpace(dwsm)){
 			kjxmmx.setDwsm(dwsm);
 		}
 		if(!StringUtil.isNullAndSpace(dwsmmc)){
 			kjxmmx.setDwsmmc(dwsmmc);
 		}
 		if(!StringUtil.isNullAndSpace(kz1)){
 			kjxmmx.setKz1(kz1);
 		}
		if(!StringUtil.isNullAndSpace(kz2)){
		 	kjxmmx.setKz2(kz2);		
	    }
		if(!StringUtil.isNullAndSpace(kz3)){
			kjxmmx.setKz3(kz3);
		}
		if(!StringUtil.isNullAndSpace(remark)){
			kjxmmx.setRemark(remark);	
		}
		if(!StringUtil.isNullAndSpace(dysmItem)){
			 dylist = JsonUtil.json2Bean(dysmItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(desmItem)){
			 delist = JsonUtil.json2Bean(desmItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(dssmItem)){
			 dslist = JsonUtil.json2Bean(dssmItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(dsismItem)){
			 dsilist = JsonUtil.json2Bean(dsismItem, ArrayList.class);
		}
		if(!StringUtil.isNullAndSpace(dwsmItem)){
			 dwlist = JsonUtil.json2Bean(dwsmItem, ArrayList.class);
		}
		String msg = kjSer.updateTable(kjxmmx);
		List<HPKJ_KJGRMX> grmxList = new ArrayList<HPKJ_KJGRMX>();
		if(dylist!= null){
			for(int i=0;i<dylist.size();i++){
				Map map = (Map) dylist.get(i);
				HPKJ_KJGRMX kjgrmx1 = new HPKJ_KJGRMX();
				kjgrmx1.setSmbm(dysmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx1.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx1.setKsnm(ksnm);
				kjgrmx1.setXmid(xmid);
				kjgrmx1.setFz(Double.parseDouble(dysmfz));
				kjgrmx1.setCjr("superadmin");
				kjgrmx1.setZt("1");
				grmxList.add(kjgrmx1);
			}
			kjSer.deleteBysmbm(dysmbm);
		}
		if(delist != null){
			for(int i=0;i<delist.size();i++){
				Map map = (Map) delist.get(i);
				HPKJ_KJGRMX kjgrmx2 = new HPKJ_KJGRMX();
				kjgrmx2.setSmbm(desmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx2.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx2.setKsnm(ksnm);
				kjgrmx2.setXmid(xmid);
				kjgrmx2.setFz(Double.parseDouble(desmfz));
				kjgrmx2.setCjr("superadmin");
				kjgrmx2.setZt("1");
				grmxList.add(kjgrmx2);
			}
			kjSer.deleteBysmbm(desmbm);
		}
		if(dslist != null){
			for(int i=0;i<dslist.size();i++){
				Map map = (Map) dslist.get(i);
				HPKJ_KJGRMX kjgrmx3 = new HPKJ_KJGRMX();
				kjgrmx3.setSmbm(dssmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx3.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx3.setKsnm(ksnm);
				kjgrmx3.setXmid(xmid);
				kjgrmx3.setFz(Double.parseDouble(dssmfz));
				kjgrmx3.setCjr("superadmin");
				kjgrmx3.setZt("1");
				grmxList.add(kjgrmx3);
			}
			kjSer.deleteBysmbm(dssmbm);
		}
		if(dsilist != null){
			for(int i=0;i<dsilist.size();i++){
				Map map = (Map) dsilist.get(i);
				HPKJ_KJGRMX kjgrmx4 = new HPKJ_KJGRMX();
				kjgrmx4.setSmbm(dsismbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx4.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx4.setKsnm(ksnm);
				kjgrmx4.setXmid(xmid);
				kjgrmx4.setFz(Double.parseDouble(dsismfz));
				kjgrmx4.setCjr("superadmin");
				kjgrmx4.setZt("1");
				grmxList.add(kjgrmx4);
			}
			kjSer.deleteBysmbm(dsismbm);
		}
		if(dwlist !=null){
			for(int i=0;i<dwlist.size();i++){
				Map map = (Map) dwlist.get(i);
				HPKJ_KJGRMX kjgrmx5 = new HPKJ_KJGRMX();
				kjgrmx5.setSmbm(dwsmbm);
				String ygbh = (String) map.get("ygbh");
				kjgrmx5.setYgbh(ygbh);
				String ksnm = (String) map.get("ksnm");
				kjgrmx5.setKsnm(ksnm);
				kjgrmx5.setXmid(xmid);
				kjgrmx5.setFz(Double.parseDouble(dwsmfz));
				kjgrmx5.setCjr("superadmin");
				kjgrmx5.setZt("1");
				grmxList.add(kjgrmx5);
			}
			kjSer.deleteBysmbm(dwsmbm);
		}
		if(msg != null){
			kjSer.insertGrmx(grmxList);
		}
		return msg;
	}
	
	@RequestMapping(value="/queryYG", method=RequestMethod.POST)
	@ResponseBody
	public Object queryYG(HttpServletRequest request,HttpServletResponse response){
		String ygxm = request.getParameter("ygxm");
//		Map<String, Object> map = ySer.selectAll(page, rows, ygxm);
		List<HPXT_YGZCXX> list = ySer.selectByP(ygxm);
		return list;
	}
	
	@RequestMapping(value="/xmmxExp", method=RequestMethod.GET)
	@ResponseBody
	public void xmmxExp(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String xmbm = request.getParameter("xmbm");
		String xm = request.getParameter("xm");
		String ksnm = request.getParameter("ksnm");
		Map<String, Object> map = new HashMap<String ,Object>();
		if(!StringUtil.isNullAndSpace(startTime)){
			map.put("startTime",startTime);
		}
		if(!StringUtil.isNullAndSpace(endTime)){
			map.put("endTime",endTime );
		}
		if(!StringUtil.isNullAndSpace(xmbm)){
			map.put("xmbm", xmbm);
		}
		if(!StringUtil.isNullAndSpace(xm)){
			map.put("xm",xm );
		}
		if(!StringUtil.isNullAndSpace(ksnm)){
			map.put("ksnm", ksnm);
		}
		
		List<Object> data = kjSer.exportList(map);
		
		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		response.reset();// 清空输出流
		String filename = "科教项目信息采集.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
		HSSFWorkbook wb = kjSer.export("科教项目信息采集", "荣获年度,荣获时间,项目编码,项目名称,类别编码,类别名称,级别编码,级别名称,第一署名编码,第一署名,"
				+ "第二署名编码,第二署名,第三署名编码,第三署名,第四署名编码,第四署名,第五署名编码,第五署名,扩展信息1,扩展信息2", data);
		wb.write(ouputStream);
	}
	
	@RequestMapping(value="/importExl", method=RequestMethod.POST)
	@ResponseBody
	public Object importExl(@RequestParam("file") MultipartFile file, @RequestParam("rhsj2") String rhsj){
		String msg = null;
		InputStream inputStream = null;
		HSSFWorkbook wb=null;
		List<HPKJ_KJXMMX> array =null;
		
		String fileName = file.getOriginalFilename();
		String prefix=fileName.substring(fileName.lastIndexOf(".") + 1);
		String pre = prefix.toLowerCase();
		if(!"xls".equals(pre) && !"xlsx".equals(pre)){
			msg = "3";
		}else{
			try {
				 inputStream = file.getInputStream();
				 wb = new HSSFWorkbook(inputStream);
				 array = new ArrayList<HPKJ_KJXMMX>();
				    HPKJ_KJXMMX bean;
				    HSSFSheet sheet = wb.getSheetAt(0);
				 // 得到总行数
			 		int rowNum = sheet.getLastRowNum();
			 		HSSFRow row = sheet.getRow(0);
			 		int colNum = row.getPhysicalNumberOfCells();
			 		for (int i = 1; i <= rowNum; i++) {
			 			row = sheet.getRow(i);
			 			HSSFCell c0 = row.getCell(0);
			 			HSSFCell c1 = row.getCell(1);
			 			HSSFCell c2 = row.getCell(2);
			 			HSSFCell c3 = row.getCell(3);
			 			HSSFCell c4 = row.getCell(4);
			 			HSSFCell c5 = row.getCell(5);
			 			HSSFCell c6 = row.getCell(6);
			 			HSSFCell c7 = row.getCell(7);
			 			HSSFCell c8 = row.getCell(8);
			 			HSSFCell c9 = row.getCell(9);
			 			HSSFCell c10 = row.getCell(10);
			 			HSSFCell c11 = row.getCell(11);
			 			HSSFCell c12 = row.getCell(12);
			 			HSSFCell c13 = row.getCell(13);
			 			HSSFCell c14 = row.getCell(14);
			 			bean = new HPKJ_KJXMMX();
			 			String kz = null;
			 			String remark = null;
			 			String xmid = UUID.randomUUID().toString();
			 			String xmbm = getStringCellValue(c0);
			 			String lbbm = getStringCellValue(c1);
			 			String jbbm = getStringCellValue2(c2);
			 			String dysm = getStringCellValue(c3);
			 			String dysmmc = getStringCellValue(c4);
			 			String desm = getStringCellValue(c5);
			 			String desmmc = getStringCellValue(c6);
			 			String dssm = getStringCellValue(c7);
			 			String dssmmc = getStringCellValue(c8);
			 			String dsism = getStringCellValue(c9);
			 			String dsismmc = getStringCellValue(c10);
			 			String dwsm = getStringCellValue(c11);
			 			String dwsmmc = getStringCellValue(c12);
			 			if(xmbm != "H"){
			 				 kz = getStringCellValue2(c13);
				 			 remark = getStringCellValue2(c14);
			 			}else{
			 				 kz = getStringCellValue2(c13);
			 				 remark = getStringCellValue(c14);
			 			}
			 			
			 			if(StringUtil.isNullAndSpace(xmbm) || StringUtil.isNullAndSpace(lbbm) || StringUtil.isNullAndSpace(jbbm)){
			 				msg = "2";
			 			}else{
			 				String kjbm = xmbm+lbbm+jbbm;
			 				bean.setKjbm(kjbm);
			 			}
			 			if(!StringUtil.isNullAndSpace(rhsj)){
			 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			 				try {
			 					bean.setRhsj(sdf.parse(rhsj));
			 				} catch (ParseException e) {
			 					e.printStackTrace();
			 				}
			 				bean.setRhnd(Integer.parseInt(rhsj.substring(0,4)));
			 	 		}
			 			if(!StringUtil.isNullAndSpace(dysm)){
			 	 			bean.setDysm(dysm);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dysmmc)){
			 	 			bean.setDysmmc(dysmmc);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(desm)){
			 	 			bean.setDesm(desm);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(desmmc)){
			 	 			bean.setDesmmc(desmmc);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dssm)){
			 	 			bean.setDssm(dssm);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dssmmc)){
			 	 			bean.setDssmmc(dssmmc);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dsism)){
			 	 			bean.setDsism(dsism);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dsismmc)){
			 	 			bean.setDsismmc(dsismmc);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dwsm)){
			 	 			bean.setDwsm(dwsm);
			 	 		}
			 	 		if(!StringUtil.isNullAndSpace(dwsmmc)){
			 	 			bean.setDwsmmc(dwsmmc);
			 	 		}
		 	 			if(!StringUtil.isNullAndSpace(kz)){
		 	 				bean.setKz1(kz);
		 	 			}
		 	 			if(!StringUtil.isNullAndSpace(remark)){
		 	 				bean.setKz2(remark);
		 	 			}
			 			bean.setXmid(xmid);
			 			bean.setCreator("admin");
			 			bean.setCreateat(new Date());
			 			bean.setStatus("1");
			 			array.add(bean);
			 		}
			 		if(msg != "2"){
			 			kjSer.addBatch(array);
			 			msg = "1";
			 		}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(wb != null){
					try {
						wb.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	    
		return msg;
	}
	
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		if(cell == null){
			strCell = "";
		}else{
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				strCell = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				strCell = "";
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}
	
	private String getStringCellValue2(HSSFCell cell) {
		String strCell = "";
		if(cell == null){
			strCell = "";
		}else{
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				Double c = cell.getNumericCellValue();
				BigDecimal bd = new BigDecimal(c); 
				strCell = bd.toPlainString();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				strCell = "";
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}
	
	@RequestMapping("/exportExportExcel")
	@ResponseBody
	public List<Combobox> queryExpoetExcel() {
		List<Combobox> list = kjSer.queryExportExcel();
		list.add(0, new Combobox("", "---请选择---"));
		return list;
	}
	
	@RequestMapping(value="/{fileName}/exportExcel", produces="text/html;charset=UTF-8")
	public void exportExcel(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
		 /*BufferedInputStream bis = null;  
	     BufferedOutputStream bos = null;  
	     response.setContentType("application/vnd.ms-excel");
	     response.setHeader("Content-disposition", "attachment;filename=模板.xls"); 

		 String realpath = request.getSession().getServletContext().getRealPath("/");  
		 
		 try {
			 File file = new File(realpath+"/resources/excel/"+new String(fileName.getBytes(),"GBK")+".xls");
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(response.getOutputStream());  
			byte[] data = new byte[1024];
			int len = 0;
	        while (-1 != (len=bis.read(data, 0, data.length))) {
	           bos.write(data, 0, len);  
	        }  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		try {
			String realpath = request.getSession().getServletContext().getRealPath("/");
			File file = new File(realpath+"/resources/excel/"+new String(fileName.getBytes(),"GBK")+".xls");
			InputStream input = FileUtils.openInputStream(file);
			byte[] data = IOUtils.toByteArray(input);

			String fn = URLEncoder.encode(file.getName(), "UTF-8");

			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + UtilStr.encodeFileName(request, fileName) + ".xls\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/x-msdownload; charset=UTF-8");

			IOUtils.write(data, response.getOutputStream());
			IOUtils.closeQuietly(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
