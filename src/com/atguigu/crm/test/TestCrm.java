package com.atguigu.crm.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class TestCrm {

	@Test
	public void test() {
		UUID uuid = UUID.randomUUID();
		String string = uuid.toString();
		String[] split = string.split("-");
		StringBuffer sb=new StringBuffer();
		for (String string2 : split) {
			sb.append(string2);
		}
		System.out.println(sb.substring(0, 16));
	}

}
