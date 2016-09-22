package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerService;

public interface CustomerServiceMapper {

	List<String> getServiceType();

	void save(CustomerService customerService);

	long getAllotTotalElment(Map<String, Object> mybatisParams);

	List<CustomerService> getAllotContent(Map<String, Object> mybatisParams);

	void allot(Map<String, Object> map);

	void delete(@Param("id")Long id);

	CustomerService get(@Param("id")Long id);

	void deal(CustomerService service);

	long getDealTotalElment(Map<String, Object> mybatisParams);

	List<CustomerService> getDealContent(Map<String, Object> mybatisParams);

	long getFeedbackTotalElment(Map<String, Object> mybatisParams);

	List<CustomerService> getFeedbackContent(Map<String, Object> mybatisParams);

	void feedBack(CustomerService service);

	long getArchiveTotalElment(Map<String, Object> mybatisParams);

	List<CustomerService> getArchiveContent(Map<String, Object> mybatisParams);

}
