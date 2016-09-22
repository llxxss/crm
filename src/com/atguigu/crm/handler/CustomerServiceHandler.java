package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.CustomerServiceService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/service")
@Controller
public class CustomerServiceHandler {

	@Autowired
	private CustomerServiceService css;
	@Autowired
	private CustomerService cs;
	@Autowired
	private UserService us;
	
	@RequestMapping("/create")
	public String toinput(Map<String, Object> map) {
		List<Customer> customer = cs.getAll();
		List<String> serviceTypes=css.getServiceType();
		map.put("customers", customer);
		map.put("serviceTypes", serviceTypes);
 		return "service/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String input(com.atguigu.crm.entity.CustomerService customerService,HttpSession session){
		User createBy = (User) session.getAttribute("user");
		customerService.setCreatedby(createBy);
		customerService.setCreateDate(new Date());
		System.out.println(cs);
		css.save(customerService);
		return "redirect:/service/allot/list";
	}
	
	@RequestMapping(value="/allot/list")
	public String Allotlist(Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam( value="pageNoStr",required=false) String pageNoStr) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<com.atguigu.crm.entity.CustomerService> page=css.getAllotPage(pageNoStr,params);
		map.put("page", page);
		List<User> users=us.getlist();
		map.put("users", users);
		map.put("queryString", queryString);
		return "service/allot/list";
		
	}
	@ResponseBody
	@RequestMapping("/allot")
	public String allot(@RequestParam("id") Long id,@RequestParam("allotId") Long allotId){
		css.allot(id,allotId);
		return "1";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		css.delete(id);
		return "redirect:/service/allot/list";
	}
	@RequestMapping(value="/deal/list")
	public String todeal(Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam( value="pageNoStr",required=false) String pageNoStr) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<com.atguigu.crm.entity.CustomerService> page=css.getDealPage(pageNoStr,params);
		map.put("page", page);
		List<User> users=us.getlist();
		map.put("users", users);
		map.put("queryString", queryString);
		return "service/deal/list";
	}
	@RequestMapping(value="/deal/{id}",method=RequestMethod.GET)
	public String toDeal(Map<String, Object> map,@PathVariable("id") Long id){
		com.atguigu.crm.entity.CustomerService service=css.get(id);
		map.put("service", service);
		return "service/deal/deal";
		
	}
	@RequestMapping(value="/deal",method=RequestMethod.PUT)
	public String deal(com.atguigu.crm.entity.CustomerService service){
		service.setDealDate(new Date());
		css.deal(service);
		return "redirect:/service/deal/list";
	}
	@RequestMapping(value="/feedback/list")
	public String toFeedback(Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam( value="pageNoStr",required=false) String pageNoStr) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<com.atguigu.crm.entity.CustomerService> page=css.getFeekbackPage(pageNoStr,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "service/feedback/list";
	}
	@RequestMapping("/feedback/{id}")
	public String toFeedBack(@PathVariable("id") Long id,
			Map<String, Object> map){
		com.atguigu.crm.entity.CustomerService service = css.get(id);
		map.put("service", service);
		return "service/feedback/feedback";
	}
	@RequestMapping(value="/feedback",method=RequestMethod.PUT)
	public String feedback(com.atguigu.crm.entity.CustomerService service){
		css.feedback(service);
		return "redirect:/service/feedback/list";
	}
	
	@RequestMapping(value="/archive/list")
	public String toarchice(Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam( value="pageNoStr",required=false) String pageNoStr) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<com.atguigu.crm.entity.CustomerService> page=css.getArchivePage(pageNoStr,params);
		map.put("page", page);
		List<User> users=us.getlist();
		map.put("users", users);
		map.put("queryString", queryString);
		return "service/archive/list";
	}
	
	@RequestMapping("/archive/{id}")
	public String toAchive(@PathVariable("id") Long id,
			Map<String, Object> map){
		com.atguigu.crm.entity.CustomerService service = css.get(id);
		map.put("service", service);
		return "service/archive/archive";
	}
	
}
