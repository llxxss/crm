package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Customer;

public interface CustomerMapper {

	void save(Customer customer);

	long getAlltotalCount();

	List<Customer> getAllList(Map<String, Object> map);

	Customer get(@Param("id")Long id);

	void update(Customer customer);
	
	//带查询条件的分页
	long getAlltotalCount2(Map<String, Object> mybatisParams);

	List<Customer> getTotalContent(Map<String, Object> mybatisParams);

	void delete(@Param("id")Long id);

	List<Customer> getAll();

}
