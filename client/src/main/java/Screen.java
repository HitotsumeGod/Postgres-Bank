import java.awt.*;
import javax.swing.*;

class Screen extends JFrame {

	private Screen() {

		JPanel panel = new JPanel();
		JPanel loginPanel = new JPanel();
		JLabel title = new JLabel("PeterBank Basic Frontend");
		JTextField userf = new JTextField("                                                   ");
		JTextField passf = new JTextField("                                                   ");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		title.setPreferredSize(new Dimension(300, 100));
		loginPanel.add(userf);
		loginPanel.add(passf);
		loginPanel.setPreferredSize(new Dimension(200, 200));
		panel.add(title);
		panel.add(loginPanel);
		this.add(panel);
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
