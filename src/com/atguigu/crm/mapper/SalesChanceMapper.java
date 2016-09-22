package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;

public interface SalesChanceMapper {
	
	SalesChance get(@Param("id") long id);
	
	void save(SalesChance salesChance);

	long getTotalElment(@Param("creatBy") User creatBy);
	
	List<SalesChance> getContent(Map<String, Object> map);

	void update(SalesChance chance);

	void delete(@Param("id")Long id);

	void stop(@Param("chanceId")Long chanceId);

	void dispath(SalesChance salesChance);

	long getChanceTotalConut();


	List<SalesChance> getChanceList(Map<String, Object> map);
	
	//带查询条件的
	long getChanceTotalConut2(Map<String, Object> myBatisParams);

	List<SalesChance> getChanceList2(Map<String, Object> myBatisParams);

	
}
