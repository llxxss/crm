package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerDrain;

public interface ReportMapper {

	long getCustomerPayTotalElment(Map<String, Object> mybatisParams);

	List<Map<String, Object>> getCustomerPayContent(
			Map<String, Object> mybatisParams);

	List<Map<String, Object>> getCustomerConsistContent(
			@Param("type")String type);

	long getCustomerConsistTotalElement(@Param("type")String type);
	
	List<CustomerDrain> getCustomerDrainContent(Map<String, Object> map);
	
	long getCustomerDrainTotalElment();

}
