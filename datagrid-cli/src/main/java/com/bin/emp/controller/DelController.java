package com.bin.emp.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;



import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bin.emp.entity.Item;
import com.bin.emp.service.ShowService;

@Controller
public class DelController {

	
	
private static final Logger logger = LoggerFactory.getLogger(DelController.class);
	
	@Autowired
	private ShowService showService;
	
	@ResponseBody
	@RequestMapping(value = "/del")
	public Map<String, Object> delItems(Item item) throws UnsupportedEncodingException{
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (item.getItemid() == "" || item.getItemid() == null){
			
			result.put("failure", true);
			result.put("msg", "未选定");
		} 
		else{
			int count = showService.del(item.getItemid());
			if(count <= 0){
				result.put("failure", true);
				result.put("msg", "删除失败");
			}
			else{
				
				result.put("success", true);
				result.put("msg", "删除成功");
			}
		}
		
		return result;
	}
}
