package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.orm.Page;

@Service
public class StorageService {

	
	@Autowired
	private StorageMapper sm;
	@Autowired
	private ProductMapper pm;
	
	@Transactional(readOnly=true)
	public  Page<Storage> getPage(String pageNoStr){
		int totalelment = sm.getTotalelment();
		int pageNo=1;
		if(pageNoStr!=null){
			pageNo=Integer.valueOf(pageNoStr);
		}
		Page<Storage> page=new Page<>();
		page.setTotalElments(totalelment);
		page.setPageNo(pageNo);
		
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		
		Map<String, Object> map=new HashMap<>();
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		
		List<Storage> content = sm.getContent(map);
		/*for (Storage storage : content) {
			Product product = pm.get(storage.getProduct().getId());
			storage.setProduct(product);
		}*/
		page.setContent(content);
		
		return page;
	}

	public void save(Storage storage) {
		// TODO Auto-generated method stub
		sm.save(storage);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		sm.delete(id);
	}

	public Storage get(Long id) {
		// TODO Auto-generated method stub
		return sm.get(id);
	}

	public void updateStockCount(Storage storage) {
		// TODO Auto-generated method stub
		sm.updateStockCount(storage);
	}
}
