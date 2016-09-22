package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Role;

public interface RoleMapper {

	List<Role> getAll();

	long getToealCount();

	List<Role> getList(Map<String, Object> map);

	Role get(@Param("id") Long id);

	void deleteAuth(@Param("id") Long id);

	void updateAuth(Role role);

}
