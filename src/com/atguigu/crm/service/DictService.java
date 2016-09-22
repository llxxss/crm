package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mapper.DictMapper;

@Service
public class DictService {

	@Autowired
	private DictMapper dm;
	
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		return dm.getRegions();
	}

	public List<String> getLevels() {
		// TODO Auto-generated method stub
		return dm.getLevels();
	}

	public List<String> getsatisfies() {
		// TODO Auto-generated method stub
		return dm.getsatisfies();
	}

	public List<String> getcredits() {
		// TODO Auto-generated method stub
		return dm.getcredits();
	}

}
