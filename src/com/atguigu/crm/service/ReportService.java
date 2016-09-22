package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.mapper.ReportMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class ReportService {

	@Autowired
	private ReportMapper rm;

	public Page<Map<String, Object>> getCustomerPayPage(
			Map<String, Object> params, String pageNoStr) throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
		System.out.println(mybatisParams);
		Page<Map<String, Object>> page=new Page<>();
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNoStr);
		} catch (NumberFormatException e) {}
		page.setPageNo(pageNo);
		long totalElement=rm.getCustomerPayTotalElment(mybatisParams);
		page.setTotalElments(totalElement);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		List<Map<String, Object>> content = rm.getCustomerPayContent(mybatisParams);
		page.setContent(content);
		
		return page;
	}

	public Page<Map<String, Object>> getCustomerConsistPage(String type,String pageNoStr) throws ParseException {
		// TODO Auto-generated method stub
			if("level".equals(type)){
				type="customer_"+type;
			}
			Page<Map<String, Object>> page = new Page<>();
			int pageNo=1;
			try {
				pageNo=Integer.valueOf(pageNoStr);
			} catch (NumberFormatException e) {}
			page.setPageNo(pageNo);
			long totalElments=rm.getCustomerConsistTotalElement(type);
			page.setTotalElments(totalElments);
			List<Map<String,Object>> content=rm.getCustomerConsistContent(type);
			page.setContent(content);
			System.out.println(content);
			return page;
	}
}
