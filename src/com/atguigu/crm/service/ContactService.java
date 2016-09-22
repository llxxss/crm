package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ContactService {

	@Autowired
	private ContactMapper cm;

	public List<Contact> getManagers(Long id) {
		// TODO Auto-generated method stub
		return cm.getManagers(id);
	}

	public List<Contact> getListByid(Long id) {
		// TODO Auto-generated method stub
		return cm.getListByid(id);
	}

	public Page<Contact> getpage(String pageNoStr,Long id) {
		Page<Contact> page=new Page<>();
		int pageNo=1;
		try {
			pageNo=Integer.valueOf(pageNoStr);
		} catch (Exception e) {}
		page.setPageNo(pageNo);
		long totalElment=cm.getTotalElment(id);
		page.setTotalElments(totalElment);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		List<Contact> content=cm.getContent(map);
		page.setContent(content);
		return page;
	}

	public void save(Contact contact) {
		// TODO Auto-generated method stub
		cm.saveContact(contact);
	}

	public Contact get(Long id) {
		// TODO Auto-generated method stub
		return cm.get(id);
	}

	public void update(Contact contact) {
		// TODO Auto-generated method stub
		cm.update(contact);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		cm.delete(id);
	}
}
