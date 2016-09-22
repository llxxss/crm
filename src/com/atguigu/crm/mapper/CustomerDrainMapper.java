/**
 * 
 */
package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerDrain;

/**
 * @author lixusong
 *
 */
public interface CustomerDrainMapper {

	@Update("{call check_drain()}")
	void callCheckCustomerDrain();

	long getTotalElment(Map<String, Object> mybatisParams);

	List<CustomerDrain> getContent(Map<String, Object> mybatisParams);

	CustomerDrain get(@Param("id") Long id);

	void delay(CustomerDrain drain);

	void confirm(CustomerDrain drain);

}
