package org.cqipc.edu.luosen.tablemodle;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.cqipc.edu.luosen.bean.Bank;
public class BankModel extends AbstractTableModel {
	private String[] columnNames= {"银行编号","银行名称"};
	private List<Bank> data=null;
	public BankModel(List<Bank> data) {
		this.data=data;
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
		Bank bank=data.get(rowIndex);
		switch(columnIndex) {
		case 0:return bank.getB_id();
		default:return bank.getB_name();
		}
	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

}
