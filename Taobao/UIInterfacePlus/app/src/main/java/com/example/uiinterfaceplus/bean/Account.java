package com.example.uiinterfaceplus.bean;

public class Account {
	private Long id;
	private int icon;
	private String dianjia;
	private String name;
	private Double price;
	private Double sum;
	private Integer number;
	private int flag;

	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDianjia() {
		return dianjia;
	}
	public void setDianjia(String dianjia) {
		this.name = dianjia;
	}
	public Double getPrice() { return price; }
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public int getFlag() { return flag; }
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Account(Long id,int icon,String dianjia, String name, Double price,Double sum,Integer number,int flag) {
		super();
		this.icon=icon;
		this.id = id;
		this.dianjia=dianjia;
		this.name = name;
		this.price = price;
		this.sum=sum;
		this.number=number;
		this.flag=flag;
	}

	public Account(int icon,String dianjia, String name, Double price,Double sum,Integer number,int flag) {
		super();
		this.icon=icon;
		this.dianjia=dianjia;
		this.name = name;
		this.price = price;
		this.sum=sum;
		this.number=number;
		this.flag=flag;
	}

	public Account() {
		super();
	}
	public String toString() {
		return "[序号: " + id + ", 店铺: " + dianjia +", 商品介绍: " + name + ",单价: " + price + ",总额: " + sum+"数量:"+number+ "]";
	}
}
