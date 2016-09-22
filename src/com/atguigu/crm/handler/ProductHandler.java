package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;


@RequestMapping("/product")
@Controller
public class ProductHandler {

	@Autowired
	private ProductService ps;
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(Product product){
		ps.update(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id,
			Map<String,Object> map){
		Product product = ps.get(id);
		map.put("product", product);
		return "product/input";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		ps.deleteById(id);
		return "redirect:/product/list";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Product product){
		
		ps.save(product);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toSave(Map<String,Object> map){
		Product product = new Product();
		map.put("product", product);
		return "product/input";
	}
	
	
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,
				@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		System.out.println(pageNoStr);
		Page<Product> page=ps.getPage(pageNoStr);
		map.put("page", page);
		return "product/list";
	}
	
}
