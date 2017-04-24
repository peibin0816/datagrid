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

import org.springframework.web.multipart.MultipartFile;

@Controller
public class SaveController {

	
	
private static final Logger logger = LoggerFactory.getLogger(SaveController.class);
	
	@Autowired
	private ShowService showService;
	
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public Map<String, Object> saveItems(Item item) throws UnsupportedEncodingException{
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (item.getItemid() == ""||item.getItemid() == null){
			
			result.put("failure", true);
			result.put("msg", "产品id不能为空");
		} 
		else{
			int count = showService.save(item);
			if(count <= 0){
				result.put("failure", true);
				result.put("msg", "添加失败");
			}
			else{
				
				result.put("success", true);
				result.put("msg", "添加成功");
			}
		}
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saveimg")
	public Map<String, Object> saveimg(String itemid,MultipartFile photo) throws UnsupportedEncodingException{
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (itemid == ""||itemid == null){
			
			result.put("failure", true);
			result.put("msg", "产品id不能为空");
		}else if (photo.isEmpty() == true ){
			
			result.put("failure", true);
			result.put("msg", "上传图片为空");
		}else{
			try{
				int count = showService.saveImg(itemid,photo);
			
				if(count <= 0){
					result.put("failure", true);
					result.put("msg", "添加失败");
				}
				else{
					
					result.put("success", true);
					result.put("msg", "添加成功");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
