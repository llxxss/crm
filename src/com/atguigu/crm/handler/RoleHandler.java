package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.AuthorityService;
import com.atguigu.crm.service.RoleService;


@RequestMapping("/role")
@Controller
public class RoleHandler {

	@Autowired
	private RoleService rs;
	@Autowired
	private AuthorityService as;
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(Role role,@PathVariable("id") Long id){
		
		rs.updateAuth(role);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String dispath(@PathVariable("id") Long id,
			Map<String, Object> map){
		Role role=rs.get(id);
		System.out.println(role);
		map.put("role", role);
		List<Authority> parentAuthorities=as.getPrentaAuth();
		map.put("parentAuthorities", parentAuthorities);
		return "role/assign";
	}
	
	@RequestMapping(value="/list")
	public String list(Map<String,Object> map,
			@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		Page<Role> page=rs.getPage(pageNoStr);
		map.put("page", page);
		return "role/list";
	}
}
