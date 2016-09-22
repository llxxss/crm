package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Page;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String name,String password){
		User user = userMapper.getUserByName(name);
		if(user!=null
				&&user.getEnabled()==1
				&& password.equals(user.getPassword())){
			return user;
		}
		return null;
	}


	public Page<User> getPage(String pageNoStr) {
		// TODO Auto-generated method stub
		int pageNo=1;
		if(pageNoStr!=null){
			pageNo=Integer.valueOf(pageNoStr);
		}
		long totalCount=userMapper.getTotalCount();
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);
		page.setTotalElments(totalCount);
		Map<String, Object> map=new HashMap<>();
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=pageNo+page.getPageSize();
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		List<User> list=userMapper.getList(map);
		page.setContent(list);
		return page;
	}


	public void save(User user) {
		UUID uuid = UUID.randomUUID();
		String string = uuid.toString();
		String[] split = string.split("-");
		StringBuffer sb=new StringBuffer();
		for (String string2 : split) {
			sb.append(string2);
		}
		user.setSalt(sb.substring(0, 16));
		userMapper.save(user);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		userMapper.delete(id);
	}


	public User get(long id) {
		// TODO Auto-generated method stub
		return userMapper.get(id);
	}


	public void update(User user) {
		// TODO Auto-generated method stub
		userMapper.update(user);
	}


	public List<User> getlist() {
		// TODO Auto-generated method stub
	
		return 	userMapper.getAllList();
	}
	
}
