package customerPage;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import customer.Customer;


import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class CustomerPage {

	private JFrame frame;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerPage window = new CustomerPage();
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
	public CustomerPage() {
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
		
		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(18, 30, 61, 16);
		frame.getContentPane().add(isbnLabel);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(18, 60, 61, 16);
		frame.getContentPane().add(typeLabel);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(18, 90, 61, 16);
		frame.getContentPane().add(titleLabel);
		
		JLabel languageLabel = new JLabel("Language");
		languageLabel.setBounds(18, 120, 61, 16);
		frame.getContentPane().add(languageLabel);
		
		JLabel genreLabel = new JLabel("Genre");
		genreLabel.setBounds(18, 150, 61, 16);
		frame.getContentPane().add(genreLabel);
		
		JLabel releaseDateLabel = new JLabel("Release Date");
		releaseDateLabel.setBounds(18, 180, 111, 16);
		frame.getContentPane().add(releaseDateLabel);
		
		JLabel retailPriceLabel = new JLabel("Retail Price");
		retailPriceLabel.setBounds(18, 210, 111, 16);
		frame.getContentPane().add(retailPriceLabel);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(18, 240, 111, 16);
		frame.getContentPane().add(quantityLabel);
		
		JLabel add1Label = new JLabel("Addtional Info 1");
		add1Label.setBounds(18, 270, 111, 16);
		frame.getContentPane().add(add1Label);
		
		JLabel add2Label = new JLabel("Addtional Info 2");
		add2Label.setBounds(18, 300, 111, 16);
		frame.getContentPane().add(add2Label);
		
		JComboBox isbnComboBox = new JComboBox();
		isbnComboBox.setBounds(143, 30, 180, 27);
		frame.getContentPane().add(isbnComboBox);
		
		JComboBox typeComboBox = new JComboBox();
		typeComboBox.setBounds(143, 60, 180, 27);
		frame.getContentPane().add(typeComboBox);
		
		JComboBox titleComboBox = new JComboBox();
		titleComboBox.setBounds(143, 90, 180, 27);
		frame.getContentPane().add(titleComboBox);
		
		JComboBox languageComboBox = new JComboBox();
		languageComboBox.setBounds(143, 120, 180, 27);
		frame.getContentPane().add(languageComboBox);
		
		JComboBox genreComboBox = new JComboBox();
		genreComboBox.setBounds(143, 150, 180, 27);
		frame.getContentPane().add(genreComboBox);
		
		JComboBox releaseDateComboBox = new JComboBox();
		releaseDateComboBox.setBounds(141, 180, 182, 27);
		frame.getContentPane().add(releaseDateComboBox);
		
		JComboBox retailPriceComboBox = new JComboBox();
		retailPriceComboBox.setBounds(141, 210, 182, 27);
		frame.getContentPane().add(retailPriceComboBox);
		
		JComboBox quantityComboBox = new JComboBox();
		quantityComboBox.setBounds(141, 240, 182, 27);
		frame.getContentPane().add(quantityComboBox);
		
		JComboBox add1ComboBox = new JComboBox();
		add1ComboBox.setBounds(141, 270, 182, 27);
		frame.getContentPane().add(add1ComboBox);
		
		JComboBox add2ComboBox = new JComboBox();
		add2ComboBox.setBounds(141, 300, 182, 27);
		frame.getContentPane().add(add2ComboBox);
		
		JButton addButton = new JButton("Add To Basket");
		addButton.setBounds(8, 413, 121, 36);
		frame.getContentPane().add(addButton);
		
		JButton removeButton = new JButton("Remove From Basket");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeButton.setBounds(157, 413, 167, 36);
		frame.getContentPane().add(removeButton);
		
		JButton emptyBasketButton = new JButton("Empty Basket");
		emptyBasketButton.setBounds(337, 413, 153, 36);
		frame.getContentPane().add(emptyBasketButton);
		
		JButton payForItems = new JButton("Pay for Items");
		payForItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		payForItems.setBounds(510, 413, 140, 36);
		frame.getContentPane().add(payForItems);
		
		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


			}

		});
		viewAllButton.setBounds(815, 413, 111, 36);
		frame.getContentPane().add(viewAllButton);
		
		JButton viewBasketButton = new JButton("View Basket");
		viewBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



			}
		});
		viewBasketButton.setBounds(953, 413, 140, 36);
		frame.getContentPane().add(viewBasketButton);
		
		JButton searchButton = new JButton("Search Books");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchButton.setBounds(671, 413, 121, 36);
		frame.getContentPane().add(searchButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame= new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit",
						"Library System", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			
			}
		});

		

		exitButton.setBounds(1119, 413, 89, 36);
		frame.getContentPane().add(exitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 30, 347, 286);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);


	}
}
