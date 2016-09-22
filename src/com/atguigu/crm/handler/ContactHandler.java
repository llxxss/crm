package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;
@RequestMapping("/contact")
@Controller
public class ContactHandler {

	@Autowired
	private ContactService cs;
	@Autowired
	private CustomerService ts;
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,@RequestParam("customerid") Long customerId){
		cs.delete(id);
		System.out.println(customerId);
		return "redirect:/contact/list/"+customerId;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id") Long id,Contact contact){
		contact.setId(id);
		cs.update(contact);
		return "redirect:/contact/list/"+contact.getCustomer().getId();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id,Map<String, Object> map){
		Contact contact=cs.get(id);
		map.put("contact", contact);
		return "contact/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String input(Contact contact){
		cs.save(contact);
		
		return "redirect:/contact/list/"+contact.getCustomer().getId();
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toInput(@RequestParam("customerid") Long customerId,
			Map<String,Object> map){
		Contact contact = new  Contact();
		Customer customer = new Customer();
		customer.setId(customerId);
		contact.setCustomer(customer);
		map.put("contact", contact);
		return "contact/input";
	}
	
	@RequestMapping(value="/list/{id}",method=RequestMethod.GET)
	public String toList(@PathVariable Long id,Map<String, Object> map,@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		Page<Contact> page=cs.getpage(pageNoStr,id);
		map.put("page", page);
		System.out.println(page);
		Customer customer = ts.get(id);
		map.put("customer", customer);
		return "contact/list";
	}
}
