package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mapper.CustomerDrainMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class CustomerDrainService {

	@Autowired
	private CustomerDrainMapper drainMapper;
	
	public void callCheckCustomerDrain(){
		drainMapper.callCheckCustomerDrain();
	}

	public Page<CustomerDrain> getPage(Map<String, Object> params,
			String pageNoStr) throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mybatisParams = CRMUtils.paramsToMybatisParams(params);
		long totalElment=drainMapper.getTotalElment(mybatisParams);
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<CustomerDrain> page=new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElments(totalElment);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<CustomerDrain> content=drainMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public CustomerDrain get(Long id) {
		// TODO Auto-generated method stub
		return drainMapper.get(id);
	}

	public void delay(CustomerDrain drain) {
		// TODO Auto-generated method stub
		CustomerDrain customerDrain = drainMapper.get(drain.getId());
		if(customerDrain.getDelay()!=null){
			drain.setDelay(customerDrain.getDelay()+"`"+drain.getDelay());
		}
		drainMapper.delay(drain);
	}

	public void confirm(CustomerDrain drain) {
		// TODO Auto-generated method stub
		System.out.println(drain.getDrainDate());
		drainMapper.confirm(drain);
	}
}
