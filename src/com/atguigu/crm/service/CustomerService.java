package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerMapper cm;


	public Customer get(Long id) {
		return cm.get(id);
	}

	public void update(Customer customer) {
		// TODO Auto-generated method stub
		cm.update(customer);
	}

	public Page<Customer> getPage(Map<String, Object> params, String pageNoStr) throws ParseException {
		
				Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
				long totalCount=cm.getAlltotalCount2(mybatisParams);
				System.out.println(mybatisParams);
				Page<Customer> page=new Page<>();
				int pageNo=1;
				try {
					pageNo=Integer.valueOf(pageNoStr);
				} catch (NumberFormatException e) {}
				page.setPageNo(pageNo);
				page.setTotalElments(totalCount);
				
				int firstIndex=(pageNo-1)*page.getPageSize()+1;
				int endIndex=firstIndex+page.getPageSize();
				
				
				mybatisParams.put("firstIndex", firstIndex);
				mybatisParams.put("endIndex", endIndex);
				
				List<Customer> list=cm.getTotalContent(mybatisParams);
				page.setContent(list);
				return page;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		cm.delete(id);
	}

	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return cm.getAll();
	}
	
}
