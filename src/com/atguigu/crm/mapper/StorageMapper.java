package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Storage;


public interface StorageMapper {

	
	int getTotalelment();
	List<Storage> getContent(Map<String, Object> map);
	void save(Storage storage);
	void delete(@Param("id") Long id);
	Storage get(@Param("id") Long id);
	void updateStockCount(Storage storage);
}
