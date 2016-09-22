package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ReportService;
import com.atguigu.crm.utils.CRMUtils;
import com.sun.org.apache.bcel.internal.generic.Type;

@RequestMapping("/report")
@Controller
public class ReportHandler {

	
	@Autowired
	private ReportService rs;
	
	
	
	@RequestMapping("/consist")
	public String custmerConsist(@RequestParam("type") String type,@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			HttpServletRequest request,
			Map<String, Object> map) throws ParseException{
			type = type.split("_")[1];
			Page<Map<String, Object>> page=rs.getCustomerConsistPage(type,pageNoStr);
			map.put("page", page);
		return "report/consist";
	}
	
	@RequestMapping("/pay")
	public String customerPay(@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			HttpServletRequest request,
			Map<String, Object> map) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<Map<String, Object>> page=rs.getCustomerPayPage(params,pageNoStr);
		map.put("queryString", queryString);
		map.put("page", page);
		return "report/pay";
	}
}
