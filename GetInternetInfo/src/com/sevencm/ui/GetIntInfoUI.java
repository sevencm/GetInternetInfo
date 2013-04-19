package com.sevencm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import com.sevencm.method.GetInfoMethod;
import com.sevencm.method.IGetInfoMethod;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Set;

public class GetIntInfoUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetIntInfoUI frame = new GetIntInfoUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GetIntInfoUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				"\u83B7\u53D6\u4FE1\u606F\u6E90", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 888, 97);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblhttp = new JLabel("\u5730\u5740\uFF08\u542Bhttp://\uFF09");
		lblhttp.setBounds(10, 43, 102, 15);
		panel.add(lblhttp);
		textField = new JTextField();
		textField.setBounds(110, 40, 655, 21);
		panel.add(textField);
		textField.setColumns(10);
		JButton btnNewButton = new JButton("\u83B7\u53D6\u4FE1\u606F");
		btnNewButton.setBounds(775, 38, 95, 25);
		panel.add(btnNewButton);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "\u4FE1\u606F\u8F93\u51FA",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(10, 117, 888, 246);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 868, 155);
		panel_1.add(scrollPane);
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		JButton btnNewButton_1 = new JButton("\u8F93\u51FAtxt");
		btnNewButton_1.setBounds(769, 197, 95, 25);
		panel_1.add(btnNewButton_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "\u6570\u636E\u7EDF\u8BA1",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(10, 373, 888, 110);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.BLACK);
		textArea_1.setBounds(10, 20, 868, 80);
		panel_2.add(textArea_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getUrl = textField.getText();
				// System.out.println(getUrl);
				IGetInfoMethod getInfoMethod = new GetInfoMethod();
				String str = getInfoMethod.GetResponseDataByID(getUrl);
				// System.out.println(str);
				Set<String> urlSet = getInfoMethod.getURL(str);
				int i = 0;
				for (Object url : urlSet) {
					String urlHtml = (String) url;
					// System.out.println(urlHtml);
					String strGetI = getInfoMethod.GetResponseDataByID(urlHtml);
					// System.out.println(strGetI);
					String wantedInfo = getInfoMethod.getCompanyInfo(strGetI);
					i++;
					// System.out.println(wantedInfo);
					textArea.setText(textArea.getText() + wantedInfo);
				}
			}
		});
	}
}
