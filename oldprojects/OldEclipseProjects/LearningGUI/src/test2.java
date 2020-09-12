import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test2 {
	JFrame frame = new JFrame();
	JButton b = new JButton();
	MyPanel p = new MyPanel();

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			while(true){
				try {
					frame.repaint();
					Thread.sleep(500);
				} catch (InterruptedException exp) {
					exp.printStackTrace();
				}
			}
		}
	}

	public void go() {
		b.addActionListener(new ButtonListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.CENTER, p);
		frame.add(BorderLayout.SOUTH, b);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		(new test2()).go();
	}

}

class MyPanel extends JPanel {

	public void paintComponent(Graphics gr) {
		gr.fillRect(0, 0, this.getWidth(), this.getHeight());

		int r, g, b, x, y;
		r = (int) (Math.random() * 256);
		g = (int) (Math.random() * 256);
		b = (int) (Math.random() * 256);
		x = (int) (Math.random() * (this.getWidth() - 15 ));
		y = (int) (Math.random() * (this.getHeight() - 15));

		Color customColor = new Color(r, g, b);
		gr.setColor(customColor);
		gr.fillOval(x, y, 30, 30);
	}
}