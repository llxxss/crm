package com.atguigu.crm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.ReportMapper;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.mapper.UserMapper;

public class ApplicationContentTest {

	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	private SalesChanceMapper salesChanceMapper=ioc.getBean(SalesChanceMapper.class);
	private StorageMapper sm=ioc.getBean(StorageMapper.class);
	private UserMapper um=ioc.getBean(UserMapper.class);
	private ReportMapper rp=ioc.getBean(ReportMapper.class);
	@Test
	public void testRe(){
		List<Map<String,Object>> content = rp.getCustomerConsistContent("satify");
		System.out.println(content);
		long element = rp.getCustomerConsistTotalElement("satify");
		System.out.println(element);
	}
	
	@Test
	public void testUser(){
		User user = um.getUserByName("bcde");
		System.out.println(user.getName());
	}
	@Test
	public void testgetotalStorage(){
		int totalelment = sm.getTotalelment();
		System.out.println(totalelment);
	}
	
	@Test
	public void testGetContent(){
		Map<String, Object> map=new HashMap<>();
		map.put("firstIndex", 1);
		map.put("endIndex", 4);
		User createBy=new User();
		createBy.setId(21L);
		map.put("createBy", createBy);
		
		List<SalesChance> list = salesChanceMapper.getContent(map);
		System.out.println(list);
	}
	
	@Test
	public void totalElement(){
		User creatBy=new User();
		creatBy.setId(24L);
		long totalElment = salesChanceMapper.getTotalElment(creatBy);
		System.out.println(totalElment);
	}
}
