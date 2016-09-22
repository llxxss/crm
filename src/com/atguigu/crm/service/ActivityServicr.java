package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.mapper.ActivityMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ActivityServicr {

	@Autowired
	private ActivityMapper am;

	public Page<CustomerActivity> getPage(Long id, String pageNoStr) {
		// TODO Auto-generated method stub
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<CustomerActivity> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(5);
		long totalElment=am.getTotalElemnt(id);
		page.setTotalElments(totalElment);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		List<CustomerActivity> content=am.getContent(map);
		page.setContent(content);
		
		return page;
	}

	public void save(CustomerActivity activity) {
		// TODO Auto-generated method stub
		am.save(activity);
	}

	public CustomerActivity get(Long id) {
		// TODO Auto-generated method stub
		return am.get(id);
	}

	public void update(CustomerActivity activity) {
		// TODO Auto-generated method stub
		am.update(activity);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		am.delete(id);
	}
}
