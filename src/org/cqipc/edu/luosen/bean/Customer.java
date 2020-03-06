package org.cqipc.edu.luosen.bean;

public class Customer {
	private int c_id;
	private String c_name;
	private String c_tel;
	private String c_addr;
	private String c_createDate;
	private int b_id;
	public Customer() {
		super();
	}
	public Customer(String c_name, String c_tel, String c_addr, String c_createDate, int b_id) {
		super();
		this.c_name = c_name;
		this.c_tel = c_tel;
		this.c_addr = c_addr;
		this.c_createDate = c_createDate;
		this.b_id = b_id;
	}
	public Customer(int c_id, String c_name, String c_tel, String c_addr, String c_createDate, int b_id) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_tel = c_tel;
		this.c_addr = c_addr;
		this.c_createDate = c_createDate;
		this.b_id = b_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	public String getC_addr() {
		return c_addr;
	}
	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}
	public String getC_createDate() {
		return c_createDate;
	}
	public void setC_createDate(String c_createDate) {
		this.c_createDate = c_createDate;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	
}
