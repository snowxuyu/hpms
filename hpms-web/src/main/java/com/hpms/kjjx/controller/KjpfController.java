package com.hpms.kjjx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.utils.JsonUtil;
import com.hpms.kjjx.entity.HPKJ_KJXMPF;
import com.hpms.kjjx.entity.HPKJ_PFMID;
import com.hpms.kjjx.service.KjpfSer;

@Controller
@RequestMapping("/kjjx/kjpfAction")
public class KjpfController {
	@Resource
	private KjpfSer kjpfSer;

	@RequestMapping
	public String show(HttpServletRequest request) {

		List<HPKJ_KJXMPF> list2 = kjpfSer.findAll();
		List<HPKJ_PFMID> lt = new ArrayList<>();
		Map<String, HPKJ_PFMID> map = new HashMap<String, HPKJ_PFMID>();
		String key = "";
		for (int i = 0; i < list2.size(); i++) {
			HPKJ_KJXMPF t = list2.get(i);
			HPKJ_PFMID t1 = null;
			HPKJ_PFMID t2 = null;
			HPKJ_PFMID t3 = null;
			HPKJ_PFMID t4 = null;
			key = t.getXmbm();
			// key = rs.getObject("XMBM").toString();
			if (null == map.get(key)) {
				t1 = new HPKJ_PFMID();
				t1.setCode(t.getXmbm());
				t1.setName(t.getXmmc());
				map.put(key, t1);
				lt.add(t1);
			} else {
				t1 = map.get(key);
			}
			key += t.getLbbm();
			if (null == map.get(key)) {
				t2 = new HPKJ_PFMID();
				t2.setCode(t.getLbbm());
				t2.setName(t.getLbmc());
				map.put(key, t2);
				t1.getList().add(t2);
			} else {
				t2 = map.get(key);
			}
			key += t.getJbbm();
			if (null == map.get(key)) {
				t3 = new HPKJ_PFMID();
				t3.setCode(t.getJbbm());
				t3.setName(t.getJbmc());
				map.put(key, t3);
				t2.getList().add(t3);
			} else {
				t3 = map.get(key);
			}

			key += t.getSmbh();
			if (null == map.get(key)) {
				t4 = new HPKJ_PFMID();
				t4.setCode(t.getSmbm());
				t4.setName(t.getSmmc());
				t4.setFz(t.getFz());
				map.put(key, t4);
				t3.getList().add(t4);
			} else {
				t4 = map.get(key);
			}

			t1.setCount(t1.getCount() + 1);
			t2.setCount(t2.getCount() + 1);
			t3.setCount(t3.getCount() + 1);
			t4.setCount(t4.getCount() + 1);
		}

		request.setAttribute("list", lt);
		return "/kjjx/kjpf";
	}

	@RequestMapping(value = "/findByPage", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> find(@RequestParam int page,
			@RequestParam int rows, @RequestParam(required = false) String sn) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != sn)
			map.put("sn", sn);
		return kjpfSer.find(page, rows, map);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Object findAll() {
		return kjpfSer.findAll();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request) {
		String rowstr = request.getParameter("mobile");
		List list = JsonUtil.json2Bean(rowstr, ArrayList.class);
		List<HPKJ_KJXMPF> pfList = new ArrayList<HPKJ_KJXMPF>();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String smbm = (String) map.get("SMBM");
			String fz = (String) map.get("FZ");
			HPKJ_KJXMPF h = kjpfSer.findById(smbm);
			h.setFz(fz);
			pfList.add(h);
		}
		try {
			kjpfSer.updateBatch(pfList);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
}
