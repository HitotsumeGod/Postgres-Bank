package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Screen extends JFrame {
	
	private class BankListener implements ActionListener {
		
		private String username;
		private String password;
		private Component[] comps;
		
		private BankListener(JPanel panel) {
			
			this.comps = panel.getComponents();
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			this.username = ((JTextField) comps[0]).getText().trim();
			this.password = ((JTextField) comps[2]).getText().trim();
			System.out.print(username + '\n' + password + '\n');
			
		}
		
	}
	
	private static final int STRUTHEIGHT = 25;
	private static final int STRUTWIDTH = 25;

	private Screen() {

		Font libera = new Font("Nimbus Mono PS", Font.BOLD, 16);
		JPanel panel = new JPanel();
		JPanel loginPanel = new JPanel();
		JLabel title = new JLabel("PeterBank Basic Frontend");
		JTextField userf = new JTextField("                                                   ");
		JTextField passf = new JTextField("                                                   ");
		JButton loginButton = new JButton("LOGIN");
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		title.setPreferredSize(new Dimension(300, 100));
		title.setFont(libera);
		title.setAlignmentX(CENTER_ALIGNMENT);
		loginButton.setFont(libera);
		loginPanel.add(userf);
		loginPanel.add(Box.createVerticalStrut(STRUTHEIGHT));
		loginPanel.add(passf);
		loginPanel.add(Box.createVerticalStrut(STRUTHEIGHT));
		loginButton.addActionListener(new BankListener(loginPanel));
		loginPanel.add(loginButton);
		loginPanel.setPreferredSize(new Dimension(200, 200));
		panel.add(title);
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
