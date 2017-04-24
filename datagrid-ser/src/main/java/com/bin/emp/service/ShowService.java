package com.bin.emp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bin.emp.entity.Item;

public interface ShowService {
	//分页查询
	public List<Item> showByPage(int page,int rows);
	//总记录条数
	public int count();
	
	public Item showById(String itemid);
	
	public int save(Item item);
	
	public int update(Item item);
	
	public int del(String itemid);
	
	public int saveImg(String itemid,MultipartFile  photo) throws Exception;
	
	public byte[] showImgById(String itemid) throws Exception;
	

}
