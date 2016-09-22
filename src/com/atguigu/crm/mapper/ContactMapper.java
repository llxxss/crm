package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {

	void save(Contact contact);

	List<Contact> getManagers(@Param("id") Long id);

	List<Contact> getListByid(Long id);

	long getTotalElment(@Param("id") Long id);

	List<Contact> getContent(Map<String, Object> map);

	void saveContact(Contact contact);

	Contact get(@Param("id")Long id);

	void update(Contact contact);

	void delete(@Param("id")Long id);

}
