package org.cqipc.edu.luosen.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cqipc.edu.luosen.bean.Account;
import org.cqipc.edu.luosen.bean.Bank;
import org.cqipc.edu.luosen.bean.Customer;
import org.cqipc.edu.luosen.controller.BankManageService;
import org.cqipc.edu.luosen.controller.Impl.BankManageServiceImpl;
import org.cqipc.edu.luosen.dao.AccountDao;
import org.cqipc.edu.luosen.dao.BankDao;
import org.cqipc.edu.luosen.dao.CustomerDao;
import org.cqipc.edu.luosen.dao.impl.AccountDaoImpl;
import org.cqipc.edu.luosen.dao.impl.BankDaoImpl;
import org.cqipc.edu.luosen.dao.impl.CustomerDaoImpl;
import org.cqipc.edu.luosen.tablemodle.AccountModel;
import org.cqipc.edu.luosen.tablemodle.BankModel;
import org.cqipc.edu.luosen.tablemodle.CustomerModel;
import org.cqipc.edu.luosen.utils.Tools;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

/**
 * BankManageSystemApplication窗口
 * 
 * @author luosen
 *
 */
public class ManageFrame {
	private BankManageService BMS;
	private List<Bank> bankdate;
	private String[] bankNames;
	private BankDao bankDao;
	private JTable bankTable;
	private int bankSelectedRow = -1;
	private int userSelectedRow = -1;
	private int accountSelectedRow = -1;
	private List<Customer> customerdate;
	private String[] userNames;
	private CustomerDao customerDao;
	private JTable customerTable;
	private List<Account> accountdate;
	private double depositBalance;
	private double outMoneyBalance;
	private String[] accountNames;
	private String[] customerNames;
	private AccountDao accountDao;
	private JTable accountTable;
	private JDesktopPane desktopPane;
	public JFrame frmManage;

