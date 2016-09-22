package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {
	
	
	@Autowired
	private SalesChanceService scs;
	@Autowired
	private UserService us;
	
	@RequestMapping(value="/dispatch/{chanceId}",method=RequestMethod.PUT)
	public String dispath(SalesChance chance,@PathVariable("chanceId") Long chanceId){
		chance.setId(chanceId);
		scs.dispath(chance);
		
		return "redirect:/chance/list"; 
	}
	
	
	@RequestMapping(value="/dispatch/{chanceId}",method=RequestMethod.GET)
	public String toDispath(@PathVariable("chanceId") Long chanceId,
			Map<String, Object> map){
		SalesChance chance = scs.getAll(chanceId);
		map.put("chance", chance);
		List<User> users = us.getlist();
		map.put("users", users);
		return "chance/dispatch";
	}
	
	@RequestMapping(value="/stop/{chanceId}",method=RequestMethod.GET)
	public String stop(@PathVariable("chanceId") Long chanceId){
		scs.stop(chanceId);
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/finish/{chanceId}",method=RequestMethod.PUT)
	public String finish(@PathVariable("chanceId") Long chanceId){
		scs.finish(chanceId);
		return "redirect:/chance/list";
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		scs.delete(id);
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id") Long id,
			SalesChance chance){
		scs.update(chance);
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id,
			Map<String,Object> map){
		SalesChance chance = scs.get(id);
		map.put("chance", chance);
		return "chance/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(HttpSession session,SalesChance chance){
		User user = (User) session.getAttribute("user");
		chance.setCreateBy(user);
		scs.save(chance);
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toSave(Map<String,Object> map){
		SalesChance chance=new SalesChance();
		chance.setCreateDate(new Date());
		map.put("chance", chance);
		return "chance/input";
	}

	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			HttpSession session,Map<String,Object> map,HttpServletRequest request) throws ParseException{
		
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request,prefix);
		
		String queryString = CRMUtils.encodeParamsToQueryString(params,prefix);
		map.put("queryString", queryString);
		
		User user = (User) session.getAttribute("user");
		Page<SalesChance> page = scs.getPage2(user, pageNoStr,params);
		map.put("page", page);
		
		return "chance/list";
	}
	
	/*@RequestMapping("/list")
	public String list(@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			HttpSession session,Map<String,Object> map){
		User user = (User) session.getAttribute("user");
		Page<SalesChance> page = scs.getPage(user, pageNoStr);
		map.put("page", page);
		
		return "chance/list";
	}*/
}
