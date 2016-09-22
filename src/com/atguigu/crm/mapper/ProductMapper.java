package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Product;

public interface ProductMapper {

	Product get(@Param("id") Long id);
	
	List<Product> getAll();

	long getTotalCount();

	List<Product> getElment(int firstIndex, int endIndex);

	void save(Product product);

	void delete(@Param("id") Long id);

	void update(Product product);
}
