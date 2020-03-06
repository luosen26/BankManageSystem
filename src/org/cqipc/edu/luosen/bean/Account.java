package org.cqipc.edu.luosen.bean;

public class Account {
	private int a_id;
	private String a_type;
	private double a_balance;
	private int c_id;
	
	public Account() {
		super();
	}
	public Account(String a_type, long a_balance, int c_id) {
		super();
		this.a_type = a_type;
		this.a_balance = a_balance;
		this.c_id = c_id;
	}
	public Account(int a_id, String a_type, long a_balance, int c_id) {
		super();
		this.a_id = a_id;
		this.a_type = a_type;
		this.a_balance = a_balance;
		this.c_id = c_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_type() {
		return a_type;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public double getA_balance() {
		return a_balance;
	}
	public void setA_balance(double a_balance) {
		this.a_balance = a_balance;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
}
