package org.cqipc.edu.luosen.bean;

public class Detail {
	private int d_id;
	private int a_id;
	private String d_name;
	private long d_money;
	private String d_data;
	public Detail() {
		super();
	}
	public Detail(int a_id, String d_name, long d_money, String d_data) {
		super();
		this.a_id = a_id;
		this.d_name = d_name;
		this.d_money = d_money;
		this.d_data = d_data;
	}
	public Detail(int d_id, int a_id, String d_name, long d_money, String d_data) {
		super();
		this.d_id = d_id;
		this.a_id = a_id;
		this.d_name = d_name;
		this.d_money = d_money;
		this.d_data = d_data;
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public long getD_money() {
		return d_money;
	}
	public void setD_money(long d_money) {
		this.d_money = d_money;
	}
	public String getD_data() {
		return d_data;
	}
	public void setD_data(String d_data) {
		this.d_data = d_data;
	}
	
}
