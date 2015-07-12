package com.hpms.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;


public class UtilStr {
	private static NumberFormat nf = new DecimalFormat(",### 元");
	
	public static Map<String, Object> mapFilter(Map<String, Object> map) {
		for (String key : map.keySet()) {
			if (UtilStr.isNull(map.get(key))) {
				map.remove(key);
			}
		}
		return map;
	}
	
	public static String formatMoney(String number){
		  if(isNull(number)) return "0 元";
		  String testStr = null;
		  try{
			  testStr = nf.format(Double.valueOf(number)); 
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return testStr;
	}
	
	public static String unicode(String code){
	    String codeStr="";
	    if(!"".equals(code)){
	    	byte[] by;
			try {
				by = code.getBytes("iso-8859-1");
				codeStr=new String(by, "GB2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    }
	    return codeStr;
   }

	public static String formatDouble(Double number){
		String str = "";
		if(number != null){
		  str = new DecimalFormat("0.00").format(number);
		}
		return str;
	}
	
	public static void main(String[] args) {
		Double a = 104.50;
		System.out.println(new DecimalFormat("#.##").format(a));
		System.out.println(new DecimalFormat("##.##").format(a));
		System.out.println(new DecimalFormat("0.00").format(a));
	}
	
	public static boolean isNull(Object data)
	{
		if (data instanceof String)
			return data==null || ((String)data).trim().equals("");
		else
			return data==null;
	}
	
	public static boolean isNotNull(Object data)
	{
		return !isNull(data);
	}

	/**
	 * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeFileName(HttpServletRequest request,
			String pFileName) throws UnsupportedEncodingException {

		String filename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent) {
			if (-1 != agent.indexOf("Firefox")) {// Firefox
				filename = "=?UTF-8?B?" + (new String(
								org.apache.commons.codec.binary.Base64
										.encodeBase64(pFileName
												.getBytes("UTF-8")))) + "?=";
			} else if (-1 != agent.indexOf("Chrome")) {// Chrome
				filename = new String(pFileName.getBytes(), "ISO8859-1");
			} else {// IE7+
				filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
				filename = StringUtils.replace(filename, "+", "%20");// 替换空格
			}
		} else {
			filename = pFileName;
		}
		return filename;
	}

}
