package client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

class BankListener implements ActionListener {
		
		private final ClientCommunicator com;
		private JTextField f1;
		private JPasswordField f2;
		private Screen screen;
		
		BankListener(JPanel panel, Screen screen, ClientCommunicator com) {
			
			Component[] comps = panel.getComponents();
			this.f1 = (JTextField) comps[0];
			this.f2 = (JPasswordField) comps[2];
			this.screen = screen;
			this.com = com;
			
		}
		
		public void actionPerformed(ActionEvent e) {
		
			try {
				com.writeLogin(Integer.parseInt(f1.getText()), new String(f2.getPassword()));
				if (com.checkLogin()) {
					screen = Screen.createAccountScreen(com.getAccountDetails());
					screen.updateScreen();
				} else {
					f1.setText("LOGIN FAILED");
					f2.setText(null);
				}
			} catch (IOException io) {
				io.printStackTrace();
			}
			
		}
		
	}

class PF_Listener implements KeyListener {

	private final JPasswordField jpf;
	
	PF_Listener(JPasswordField jpf) {

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

class UF_Listener implements KeyListener {

	private final JTextField jtf;
	private boolean toDo;

	UF_Listener(JTextField jtf) {

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
