package com.atguigu.crm.handler;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.OrderService;

@RequestMapping("/order")
@Controller
public class OrderHandler {

	@Autowired
	private OrderService os;
	
	@RequestMapping("/details/{orderid}")
	public String detail(@PathVariable("orderid") Long orderid,
			Map<String, Object> map){
		Order order=os.getOrder(orderid);
		map.put("order", order);
		return "order/details";
		
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam("customerid") Long customerId,
			Map<String, Object> map,
			@RequestParam(value="pageNoStr",required=false) String pageNoStr){
		Page<Order> page=os.getPage(customerId,pageNoStr);
		map.put("page", page);
		return "order/list";
	}
}
