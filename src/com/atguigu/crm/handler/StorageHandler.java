package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.service.StorageService;
@RequestMapping("/storage")
@Controller
public class StorageHandler {

	@Autowired
	private StorageService ss;
	@Autowired
	private ProductService ps;
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id") Long id,Storage storage,
			@RequestParam("incrementCount") int incrementCount){
		int stockCount = storage.getStockCount();
		storage.setStockCount(stockCount+incrementCount);
		ss.updateStockCount(storage);
		return "redirect:/storage/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toUpdate(Map<String,Object> map,@PathVariable("id") Long id ){
		Storage storage=ss.get(id);
		map.put("storage", storage);
		List<Product> list = ps.getAll();
		map.put("products", list);
		return "storage/input";
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		System.out.println(id);

		return "redirect:/storage/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Storage storage){
		System.out.println(storage);
		ss.save(storage);
		
		return "redirect:/storage/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toSave(Map<String,Object> map){
		Storage s=new Storage();
		map.put("storage", s);
		List<Product> list = ps.getAll();
		map.put("products", list);
		return "storage/input";
	}
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		Page<Storage> page = ss.getPage(pageNoStr);
		
		map.put("page", page);
		return "storage/list";
	}
	
}
