import javax.swing.*;
import java.awt.event.*;
public class test1{
	JFrame frame = new JFrame();
	JButton b = new JButton();
	public class ButtonListener implements ActionListener{
		int count = 0;
		@Override
		public void actionPerformed(ActionEvent e){
			b.setText("You have clicked me " + (++count) + " times!");
		}
	}
	public void go(){
		b.addActionListener(new ButtonListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.getContentPane().add(b);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	public static void main(String[] args){
		(new test1()).go();
	}
}