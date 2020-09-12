import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class simpleGui {
	JFrame frame;
	JButton button1, button3, button2;
	JLabel label;
	myPanel panel;
	String[] text = { "yay!", "nay!" };

	public simpleGui() {
		frame = new JFrame();
		button1 = new JButton(text[0]);
		button2 = new JButton(text[0]);
		button3 = new JButton(text[0]);
		panel = new myPanel();
		label = new JLabel("im a label!");
		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());
		button3.addActionListener(new ButtonListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(button1, BorderLayout.NORTH);
		frame.getContentPane().add(button2, BorderLayout.WEST);
		frame.getContentPane().add(button3, BorderLayout.SOUTH);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(label, BorderLayout.EAST);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			toggleButton(button);
			panel.repaint();

		}

	}

	class LabelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panel.repaint();

		}

	}

	private void toggleButton(JButton button) {
		if (button.getText().equals(text[0])) {
			button.setText(text[1]);
		} else {
			button.setText(text[0]);
		}

	}

	public static void main(String[] args) {
		simpleGui g = new simpleGui();
	}

}

class myPanel extends JPanel {
	Color color;

	public void paintComponent(Graphics g) {
		int red = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		color = new Color(red, green, blue);
		g.setColor(color);
		int width = this.getWidth();
		int height = this.getHeight();
		g.fillOval(15, 15, width / 2, height / 2);
	}
}