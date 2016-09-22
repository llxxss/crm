package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mapper.OrderMapper;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.orm.Page;

@Service
public class OrderService {

	@Autowired
	private OrderMapper om;
	@Autowired
	private ProductMapper pm;

	public Page<Order> getPage(Long customerId, String pageNoStr) {
		// TODO Auto-generated method stub
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNo);
		} catch (Exception e) {}
		long totalElments=om.getTotalElments(customerId);
		Page<Order> page = new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElments(totalElments);
		
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		HashMap<String,Object> map = new HashMap<>();
		map.put("customerId", customerId);
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		
		List<Order> content=om.getContent(map);
		page.setContent(content);
		return page;
	}

	public Order getOrder(Long orderid) {
		// TODO Auto-generated method stub
		Order order = om.get(orderid);
		
		Set<OrderItem> items = 
		order.getItems();
		
		for (OrderItem orderItem : items) {
			Long id = orderItem.getProduct().getId();
			Product product = pm.get(id);
			orderItem.setProduct(product);
		}
		order.setItems(items);
		return order;
	}
}
