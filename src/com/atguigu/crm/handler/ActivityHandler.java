package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ActivityServicr;
import com.atguigu.crm.service.CustomerService;

@RequestMapping("/activity")
@Controller
public class ActivityHandler {
	
	@Autowired
	private ActivityServicr as;
	@Autowired
	private CustomerService cs;
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,@RequestParam("customerid") Long customerid){
		as.delete(id);
		return "redirect:/activity/list?customerid="+customerid;
	}
	
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(CustomerActivity activity){
		Long id = activity.getCustomer().getId();
		System.out.println("id="+id);
		as.update(activity);
		return "redirect:/activity/list?customerid="+activity.getCustomer().getId();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id,Map<String, Object> map){
		CustomerActivity activity=as.get(id);
		map.put("activity", activity);
		System.out.println(activity);
		return "activity/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String input(CustomerActivity activity){
		as.save(activity);
	
		return "redirect:/activity/list?customerid="+activity.getCustomer().getId();
		
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toInput(@RequestParam("customerid") Long id,
			Map<String,Object> map){
		CustomerActivity activity = new CustomerActivity();
		Customer customer = new Customer();
		customer.setId(id);
		activity.setCustomer(customer);
		map.put("activity", activity);
		return "activity/input";
	}
	
	@RequestMapping(value="/list")
	public String list(@RequestParam("customerid") Long id,Map<String,Object> map,
			@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		Customer customer = cs.get(id);
		map.put("customer", customer);
		Page<CustomerActivity> page=as.getPage(id,pageNoStr);
		map.put("page", page);
		return "activity/list";
		
	}
}
