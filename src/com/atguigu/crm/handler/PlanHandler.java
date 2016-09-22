package com.atguigu.crm.handler;

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

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.SalesPlanService;
import com.atguigu.crm.utils.CRMUtils;
@RequestMapping("/plan")
@Controller
public class PlanHandler {

	@Autowired
	private SalesChanceService scs;
	@Autowired
	private SalesPlanService sps;
	
	@RequestMapping("/chance/list")
	public String toChanceList(@RequestParam(value="pageNoStr",
			required=false) String pageNoStr,
			Map<String, Object> map){
		Page<SalesChance> page=scs.getPageChance(pageNoStr);
		map.put("page", page);
		return "plan/list";
	}
	
	@RequestMapping("/detail/{chanceId}")
	public String toDetails(@PathVariable("chanceId") Long id,
			Map<String, Object> map){
		SalesChance chance = scs.getAll(id);
		map.put("chance", chance);
		return "plan/detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/execute/{id}",method=RequestMethod.PUT)
	public String execute(@PathVariable("id") Long id,
			@RequestParam("result") String result){
		SalesPlan salesPlan = new SalesPlan();
		salesPlan.setId(id);
		salesPlan.setResult(result);
		sps.update(salesPlan);
		return "1";
	}
	
	@RequestMapping("/execute/{id}")
	public String toExecute(@PathVariable("id") Long id,Map<String, Object> map){
		SalesChance chance = scs.getAll(id);
		map.put("chance", chance);
		return "plan/exec";
	}
	
	@ResponseBody
	@RequestMapping(value="/make/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		sps.delete(id);
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="/make/{id}",method=RequestMethod.PUT)
	public String update(@RequestParam("todo") String todo,@PathVariable("id") Long id){
		SalesPlan salesPlan = new SalesPlan();
		salesPlan.setId(id);
		salesPlan.setTodo(todo);
		sps.update(salesPlan);
		return "1";
	}
	@ResponseBody
	@RequestMapping(value="/make/",method=RequestMethod.POST)
	public String save(SalesPlan salesPlan,@RequestParam("chance.id") Long id) {
		SalesChance chance=new SalesChance();
		chance.setId(id);
		salesPlan.setChance(chance);
		long planid=sps.save(salesPlan);
		return planid+"";
	}
	
	@RequestMapping(value="/make/{chanceId}",method=RequestMethod.GET)
	public String toMake(@PathVariable("chanceId") Long chanceId,
			Map<String,Object> map){
		SalesChance chance=scs.getAll(chanceId);
		map.put("chance", chance);
		return "plan/make";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			Map<String,Object> map,HttpServletRequest request){
		String prefix="search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CRMUtils.encodeParamsToQueryString(params, prefix);
		Page<SalesChance> page=scs.getPageAll(pageNoStr);
		map.put("queryString", queryString);
		map.put("page", page);
		return "plan/list";
	}
	
	/*@RequestMapping("/list")
	public String list(@RequestParam("pageNoStr") String pageNoStr,
			Map<String,Object> map){
		Page<SalesChance> page=scs.getPageAll(pageNoStr);
		return "plan/list";
	}*/
}
