package org.cqipc.edu.luosen.mainapp;

import java.awt.EventQueue;

import org.cqipc.edu.luosen.view.ManageFrame;

/**
 * BankManageSystemApplication∆Ù∂Ø≥Ã–Ú
 * @author luosen
 *
 */
public class MainApp {
	/**
	 * Launch the BankManageSystemApplication.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageFrame window = new ManageFrame();
					window.frmManage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
