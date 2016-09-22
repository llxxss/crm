package com.atguigu.crm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.mapper.SalesPlanMapper;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper spm;
	
	public long save(SalesPlan salesPlan) {
		spm.save(salesPlan);
		return spm.getBytodo(salesPlan.getTodo());
	}

	public void update(SalesPlan salesPlan) {
		// TODO Auto-generated method stub
		spm.update(salesPlan);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		spm.delete(id);
	}

}
