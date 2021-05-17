package library;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Library {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library window = new Library();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Library() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0,1306 , 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(18, 30, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setBounds(18, 60, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title");
		lblNewLabel_2.setBounds(18, 90, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Language");
		lblNewLabel_2_1.setBounds(18, 120, 61, 16);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Genre");
		lblNewLabel_2_2.setBounds(18, 150, 61, 16);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Release Date");
		lblNewLabel_2_3.setBounds(18, 180, 111, 16);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Retail Price");
		lblNewLabel_2_4.setBounds(18, 210, 111, 16);
		frame.getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Quantity");
		lblNewLabel_2_5.setBounds(18, 240, 111, 16);
		frame.getContentPane().add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("Addtional Info 1");
		lblNewLabel_2_6.setBounds(18, 270, 111, 16);
		frame.getContentPane().add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_6_1 = new JLabel("Addtional Info 2");
		lblNewLabel_2_6_1.setBounds(18, 300, 111, 16);
		frame.getContentPane().add(lblNewLabel_2_6_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(143, 30, 180, 27);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(143, 60, 180, 27);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(143, 90, 180, 27);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(143, 120, 180, 27);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(143, 150, 180, 27);
		frame.getContentPane().add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(141, 180, 182, 27);
		frame.getContentPane().add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(141, 210, 182, 27);
		frame.getContentPane().add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(141, 240, 182, 27);
		frame.getContentPane().add(comboBox_7);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setBounds(141, 270, 182, 27);
		frame.getContentPane().add(comboBox_8);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setBounds(141, 300, 182, 27);
		frame.getContentPane().add(comboBox_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 30, 933, 312);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Type", "Title", "Language", "Genre", "Release Date", "Retail Price",
				"Quantity", "Additiona Info 1", "Additiona Info 2"
			}
		));
		
		JButton btnNewButton = new JButton("Add To Basket");
		btnNewButton.setBounds(60, 426, 121, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove From Basket");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(191, 426, 167, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Empty Basket");
		btnNewButton_2.setBounds(367, 426, 153, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Pay for Items");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(530, 426, 140, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("View All");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBounds(857, 426, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("View Basket");
		btnNewButton_5.setBounds(980, 426, 140, 23);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Search Books");
		btnNewButton_6.setBounds(700, 426, 121, 23);
		frame.getContentPane().add(btnNewButton_6);
	}
}
