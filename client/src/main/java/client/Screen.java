package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.ArrayList;

class Screen extends JFrame {
	
	private static final int STRUTHEIGHT = 25;
	private static final int STRUTWIDTH = 25;
	private NetCom com;
	private Account_Template at;
	
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
			com = NetCom.getCom("127.0.0.1", 6666);
			com.getConnection();
			try {
				com.writeLogin(Integer.parseInt(username), password);
				if (com.checkLogin())
					at = com.getAccountDetails();
			} catch (IOException io) {
				io.printStackTrace();
			}
			
		}
		
	}

	private class PF_Listener implements KeyListener {

		private final JPasswordField jpf;

		private PF_Listener(JPasswordField jpf) {

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
	
	private class UF_Listener implements KeyListener {

		private final JTextField jtf;
		private boolean toDo;

		private UF_Listener(JTextField jtf) {

			this.jtf = jtf;
			toDo = true;

		}

		public void keyPressed(KeyEvent key) {

				if (toDo) {
					jtf.setText(null);
					jtf.updateUI();
					toDo = false;
				}

		}

		public void keyTyped(KeyEvent key) {}

		public void keyReleased(KeyEvent key) {}

	}
	
	private Screen() {}
	
	public static Screen createLoginScreen() {
		
		Screen newScreen = new Screen();
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
		//userf.addKeyListener(new UF_Listener(userf));
		passf.setEchoChar((char) 0);
		//passf.addKeyListener(new PF_Listener(passf));
		loginPanel.add(userf);
		loginPanel.add(Box.createVerticalStrut(STRUTHEIGHT));
		loginPanel.add(passf);
		loginPanel.add(Box.createVerticalStrut(STRUTHEIGHT));
		//loginButton.addActionListener(new BankListener(loginPanel));
		loginPanel.add(loginButton);
		loginPanel.setPreferredSize(new Dimension(200, 200));
		loginPanel.setBorder(viewBorder);
		panel.add(title);
		panel.add(imagel);
		newScreen.setLayout(new BorderLayout());
		newScreen.add(panel, BorderLayout.NORTH);
		newScreen.add(loginPanel, BorderLayout.CENTER);
		newScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return newScreen;
		
	}
	
	public static Screen createAccountScreen(Account_Template at) {
		
		Screen newScreen = new Screen();
		JPanel namePanel, accountIDPanel, balancePanel, linkedAccountsPanel, interestRatePanel;
		JLabel nameTitleLabel, accountTitleLabel, balanceTitleLabel, accountsTitleLabel, interestTitleLabel, nameLabel, accountLabel, balanceLabel, accountsLabel, interestLabel;
		Font libera = new Font("Nimbus Mono PS", Font.BOLD, 16);
		ArrayList<JLabel> labelList = new ArrayList<>();
		
		namePanel = accountIDPanel = balancePanel = linkedAccountsPanel = interestRatePanel = null;
		nameTitleLabel = accountTitleLabel = balanceTitleLabel = accountsTitleLabel = interestTitleLabel = nameLabel = accountLabel = balanceLabel = accountsLabel = interestLabel = null;
		namePanel = new JPanel(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		accountIDPanel = new JPanel(new BoxLayout(accountIDPanel, BoxLayout.Y_AXIS));
		balancePanel = new JPanel(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
		linkedAccountsPanel = new JPanel(new BoxLayout(linkedAccountsPanel, BoxLayout.Y_AXIS));
		interestRatePanel = new JPanel(new BoxLayout(interestRatePanel, BoxLayout.Y_AXIS));
		nameTitleLabel = new JLabel("Customer Full Name :");
		accountTitleLabel = new JLabel("Customer Account Identification Number :");
		balanceTitleLabel = new JLabel("Customer Account Balance (USD) :");
		accountsTitleLabel = new JLabel("Linked Accounts :");
		interestTitleLabel = new JLabel("Account Interest Percentage :");
		nameLabel = new JLabel(at.getLastName() + ", " + at.getFirstName());
		accountLabel = new JLabel(String.valueOf(at.getAccountID()));
		balanceLabel = new JLabel(String.valueOf(at.getBalance()));
		interestLabel = new JLabel(String.valueOf(at.getInterestRate()));
		labelList.add(nameTitleLabel);
		labelList.add(accountTitleLabel);
		labelList.add(balanceTitleLabel);
		labelList.add(accountsTitleLabel);
		labelList.add(interestTitleLabel);
		labelList.add(nameLabel);
		labelList.add(accountLabel);
		labelList.add(balanceLabel);
		labelList.add(accountsLabel);
		labelList.add(interestLabel);
		for (JLabel l : labelList)
			if (l != null)
				l.setFont(libera);
		newScreen.add(namePanel);
		newScreen.add(accountIDPanel);
		newScreen.add(balancePanel);
		newScreen.add(linkedAccountsPanel);
		newScreen.add(interestRatePanel);
		newScreen.setLayout(new GridLayout(5, 2));
		return newScreen;
		
		
	}

	public void updateScreen() {

		this.pack();
		this.setVisible(true);

	}

}
