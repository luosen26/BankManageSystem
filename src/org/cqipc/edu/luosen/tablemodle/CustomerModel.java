package org.cqipc.edu.luosen.tablemodle;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cqipc.edu.luosen.bean.Customer;
import org.cqipc.edu.luosen.dao.BankDao;

public class CustomerModel extends AbstractTableModel {
	private String[] columnNames= {"用户编号","用户名称","用户电话号码","用户地址","用户创建时间","银行名称"};
	private List<Customer> data=null;
	private BankDao bankDao=null;
	public CustomerModel(List<Customer> data,BankDao bankDao) {
		this.data=data;
		this.bankDao=bankDao;
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer customer=data.get(rowIndex);
		switch(columnIndex) {
		case 0:return customer.getB_id();
		case 1:return customer.getC_name();
		case 2:return customer.getC_tel();
		case 3:return customer.getC_addr();
		case 4:return customer.getC_createDate();
		default:return bankDao.findById(customer.getB_id());
		}
	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

}
