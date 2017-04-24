package com.bin.emp.service.impl;

import java.util.List;

import com.bin.emp.dao.ItemDao;
import com.bin.emp.entity.Item;
import com.bin.emp.service.ShowService;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("showService")
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ItemDao itemDao;
	
	@Override
	public List<Item> showByPage(int page,int rows){
		
		int min,max;
		
		min = (page-1) * rows + 1;
		max = page * rows;
		
		List<Item> items = itemDao.findByPage(min, max);
		
		
		return items;
		
	}
	
	@Override
	public Item showById(String itemid){
		
		Item item = itemDao.findByItemId(itemid);
		return item;
	}

	@Override
	public int count() {
		
		int count = itemDao.findByCount();
		return count;
	}
	
	@Override
	public int save(Item item) {
		
		int count = itemDao.save(item);
		return count;
	}
	
	@Override
	public int update(Item item) {
		
		int count = itemDao.update(item);
		return count;
	}
	
	@Override
	public int del(String itemid){
		
		int count = itemDao.del(itemid);
		return count;
	}
	
	@Override
	public int saveImg(String itemid,MultipartFile  photo) throws Exception{
		 
		
		InputStream is = photo.getInputStream();  
	    byte[] photoData = new byte[(int) photo.getSize()];  
	    is.read(photoData); 
	    int count = itemDao.createPhoto(itemid, photoData);
	    return count;
		
	}
	
	@Override
	public byte[] showImgById(String itemid) throws Exception{
		byte[] bytes = new byte[1024];
		Map<String, Object> map = (Map<String, Object>)itemDao.findImgByItemid(itemid);
		if(map!=null){
			bytes = (byte[])map.get("photo_data");
		}
		return bytes;
	}
	
}
