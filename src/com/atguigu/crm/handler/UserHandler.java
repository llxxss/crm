package com.atguigu.crm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Navigation;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.RoleService;
import com.atguigu.crm.service.UserService;
@RequestMapping("/user")
@Controller
public class UserHandler {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService rs;
	@Autowired
	private UserMapper um;
	
	@ResponseBody
	@RequestMapping("/navigation")
	public List<Navigation> getMemu(HttpSession session){
		List<Navigation> navigations = new ArrayList<>();
		Navigation navigation=new Navigation();
		navigation.setId(Long.MAX_VALUE);
		navigation.setText("客户关系管理系统");
		String path = session.getServletContext().getContextPath();
		/*Navigation navigation2 = new Navigation();
		navigation2.setId(1L);
		navigation2.setText("客户管理");
		navigation.getChildren().add(navigation2);
		navigations.add(navigation);*/
		User user = (User) session.getAttribute("user");
		List<Authority> authorities = user.getRole().getAuthorities();
		HashMap<Long, Navigation> parentNavigation = new HashMap<>();
		
		for (Authority authority : authorities) {
			Navigation navigation2 = new Navigation();
			navigation2.setId(authority.getId());
			navigation2.setText(authority.getName());
			navigation2.setUrl(path+authority.getUrl());
			
			Authority pa = authority.getParentAuthority();
			Navigation pn = parentNavigation.get(pa.getId());
			
			if(pn==null){
				pn=new Navigation();
				pn.setId(pa.getId());
				pn.setText(pa.getName());
				pn.setState("closed");
				navigation.getChildren().add(pn);
				parentNavigation.put(pa.getId(), pn);
				
			}
			pn.getChildren().add(navigation2);
		}
		navigations.add(navigation);
		return navigations;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(User user){
		userService.update(user);
		return "redirect:/user/list";
	}	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(Map<String, Object> map,
			@PathVariable("id") long id){
		User user=userService.get(id);
		map.put("user", user);
		List<Role> list=rs.getAll();
		map.put("roles", list);
		List<Integer> allStatus=new ArrayList<>();
		allStatus.add(0);
		allStatus.add(1);
		map.put("allStatus", allStatus);
		return "user/input";
	}
	
	
	public String delete(@PathVariable("id") long id){
		userService.delete(id);
		return "redirect:/user/list";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(User user){
		userService.save(user);
		return "redirect:/user/list";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toSave(Map<String, Object> map){
		User user = new User();
		map.put("user", user);
		List<Role> list=rs.getAll();
		map.put("roles", list);
		List<Integer> allStatus=new ArrayList<>();
		allStatus.add(0);
		allStatus.add(1);
		map.put("allStatus", allStatus);
		return "user/input";
	}
	
	@RequestMapping(value="/list")
	public String list(Map<String, Object> map,
			@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		Page<User> page=userService.getPage(pageNoStr);
		map.put("page", page);
		return "user/list";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam(value="name",required=false) String name,
			@RequestParam(value="password",required=false) String password,
			HttpSession session){
		/*User user = userService.login(name, password);
		if(user!=null){
			session.setAttribute("user", user);
			return "home/success";
		}
				return "redirect:/home/index.jsp";*/
		
		
		//--------------------------------------------------------------
		Subject currentUser=SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			System.out.println("--------------"+currentUser);
			UsernamePasswordToken token=new UsernamePasswordToken(name, password);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (AuthenticationException ae) {
				System.out.println("登录失败"+ae.getMessage());
				return "redirect:/index.jsp";
			}
		}
		Object user = currentUser.getPrincipals().getPrimaryPrincipal();
		session.setAttribute("user", user);
		return "home/success";
	}
}
