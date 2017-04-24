package com.bin.emp.controller;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
import com.bin.emp.service.impl.ShowServiceImpl;

@Controller
public class ShowController {

	
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	@Autowired
	private ShowService showService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/data")
	public Map<String, Object> getItems(int page, int rows) throws UnsupportedEncodingException{
		
		List<Item> items = showService.showByPage(page,rows);
		long total = showService.count();
		//List<Item> items = items.subList((page-1)*rows,rows*page-1);
		//System.out.println(total+""+(page-1)*rows+""+rows*page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", items);
		return result;
	}
		@ResponseBody
		@RequestMapping(value = "/data1")
		public Item getItem(String itemid) throws UnsupportedEncodingException{
			
			Item item = showService.showById(itemid);
			return item;
		}
		
		//@ResponseBody
		@RequestMapping(value = "/data_img")
		public void getImg(String itemid,final HttpServletResponse response) throws UnsupportedEncodingException{
			try{
				byte[] data = showService.showImgById(itemid);
				response.setContentType("image/jpeg");  
			    response.setCharacterEncoding("UTF-8");  
			    OutputStream outputSream = response.getOutputStream();  
			    InputStream in = new ByteArrayInputStream(data);  
			    int len = 0;  
			    byte[] buf = new byte[1024];  
			    while ((len = in.read(buf, 0, 1024)) != -1) {  
			        outputSream.write(buf, 0, len);  
			    }  
			    outputSream.close();  
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
}
