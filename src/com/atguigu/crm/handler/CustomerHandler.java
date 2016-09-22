package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/customer")
@Controller
public class CustomerHandler {

	@Autowired
	private CustomerService cs;
	@Autowired
	private DictService ds;
	
	@Autowired
	private ContactService contactService;
	
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		cs.delete(id);
		
		return "1";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String input(Customer customer,@PathVariable("id") Long id ){
		customer.setId(id);
		
		cs.update(customer);
		return "redirect:/customer/list";
	} 
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			HttpServletRequest request) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<Customer> page=cs.getPage(params,pageNoStr);
		
		map.put("page", page);
		map.put("queryString", queryString);
		List<String> regions=ds.getRegions();
		List<String> levels=ds.getLevels();
		map.put("regions", regions);
		map.put("levels", levels);
		return "customer/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toInput(@PathVariable("id") Long id,Map<String,Object> map){
			Customer customer=cs.get(id);
			map.put("customer", customer);
			
			List<String> regions=ds.getRegions();
			List<String> levels=ds.getLevels();
			//满意度
			List<String> satisfies=ds.getsatisfies();
			//信誉度
			List<String> credits=ds.getcredits();
			//客户经理列表
			List<Contact> managers=contactService.getManagers(id);
			
			map.put("managers", managers);
			map.put("satisfies", satisfies);
			map.put("credits", credits);
			map.put("regions", regions);
			map.put("levels", levels);
		return "customer/input";
	}
}
