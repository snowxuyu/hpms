package com.pbcs.pbgl.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpms.util.DateUtil;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.pbcs.pbgl.entity.PBCS_KSPBGL;
import com.pbcs.pbgl.entity.PBCS_KSPBMX;
import com.pbcs.pbgl.service.KspbglSer;
import com.pbcs.pbgl.service.KspbmxSer;
import com.pbcs.pbgl.service.PbbszSer;
import com.pbcs.pbgl.vo.PbmxVo;
import com.rongda.framework.util.IdUtil;

@Controller
@RequestMapping("/pbgl/pbbsz")
public class PbbszController {

	@Autowired
	private PbbszSer ser;
	
	@Autowired
	private KspbglSer kspbglSer;
	@Autowired
	private KspbmxSer kspbmxSer;
	
	@RequestMapping
	public String show(Model model, String nd, String yd, String ksnm) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		map.put("ksnm", ksnm);
		PBCS_KSPBGL bean = ser.findPB(map);
		String[][] dateAndWeeks = DateUtil.getDateAndWeeks(nd, yd);
		if (bean == null) {
			//插入科室排班管理表
			PBCS_KSPBGL kspbgl = new PBCS_KSPBGL();
			String createId = IdUtil.createId();
			kspbgl.setPblsh(createId);
			kspbgl.setKsnm(ksnm);
			kspbgl.setNd(Short.valueOf(nd));
			kspbgl.setYd(Short.valueOf(yd));
			kspbgl.setCjr("admin");
			kspbgl.setZt("1");
			kspbglSer.add(kspbgl);
			//查询相关人员
			map = new HashMap<String, Object>();
			map.put("ksnm", ksnm);
			List<HPXT_YGZCXX> listYG = ser.findYG(map);
			List<PBCS_KSPBMX> list = new ArrayList<PBCS_KSPBMX>();
			
			PBCS_KSPBMX kspbmx = null;
			Calendar calendar = Calendar.getInstance();
			for (HPXT_YGZCXX yg : listYG) {
				for (int i = 0; i<dateAndWeeks.length; i++) {
					kspbmx = new PBCS_KSPBMX();
					kspbmx.setYgbh(yg.getYgbh());
					kspbmx.setPblsh(createId);
					kspbmx.setRq(Short.valueOf(dateAndWeeks[i][0]));
					kspbmx.setSjxq(dateAndWeeks[i][1]);
					calendar.set(Integer.parseInt(nd), Integer.parseInt(yd), Integer.parseInt(dateAndWeeks[i][0]));
					kspbmx.setSjrq(calendar.getTime());
					kspbmx.setCjr("admin");
					kspbmx.setZt("1");
					list.add(kspbmx);
				}
			}
			kspbmxSer.addBatch(list);
			map = new HashMap<String, Object>();
			map.put("nd", nd);
			map.put("yd", yd);
			map.put("ksnm", ksnm);
			List<PbmxVo> listMX = ser.findMX(map);
			model.addAttribute("listMX", listMX);
			model.addAttribute("dateAndWeeks", dateAndWeeks);
		} else {
			List<PbmxVo> listMX = ser.findMX(map);
			model.addAttribute("listMX", listMX);
			model.addAttribute("dateAndWeeks", dateAndWeeks);
		}
		List<HPCS_BBFLK> listBBFL = ser.findBbfl(null);
		model.addAttribute("listBBFL", listBBFL);
		return "/pbcs/pbgl/pbbsz";
	}
}
