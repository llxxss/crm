package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerActivity;

public interface ActivityMapper {

	long getTotalElemnt(@Param("id") Long id);

	List<CustomerActivity> getContent(Map<String, Object> map);

	void save(CustomerActivity activity);

	CustomerActivity get(@Param("id") Long id);

	void update(CustomerActivity activity);

	void delete(@Param("id") Long id);

}
