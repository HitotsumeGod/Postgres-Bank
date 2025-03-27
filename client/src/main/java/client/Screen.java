package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

class Screen extends JFrame {
	
	private class BankListener implements ActionListener {
		
		private String username;
		private String password;
		private Component[] comps;
		
		private BankListener(JPanel panel) {
			
			this.comps = panel.getComponents();
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			this.username = ((JTextField) comps[0]).getText();
			this.password = new String(((JPasswordField) comps[2]).getPassword());
			com = NetCom.getCom("127.0.0.1", 4445);
			com.getConnection();
			try {
				com.writeLogin(Integer.parseInt(username), password);
			} catch (IOException io) {
				io.printStackTrace();
			}
			
		}
		
	}

	private class BKL implements KeyListener {

		private final JPasswordField jpf;

		private BKL(JPasswordField jpf) {

			this.jpf = jpf;

		}

		public void keyPressed(KeyEvent key) {

			if (jpf.getEchoChar() != '*') {
				jpf.setText(null);
				jpf.setEchoChar('*');
				jpf.updateUI();
			}

		}

		public void keyTyped(KeyEvent key) {}

		public void keyReleased(KeyEvent key) {}

	}
	
	private static final int STRUTHEIGHT = 25;
	private static final int STRUTWIDTH = 25;
	private NetCom com;
	private Account_Template at;

	private Screen() {

		Font libera = new Font("Nimbus Mono PS", Font.BOLD, 16);
		JPanel panel = new JPanel();
		JPanel loginPanel = new JPanel();
		JLabel title = new JLabel("PeterBank Basic Frontend");
		JLabel imagel = new JLabel(new ImageIcon("src/resources/bankimage.jpg"));
		JTextField userf = new JTextField("Username");
		JPasswordField passf = new JPasswordField("Password");
		JButton loginButton = new JButton("LOGIN");
		EmptyBorder viewBorder = new EmptyBorder(0, 50, 50, 50);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		title.setPreferredSize(new Dimension(300, 100));
		title.setFont(libera);
		title.setAlignmentX(CENTER_ALIGNMENT);
		imagel.setAlignmentX(CENTER_ALIGNMENT);
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		loginButton.setFont(libera);
		passf.setEchoChar((char) 0);
		passf.addKeyListener(new BKL(passf));
		loginPanel.add(userf);
		loginPanel.add(Box.createVerticalStrut(STRUTHEIGHT));
		loginPanel.add(passf);
		loginPanel.add(Box.createVerticalStrut(STRUTHEIGHT));
		loginButton.addActionListener(new BankListener(loginPanel));
		loginPanel.add(loginButton);
		loginPanel.setPreferredSize(new Dimension(200, 200));
		loginPanel.setBorder(viewBorder);
		panel.add(title);
		panel.add(imagel);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.NORTH);
		this.add(loginPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void updateScreen() {

		this.pack();
		this.setVisible(true);

	}

	public static Screen getScreen() {

		return new Screen();

	}

}