	public ManageFrame() {
		BMS = new BankManageServiceImpl();
		bankDao = new BankDaoImpl();
		bankdate = BMS.findBankAll(bankDao);
		bankNames = Tools.getbankNames(bankdate);
		customerDao = new CustomerDaoImpl();
		customerdate = BMS.findCustomerAll(customerDao);
		userNames = Tools.getuserNames(customerdate);
		accountDao = new AccountDaoImpl();
		accountdate = BMS.findAccountAll(accountDao);
		customerNames = Tools.getaccountNames(accountdate, customerDao);
		accountNames = Tools.getuserNames(customerdate);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManage = new JFrame();
		frmManage.setResizable(false);
		frmManage.setBackground(Color.LIGHT_GRAY);
		frmManage.getContentPane().setBackground(Color.WHITE);
		frmManage.getContentPane().setForeground(Color.WHITE);
		frmManage.setIconImage(Toolkit.getDefaultToolkit().getImage(ManageFrame.class.getResource("/image/bank.png")));
		frmManage.setTitle("Manage");
		frmManage.setBounds(100, 100, 1048, 747);
		frmManage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManage.getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 255, 255));
		desktopPane.setBounds(0, 28, 1045, 693);
		frmManage.getContentPane().add(desktopPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		menuBar.setBounds(0, 0, 1045, 30);
		frmManage.getContentPane().add(menuBar);
		menuBar.setFont(new Font("宋体", Font.BOLD, 20));

		JMenu mnFile = new JMenu("\u7CFB\u7EDF\u64CD\u4F5C");
		mnFile.setIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D-34.png")));
		mnFile.setForeground(Color.BLACK);
		mnFile.setBackground(SystemColor.window);
		mnFile.setFont(new Font("宋体", Font.BOLD, 20));
		menuBar.add(mnFile);

		JMenuItem nomenuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		nomenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "是否退出银行管理系统？") == JOptionPane.OK_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});
		nomenuItem.setIcon(null);
		nomenuItem.setBackground(Color.WHITE);
		nomenuItem.setForeground(Color.BLACK);
		nomenuItem.setFont(new Font("宋体", Font.BOLD, 18));
		mnFile.add(nomenuItem);

		JMenu mnNewMenu_1 = new JMenu("\u94F6\u884C\u7BA1\u7406");
		mnNewMenu_1.setIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D \u94F6\u884C.png")));
		mnNewMenu_1.setFont(new Font("宋体", Font.BOLD, 20));
		mnNewMenu_1.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_1);

		JMenuItem addBankmenuItem = new JMenuItem("\u6DFB\u52A0\u94F6\u884C");
		addBankmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBank(e);
			}
		});
		addBankmenuItem.setForeground(Color.BLACK);
		addBankmenuItem.setFont(new Font("宋体", Font.BOLD, 18));
		mnNewMenu_1.add(addBankmenuItem);

		JMenuItem bankMessageControlmenuItem = new JMenuItem("\u94F6\u884C\u4FE1\u606F\u7BA1\u7406");
		bankMessageControlmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bankMessageManage(e);
			}
		});
		bankMessageControlmenuItem.setForeground(Color.BLACK);
		bankMessageControlmenuItem.setFont(new Font("宋体", Font.BOLD, 18));
		mnNewMenu_1.add(bankMessageControlmenuItem);

		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menu.setIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D-57.png")));
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("宋体", Font.BOLD, 20));
		menuBar.add(menu);

		JMenuItem menuItem_3 = new JMenuItem("\u6DFB\u52A0\u7528\u6237");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser(e);
			}
		});
		menuItem_3.setForeground(Color.BLACK);
		menuItem_3.setFont(new Font("宋体", Font.BOLD, 18));
		menu.add(menuItem_3);

		JMenuItem menuItem_4 = new JMenuItem("\u7528\u6237\u4FE1\u606F\u7BA1\u7406");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userMessageManage(e);
			}
		});
		menuItem_4.setForeground(Color.BLACK);
		menuItem_4.setFont(new Font("宋体", Font.BOLD, 18));
		menu.add(menuItem_4);

		JMenu menu_1 = new JMenu("\u8D26\u6237\u7BA1\u7406");
		menu_1.setIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D\u8D27\u5E01.png")));
		menu_1.setForeground(Color.BLACK);
		menu_1.setFont(new Font("宋体", Font.BOLD, 20));
		menuBar.add(menu_1);

		JMenuItem menuItem_5 = new JMenuItem("\u6DFB\u52A0\u8D26\u6237");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAccount(e);

			}
		});
		menuItem_5.setForeground(Color.BLACK);
		menuItem_5.setFont(new Font("宋体", Font.BOLD, 18));
		menu_1.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("\u8D26\u6237\u4FE1\u606F\u7BA1\u7406");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountMessageManage(e);
			}
		});
		menuItem_6.setForeground(Color.BLACK);
		menuItem_6.setFont(new Font("宋体", Font.BOLD, 18));
		menu_1.add(menuItem_6);

		JMenu menu_2 = new JMenu("\u4E1A\u52A1\u7BA1\u7406");
		menu_2.setIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D\u6587\u5E93.png")));
		menu_2.setForeground(Color.BLACK);
		menu_2.setFont(new Font("宋体", Font.BOLD, 20));
		menuBar.add(menu_2);

		JMenuItem menuItem_7 = new JMenuItem("\u5B58\u6B3E\u4E1A\u52A1\u529E\u7406");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit(e);
			}
		});
		menuItem_7.setForeground(Color.BLACK);
		menuItem_7.setFont(new Font("宋体", Font.BOLD, 18));
		menu_2.add(menuItem_7);

		JMenuItem menuItem_8 = new JMenuItem("\u53D6\u6B3E\u4E1A\u52A1\u529E\u7406");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outMoney(e);
			}
		});
		menuItem_8.setForeground(Color.BLACK);
		menuItem_8.setFont(new Font("宋体", Font.BOLD, 18));
		menu_2.add(menuItem_8);

		JMenuItem menuItem_9 = new JMenuItem("\u6C47\u6B3E\u4E1A\u52A1\u529E\u7406");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remit(e);
			}
		});
		menuItem_9.setForeground(Color.BLACK);
		menuItem_9.setFont(new Font("宋体", Font.BOLD, 18));
		menu_2.add(menuItem_9);

	}

	// 添加汇款的窗口
	private void remit(ActionEvent e) {

		JInternalFrame internalFrame = new JInternalFrame("\u6C47\u6B3E\u4E1A\u52A1");
		internalFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D\u6587\u5E93.png")));
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(197, 105, 592, 415);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		JLabel label_1 = new JLabel("\u6C47\u6B3E\u7528\u6237\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(25, 48, 134, 44);
		internalFrame.getContentPane().add(label_1);

		JTextField remitMoneyText = new JTextField();
		remitMoneyText.setForeground(Color.RED);
		remitMoneyText.setFont(new Font("宋体", Font.BOLD, 25));
		remitMoneyText.setBounds(149, 249, 240, 40);
		internalFrame.getContentPane().add(remitMoneyText);
		remitMoneyText.setColumns(10);

		JLabel label = new JLabel("\u6C47\u6B3E\u8D26\u6237\uFF1A");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBounds(301, 48, 134, 44);
		internalFrame.getContentPane().add(label);

		JLabel label_2 = new JLabel("\u6536\u6B3E\u7528\u6237:");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		label_2.setBounds(25, 147, 134, 44);
		internalFrame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("\u6C47\u6B3E\u91D1\u989D:");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("宋体", Font.BOLD, 25));
		label_3.setBounds(25, 246, 134, 44);
		internalFrame.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("\u6536\u6B3E\u8D26\u6237:");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("宋体", Font.BOLD, 25));
		label_4.setBounds(301, 147, 134, 44);
		internalFrame.getContentPane().add(label_4);

		JLabel outMoneyLabel = new JLabel("");
		outMoneyLabel.setForeground(Color.BLUE);
		outMoneyLabel.setFont(new Font("宋体", Font.BOLD, 25));
		outMoneyLabel.setBounds(25, 102, 524, 44);
		internalFrame.getContentPane().add(outMoneyLabel);

		JLabel depositLabel = new JLabel("");
		depositLabel.setForeground(Color.BLUE);
		depositLabel.setFont(new Font("宋体", Font.BOLD, 25));
		depositLabel.setBounds(25, 195, 524, 44);
		internalFrame.getContentPane().add(depositLabel);

		JComboBox remitAidComboBox = new JComboBox();
		remitAidComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		remitAidComboBox.setForeground(Color.BLUE);
		remitAidComboBox.addItem("-请选择-");
		remitAidComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aId = (String) remitAidComboBox.getSelectedItem();
				if (!aId.equals("-请选择-")) {
					outMoneyBalance = BMS.findAccountById(Integer.parseInt(aId), accountDao).getA_balance();
					outMoneyLabel.setText("账户余额为：" + outMoneyBalance);
				} else {
					outMoneyLabel.setText("");
				}
			}
		});
		remitAidComboBox.setBounds(424, 52, 125, 35);
		internalFrame.getContentPane().add(remitAidComboBox);

		accountdate = BMS.findAccountAll(accountDao);
		customerNames = Tools.getaccountNames(accountdate, customerDao);
		JComboBox remitNamecomboBox = new JComboBox(customerNames);
		remitNamecomboBox.setFont(new Font("宋体", Font.BOLD, 20));
		remitNamecomboBox.setForeground(Color.BLUE);
		remitNamecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) remitNamecomboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					int cid = BMS.findCustomerByName(name, customerDao).get(0).getC_id();
					String[] aIds = Tools.getaccountIds(BMS.findAccountByCid(cid, accountDao));
					remitAidComboBox.setModel(new DefaultComboBoxModel<String>(aIds));
				} else {
					outMoneyLabel.setText("");
					remitAidComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "-请选择-" }));
				}
			}
		});
		remitNamecomboBox.setBounds(149, 52, 125, 35);
		internalFrame.getContentPane().add(remitNamecomboBox);

		JComboBox acceptAidComboBox = new JComboBox();
		acceptAidComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		acceptAidComboBox.setForeground(Color.BLUE);
		acceptAidComboBox.addItem("-请选择-");
		acceptAidComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aId = (String) acceptAidComboBox.getSelectedItem();
				if (!aId.equals("-请选择-")) {
					depositBalance = BMS.findAccountById(Integer.parseInt(aId), accountDao).getA_balance();
					depositLabel.setText("账户余额为：" + depositBalance);
				} else {
					depositLabel.setText("");
				}
			}
		});
		acceptAidComboBox.setBounds(424, 156, 125, 35);
		internalFrame.getContentPane().add(acceptAidComboBox);

		JComboBox acceptNamecomboBox = new JComboBox(customerNames);
		acceptNamecomboBox.setFont(new Font("宋体", Font.BOLD, 20));
		acceptNamecomboBox.setForeground(Color.BLUE);
		acceptNamecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) acceptNamecomboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					int cid = BMS.findCustomerByName(name, customerDao).get(0).getC_id();
					String[] aIds = Tools.getaccountIds(BMS.findAccountByCid(cid, accountDao));
					acceptAidComboBox.setModel(new DefaultComboBoxModel<String>(aIds));
				} else {
					depositLabel.setText("");
					acceptAidComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "-请选择-" }));
				}
			}
		});
		acceptNamecomboBox.setBounds(149, 156, 125, 35);
		internalFrame.getContentPane().add(acceptNamecomboBox);

		JButton remitButton = new JButton("\u6C47 \u6B3E");
		remitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) remitNamecomboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					String aId = (String) remitAidComboBox.getSelectedItem();
					if (!aId.equals("-请选择-")) {
						String acceptName = (String) acceptNamecomboBox.getSelectedItem();
						if (!acceptName.equals("-请选择-")) {
							String acceptaId = (String) acceptAidComboBox.getSelectedItem();
							if (!acceptaId.equals("-请选择-")) {
								boolean flag = Tools.isNumber(remitMoneyText.getText());
								if (flag) {
									String money = remitMoneyText.getText().replace(" ", "");
									double OutMoneyResult = outMoneyBalance - Double.parseDouble(money);
									if (OutMoneyResult >= 0) {
										if (BMS.remit(depositBalance, outMoneyBalance, Double.parseDouble(money),
												Integer.parseInt(aId), Integer.parseInt(acceptaId), accountDao)) {
											double getMoneyResult = depositBalance + Double.parseDouble(money);
											JOptionPane.showMessageDialog(null, "汇款成功");
											outMoneyLabel.setText("账户余额为：" + OutMoneyResult);
											depositLabel.setText("账户余额为：" + getMoneyResult);
											remitMoneyText.setText("");
											if (accountTable != null) {
												accountdate = BMS.findAccountAll(accountDao);
												accountTable.setModel(new AccountModel(accountdate, customerDao));
											}
										} else {
											JOptionPane.showMessageDialog(null, "汇款失败");
										}
									} else {
										JOptionPane.showMessageDialog(null, "汇款失败，汇款用户余额不足");
									}
								} else {
									JOptionPane.showMessageDialog(null, "你的输入汇款金额有误，请输入数字或不能为空");
								}
							} else {
								JOptionPane.showMessageDialog(null, "请选择该收款用户的一张银行卡");
							}
						} else {
							JOptionPane.showMessageDialog(null, "请选择一名收款用户");
						}
					} else {
						JOptionPane.showMessageDialog(null, "请选择该汇款用户的一张银行卡");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一名汇款用户");
				}
			}
		});
		remitButton.setFont(new Font("宋体", Font.BOLD, 25));
		remitButton.setForeground(Color.BLUE);
		remitButton.setBounds(200, 301, 120, 50);
		internalFrame.getContentPane().add(remitButton);
	}

	// 添加取款的窗口
	private void outMoney(ActionEvent e) {

		JInternalFrame internalFrame = new JInternalFrame("\u53D6\u6B3E\u4E1A\u52A1");
		internalFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D\u6587\u5E93.png")));
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(72, 96, 592, 317);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		JLabel label_1 = new JLabel("\u53D6\u6B3E\u7528\u6237\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(25, 48, 134, 44);
		internalFrame.getContentPane().add(label_1);

		JTextField outMoneyText = new JTextField();
		outMoneyText.setForeground(Color.RED);
		outMoneyText.setFont(new Font("宋体", Font.BOLD, 25));
		outMoneyText.setBounds(149, 150, 240, 40);
		internalFrame.getContentPane().add(outMoneyText);
		outMoneyText.setColumns(10);

		JLabel label = new JLabel("\u53D6\u6B3E\u8D26\u6237\uFF1A");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBounds(301, 48, 134, 44);
		internalFrame.getContentPane().add(label);

		JLabel balanceLabel = new JLabel("");
		balanceLabel.setForeground(Color.BLUE);
		balanceLabel.setFont(new Font("宋体", Font.BOLD, 25));
		balanceLabel.setBounds(25, 97, 524, 44);
		internalFrame.getContentPane().add(balanceLabel);

		JComboBox outMoneyAIdComboBox = new JComboBox();
		outMoneyAIdComboBox.addItem("-请选择-");
		outMoneyAIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aId = (String) outMoneyAIdComboBox.getSelectedItem();
				if (!aId.equals("-请选择-")) {
					outMoneyBalance = BMS.findAccountById(Integer.parseInt(aId), accountDao).getA_balance();
					balanceLabel.setText("账户余额为：" + outMoneyBalance);
				} else {
					balanceLabel.setText("");
				}
			}
		});
		outMoneyAIdComboBox.setForeground(Color.BLUE);
		outMoneyAIdComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		outMoneyAIdComboBox.setBounds(424, 52, 125, 35);
		internalFrame.getContentPane().add(outMoneyAIdComboBox);

		accountdate = BMS.findAccountAll(accountDao);
		customerNames = Tools.getaccountNames(accountdate, customerDao);
		JComboBox outMoneyNameComboBox = new JComboBox(customerNames);
		outMoneyNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) outMoneyNameComboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					int cid = BMS.findCustomerByName(name, customerDao).get(0).getC_id();
					String[] aIds = Tools.getaccountIds(BMS.findAccountByCid(cid, accountDao));
					outMoneyAIdComboBox.setModel(new DefaultComboBoxModel<String>(aIds));
				} else {
					balanceLabel.setText("");
					outMoneyAIdComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "-请选择-" }));
				}
			}
		});
		outMoneyNameComboBox.setForeground(Color.BLUE);
		outMoneyNameComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		outMoneyNameComboBox.setBounds(149, 52, 125, 35);
		internalFrame.getContentPane().add(outMoneyNameComboBox);

		JLabel label_2 = new JLabel("\u53D6\u6B3E\u91D1\u989D:");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		label_2.setBounds(25, 147, 134, 44);
		internalFrame.getContentPane().add(label_2);

		JButton outMoneyButton = new JButton("\u53D6 \u6B3E");
		outMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) outMoneyNameComboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					String aId = (String) outMoneyAIdComboBox.getSelectedItem();
					if (!aId.equals("-请选择-")) {
						boolean flag = Tools.isNumber(outMoneyText.getText());
						if (flag) {
							String money = outMoneyText.getText().replace(" ", "");
							outMoneyBalance -= Double.parseDouble(money);
							if (outMoneyBalance >= 0) {
								if (BMS.outMonry(outMoneyBalance,
										Integer.parseInt((String) outMoneyAIdComboBox.getSelectedItem()), accountDao)) {
									JOptionPane.showMessageDialog(null, "取款成功");
									balanceLabel.setText("账户余额为：" + outMoneyBalance);
									outMoneyText.setText("");
									if (accountTable != null) {
										accountdate = BMS.findAccountAll(accountDao);
										accountTable.setModel(new AccountModel(accountdate, customerDao));
									}
								} else {
									JOptionPane.showMessageDialog(null, "取款失败");
								}
							} else {
								JOptionPane.showMessageDialog(null, "取款失败，余额不足");
							}
						} else {
							JOptionPane.showMessageDialog(null, "你的输入有误，请输入数字或不能为空");
						}
					} else {
						JOptionPane.showMessageDialog(null, "请选择该用户的一张银行卡");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一名用户");
				}
			}
		});
		outMoneyButton.setFont(new Font("宋体", Font.BOLD, 25));
		outMoneyButton.setForeground(Color.BLUE);
		outMoneyButton.setBounds(205, 215, 120, 50);
		internalFrame.getContentPane().add(outMoneyButton);
	}

	// 添加存款的窗口
	private void deposit(ActionEvent e) {

		JInternalFrame internalFrame = new JInternalFrame("\u5B58\u6B3E\u4E1A\u52A1");
		internalFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D\u6587\u5E93.png")));
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(72, 96, 596, 318);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		JLabel label_1 = new JLabel("\u5B58\u6B3E\u7528\u6237\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(25, 48, 134, 44);
		internalFrame.getContentPane().add(label_1);

		JTextField depositMoneyText = new JTextField();
		depositMoneyText.setForeground(Color.RED);
		depositMoneyText.setFont(new Font("宋体", Font.BOLD, 25));
		depositMoneyText.setBounds(149, 150, 240, 40);
		internalFrame.getContentPane().add(depositMoneyText);
		depositMoneyText.setColumns(10);

		JLabel label = new JLabel("\u5B58\u6B3E\u8D26\u6237\uFF1A");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBounds(301, 48, 134, 44);
		internalFrame.getContentPane().add(label);

		JLabel balanceLabel = new JLabel("");
		balanceLabel.setForeground(Color.BLUE);
		balanceLabel.setFont(new Font("宋体", Font.BOLD, 25));
		balanceLabel.setBounds(25, 96, 524, 44);
		internalFrame.getContentPane().add(balanceLabel);

		JComboBox depostAIdComboBox = new JComboBox();
		depostAIdComboBox.addItem("-请选择-");
		depostAIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aId = (String) depostAIdComboBox.getSelectedItem();
				if (!aId.equals("-请选择-")) {
					depositBalance = BMS.findAccountById(Integer.parseInt(aId), accountDao).getA_balance();
					balanceLabel.setText("账户余额为：" + depositBalance);
				} else {
					balanceLabel.setText("");
				}
			}
		});
		depostAIdComboBox.setForeground(Color.BLUE);
		depostAIdComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		depostAIdComboBox.setBounds(424, 52, 125, 35);
		internalFrame.getContentPane().add(depostAIdComboBox);

		accountdate = BMS.findAccountAll(accountDao);
		customerNames = Tools.getaccountNames(accountdate, customerDao);
		JComboBox depositNameComboBox = new JComboBox(customerNames);
		depositNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) depositNameComboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					int cid = BMS.findCustomerByName(name, customerDao).get(0).getC_id();
					String[] aIds = Tools.getaccountIds(BMS.findAccountByCid(cid, accountDao));
					depostAIdComboBox.setModel(new DefaultComboBoxModel<String>(aIds));
				} else {
					balanceLabel.setText("");
					depostAIdComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "-请选择-" }));
				}
			}
		});
		depositNameComboBox.setForeground(Color.BLUE);
		depositNameComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		depositNameComboBox.setBounds(149, 52, 125, 35);
		internalFrame.getContentPane().add(depositNameComboBox);

		JLabel label_2 = new JLabel("\u5B58\u6B3E\u91D1\u989D:");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		label_2.setBounds(25, 147, 134, 44);
		internalFrame.getContentPane().add(label_2);

		JButton depositButton = new JButton("\u5B58 \u6B3E");
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) depositNameComboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					String aId = (String) depostAIdComboBox.getSelectedItem();
					if (!aId.equals("-请选择-")) {
						boolean flag = Tools.isNumber(depositMoneyText.getText());
						if (flag) {
							String money = depositMoneyText.getText().replace(" ", "");
							depositBalance += Double.parseDouble(money);
							if (BMS.deposit(depositBalance,
									Integer.parseInt((String) depostAIdComboBox.getSelectedItem()), accountDao)) {
								JOptionPane.showMessageDialog(null, "存款成功");
								balanceLabel.setText("账户余额为：" + depositBalance);
								depositMoneyText.setText("");
								if (accountTable != null) {
									accountdate = BMS.findAccountAll(accountDao);
									accountTable.setModel(new AccountModel(accountdate, customerDao));
								}
							} else {
								JOptionPane.showMessageDialog(null, "存款失败");
							}
						} else {
							JOptionPane.showMessageDialog(null, "你的输入有误，请输入数字或不能为空");
						}
					} else {
						JOptionPane.showMessageDialog(null, "请选择该用户下的一张银行卡");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一名用户");
				}
			}
		});
		depositButton.setFont(new Font("宋体", Font.BOLD, 25));
		depositButton.setForeground(Color.BLUE);
		depositButton.setBounds(205, 215, 120, 50);
		internalFrame.getContentPane().add(depositButton);
	}

	// 添加添加账户的窗口
	private void addAccount(ActionEvent e) {

		JInternalFrame internalFrame = new JInternalFrame("\u6DFB\u52A0\u8D26\u6237");
		internalFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D\u8D27\u5E01.png")));
		internalFrame.setIconifiable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(223, 141, 565, 364);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		JLabel label = new JLabel("\u6B22\u8FCE\u5149\u4E34");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.BOLD, 65));
		label.setBounds(10, 10, 533, 123);
		internalFrame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u59D3 \u540D:");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(61, 143, 100, 44);
		internalFrame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u8D26\u6237\u7C7B\u578B:");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		label_2.setBounds(28, 208, 126, 44);
		internalFrame.getContentPane().add(label_2);

		JComboBox customerNameComboBox = new JComboBox(accountNames);
		customerNameComboBox.setForeground(Color.BLUE);
		customerNameComboBox.setFont(new Font("宋体", Font.BOLD, 25));
		customerNameComboBox.setBounds(157, 143, 240, 40);
		internalFrame.getContentPane().add(customerNameComboBox);

		String[] choices = { "-请选择-", "储蓄账户", "信用账户", "定期账户", "外汇账户" };
		JComboBox accountTypeComboBox = new JComboBox(choices);
		accountTypeComboBox.setFont(new Font("宋体", Font.BOLD, 25));
		accountTypeComboBox.setForeground(Color.BLUE);
		accountTypeComboBox.setBounds(157, 210, 240, 40);
		internalFrame.getContentPane().add(accountTypeComboBox);

		JButton registerButton = new JButton("\u6CE8 \u518C");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) customerNameComboBox.getSelectedItem();
				if (!name.equals("-请选择-")) {
					String type = (String) accountTypeComboBox.getSelectedItem();
					if (!type.equals("-请选择-")) {
						int cid = BMS.findCustomerByName(name, customerDao).get(0).getC_id();
						if (BMS.addAccount(new Account(type, 0, cid), accountDao)) {
							JOptionPane.showMessageDialog(null, "注册成功");
							if (accountTable != null) {
								accountdate = BMS.findAccountAll(accountDao);
								accountTable.setModel(new AccountModel(accountdate, customerDao));
							}
							customerNameComboBox.setSelectedIndex(0);
							accountTypeComboBox.setSelectedIndex(0);

						} else {
							JOptionPane.showMessageDialog(null, "注册失败");
						}
					} else {
						JOptionPane.showMessageDialog(null, "注册失败，请选择一种账户类型");
					}
				} else {
					JOptionPane.showMessageDialog(null, "注册失败，请先选择一名用户");
				}
			}
		});
		registerButton.setFont(new Font("宋体", Font.BOLD, 25));
		registerButton.setForeground(Color.BLUE);
		registerButton.setBounds(192, 265, 140, 50);
		internalFrame.getContentPane().add(registerButton);
	}

	// 添加添加用户的窗口
	private void addUser(ActionEvent e) {

		JInternalFrame internalFrame = new JInternalFrame("\u6DFB\u52A0\u7528\u6237");
		internalFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D-57.png")));
		internalFrame.setIconifiable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(217, 51, 481, 505);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		JLabel label_1 = new JLabel("\u59D3 \u540D\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(60, 55, 100, 44);
		internalFrame.getContentPane().add(label_1);

		JTextField userNameText = new JTextField();
		userNameText.setFont(new Font("宋体", Font.BOLD, 25));
		userNameText.setBounds(150, 58, 240, 40);
		internalFrame.getContentPane().add(userNameText);
		userNameText.setColumns(10);

		JLabel label = new JLabel("\u7535 \u8BDD\uFF1A");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBounds(60, 140, 100, 44);
		internalFrame.getContentPane().add(label);

		JTextField phoneText = new JTextField();
		phoneText.setFont(new Font("宋体", Font.BOLD, 25));
		phoneText.setColumns(10);
		phoneText.setBounds(150, 143, 240, 40);
		internalFrame.getContentPane().add(phoneText);

		JLabel label_2 = new JLabel("\u5730 \u5740\uFF1A");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		label_2.setBounds(60, 225, 100, 44);
		internalFrame.getContentPane().add(label_2);

		JTextField addrText = new JTextField();
		addrText.setFont(new Font("宋体", Font.BOLD, 25));
		addrText.setColumns(10);
		addrText.setBounds(150, 228, 240, 40);
		internalFrame.getContentPane().add(addrText);

		JLabel label_3 = new JLabel("\u94F6 \u884C\uFF1A");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("宋体", Font.BOLD, 25));
		label_3.setBounds(60, 320, 100, 44);
		internalFrame.getContentPane().add(label_3);

		JComboBox bankNameComboBox = new JComboBox(bankNames);
		bankNameComboBox.setBounds(150, 322, 240, 40);
		bankNameComboBox.setForeground(Color.BLUE);
		bankNameComboBox.setFont(new Font("宋体", Font.BOLD, 25));
		internalFrame.getContentPane().add(bankNameComboBox);

		JButton registerButton = new JButton("\u6CE8 \u518C");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flagName = Tools.isUserName(userNameText.getText());
				if (flagName) {
					boolean flagPhone = Tools.isPhoneNumber(phoneText.getText());
					if (flagPhone) {
						String addr = addrText.getText().replace(" ", "");
						if (!addr.equals("")) {
							String bankName = (String) bankNameComboBox.getSelectedItem();
							if (!bankName.equals("-请选择-")) {
								String name = userNameText.getText().replace(" ", "");
								String phone = phoneText.getText().replace(" ", "");
								String createDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
								int bankId = BMS.findBankByName(bankName, bankDao).get(0).getB_id();
								if (BMS.addCustomer(new Customer(name, phone, addr, createDate, bankId), customerDao)) {
									JOptionPane.showMessageDialog(null, "注册成功");
									if (customerTable != null) {
										customerdate = BMS.findCustomerAll(customerDao);
										customerTable.setModel(new CustomerModel(customerdate, bankDao));
									}
									userNameText.setText("");
									phoneText.setText("");
									addrText.setText("");
									bankNameComboBox.setSelectedIndex(0);
								} else {
									JOptionPane.showMessageDialog(null, "注册失败");
								}
							} else {
								JOptionPane.showMessageDialog(null, "注册失败,请选择银行");
							}
						} else {
							JOptionPane.showMessageDialog(null, "注册失败,用户地址不能为空");
						}
					} else {
						JOptionPane.showMessageDialog(null, "注册失败,用户电话号码不能为空且必须为11的数字");
					}
				} else {
					JOptionPane.showMessageDialog(null, "注册失败,用户姓名不能为空或不能含有数字，且姓名长度要大于一");
				}
			}
		});
		registerButton.setFont(new Font("宋体", Font.BOLD, 36));
		registerButton.setForeground(new Color(0, 0, 255));
		registerButton.setBounds(183, 384, 140, 50);
		internalFrame.getContentPane().add(registerButton);
	}

	// 添加添加银行的窗口
	private void addBank(ActionEvent e) {

		JInternalFrame internalFrame = new JInternalFrame("\u6DFB\u52A0\u94F6\u884C");
		internalFrame
				.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D \u94F6\u884C.png")));
		internalFrame.setIconifiable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(223, 141, 565, 364);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		JLabel label = new JLabel("\u6B22\u8FCE\u5149\u4E34");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.BOLD, 65));
		label.setBounds(10, 10, 533, 123);
		internalFrame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u94F6\u884C\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(32, 175, 154, 44);
		internalFrame.getContentPane().add(label_1);

		JTextField bankNameText = new JTextField();
		bankNameText.setFont(new Font("宋体", Font.BOLD, 25));
		bankNameText.setBounds(159, 178, 240, 40);
		internalFrame.getContentPane().add(bankNameText);
		bankNameText.setColumns(10);

		JButton addBankbutton = new JButton("\u6DFB \u52A0");
		addBankbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = Tools.isBankName(bankNameText.getText());
				if (flag) {
					String bankName = bankNameText.getText().replace(" ", "");
					if (BMS.findBankByName(bankName, bankDao).size() == 0) {
						if (BMS.addBank(new Bank(bankName), bankDao)) {
							try {
								JOptionPane.showMessageDialog(null, "添加银行成功");
								bankNameText.setText("");
								if (bankTable!=null) {
									bankdate = BMS.findBankAll(bankDao);
									bankTable.setModel(new BankModel(bankdate));
								}
							} catch (Exception e1) {
							}
						} else {
							JOptionPane.showMessageDialog(null, "添加银行失败");
						}
					} else {
						JOptionPane.showMessageDialog(null, "添加银行失败,该银行已存在");
					}
				} else {
					JOptionPane.showMessageDialog(null, "添加银行失败，你输入的银行名称为空或者含有数字,不是以银行二字结尾");
				}
			}
		});
		addBankbutton.setFont(new Font("宋体", Font.BOLD, 25));
		addBankbutton.setForeground(Color.BLUE);
		addBankbutton.setBounds(415, 177, 100, 40);
		internalFrame.getContentPane().add(addBankbutton);
	}

	// 添加账户信息管理的窗口
	private void accountMessageManage(ActionEvent e) {

		JInternalFrame addBankFrame = new JInternalFrame("\u8D26\u6237\u4FE1\u606F\u7BA1\u7406");
		addBankFrame.setIconifiable(true);
		addBankFrame.setClosable(true);
		addBankFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D \u94F6\u884C.png")));
		addBankFrame.setBounds(31, 43, 969, 599);
		desktopPane.add(addBankFrame);
		addBankFrame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 937, 349);
		addBankFrame.getContentPane().add(scrollPane);
		
		accountdate=BMS.findAccountAll(accountDao);
		accountTable = new JTable(new AccountModel(accountdate, customerDao));
		accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		accountTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 16));
		accountTable.setRowHeight(51);
		scrollPane.setViewportView(accountTable);

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setForeground(Color.BLUE);
		panel.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "                                              ",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBounds(10, 419, 937, 141);
		addBankFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u8D26\u6237\u4FE1\u606F\u7F16\u8F91");
		label.setBounds(10, 0, 154, 29);
		panel.add(label);
		label.setFont(new Font("宋体", Font.BOLD, 22));
		label.setForeground(Color.BLUE);

		JLabel label_1 = new JLabel("\u8D26\u6237\u7C7B\u578B\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(108, 50, 154, 44);
		panel.add(label_1);

		String[] choices = { "-请选择-", "储蓄账户", "信用账户", "定期账户", "外汇账户" };
		JComboBox accountTypecomboBox = new JComboBox(choices);
		accountTypecomboBox.setForeground(Color.BLUE);
		accountTypecomboBox.setFont(new Font("宋体", Font.BOLD, 25));
		accountTypecomboBox.setBounds(240, 56, 328, 40);
		panel.add(accountTypecomboBox);

		JTextField selectAccountText = new JTextField();
		selectAccountText.setFont(new Font("宋体", Font.BOLD, 25));
		selectAccountText.setColumns(10);
		selectAccountText.setBounds(241, 10, 328, 40);
		addBankFrame.getContentPane().add(selectAccountText);

		JButton selectAccountButton = new JButton("\u641C  \u7D22");
		selectAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = Tools.isUserName(selectAccountText.getText());
				if (flag) {
					List<Customer> data = BMS.findCustomerByName(selectAccountText.getText().replace(" ", ""),
							customerDao);
					if (data.size() != 0) {
						int cid = data.get(0).getC_id();
						accountdate = BMS.findAccountByCid(cid, accountDao);
						accountTable.setModel(new AccountModel(accountdate, customerDao));
					} else {
						accountTable.setModel(new AccountModel(new ArrayList<Account>(), customerDao));
					}
				} else {
					JOptionPane.showMessageDialog(null, "请输入账户持有者的名称或正确的账户持有者的名称");
				}
			}
		});
		selectAccountButton.setForeground(Color.BLUE);
		selectAccountButton.setFont(new Font("宋体", Font.BOLD, 25));
		selectAccountButton.setBounds(613, 10, 137, 40);
		addBankFrame.getContentPane().add(selectAccountButton);
		addBankFrame.setVisible(true);

		JButton modifButton = new JButton("\u4FEE  \u6539");
		modifButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (accountSelectedRow != -1) {
					String tyoe = (String) accountTypecomboBox.getSelectedItem();
					Account account = accountdate.get(accountSelectedRow);
					account.setA_type(tyoe);
					if (BMS.modifyAccount(account, accountDao)) {
						JOptionPane.showMessageDialog(null, "成功失败");
						accountTable.setModel(new AccountModel(accountdate, customerDao));
						accountTypecomboBox.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(null, "修改失败");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一个账户");
				}
			}
		});
		modifButton.setFont(new Font("宋体", Font.BOLD, 25));
		modifButton.setForeground(Color.BLUE);
		modifButton.setBounds(595, 54, 137, 40);
		panel.add(modifButton);

		JButton removeButton = new JButton("\u5220  \u9664");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (accountSelectedRow != -1) {
					if (JOptionPane.showConfirmDialog(null, "你是否确定要删除该账户？") == JOptionPane.OK_OPTION) {
						Account account = accountdate.get(accountSelectedRow);
						try {
							if (BMS.deleteAccount(account.getA_id(), accountDao)) {
								JOptionPane.showMessageDialog(null, "删除成功");
								accountdate.remove(account);
								accountTable.setModel(new AccountModel(accountdate, customerDao));
								accountTypecomboBox.setSelectedIndex(0);

							} else {
								JOptionPane.showMessageDialog(null, "删除失败");
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "删除失败，该账户下还有账单信息，请先注销该用户的所有账单在测试！！！");
						}
					} else {
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一个账户");
				}
			}
		});
		removeButton.setForeground(Color.BLUE);
		removeButton.setFont(new Font("宋体", Font.BOLD, 25));
		removeButton.setBounds(752, 54, 137, 40);
		panel.add(removeButton);


		JButton resettingButton = new JButton("\u91CD  \u7F6E");
		resettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountdate = BMS.findAccountAll(accountDao);
				accountTable.setModel(new AccountModel(accountdate, customerDao));
			}
		});
		resettingButton.setForeground(Color.BLUE);
		resettingButton.setFont(new Font("宋体", Font.BOLD, 25));
		resettingButton.setBounds(774, 10, 137, 40);
		addBankFrame.getContentPane().add(resettingButton);
		ListSelectionModel rowSelectionModel = accountTable.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 只处理鼠标释放
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				accountSelectedRow = lsm.getMinSelectionIndex();
				if (accountSelectedRow < 0) {
					return;
				}
				Account account = accountdate.get(accountSelectedRow);
				accountTypecomboBox.setSelectedItem(account.getA_type());
			}
		});
	}

	// 添加用户信息管理的窗口
	private void userMessageManage(ActionEvent e) {

		JInternalFrame addBankFrame = new JInternalFrame("\u7528\u6237\u4FE1\u606F\u7BA1\u7406");
		addBankFrame.setIconifiable(true);
		addBankFrame.setClosable(true);
		addBankFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D-57.png")));
		addBankFrame.setBounds(28, 10, 966, 654);
		desktopPane.add(addBankFrame);
		addBankFrame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 937, 349);
		addBankFrame.getContentPane().add(scrollPane);
		
		customerdate = BMS.findCustomerAll(customerDao);
		customerTable = new JTable(new CustomerModel(customerdate, bankDao));
		customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		customerTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 16));
		customerTable.setRowHeight(51);
		scrollPane.setViewportView(customerTable);

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setForeground(Color.BLUE);
		panel.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "                                              ",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBounds(10, 419, 934, 196);
		addBankFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F\u7F16\u8F91");
		label.setBounds(10, 0, 154, 29);
		panel.add(label);
		label.setFont(new Font("宋体", Font.BOLD, 22));
		label.setForeground(Color.BLUE);

		JLabel label_1 = new JLabel("\u59D3 \u540D\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(90, 33, 104, 44);
		panel.add(label_1);

		JTextField userNameText = new JTextField();
		userNameText.setFont(new Font("宋体", Font.BOLD, 25));
		userNameText.setBounds(176, 36, 234, 40);
		panel.add(userNameText);
		userNameText.setColumns(10);

		JLabel label_4 = new JLabel("\u7535 \u8BDD\uFF1A");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("宋体", Font.BOLD, 25));
		label_4.setBounds(90, 111, 104, 44);
		panel.add(label_4);

		JTextField phoneText = new JTextField();
		phoneText.setFont(new Font("宋体", Font.BOLD, 25));
		phoneText.setColumns(10);
		phoneText.setBounds(176, 115, 234, 40);
		panel.add(phoneText);

		JLabel label_5 = new JLabel("\u5730 \u5740\uFF1A");
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("宋体", Font.BOLD, 25));
		label_5.setBounds(446, 33, 104, 44);
		panel.add(label_5);

		JTextField addrText = new JTextField();
		addrText.setFont(new Font("宋体", Font.BOLD, 25));
		addrText.setColumns(10);
		addrText.setBounds(529, 37, 234, 40);
		panel.add(addrText);

		JLabel label_6 = new JLabel("\u94F6 \u884C\uFF1A");
		label_6.setForeground(Color.BLUE);
		label_6.setFont(new Font("宋体", Font.BOLD, 25));
		label_6.setBounds(446, 111, 104, 44);
		panel.add(label_6);

		JComboBox bankComboBox = new JComboBox(bankNames);
		bankComboBox.setBounds(529, 115, 234, 40);
		bankComboBox.setForeground(Color.BLUE);
		bankComboBox.setFont(new Font("宋体", Font.BOLD, 25));
		panel.add(bankComboBox);

		JLabel label_3 = new JLabel("\u7528\u6237\u540D\u79F0:");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("宋体", Font.BOLD, 22));
		label_3.setBounds(355, 15, 120, 29);
		addBankFrame.getContentPane().add(label_3);

		JComboBox userNameComboBox = new JComboBox();
		userNameComboBox.addItem("-请选择-");
		userNameComboBox.setBounds(463, 15, 120, 30);
		userNameComboBox.setForeground(Color.BLUE);
		userNameComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		addBankFrame.getContentPane().add(userNameComboBox);
		addBankFrame.setVisible(true);

		JLabel label_2 = new JLabel("\u94F6\u884C\u540D\u79F0:");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 22));
		label_2.setBounds(90, 15, 120, 29);
		addBankFrame.getContentPane().add(label_2);

		JComboBox bankNameComboBox = new JComboBox(bankNames);
		bankNameComboBox.setBounds(200, 15, 120, 30);
		bankNameComboBox.setForeground(Color.BLUE);
		bankNameComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		addBankFrame.getContentPane().add(bankNameComboBox);
		bankNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bankName = (String) bankNameComboBox.getSelectedItem();
				if (!bankName.equals("-请选择-")) {
					customerdate = customerDao.findByBid(bankDao.findByName(bankName).get(0).getB_id());
					userNames = Tools.getuserNames(customerdate);
					userNameComboBox.setModel(new DefaultComboBoxModel(userNames));
				} else {
					userNameComboBox.removeAllItems();
					userNameComboBox.addItem("-请选择-");
				}
			}
		});

		JButton selectUserButton = new JButton("\u641C  \u7D22");
		selectUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bankName = (String) bankNameComboBox.getSelectedItem();
				if (!bankName.equals("-请选择-")) {
					String userName = (String) userNameComboBox.getSelectedItem();
					if (!userName.equals("-请选择-")) {
						customerdate = BMS.findCustomerByName(userName, customerDao);
						customerTable.setModel(new CustomerModel(customerdate, bankDao));
					} else {
						JOptionPane.showMessageDialog(null, "请选择一名用户");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一个银行，在选择一名用户");
				}
			}
		});
		selectUserButton.setForeground(Color.BLUE);
		selectUserButton.setFont(new Font("宋体", Font.BOLD, 25));
		selectUserButton.setBounds(613, 10, 137, 40);
		addBankFrame.getContentPane().add(selectUserButton);

		JButton resettingButton = new JButton("\u91CD  \u7F6E");
		resettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerdate = BMS.findCustomerAll(customerDao);
				customerTable.setModel(new CustomerModel(customerdate, bankDao));
				bankNameComboBox.setSelectedIndex(0);
				userNameComboBox.setSelectedIndex(0);
				userNameText.setText("");
				phoneText.setText("");
				addrText.setText("");
				bankNameComboBox.setSelectedIndex(0);
			}
		});
		resettingButton.setForeground(Color.BLUE);
		resettingButton.setFont(new Font("宋体", Font.BOLD, 25));
		resettingButton.setBounds(770, 10, 137, 40);
		addBankFrame.getContentPane().add(resettingButton);

		JButton modifButton = new JButton("\u4FEE  \u6539");
		modifButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userSelectedRow != -1) {
					boolean flagName = Tools.isUserName(userNameText.getText());
					if (flagName) {
						boolean flagPhone = Tools.isPhoneNumber(phoneText.getText());
						if (flagPhone) {
							String addr = addrText.getText().replace(" ", "");
							if (!addr.equals("")) {
								String bankName = (String) bankComboBox.getSelectedItem();
								if (!bankName.equals("-请选择-")) {
									String name = userNameText.getText().replace(" ", "");
									String phone = phoneText.getText().replace(" ", "");
									int bankId = BMS.findBankByName(bankName, bankDao).get(0).getB_id();
									Customer customer = customerdate.get(userSelectedRow);
									customer.setC_name(name);
									customer.setC_tel(phone);
									customer.setC_addr(addr);
									customer.setB_id(bankId);
									if (BMS.modifyCustomer(customer, customerDao)) {
										JOptionPane.showMessageDialog(null, "修改成功");
										customerTable.setModel(new CustomerModel(customerdate, bankDao));
										userNameText.setText("");
										phoneText.setText("");
										addrText.setText("");
										bankNameComboBox.setSelectedIndex(0);
									} else {
										JOptionPane.showMessageDialog(null, "修改失败");
									}
								} else {
									JOptionPane.showMessageDialog(null, "修改失败,请选择银行");
								}
							} else {
								JOptionPane.showMessageDialog(null, "修改失败,用户地址不能为空");
							}
						} else {
							JOptionPane.showMessageDialog(null, "修改失败,用户电话号码不能为空且必须为11的数字");
						}
					} else {
						JOptionPane.showMessageDialog(null, "修改失败,用户姓名不能为空或不能含有数字，且姓名长度要大于一");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一名要修改的用户");
				}
			}
		});
		modifButton.setFont(new Font("宋体", Font.BOLD, 25));
		modifButton.setForeground(Color.BLUE);
		modifButton.setBounds(779, 35, 137, 40);
		panel.add(modifButton);

		JButton removeButton = new JButton("\u5220  \u9664");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userSelectedRow != -1) {
					if (JOptionPane.showConfirmDialog(null, "是否确定要删除该用户？") == JOptionPane.OK_OPTION) {
						Customer customer = customerdate.get(userSelectedRow);
						try {
							if (BMS.deleteCustomer(customer.getC_id(), customerDao)) {
								JOptionPane.showMessageDialog(null, "删除成功");
								customerdate.remove(customer);
								customerTable.setModel(new CustomerModel(customerdate, bankDao));
								userNameText.setText("");
								phoneText.setText("");
								addrText.setText("");
								bankComboBox.setSelectedIndex(0);
							} else {
								JOptionPane.showMessageDialog(null, "删除失败");
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "删除失败，该用户下还有账户，请先注销该用户的所有账户在测试！！！");
						}
					} else {
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一名要删除的用户");
				}
			}
		});
		removeButton.setForeground(Color.BLUE);
		removeButton.setFont(new Font("宋体", Font.BOLD, 25));
		removeButton.setBounds(779, 113, 137, 40);
		panel.add(removeButton);

		ListSelectionModel rowSelectionModel = customerTable.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 只处理鼠标释放
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				userSelectedRow = lsm.getMinSelectionIndex();
				if (userSelectedRow < 0) {
					return;
				}
				Customer customer = customerdate.get(userSelectedRow);
				userNameText.setText(customer.getC_name());
				phoneText.setText(customer.getC_tel());
				addrText.setText(customer.getC_addr());
				bankComboBox.setSelectedItem(bankDao.findById(customer.getB_id()));
			}
		});
	}

	// 添加银行信息管理的窗口
	private void bankMessageManage(ActionEvent e) {

		JInternalFrame addBankFrame = new JInternalFrame("\u94F6\u884C\u4FE1\u606F\u7BA1\u7406");
		addBankFrame.setIconifiable(true);
		addBankFrame.setClosable(true);
		addBankFrame.setFrameIcon(new ImageIcon(ManageFrame.class.getResource("/image/\u91D1\u878D \u94F6\u884C.png")));
		addBankFrame.setBounds(31, 43, 969, 599);
		desktopPane.add(addBankFrame);
		addBankFrame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 937, 349);
		addBankFrame.getContentPane().add(scrollPane);
		
		bankdate = BMS.findBankAll(bankDao);
		bankTable = new JTable(new BankModel(bankdate));
		bankTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bankTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		bankTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 16));
		bankTable.setRowHeight(51);
		scrollPane.setViewportView(bankTable);

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setForeground(Color.BLUE);
		panel.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "                                              ",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBounds(10, 419, 937, 141);
		addBankFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u94F6\u884C\u4FE1\u606F\u7F16\u8F91");
		label.setBounds(10, 0, 154, 29);
		panel.add(label);
		label.setFont(new Font("宋体", Font.BOLD, 22));
		label.setForeground(Color.BLUE);

		JLabel label_1 = new JLabel("\u94F6\u884C\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(120, 53, 154, 44);
		panel.add(label_1);

		JTextField bankNametext = new JTextField();
		bankNametext.setFont(new Font("宋体", Font.BOLD, 25));
		bankNametext.setBounds(250, 57, 328, 40);
		panel.add(bankNametext);
		bankNametext.setColumns(10);

		JButton modifbutton = new JButton("\u4FEE  \u6539");
		modifbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bankSelectedRow != -1) {
					boolean flag = Tools.isBankName(bankNametext.getText());
					if (flag) {
						Bank bank = bankdate.get(bankSelectedRow);
						bank.setB_name(bankNametext.getText().replace(" ", ""));
						if (BMS.modifyBank(bank, bankDao)) {
							JOptionPane.showMessageDialog(null, "修改成功");
							bankNametext.setText("");
							bankTable.setModel(new BankModel(bankdate));
						} else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					} else {
						JOptionPane.showMessageDialog(null, "修改失败，你输入的银行名称为空或者含有数字,不是以银行二字结尾");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一个银行");
				}
			}
		});
		modifbutton.setFont(new Font("宋体", Font.BOLD, 25));
		modifbutton.setForeground(Color.BLUE);
		modifbutton.setBounds(600, 55, 137, 40);
		panel.add(modifbutton);

		JButton removebutton = new JButton("\u5220  \u9664");
		removebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bankSelectedRow != -1) {
					Bank bank = bankdate.get(bankSelectedRow);
					if (JOptionPane.showConfirmDialog(null, "是否确定删除该银行？") == JOptionPane.OK_OPTION) {
						if (BMS.deleteBank(bank.getB_id(), bankDao, customerDao)) {
							JOptionPane.showMessageDialog(null, "删除成功");
							bankdate.remove(bank);
							bankNametext.setText("");
							bankTable.setModel(new BankModel(bankdate));
						} else {
							JOptionPane.showMessageDialog(null, "删除失败，该银行下还有用户，请先转出用户在测试！！！");
						}
					} else {
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择一个银行");
				}
			}
		});
		removebutton.setForeground(Color.BLUE);
		removebutton.setFont(new Font("宋体", Font.BOLD, 25));
		removebutton.setBounds(760, 57, 137, 40);
		panel.add(removebutton);

		JTextField selectBankText = new JTextField();
		selectBankText.setFont(new Font("宋体", Font.BOLD, 25));
		selectBankText.setColumns(10);
		selectBankText.setBounds(241, 10, 328, 40);
		addBankFrame.getContentPane().add(selectBankText);

		JButton selectBankbutton = new JButton("\u641C  \u7D22");
		selectBankbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = Tools.isBankName(selectBankText.getText());
				if (flag) {
					bankdate = BMS.findBankByName(selectBankText.getText().replace(" ", ""), bankDao);
					bankTable.setModel(new BankModel(bankdate));
				} else {
					JOptionPane.showMessageDialog(null, "请输入银行名称或正确的银行名称");
				}
			}
		});
		selectBankbutton.setForeground(Color.BLUE);
		selectBankbutton.setFont(new Font("宋体", Font.BOLD, 25));
		selectBankbutton.setBounds(613, 10, 137, 40);
		addBankFrame.getContentPane().add(selectBankbutton);
		addBankFrame.setVisible(true);

		JButton resettingButton = new JButton("\u91CD  \u7F6E");
		resettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bankdate = BMS.findBankAll(bankDao);
				bankTable.setModel(new BankModel(bankdate));
			}
		});
		resettingButton.setForeground(Color.BLUE);
		resettingButton.setFont(new Font("宋体", Font.BOLD, 25));
		resettingButton.setBounds(778, 10, 137, 40);
		addBankFrame.getContentPane().add(resettingButton);
		ListSelectionModel rowSelectionModel = bankTable.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 只处理鼠标释放
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				bankSelectedRow = lsm.getMinSelectionIndex();
				if (bankSelectedRow < 0) {
					return;
				}
				Bank bank = bankdate.get(bankSelectedRow);
				bankNametext.setText(bank.getB_name());
			}
		});
	}
}