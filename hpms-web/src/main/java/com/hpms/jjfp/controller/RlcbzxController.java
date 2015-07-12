package com.hpms.jjfp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_RLCBZX;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.service.RlcbysSer;
import com.hpms.jjfp.service.RlcbzxSer;
import com.hpms.jjfp.vo.RlcbzxVO;
import com.hpms.jjfp.vo.Xj;
import com.hpms.util.DateUtil;
import com.hpms.util.DoubleUtil;

/**
 * 
 * 类名：RlcbzxController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月22日 <br>
 * 描述：人力成本执行控制器 <br>
 */
@Controller
@RequestMapping("/jjfp/rlcbzx")
public class RlcbzxController {

private static Logger logger = Logger.getLogger(RlcbzxController.class);
	
	@Autowired
	private RlcbzxSer ser;
	
	@Autowired
	private RlcbysSer rlcbysSer;
	
	@RequestMapping
	public String show() {
		return "/jjfp/rlcbzx";
	}
	
	/**
	 * 根据年度查询执行信息
	 * @return
	 */
	@RequestMapping(value="/query")
	@ResponseBody
	public List<HPYS_YSZX> query(@RequestParam(required=false) String nd) {
		return ser.selectNdYs(nd);
	}
	
	/**
	 * 查询执行表
	 * @param nd
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/query/nd")
	public String query(@RequestParam(required=false) String nd, Model model, HttpSession session) {
		if (nd == null || "".equals(nd)) {
			nd = String.valueOf(DateUtil.getYear());
		}
		session.setAttribute("nd", nd);
		List<RlcbzxVO> list = ser.findByNd(nd);
		
		//处理相同数据
		int count = 0;
		String fjmc = null;
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (fjmc == null) {
				fjmc = list.get(i).getFjmc();
				count = 1;
				continue;
			}
			if (list.get(i).getFjmc().equals(fjmc) && i != list.size() - 1) {
				count++;
			} else if (i == list.size() - 1) {
				count++;
				listCount.add(count);
			} else {
				listCount.add(count);
				count = 1;
				fjmc = list.get(i).getFjmc();
			}
		}
		List<Xj> listXj = new ArrayList<Xj>();
		Xj xj = new Xj();
		if (listCount.size() == 0) {
			listCount.add(1);
		}
		count = listCount.get(0);
		int cs = 0;
		// 小计
		for (int i = 0; i < list.size(); i++) {
			
			xj.setY1(xj.getY1() + DoubleUtil.parseLower(list.get(i).getY1()));
			xj.setY2(xj.getY2() + DoubleUtil.parseLower(list.get(i).getY2()));
			xj.setY3(xj.getY3() + DoubleUtil.parseLower(list.get(i).getY3()));
			xj.setY4(xj.getY4() + DoubleUtil.parseLower(list.get(i).getY4()));
			xj.setY5(xj.getY5() + DoubleUtil.parseLower(list.get(i).getY5()));
			xj.setY6(xj.getY6() + DoubleUtil.parseLower(list.get(i).getY6()));
			xj.setY7(xj.getY7() + DoubleUtil.parseLower(list.get(i).getY7()));
			xj.setY8(xj.getY8() + DoubleUtil.parseLower(list.get(i).getY8()));
			xj.setY9(xj.getY9() + DoubleUtil.parseLower(list.get(i).getY9()));
			xj.setY10(xj.getY10() + DoubleUtil.parseLower(list.get(i).getY10()));
			xj.setY11(xj.getY11() + DoubleUtil.parseLower(list.get(i).getY11()));
			xj.setY12(xj.getY12() + DoubleUtil.parseLower(list.get(i).getY12()));
			
			if (i == count - 1) {
				listXj.add(xj);
				cs++;
				if (cs == listCount.size()) {
					break;
				}
				count += listCount.get(cs);
				xj = new Xj();
			}
		}
		
		model.addAttribute("ysxjList", listXj);
		
		listXj = new ArrayList<Xj>();
		xj = new Xj();
		count = listCount.get(0);
		cs = 0;
		// 执行小计
		for (int i = 0; i < list.size(); i++) {
			
			xj.setY1(xj.getY1() + DoubleUtil.parseLower(list.get(i).getZ1()));
			xj.setY2(xj.getY2() + DoubleUtil.parseLower(list.get(i).getZ2()));
			xj.setY3(xj.getY3() + DoubleUtil.parseLower(list.get(i).getZ3()));
			xj.setY4(xj.getY4() + DoubleUtil.parseLower(list.get(i).getZ4()));
			xj.setY5(xj.getY5() + DoubleUtil.parseLower(list.get(i).getZ5()));
			xj.setY6(xj.getY6() + DoubleUtil.parseLower(list.get(i).getZ6()));
			xj.setY7(xj.getY7() + DoubleUtil.parseLower(list.get(i).getZ7()));
			xj.setY8(xj.getY8() + DoubleUtil.parseLower(list.get(i).getZ8()));
			xj.setY9(xj.getY9() + DoubleUtil.parseLower(list.get(i).getZ9()));
			xj.setY10(xj.getY10() + DoubleUtil.parseLower(list.get(i).getZ10()));
			xj.setY11(xj.getY11() + DoubleUtil.parseLower(list.get(i).getZ11()));
			xj.setY12(xj.getY12() + DoubleUtil.parseLower(list.get(i).getZ12()));
			
			if (i == count - 1) {
				listXj.add(xj);
				cs++;
				if (cs == listCount.size()) {
					break;
				}
				count += listCount.get(cs);
				xj = new Xj();
			}
		}
		List<HPYS_YSZX> ndysList = ser.selectNdYs(nd);
		model.addAttribute("zxje", ndysList.get(0).getSjzje());
		model.addAttribute("listCount", listCount);
		model.addAttribute("rlcbzxList", list);
		model.addAttribute("zxxjList", listXj);
		
		return "/jjfp/rlcbzx_zx";
		
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestParam String xmbm, @RequestParam String yd, @RequestParam Double zxz, HttpSession session) {
		String nd = (String) session.getAttribute("nd");
		if (zxz == null || "".equals(zxz)) {
			zxz = 0.0;
		}
		if (xmbm == null || "".equals(xmbm)) {
			return "";
		}
		if (zxz == null || "".equals(zxz)) {
			return "";
		}
		if (nd == null || "".equals(nd)) {
			return "";
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		map.put("xmbm", xmbm);
		HPYS_RLCBZX bean = ser.findOne(map);
		bean.setSjz(zxz);
		bean.setZxl(zxz/bean.getYsz()*100);
		if (bean.getLsh() == null) {
			HPYS_RLCBYS rlcbys = rlcbysSer.findOne(map);
			bean.setYsbm(rlcbys.getYsbm());
			bean.setCjr("admin");
			bean.setZt("1");
			ser.add(bean);
		} else {
			ser.update(bean);
		}
		return "success";
	}
	
	/**
	 * 查看执行
	 * @return
	 */
	@RequestMapping("/ck")
	public String queryZx() {
		return "/jjfp/rlcbzx_ck";
	}
	
