package com.atguigu.crm.factory;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mapper.RoleMapper;

public class FilterChainDefinitionMapBuilder {

	@Autowired
	private RoleMapper rm;
	
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map=new LinkedHashMap<>();
		map.put("/index", "anon");
		map.put("/user/login", "anon");
		map.put("/static/**", "anon");
		map.put("/user/navigation", "authc");
		
		List<Role> list = rm.getAll();
		for (Role role : list) {
			List<Authority> authorities = role.getAuthorities();
			for (Authority authority : authorities) {
				map.put(authority.getUrl(),"roles["+role.getName()+"]");
			}
		}
		map.put("/** ", "authc");
		
		
		return map;
	}
}
