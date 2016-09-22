package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mapper.RoleMapper;
import com.atguigu.crm.orm.Page;

@Service
public class RoleService {

	@Autowired
	private RoleMapper rm;

	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return rm.getAll();
	}

	public Page<Role> getPage(String pageNoStr) {
		// TODO Auto-generated method stub
		int pageNo=1;
		if(pageNoStr!=null){
			pageNo=Integer.valueOf(pageNoStr);
		}
		long totalCont=rm.getToealCount();
		Page<Role> page=new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElments(totalCont);
		Map<String, Object> map=new HashMap<>();
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		List<Role> list=rm.getList(map);
		page.setContent(list);
		
		return page;
	}

	public Role get(Long id) {
		// TODO Auto-generated method stub
		return rm.get(id);
	}

	public void updateAuth(Role role) {
		// TODO Auto-generated method stub
		rm.deleteAuth(role.getId());
		rm.updateAuth(role);
	}
}