	@RequestMapping("/query/ck")
	@ResponseBody
	public List<HPYS_YSZX> queryZx(@RequestParam(required=false) String nd) {
		return ser.selectNdYs(nd);
	}
	
	@RequestMapping("/query/ck/nd")
	public String queryZx(@RequestParam(required=false) String nd, Model model, HttpSession session) {
		if (nd == null || "".equals(nd)) {
			nd = String.valueOf(DateUtil.getYear());
		}
		session.setAttribute("nd", nd);
		List<RlcbzxVO> list = ser.findByNd(nd);
		List<HPYS_YSZX> ndysList = ser.selectNdYs(nd);
		
		//处理相同数据
		int count = 0;
		String fjmc = null;
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (fjmc == null) {
				fjmc = list.get(i).getFjmc();
				count = 1;
				continue;
			}
			if (list.get(i).getFjmc().equals(fjmc) && i != list.size() - 1) {
				count++;
			} else if (i == list.size() - 1) {
				count++;
				listCount.add(count);
			} else {
				listCount.add(count);
				count = 1;
				fjmc = list.get(i).getFjmc();
			}
		}
		List<Xj> listXj = new ArrayList<Xj>();
		Xj xj = new Xj();
		if (listCount.size() == 0) {
			listCount.add(1);
		}
		count = listCount.get(0);
		int cs = 0;
		// 小计
		for (int i = 0; i < list.size(); i++) {
			
			xj.setY1(xj.getY1() + DoubleUtil.parseLower(list.get(i).getY1()));
			xj.setY2(xj.getY2() + DoubleUtil.parseLower(list.get(i).getY2()));
			xj.setY3(xj.getY3() + DoubleUtil.parseLower(list.get(i).getY3()));
			xj.setY4(xj.getY4() + DoubleUtil.parseLower(list.get(i).getY4()));
			xj.setY5(xj.getY5() + DoubleUtil.parseLower(list.get(i).getY5()));
			xj.setY6(xj.getY6() + DoubleUtil.parseLower(list.get(i).getY6()));
			xj.setY7(xj.getY7() + DoubleUtil.parseLower(list.get(i).getY7()));
			xj.setY8(xj.getY8() + DoubleUtil.parseLower(list.get(i).getY8()));
			xj.setY9(xj.getY9() + DoubleUtil.parseLower(list.get(i).getY9()));
			xj.setY10(xj.getY10() + DoubleUtil.parseLower(list.get(i).getY10()));
			xj.setY11(xj.getY11() + DoubleUtil.parseLower(list.get(i).getY11()));
			xj.setY12(xj.getY12() + DoubleUtil.parseLower(list.get(i).getY12()));
			
			if (i == count - 1) {
				listXj.add(xj);
				cs++;
				if (cs == listCount.size()) {
					break;
				}
				count += listCount.get(cs);
				xj = new Xj();
			}
		}
		
