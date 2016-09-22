package com.atguigu.crm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Order;

public interface OrderMapper {

	long getTotalElments(@Param("customerId")Long customerId);

	List<Order> getContent(HashMap<String, Object> map);

	Order get(@Param("orderid")Long orderid);

}
