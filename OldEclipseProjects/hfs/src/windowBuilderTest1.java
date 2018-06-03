import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class windowBuilderTest1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String num1 = "", num2 = "", answer = "", operator;
	private boolean firstEntry = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowBuilderTest1 frame = new windowBuilderTest1();
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public windowBuilderTest1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// adding the buttons
		JButton nine = new JButton("9");
		nine.setBounds(208, 102, 89, 23);
		contentPane.add(nine);

		JButton eight = new JButton("8");
		eight.setBounds(107, 102, 89, 23);
		contentPane.add(eight);

		JButton seven = new JButton("7");
		seven.setBounds(10, 102, 89, 23);
		contentPane.add(seven);

		JButton six = new JButton("6");
		six.setBounds(208, 136, 89, 23);
		contentPane.add(six);

		JButton five = new JButton("5");
		five.setBounds(107, 136, 89, 23);
		contentPane.add(five);

		JButton four = new JButton("4");
		four.setBounds(10, 136, 89, 23);
		contentPane.add(four);

		JButton three = new JButton("3");
		three.setBounds(208, 170, 89, 23);
		contentPane.add(three);

		JButton two = new JButton("2");
		two.setBounds(107, 170, 89, 23);
		contentPane.add(two);

		JButton one = new JButton("1");
		one.setBounds(10, 170, 89, 23);
		contentPane.add(one);

		JButton zero = new JButton("0");
		zero.setBounds(10, 204, 89, 23);
		contentPane.add(zero);

		// adding operators
		JButton add = new JButton("+");
		add.setBounds(307, 96, 50, 131);
		contentPane.add(add);

		JButton subtract = new JButton("-");
		subtract.setBounds(367, 91, 53, 44);
		contentPane.add(subtract);

		JButton multiply = new JButton("*");
		multiply.setBounds(307, 41, 50, 44);
		contentPane.add(multiply);

		JButton divide = new JButton("/");
		divide.setBounds(370, 41, 50, 44);
		contentPane.add(divide);

		JButton Enter = new JButton("=");
		Enter.setBounds(370, 136, 50, 91);
		contentPane.add(Enter);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(10, 36, 287, 55);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel(" fx-1MS ");
		lblNewLabel.setBounds(10, 0, 89, 25);
		contentPane.add(lblNewLabel);

		zero.addActionListener(new ButtonListener());
		one.addActionListener(new ButtonListener());
		two.addActionListener(new ButtonListener());
		three.addActionListener(new ButtonListener());
		four.addActionListener(new ButtonListener());
		five.addActionListener(new ButtonListener());
		six.addActionListener(new ButtonListener());
		seven.addActionListener(new ButtonListener());
		eight.addActionListener(new ButtonListener());
		nine.addActionListener(new ButtonListener());
		add.addActionListener(new operatorListener());
		multiply.addActionListener(new operatorListener());
		divide.addActionListener(new operatorListener());
		subtract.addActionListener(new operatorListener());
		Enter.addActionListener(new resultListener());

	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton pressedButton = (JButton) e.getSource();
			String name = pressedButton.getText();

			if (firstEntry) {
				num1 = num1.concat(name);
				textField.setText(num1);
			} else {
				num2 = num2.concat(name);
				textField.setText(num2);
			}

		}

	}

	class operatorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			firstEntry = false;
			JButton pressedButton = (JButton) e.getSource();
			operator = pressedButton.getText();
			textField.setText("");

		}

	}

	class resultListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer result;
			switch (operator) {
			case "+":
				result = Integer.parseInt(num1) + Integer.parseInt(num2);
				answer = result.toString();
				textField.setText(answer);
				num1 = answer;
				num2 = "";
				break;
			case "-":
				result = Integer.parseInt(num1) - Integer.parseInt(num2);
				answer = result.toString();
				textField.setText(answer);
				num1 = answer;
				num2 = "";
				break;
			case "*":
				result = Integer.parseInt(num1) * Integer.parseInt(num2);
				answer = result.toString();
				textField.setText(answer);
				num1 = answer;
				num2 = "";
				break;
			case "/":
				Double newResult = (Double.parseDouble(num1))
						/ (Double.parseDouble(num2));
				answer = newResult.toString();
				textField.setText(answer);
				num1 = answer;
				num2 = "";
				break;
			default:
				textField.setText("sysntax error!");
				break;

			}

		}

	}
}
