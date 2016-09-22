package com.atguigu.crm.mapper;

import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	List<SalesPlan> getList(@Param("id") Long id);

	void save(SalesPlan salesPlan);

	void update(SalesPlan salesPlan);

	void delete(@Param("id") Long id);

	long getBytodo(@Param("todo") String todo);


	
}
