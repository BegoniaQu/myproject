package com.pro.securitymanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Mylayout {

	public static void main(String[] args) {

		gridbaglayout();
	}

	static void gridbaglayout() {
		JFrame f = new JFrame("GridBagLayout");
		f.setLayout(new GridBagLayout());
		JButton btn = new JButton("first");
		GridBagConstraints gbc = new GridBagConstraints();
		// 设定第一个单元格的属性值
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		f.add(btn, gbc);

		// 设定第二个单元格属性值
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		btn = new JButton("second");
		f.add(btn, gbc);

		// 设定第三个单元格属性值
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.ipadx = 10;
		gbc.ipady = 10;
		btn = new JButton("three");
		f.add(btn, gbc);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	static void gridlayout() {
		JFrame f = new JFrame("GridLayout");
		f.setSize(300, 200);
		// 设置f的布局管理器为3行3列的GridLayout,组件间水平与垂直间距为5
		f.setLayout(new GridLayout(4, 3, 5, 5));
		for (int i = 1; i < 10; i++) {
			JButton btn = new JButton(String.valueOf(i));
			f.add(btn);
		}

		// f.add(one,0);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	static void borderlayout() {
		// 建立一个JFrame,JFrame的默认LayoutManager为BorderLayout
		JFrame f = new JFrame("BorderLayout");
		f.setLayout(new BorderLayout(10, 10));

		JButton btn = new JButton("BorderLayout.NORTH");
		f.add(btn, BorderLayout.NORTH);
		btn = new JButton("BorderLayout.SOUTH");
		f.add(btn, BorderLayout.SOUTH);
		btn = new JButton("BorderLayout.EAST");
		f.add(btn, BorderLayout.EAST);
		btn = new JButton("BorderLayout.West");
		f.add(btn, BorderLayout.WEST);
		btn = new JButton("BorderLayout.CENTER");
		f.add(btn, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	static void flowlayout() {
		JFrame f = new JFrame("FlowLayout");
		f.setLayout(new FlowLayout());
		for (int i = 0; i < 7; i++) {
			JButton btn = new JButton("Button" + i);
			f.add(btn);
		}
		f.setSize(300, 150);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
