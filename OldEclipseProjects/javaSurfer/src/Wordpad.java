import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;

public class Wordpad extends JFrame {

	private JPanel contentPane;
	JTextArea textArea;
	File myFile;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wordpad frame = new Wordpad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Wordpad() {

		super("untitled - Wordpad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem menuItem_0 = new JMenuItem("New");
		menuItem_0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));
		file.add(menuItem_0);

		JMenuItem menuItem_1 = new JMenuItem("Open");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		menuItem_1.addActionListener(new openActionListener());
		file.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		menuItem_2.addActionListener(new saveActionListener());
		file.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("Save As");
		menuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
		menuItem_3.addActionListener(new saveAsActionListener());

		file.add(menuItem_3);

		file.add(new JSeparator(SwingConstants.HORIZONTAL));

		JMenuItem menuItem_4 = new JMenuItem("Page Setup");
		file.add(menuItem_4);
		
		JMenuItem mntmPrintPreview = new JMenuItem("Print Preview");
		file.add(mntmPrintPreview);

		JMenuItem menuItem_5 = new JMenuItem("Print");
		menuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_DOWN_MASK));
		menuItem_5.addActionListener(new printActionListener());
		file.add(menuItem_5);
		file.add(new JSeparator(SwingConstants.HORIZONTAL));

		JMenuItem menuItem_6 = new JMenuItem("Exit");
		file.add(menuItem_6);
		contentPane = new JPanel(); // creating custom content pane.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}

	private class openActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			openFile();
		}
	}

	private class saveActionListener implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent e) {
			fileSave();
		}
	}

	private class saveAsActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fileSaveAs();
		}
	}
	
	private class printActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			filePrint();
		}
	}

	private void openFile() {
		BufferedReader fileReader = null; // without this initialization , try{}
											// inside finally{} would give error

		JFileChooser fc = new JFileChooser();
		int option = fc.showOpenDialog(null);
		myFile = fc.getSelectedFile();
		System.out.println("path of file opened : " + myFile.toString()); // debug
																			// statement
		try {
			fileReader = new BufferedReader(new FileReader(myFile));

			String line = null;
			StringBuilder str = new StringBuilder("");// stringbuffer's unsynch
														// version. used for
														// complex string
														// concats.
			while ((line = fileReader.readLine()) != null) {
				str.append(line); // wordpad characteristics
				str.append("\n");
			}
			textArea.setText(str.toString());
			setTitle(myFile.getName() + " - Notepad");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

	}

	private void fileSave() {

		if (myFile.toString().equals("")) {
			System.out.println("doing Save As");
			fileSaveAs();
		} else {
			System.out.println("just saving");
			fileSave(myFile);
		}

	}

	private void fileSave(File myFile) {
		try {
			// normally would open FileWriter in append mode , but in this case , would
			// give a copy of the original content , as during saving , that
			// content is already present in the JTextArea
			FileWriter fw = new FileWriter(myFile);// no "true" 2nd argument for append mode
			fw.write(textArea.getText());
			setTitle(myFile.getName() + " - Notepad");
			fw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Failed to save the file",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void fileSaveAs() {
		JFileChooser fc = new JFileChooser();

		int option = fc.showSaveDialog(null);
		if (option == JFileChooser.CANCEL_OPTION)
			return;

		myFile = fc.getSelectedFile();
		if (myFile == null || myFile.getName().equals("")) {
			JOptionPane.showMessageDialog(this, "enter file name", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (myFile.exists()) {
			if ((JOptionPane.showConfirmDialog(this,
					"file name already exists!\n sure you want to overwrite?")) != 0)
				return;
		}
		fileSave(myFile);

	}
	
	public void filePrint() {
		PrinterJob printer = PrinterJob.getPrinterJob();
		//HashPrintRequestAttributeSet pAttrib = new HashPrintRequestAttributeSet();//putting this inside printDialog() does not give functional properties button	
		if (printer.printDialog()) // standalone , however , this does
		{ 
			try {
				printer.print(); 
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}

}