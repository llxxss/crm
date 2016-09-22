package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.mapper.CustomerServiceMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class CustomerServiceService {

	@Autowired
	private CustomerServiceMapper csm;

	public List<String> getServiceType() {
		// TODO Auto-generated method stub
		return csm.getServiceType();
	}

	public void save(com.atguigu.crm.entity.CustomerService customerService) {
		// TODO Auto-generated method stub
		csm.save(customerService);
	}

	public Page<com.atguigu.crm.entity.CustomerService> getAllotPage(
			String pageNoStr, Map<String, Object> params) throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
		long totalElement=csm.getAllotTotalElment(mybatisParams);
		Page<CustomerService> page = new Page<>();
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNo);
		} catch (Exception e) {}
		page.setPageNo(pageNo);
		page.setTotalElments(totalElement);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<CustomerService> content=csm.getAllotContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public void allot(Long id, Long allotId) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("allotId", allotId);
		map.put("date", new Date());
		csm.allot(map);
		
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		csm.delete(id);
	}

	public com.atguigu.crm.entity.CustomerService get(Long id) {
		// TODO Auto-generated method stub
		return csm.get(id);
	}


	public Page<com.atguigu.crm.entity.CustomerService> getDealPage(
			String pageNoStr, Map<String, Object> params) throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
		long totalElement=csm.getDealTotalElment(mybatisParams);
		Page<CustomerService> page = new Page<>();
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNo);
		} catch (Exception e) {}
		page.setPageNo(pageNo);
		page.setTotalElments(totalElement);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<CustomerService> content=csm.getDealContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public void deal(com.atguigu.crm.entity.CustomerService service) {
		// TODO Auto-generated method stub
		csm.deal(service);
	}

	public Page<com.atguigu.crm.entity.CustomerService> getFeekbackPage(
			String pageNoStr, Map<String, Object> params) throws ParseException {
			Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
			long totalElement=csm.getFeedbackTotalElment(mybatisParams);
			Page<CustomerService> page = new Page<>();
			int pageNo=1;
			try {
				pageNo=Integer.valueOf(pageNo);
			} catch (Exception e) {}
			page.setPageNo(pageNo);
			page.setTotalElments(totalElement);
			int firstIndex=(pageNo-1)*page.getPageSize()+1;
			int endIndex=firstIndex+page.getPageSize();
			mybatisParams.put("firstIndex", firstIndex);
			mybatisParams.put("endIndex", endIndex);
			List<CustomerService> content=csm.getFeedbackContent(mybatisParams);
			page.setContent(content);
			return page;
	}

	public void feedback(com.atguigu.crm.entity.CustomerService service) {
		// TODO Auto-generated method stub
		csm.feedBack(service);
	}

	public Page<com.atguigu.crm.entity.CustomerService> getArchivePage(
			String pageNoStr, Map<String, Object> params) throws ParseException {
		Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
		long totalElement=csm.getArchiveTotalElment(mybatisParams);
		Page<CustomerService> page = new Page<>();
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNo);
		} catch (Exception e) {}
		page.setPageNo(pageNo);
		page.setTotalElments(totalElement);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<CustomerService> content=csm.getArchiveContent(mybatisParams);
		page.setContent(content);
		return page;
}
}
