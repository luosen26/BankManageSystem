package org.cqipc.edu.luosen.utils;

import java.util.List;

import org.cqipc.edu.luosen.bean.Account;
import org.cqipc.edu.luosen.bean.Bank;
import org.cqipc.edu.luosen.bean.Customer;
import org.cqipc.edu.luosen.dao.CustomerDao;

public class Tools {
	public static String[] getbankNames(List<Bank> data) {
		String[] names=new String[data.size()+1];
		names[0]="-请选择-";
		for(int i=1;i<names.length;i++) {
			names[i]=data.get(i-1).getB_name();
		}
		return names;
	}
	public static String[] getuserNames(List<Customer> data) {
		String[] names=new String[data.size()+1];
		names[0]="-请选择-";
		for(int i=1;i<names.length;i++) {
			names[i]=data.get(i-1).getC_name();
		}
		return names;
	}
	public static String[] getaccountTypes(List<Account> data) {
		String[] names=new String[data.size()+1];
		names[0]="-请选择-";
		for(int i=1;i<names.length;i++) {
			names[i]=data.get(i-1).getA_type();
		}
		return names;
	}
	public static String[] getaccountNames(List<Account> data,CustomerDao customerDao) {
		String[] names=new String[data.size()+1];
		names[0]="-请选择-";
		for(int i=1;i<names.length;i++) {
			names[i]=customerDao.findById(data.get(i-1).getC_id());
		}
		return names;
	}
	public static String[] getaccountIds(List<Account> data) {
		String[] names=new String[data.size()+1];
		names[0]="-请选择-";
		for(int i=1;i<names.length;i++) {
			names[i]=Integer.toString(data.get(i-1).getA_id());
		}
		return names;
	}
	public static boolean isBankName(String str) {
		String bankName=str.replace(" ", "");
		if (bankName.length()>3&&bankName.endsWith("银行")) {
			char[] buf=bankName.toCharArray();
			for (int i = 0; i < buf.length-2; i++) {
				if (48<=buf[i]&&buf[i]<=57) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	public static boolean isUserName(String str) {
		String bankName=str.replace(" ", "");
		if (bankName.length()>1) {
			char[] buf=bankName.toCharArray();
			for (int i = 0; i < buf.length; i++) {
				if (48<=buf[i]&&buf[i]<=57) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	public static boolean isPhoneNumber(String str) {
		String bankName=str.replace(" ", "");
		if (bankName.length()==11) {
			char[] buf=bankName.toCharArray();
			for (int i = 0; i < buf.length; i++) {
				if (buf[i]<48||buf[i]>57) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	public static boolean isNumber(String str) {
		String number=str.replace(" ", "");
		if (number.length()!=0) {
			char[] buf=number.toCharArray();
			for (int i = 0; i < buf.length; i++) {
				if (buf[i]<48||buf[i]>57) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
}
