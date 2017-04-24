package com.bin.emp.dao;

import java.util.List;
import java.util.Map;

import com.bin.emp.entity.Item;

public interface ItemDao {
	
	public List<Item> findByPage(int min,int max);
	
	public int findByCount();
	
	public int save(Item item);
	
	public Item findByItemId(String itemid);
	
	public int update(Item item);
	
	public int del(String itemid);
	
	public int createPhoto(String itemid,byte[] photoData);
	
	public Map<String, Object> findImgByItemid(String itemid);
 
}
