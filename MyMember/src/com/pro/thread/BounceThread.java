package com.pro.thread;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BounceThread {

	public static void main(String[] args) {
		JFrame frame = new BounceThreadFrame();
		frame.setVisible(true);

	}
}

class BounceThreadFrame extends JFrame {
	BounceThreadFrame() {
		setSize(300, 200);
		setTitle("Bounce");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container contentpane = getContentPane();
		canvas = new JPanel();
		contentpane.add(canvas, "Center");

		JPanel p = new JPanel();
		addButton(p, "start", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ball ball = new Ball(canvas, Color.blue);
				ball.start();
			}
		});

		addButton(p, "express", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					Ball ball = new Ball(canvas, Color.red);
					ball.setPriority(10);
					ball.start();
				}
			}
		});

		addButton(p, "selfish", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					Ball ball = new SelfishBall(canvas, Color.pink);
					ball.start();
				}
			}
		});

		addButton(p, "close", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setVisible(false);
				System.exit(0);
			}
		});
		contentpane.add(p, "South");
	}

	private JPanel canvas;

	public void addButton(Container c, String title, ActionListener a) {
		JButton jb = new JButton(title);
		c.add(jb);
		jb.addActionListener(a);
	}
}

class SelfishBall extends Ball {

	SelfishBall(JPanel arg0, Color arg1) {
		super(arg0, arg1);
	}

	public void run() {
		draw();
		for (int i = 0;; i++) {
			move();
			long t = System.currentTimeMillis();
			while (System.currentTimeMillis() < t + 50) {
				System.out.println("ddd");
			}
		}
	}
}

class Ball extends Thread {
	private JPanel box;
	private static final int XSIZE = 10;
	private static final int YSIZE = 10;
	private int x = 0;
	private int y = 0;
	private int dx = 8;
	private int dy = 8;
	private Color color;

	Ball(JPanel jp, Color c) {
		box = jp;
		color = c;
	}

	public void draw() {
		Graphics g = box.getGraphics();
		g.setColor(color);
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();

	}

	public void run() {
		draw();
		for (int i = 0;; i++) {
			move();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void move() {

		if (!box.isVisible()) {
			return;
		}
		Graphics g = box.getGraphics();
		g.setColor(color);
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);

		x += dx;
		y += dy;
		Dimension d = box.getSize();
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + XSIZE >= d.width) {
			x = d.width - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + YSIZE >= d.height) {
			y = d.height - YSIZE;
			dy = -dy;
		}
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}
}
