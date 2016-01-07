package com.pro.securitymanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame f = new ClassLoaderFrame();
		f.setVisible(true);
	}

}

class ClassLoaderFrame extends JFrame {
	public ClassLoaderFrame() {
		setTitle("ClassLoaderTest");
		setSize(300, 200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 0;
		constraint.weighty = 100;
		constraint.fill = GridBagConstraints.NONE;
		constraint.anchor = GridBagConstraints.EAST;
		add(new JLabel("Class"), constraint, 0, 0, 1, 1);
		add(new JLabel("Key"), constraint, 0, 1, 1, 1);
		constraint.weightx = 100;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.anchor = GridBagConstraints.CENTER;

		add(nameField, constraint, 1, 0, 1, 1);
		add(keyField, constraint, 1, 1, 1, 1);

		constraint.fill = GridBagConstraints.NONE;
		constraint.anchor = GridBagConstraints.CENTER;
		JButton but = new JButton("Load");
		but.setBackground(Color.blue);
		add(but, constraint, 0, 2, 2, 1);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runClass(nameField.getText(), keyField.getText());
			}
		});
	}

	private JTextField nameField = new JTextField(30);
	private JTextField keyField = new JTextField("3", 4);

	private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
	private int key;

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(c, gbc);
	}

	public void runClass(String name, String key) {
		ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
		try {
			Class<?> c = loader.loadClass(name);
			String[] args = new String[] {};
			Method m = c.getMethod("main", new Class[] { args.getClass() });
			m.invoke(null, new Object[] { args });
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	class CryptoClassLoader extends ClassLoader {
		public CryptoClassLoader(int k) {
			key = k;
		}

		protected synchronized Class<?> loadClass(String name, boolean resolve)
				throws ClassNotFoundException {
			Class<?> c1 = classes.get(name);
			if (c1 == null) {
				return findSystemClass(name);
			}
			byte[] classbytes = loadClassBytes(name);
			if (classbytes == null) {
				throw new ClassNotFoundException(name);
			}
			c1 = defineClass(name, classbytes, 0, classbytes.length); // 往虚拟机装入一个新类
			if (c1 == null) {
				throw new ClassNotFoundException(name);
			}
			classes.put(name, c1);
			if (resolve == true) {
				resolveClass(c1);
			}
			return c1;
		}

		public byte[] loadClassBytes(String name) {
			String cname = name.replace(".", "/") + ".caesar";
			FileInputStream in = null;
			try {
				in = new FileInputStream(cname);
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int ch;
				while ((ch = in.read()) != -1) {
					byte b = (byte) (ch - key); // key为解密秘钥 key传统上定义为3 Caesar密码
												// key可以取1-255之间的值
					buffer.write(b);
				}
				return buffer.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}
}