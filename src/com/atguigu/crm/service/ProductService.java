package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ProductService {

	@Autowired
	private ProductMapper pm;
	
	@Transactional(readOnly=true)
	public List<Product> getAll(){
		return pm.getAll();
	}

	public Page<Product> getPage(String pageNoStr) {
		// TODO Auto-generated method stub
		int pageNo=1;
		
		if(pageNoStr!=null){
			pageNo=Integer.valueOf(pageNoStr);
		}
		
		long totalCount=pm.getTotalCount();
		Page<Product> page=new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElments(totalCount);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		List<Product> totalElment=pm.getElment(firstIndex,endIndex);
		page.setContent(totalElment);
		System.out.println(page);
		return page;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		pm.save(product);
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		pm.delete(id);
	}

	public Product get(Long id) {
		// TODO Auto-generated method stub
		Product product = pm.get(id);
		return product;
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		pm.update(product);
	}
}
