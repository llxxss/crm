package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.User;

public interface UserMapper {

	User getUserByName(@Param("name") String name);

	long getTotalCount();

	List<User> getList(Map<String, Object> map);

	void save(User user);

	void delete(@Param("id") long id);

	User get(@Param("id") long id);

	void update(User user);

	List<User> getAllList();
}
