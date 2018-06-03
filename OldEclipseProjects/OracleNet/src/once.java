import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class once extends JFrame{
	
	once(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,50,50);
		
		JPanel cp = new JPanel();
		JButton b = new JButton("click me !");
		b.addActionListener(new ButtonListener(b));
		cp.setLayout(new BorderLayout());
		cp.add(b,BorderLayout.CENTER);
		setContentPane(cp);
		setVisible(true);
	}
	

	
	public static void main(String[] args){
		new once();
	}
	
}
class ButtonListener implements ActionListener {
	JButton B;
	public ButtonListener(JButton B){
		this.B = B;
	}
	public void actionPerformed(ActionEvent e){
		//if(e.getSource() instance of JButton) { 
			try { Thread.sleep(2000); } catch(InterruptedException ex){}
			B.setText("button pressed, exiting in 2 seconds");
			
			System.out.println("exiting.");
		//}
		System.exit(0);
	}
}