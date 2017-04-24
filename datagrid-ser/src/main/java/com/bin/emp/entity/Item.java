package com.bin.emp.entity;

import java.io.Serializable;

public class Item  implements Serializable {
	
	 public String itemid;
	 public String productid;
	 public Integer listprice;
	 public Integer unitcost;
	 public String attr1;
	 public String status;
	 
	 
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public Integer getListprice() {
		return listprice;
	}
	public void setListprice(Integer listprice) {
		this.listprice = listprice;
	}
	public Integer getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(Integer unitcost) {
		this.unitcost = unitcost;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
	 

}
