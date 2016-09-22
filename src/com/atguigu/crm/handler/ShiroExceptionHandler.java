package com.atguigu.crm.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShiroExceptionHandler {

	@ExceptionHandler({org.apache.shiro.authz.UnauthorizedException.class})
	public String handlerShiroException(Exception e){
		System.out.println("出异常了"+e.getMessage());
		return "redirect:/unauthorized.jsp";
	}
}