		model.addAttribute("ysxjList", listXj);
		
		listXj = new ArrayList<Xj>();
		xj = new Xj();
		count = listCount.get(0);
		cs = 0;
		// 执行小计
		for (int i = 0; i < list.size(); i++) {
			
			xj.setY1(xj.getY1() + DoubleUtil.parseLower(list.get(i).getZ1()));
			xj.setY2(xj.getY2() + DoubleUtil.parseLower(list.get(i).getZ2()));
			xj.setY3(xj.getY3() + DoubleUtil.parseLower(list.get(i).getZ3()));
			xj.setY4(xj.getY4() + DoubleUtil.parseLower(list.get(i).getZ4()));
			xj.setY5(xj.getY5() + DoubleUtil.parseLower(list.get(i).getZ5()));
			xj.setY6(xj.getY6() + DoubleUtil.parseLower(list.get(i).getZ6()));
			xj.setY7(xj.getY7() + DoubleUtil.parseLower(list.get(i).getZ7()));
			xj.setY8(xj.getY8() + DoubleUtil.parseLower(list.get(i).getZ8()));
			xj.setY9(xj.getY9() + DoubleUtil.parseLower(list.get(i).getZ9()));
			xj.setY10(xj.getY10() + DoubleUtil.parseLower(list.get(i).getZ10()));
			xj.setY11(xj.getY11() + DoubleUtil.parseLower(list.get(i).getZ11()));
			xj.setY12(xj.getY12() + DoubleUtil.parseLower(list.get(i).getZ12()));
			
			if (i == count - 1) {
				listXj.add(xj);
				cs++;
				if (cs == listCount.size()) {
					break;
				}
				count += listCount.get(cs);
				xj = new Xj();
			}
		}
		model.addAttribute("ndysList", ndysList);
		model.addAttribute("listCount", listCount);
		model.addAttribute("rlcbzxList", list);
		model.addAttribute("zxxjList", listXj);
		
		return "/jjfp/rlcbzx_ck_zx";
		
	}
}
