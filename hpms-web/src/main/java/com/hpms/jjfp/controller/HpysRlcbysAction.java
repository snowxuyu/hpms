package com.hpms.jjfp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.utils.JsonUtil;
import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.jjfp.entity.HPYS_RLCBDY;
import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_RLCBZX;
import com.hpms.jjfp.entity.HPYS_ZXL;
import com.hpms.jjfp.service.HpysRlcbysSer;
import com.hpms.jjfp.service.RlcbysSer;
import com.rongda.common.util.DBUtil;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("jjfp/HpysRlcbysAction")
public class HpysRlcbysAction {

	@Autowired
	private HpysRlcbysSer hrSer;
	
	@Autowired
	private RlcbysSer ser;

	@RequestMapping
	public String showIndex(){
		return "jjfp/hpysRlcbxmsz";
	}

	//查询年度
	@RequestMapping(value="/queryNd",method=RequestMethod.POST)
	@ResponseBody
	public Object queryNd(HttpServletRequest request,HttpServletResponse response){
		String nd = request.getParameter("nd");
		HashMap<String, Object> map = new HashMap<String,Object>();

		if (!StringUtil.isNullAndSpace(nd)){
			map.put("jxnd", nd);
		}
		List<BPE_JX001> list = hrSer.selectNd(map);

		return list;
	}
	//查询项目
	@RequestMapping(value="/queryXm",method=RequestMethod.POST)
	@ResponseBody
	public Object queryXm(HttpServletRequest request,HttpServletResponse response){
		String nd = request.getParameter("nd");
		HashMap<String, Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(nd)){
			map.put("nd", nd);
		}
		List<HPYS_RLCBDY> list = hrSer.selectXm(map);
		return list;
	}

	//新增项目
	@RequestMapping(value="/insertTable",method=RequestMethod.POST)
	@ResponseBody
	public Object insertTable(HttpServletRequest request,HttpServletResponse response){
		String ss = request.getParameter("item");
		String nd2 = request.getParameter("nd");
		String yybm = request.getParameter("yybm");
		Integer nd = Integer.parseInt(nd2);
		List list = JsonUtil.json2Bean(ss, ArrayList.class);

		List<HPYS_RLCBYS> list2 = new ArrayList<HPYS_RLCBYS>();
		
		List<String> listFJ = new ArrayList<String>();
		
		for(int i =0;i<list.size();i++){
			Map map = (Map) list.get(i);
			String sjzt = (String) map.get("sjzt");
			String cjr = (String) map.get("cjr");
			String zt = (String) map.get("zt");
			Date cjsj = new Date();
			int index = 0;
			if(sjzt !=null && sjzt.equals("2")){
				String px = (Double) map.get("px") + "01";
				px = px.replace(".","");
				int _px = Integer.parseInt(px);
				for(int k=1;k<=12;k++){
					Integer yd = k;
					String xmbm = (String) map.get("xmbm");
					String xmmc = (String) map.get("xmmc");
					String fjbm = (String) map.get("fjbm");
					String fjmc = (String) map.get("fjmc");
					String jb = (String) map.get("jb");
					//					Double px1 =(Double) map.get("px");
					//					Integer px = (int) (px1*10/10);
					String ysbm = nd.toString()+yd.toString()+xmbm;
					HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
					rlcbys.setYsbm(ysbm);
					rlcbys.setYybm(yybm);
					rlcbys.setXmbm(xmbm);
					rlcbys.setXmmc(xmmc);
					rlcbys.setFjbm(fjbm);
					rlcbys.setFjmc(fjmc);
					rlcbys.setJb(jb);
					rlcbys.setPx(++_px);
					rlcbys.setNd(nd);
					rlcbys.setYd(yd);
					rlcbys.setCjr(cjr);
					rlcbys.setZt(zt);
					list2.add(rlcbys);
				}
			} 
			if(sjzt != null && sjzt.equals("1")){
				String px = (Double) map.get("px") + "01";
				px = px.replace(".","");
				int _px = Integer.parseInt(px);
				for(int j=1;j<=12;j++){
					Integer yd = j;
					String xmbm = (String) map.get("xmbm");
					String xmmc = (String) map.get("xmmc");
					String fjbm = (String) map.get("fjbm");
					String fjmc = (String) map.get("fjmc");
					String jb = (String) map.get("jb");
					//					Double px1 =(Double) map.get("px");
					//					Integer px = (int) (px1*10/10);
					String ysbm = nd.toString()+yd.toString()+xmbm;
					HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
					rlcbys.setYsbm(ysbm);
					rlcbys.setYybm(yybm);
					rlcbys.setXmbm(xmbm);
					rlcbys.setXmmc(xmmc);
					rlcbys.setFjbm(fjbm);
					rlcbys.setFjmc(fjmc);
					rlcbys.setJb(jb);
					rlcbys.setPx(++_px);
					rlcbys.setNd(nd);
					rlcbys.setYd(yd);
					rlcbys.setCjr(cjr);
					rlcbys.setZt(zt);
					list2.add(rlcbys);
				}
			}
			if(sjzt == null){
				/*String xmbm = (String) map.get("xmbm");
				String xmmc = (String) map.get("xmmc");
				String fjbm = (String) map.get("fjbm");
				String fjmc = (String) map.get("fjmc");
				String jb = (String) map.get("jb");
				//				Double px1 =(Double) map.get("px");
				//				Integer px = (int) (px1*10/10);
				String ysbm = nd.toString()+xmbm;
				HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
				rlcbys.setYsbm(ysbm);
				rlcbys.setYybm(yybm);
				rlcbys.setXmbm(xmbm);
				rlcbys.setXmmc(xmmc);
				rlcbys.setFjbm(fjbm);
				rlcbys.setFjmc(fjmc);
				rlcbys.setJb(jb);
				rlcbys.setPx(++index);
				rlcbys.setNd(nd);
				rlcbys.setCjr(cjr);
				rlcbys.setZt(zt);
				list2.add(rlcbys);*/
				
				String xmbm = (String) map.get("xmbm");
				List<HPYS_RLCBYS> listXM = ser.findByXmbm(String.valueOf(nd), xmbm);
				if (listXM.size() == 0) {
				
					HPYS_RLCBDY bean = hrSer.findByXMBM(xmbm);
					String xmmc = bean.getXmmc();
					
					String ysbm = nd.toString()+xmbm;
					HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
					rlcbys.setYsbm(ysbm);
					rlcbys.setYybm(yybm);
					rlcbys.setXmbm(xmbm);
					rlcbys.setXmmc(xmmc);
					rlcbys.setJb(bean.getJb());
					rlcbys.setPx(bean.getPx());
					rlcbys.setNd(nd);
					rlcbys.setCjr(cjr);
					rlcbys.setZt(zt);
					list2.add(rlcbys);
					listFJ.add(xmbm);
					
				}
			} else if (!listFJ.contains((String) map.get("fjbm"))) {
				
				String xmbm = (String) map.get("fjbm");
				
				List<HPYS_RLCBYS> listXM = ser.findByXmbm(String.valueOf(nd), xmbm);
				if (listXM.size() == 0) {
					HPYS_RLCBDY bean = hrSer.findByXMBM(xmbm);
					String xmmc = bean.getXmmc();
					
					String ysbm = nd.toString()+xmbm;
					HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
					rlcbys.setYsbm(ysbm);
					rlcbys.setYybm(yybm);
					rlcbys.setXmbm(xmbm);
					rlcbys.setXmmc(xmmc);
					rlcbys.setJb(bean.getJb());
					rlcbys.setPx(bean.getPx());
					rlcbys.setNd(nd);
					rlcbys.setCjr(cjr);
					rlcbys.setZt(zt);
					list2.add(rlcbys);
				}
				listFJ.add(xmbm);
			}
		}
		String msg = hrSer.insertRlcbys(list2);
		return msg;

	}
	//删除项目
	@RequestMapping(value="/deleteTable",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteTable(HttpServletRequest request,HttpServletResponse response){
		String msg = "1";
		String ss = request.getParameter("item");
		String nd2 = request.getParameter("nd");
		List list = JsonUtil.json2Bean(ss, ArrayList.class);
		List<HPYS_RLCBYS> list2 = new ArrayList<HPYS_RLCBYS>();
		Integer nd = Integer.parseInt(nd2);
		Set<String> listXM = new HashSet<String>(); 
		for(int i =0;i<list.size();i++){
			Map map = (Map) list.get(i);
			String sjzt = (String) map.get("sjzt");

			/*if(sjzt == null){
				String xmbm = (String) map.get("xmbm");
				listXM.add(xmbm);
				List<HPYS_RLCBZX> list3 = hrSer.selectByysbm(ysbm);
				if(list3.size() == 0){
					HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
					rlcbys.setYsbm(ysbm);
					list2.add(rlcbys);
				}else{
					msg = "2";
				}
				
			}*/
			if(sjzt != null && sjzt.equals("1")){
				String xmbm = (String) map.get("xmbm");
				for(int j=1;j<=12;j++){
					Integer yd = j;
					String ysbm = nd.toString()+yd.toString()+xmbm;
					List<HPYS_RLCBZX> list4 = hrSer.selectByysbm(ysbm);
					if(list4.size() == 0){
						HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
						rlcbys.setYsbm(ysbm);
						list2.add(rlcbys);
					}else{
						msg = "2";
					}
				}
			}
			if(sjzt != null && sjzt.equals("2")){
				String xmbm = (String) map.get("xmbm");
				for(int k=1;k<=12;k++){
					Integer yd = k;
					String ysbm = nd.toString()+yd.toString()+xmbm;
					List<HPYS_RLCBZX> list5 = hrSer.selectByysbm(ysbm);
					if(list5.size() == 0){
						HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
						rlcbys.setYsbm(ysbm);
						list2.add(rlcbys);
					}else{
						msg = "2";
					}
				}
			}
		}
		if(msg != "2"){
			msg = hrSer.deleteRlcbys(list2);
			if (msg=="1") {
				/*list2.clear();
				for (String fjbm : listXM) {
					List<HPYS_RLCBYS> listFJ = hrSer.selectByFJbm(fjbm);
					if (listFJ.size() == 0) {
						DBUtil.deleteById(HPYS_RLCBYS.class, nd.toString()+fjbm);
					}
				}*/
				List<HPYS_RLCBYS> listNotFJ = hrSer.selectNotFJ(nd.toString(), nd.toString());
				DBUtil.deleteBatch(listNotFJ);
			}
		}
		return msg;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/queryYear",method=RequestMethod.GET)
	public String query(@RequestParam(required=false) String year ,@RequestParam(required=false) String modify, HttpServletRequest request){
		if(StringUtils.isEmpty(year))
			year = "2014";
		List list = hrSer.selectRlcbdyRlcbys(year);
		HPYS_ZXL zxl =  hrSer.selectSumZXL(year);
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("zxl", zxl);
		request.setAttribute("modify", modify);
		return "jjfp/year";
	}

	@RequestMapping(value="/queryByXmbm",method=RequestMethod.POST)
	@ResponseBody
	public Object queryByXmbm(HttpServletRequest request,HttpServletResponse response){
		String year = request.getParameter("year");
		String xmbm = request.getParameter("xmbm");
		Map<String, Object> map = hrSer.selectByXmbm(year, xmbm);
		return map;
	}

	@RequestMapping(value="/insertRlcbzx",method=RequestMethod.POST)
	@ResponseBody
	public Object insertRlcbzx(HttpServletRequest request,HttpServletResponse response){
		String rows = request.getParameter("rows");
		String msg =null;
		List list = JsonUtil.json2Bean(rows, ArrayList.class);
		List<HPYS_RLCBZX> list2 = new ArrayList<HPYS_RLCBZX>();
		List<HPYS_RLCBYS> list3 = new ArrayList<HPYS_RLCBYS>();
		for(int i=0;i<list.size();i++){
			Map map = (Map) list.get(i);
			if(map.get("sjz") == null || map.get("zxl") == null){
				msg = "2";
			}else{
				String lsh = UUID.randomUUID().toString();
				String ysbm = (String) map.get("ysbm");
				Double sjz = (map.get("sjz") instanceof String) ?Double.parseDouble((String) map.get("sjz")) : (Double) map.get("sjz");
				Double zxl = (map.get("zxl") instanceof String) ?Double.parseDouble((String) map.get("zxl")) : (Double) map.get("zxl");
				String cjr = "许浩";
				Date cjsj = new Date();
				String zt = "1";
				HPYS_RLCBZX rlcbzx = new HPYS_RLCBZX();
				rlcbzx.setLsh(lsh);
				rlcbzx.setYsbm(ysbm);
				rlcbzx.setSjz(sjz);
				rlcbzx.setZxl(zxl);
				rlcbzx.setCjr(cjr);
				rlcbzx.setCjsj(cjsj);
				rlcbzx.setZt(zt);
				list2.add(rlcbzx);
			}
		}
		if(list2.size() != 0){
			msg = hrSer.insertRlcbzx(list2);
		}

		for(int i=0;i<list.size();i++){
			Map map = (Map) list.get(i);
			String ysbm = (String) map.get("ysbm");
			Double ysz = (map.get("ysz") instanceof String) ?Double.parseDouble((String) map.get("ysz")) : (Double) map.get("ysz");
			HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
			rlcbys.setYsbm(ysbm);
			rlcbys.setYsz(ysz);
			list3.add(rlcbys);
		}
		hrSer.updateRlcbys(list3);
		return msg;
	}

	@RequestMapping(value="/updateRlcbzx",method=RequestMethod.POST)
	@ResponseBody
	public Object updateRlcbzx(HttpServletRequest request,HttpServletResponse response){
		String msg = null;
		String rows = request.getParameter("rows");
		List list = JsonUtil.json2Bean(rows, ArrayList.class);
		List<HPYS_RLCBZX> list2 = new ArrayList<HPYS_RLCBZX>();
		List<HPYS_RLCBYS> list3 = new ArrayList<HPYS_RLCBYS>();
		for(int i=0;i<list.size();i++){
			Map map = (Map) list.get(i);
			if(map.get("sjz") == null || map.get("zxl") == null){
				msg = "2";
			}else{
				String lsh = (String) map.get("lsh");
				Double sjz =  (map.get("sjz") instanceof String) ?Double.parseDouble((String) map.get("sjz")) : (Double) map.get("sjz");
				Double zxl = (map.get("zxl") instanceof String) ?Double.parseDouble((String) map.get("zxl")) : (Double) map.get("zxl");
				HPYS_RLCBZX rlcbzx = new HPYS_RLCBZX();
				rlcbzx.setLsh(lsh);
				rlcbzx.setSjz(sjz);
				rlcbzx.setZxl(zxl);
				list2.add(rlcbzx);
			}

		}
		if(list2.size() !=0){
			msg = hrSer.updateRlcbzx(list2);
		}
		for(int i=0;i<list.size();i++){
			Map map = (Map) list.get(i);
			String ysbm = (String) map.get("ysbm");
			Double ysz = (map.get("ysz") instanceof String) ?Double.parseDouble((String) map.get("ysz")) : (Double) map.get("ysz");
			HPYS_RLCBYS rlcbys = new HPYS_RLCBYS();
			rlcbys.setYsbm(ysbm);
			rlcbys.setYsz(ysz);
			list3.add(rlcbys);
		}
		hrSer.updateRlcbys(list3);
		return msg;
	}

}
