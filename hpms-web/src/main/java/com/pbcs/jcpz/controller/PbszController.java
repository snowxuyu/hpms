package com.pbcs.jcpz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hpms.entity.TreeNode;
import com.hpms.qmys.vo.TempVO;
import com.hpms.yljx.service.BbflkSer;
import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.vo.Combobox;
import com.pbcs.pbgl.entity.PBCS_KSSYMX;
import com.pbcs.pbgl.service.KsbbmxSer;
import com.pbcs.pbgl.service.KsbbsySer;
import com.pbcs.pbgl.service.KspbmxSer;
import com.pbcs.pbgl.service.KssymxSer;

@Controller
@RequestMapping("/pbcs/bbsz")
public class PbszController {

	@Resource
	private KsbbmxSer ksbbmxSer;

	@Resource
	private KszcxxSer kszcxxSer;

	@Resource
	private KsbbsySer ksbbsySer;

	@Resource
	private KspbmxSer kspbmxSer;

	@Resource
	private KssymxSer kssymxSer;

	@Resource
	private BbflkSer bbflkSer;

	@RequestMapping
	public String show() {
		return "/pbcs/pbgl/bbsz";
	}

	@RequestMapping(value = "/findByPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows,
			HttpServletRequest request) {
		String sylsh = request.getParameter("sylsh");
		Map<String, Object> map = new HashMap<String, Object>();
		if ((!"".equals(sylsh)) && sylsh != null) {
			map.put("sylsh", sylsh);
		}
		return ksbbmxSer.find(page, rows, map);
	}

	@RequestMapping(value = "/getTreeList", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> getTreeList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sylsh = request.getParameter("sylsh");
		System.out.println("sylsh" + sylsh);
		if ((!"".equals(sylsh)) && sylsh != null) {
			map.put("sylsh", sylsh);
		}
		return kszcxxSer.getTreeList(map);
	}

	@RequestMapping("/findTreeList")
	@ResponseBody
	public String findTreeList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sylsh = request.getParameter("sylsh");
		if ((!"".equals(sylsh)) && sylsh != null) {
			map.put("sylsh", sylsh);
		}
		List<TempVO> list = kssymxSer.getKsnms(map);
		System.out.println(" " + list.get(0).getP1());
		return list.size() > 0 ? list.get(0).getP1() : "";
	}

	@RequestMapping(value = "/getBbsy", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBbsy(int page, int rows,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		return ksbbsySer.find(page, rows, map);
	}

	@RequestMapping(value = "/saveTree", method = RequestMethod.POST)
	@ResponseBody
	public Object saveTree(HttpServletRequest request) {
		String json = request.getParameter("sel");
		String selId = request.getParameter("selId");
		Gson gson = new Gson();
		String result = "success";
		List<PBCS_KSSYMX> list = gson.fromJson(json,
				new TypeToken<List<PBCS_KSSYMX>>() {
				}.getType());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if ((!"".equals(selId)) && selId != null) {
				map.put("sylsh", selId);
			}
			kssymxSer.deleteByIds(selId);
			kssymxSer.addBatch(list);
		} catch (Exception e) {
			result = "error";
		}
		return result;
	}

	@RequestMapping(value = "/queryBb")
	@ResponseBody
	public List<Combobox> queryBb() {
		List<Combobox> list = bbflkSer.listAllBb();
		return list;
	}

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request) {
		String saveJson = request.getParameter("saveJson");
		String updateJson = request.getParameter("updateJson");
		String result = "success";
		try {
			ksbbmxSer.saveOrUpdate(saveJson, updateJson);

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}

	@RequestMapping("/saveLb")
	@ResponseBody
	public String saveLb(HttpServletRequest request) {
		String saveJson = request.getParameter("saveJson");
		String updateJson = request.getParameter("updateJson");
		String result = "success";
		try {
			ksbbsySer.saveOrUpdate(saveJson, updateJson);
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}

	@RequestMapping("/deleteMx")
	@ResponseBody
	public String deleteMx(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String result = "success";
		try {
			ksbbmxSer.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}

	@RequestMapping("/deleteLb")
	@ResponseBody
	public String deleteLb(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String result = "success";
		try {
			ksbbsySer.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}
}