package org.cqipc.edu.luosen.tablemodle;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cqipc.edu.luosen.bean.Account;
import org.cqipc.edu.luosen.dao.CustomerDao;

public class AccountModel extends AbstractTableModel {
	private String[] columnNames= {"账户编号","账户持有者","账户类型","账户余额"};
	private List<Account> data=null;
	private CustomerDao customerDao=null;
	public AccountModel(List<Account> data,CustomerDao customerDao) {
		this.data=data;
		this.customerDao=customerDao;
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
		Account account=data.get(rowIndex);
		switch (columnIndex) {
		case 0:return account.getA_id();
		case 1:return customerDao.findById(account.getC_id());
		case 2:return account.getA_type();
		default:return account.getA_balance();
		}
	}
	
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}
}
