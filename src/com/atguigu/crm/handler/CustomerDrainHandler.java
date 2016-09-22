package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerDrainService;
import com.atguigu.crm.utils.CRMUtils;
@RequestMapping("/drain")
@Controller
public class CustomerDrainHandler {

	@Autowired
	private CustomerDrainService cds;
	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public String confirm(CustomerDrain drain){
		drain.setDrainDate(new Date());
		cds.confirm(drain);
		return "redirect:/drain/list";
	}
	
	@RequestMapping(value="/confirm/{id}",method=RequestMethod.GET)
	public String toConfirm(@PathVariable("id") Long id,Map<String,Object> map){
		CustomerDrain drain = cds.get(id);
		map.put("drain",drain);
		return "drain/confirm";
	}
	
	/*@RequestMapping(value="/delay/",method=RequestMethod.POST)
	public String delay(CustomerDrain drain){
		cds.delay(drain);
		return "redirect:/drain/delay/"+drain.getId();
	}*/
	
	
	@ResponseBody
	@RequestMapping(value="/delay/",method=RequestMethod.POST)
	public String delay(@RequestParam("id") Long id,@RequestParam("delay") String delay){
		CustomerDrain drain = new CustomerDrain();
		drain.setId(id);
		drain.setDelay(delay);
		cds.delay(drain);
		CustomerDrain customerDrain = cds.get(id);
		int length = customerDrain.getDelay().split("`").length;
		return ""+length;
	}
	
	
	@RequestMapping(value="/delay/{id}",method=RequestMethod.GET)
	public String toDelay(@PathVariable("id") Long id,Map<String,Object> map){
		CustomerDrain drain= cds.get(id);
		System.out.println(drain);
		map.put("drain", drain);
		return "drain/delay";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			Map<String, Object> map,HttpServletRequest request) throws ParseException{
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		map.put("queryString", queryString);
		Page<CustomerDrain> page=cds.getPage(params,pageNoStr);
		map.put("page", page);
		return "drain/list";
	}
}
