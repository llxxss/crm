package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.mapper.SalesPlanMapper;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;
@Service
public class SalesChanceService {
	
	
	
	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Autowired
	private UserMapper um;
	
	@Autowired
	private SalesPlanMapper pm;
	
	@Autowired
	private CustomerMapper cm;
	
	@Autowired
	private ContactMapper contactMapper;
	
	@Transactional
	public SalesChance get(long id){
		SalesChance salesChance = salesChanceMapper.get(id);
		return salesChance;
	}

	@Transactional
	public void save(SalesChance salesChance){
		salesChanceMapper.save(salesChance);
	}
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(User user,String pageNoStr){
		int pageNo=1;
		if(pageNoStr!=null){
			pageNo=Integer.valueOf(pageNoStr);
		}
		System.out.println(user);
		long totalElments = salesChanceMapper.getTotalElment(user);
		
		Page<SalesChance> page=new Page<>();
		
		page.setTotalElments(totalElments);
		
		page.setPageNo(pageNo);
		
		Map<String, Object> map=new HashMap<>();
		
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		map.put("createBy", user);
		
		List<SalesChance> content = salesChanceMapper.getContent(map);
		
		page.setContent(content);
		
		return page;
	}

	public void update(SalesChance chance) {
		// TODO Auto-generated method stub
		salesChanceMapper.update(chance);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		salesChanceMapper.delete(id);
	}

	public Page<SalesChance> getPageAll(String pageNoStr) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	public SalesChance getAll(Long chanceId) {
		// TODO Auto-generated method stub
		SalesChance chance = salesChanceMapper.get(chanceId);
		User createBy = chance.getCreateBy();
		User user = um.get(createBy.getId());
		chance.setCreateBy(user);
		
		User designee = chance.getDesignee();
		if(designee!=null){
			
			User user2 = um.get(designee.getId());
			chance.setDesignee(user2);
			
		}
		 List<SalesPlan> list = pm.getList(chance.getId());
		 System.out.println(list);
		 Set<SalesPlan> salesPlans=new HashSet<>(list);
		
		
		chance.setSalesPlans(salesPlans);
		
		return chance;
	}

	public void finish(Long chanceId) {
		// TODO Auto-generated method stub
		SalesChance salesChance =salesChanceMapper.get(chanceId);
		salesChance.setId(chanceId);
		salesChance.setStatus(3);
		salesChanceMapper.update(salesChance);
		Customer customer = new Customer();
		String custName = salesChance.getCustName();
		customer.setName(custName);
		customer.setNo(UUID.randomUUID().toString());
		customer.setState("正常");
		cm.save(customer);
		
		Contact contact = new Contact();
		String contactName = salesChance.getContact();
		contact.setName(contactName);
		contact.setTel(salesChance.getContactTel());
		
		contactMapper.save(contact);
	}

	@Transactional
	public void stop(Long chanceId) {
		// TODO Auto-generated method stub
		salesChanceMapper.stop(chanceId);
	}

	public void dispath(SalesChance chance) {
		// TODO Auto-generated method stub\
		chance.setDesigneeDate(new Date());
		chance.setStatus(2);
		salesChanceMapper.dispath(chance);
	}

	public Page<SalesChance> getPageChance(String pageNoStr) {
		// TODO Auto-generated method stub
		int pageNo=1;
		if(pageNoStr!=null){
			pageNo=Integer.valueOf(pageNoStr);
		}
		Page<SalesChance> page=new Page<>();
		page.setPageNo(pageNo);
		long totalElemnt=salesChanceMapper.getChanceTotalConut();
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		Map<String,Object> map=new HashMap<>();
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		List<SalesChance> list=salesChanceMapper.getChanceList(map);
		page.setContent(list);
		page.setTotalElments(totalElemnt);
		return page;
	}

	public Page<SalesChance> getPage2(User user, String pageNoStr,
			Map<String, Object> params) throws ParseException {
		
		Map<String, Object> myBatisParams=CRMUtils.paramsToMybatisParams(params);
		
		myBatisParams.put("user", user);
		
		System.out.println(myBatisParams);
		
		int pageNo=1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		Page<SalesChance> page=new Page<>();
		page.setPageNo(pageNo);
		
		long totalElemnt=salesChanceMapper.getChanceTotalConut2(myBatisParams);
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		
		myBatisParams.put("firstIndex", firstIndex);
		myBatisParams.put("endIndex", endIndex);
		List<SalesChance> list=salesChanceMapper.getChanceList2(myBatisParams);
		page.setContent(list);
		page.setTotalElments(totalElemnt);
		
		return page;
	}

	
}
