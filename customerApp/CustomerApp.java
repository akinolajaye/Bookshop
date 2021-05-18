package customerApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import customer.Customer;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class CustomerApp extends JFrame {

	private JPanel contentPane;
	private JList displayBox;
	private String customerID;

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerApp frame = new CustomerApp();
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

	public CustomerApp(){}
	public CustomerApp(String id) {
		customerID=id;
		Customer customer=new Customer(customerID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1306 , 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(18, 30, 61, 16);
		contentPane.add(isbnLabel);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(18, 60, 61, 16);
		contentPane.add(typeLabel);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(18, 90, 61, 16);
		contentPane.add(titleLabel);
		
		JLabel languageLabel = new JLabel("Language");
		languageLabel.setBounds(18, 120, 61, 16);
		contentPane.add(languageLabel);
		
		JLabel genreLabel = new JLabel("Genre");
		genreLabel.setBounds(18, 150, 61, 16);
		contentPane.add(genreLabel);
		
		JLabel releaseDateLabel = new JLabel("Release Date");
		releaseDateLabel.setBounds(18, 180, 111, 16);
		contentPane.add(releaseDateLabel);
		
		JLabel retailPriceLabel = new JLabel("Retail Price");
		retailPriceLabel.setBounds(18, 210, 111, 16);
		contentPane.add(retailPriceLabel);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(18, 240, 111, 16);
		contentPane.add(quantityLabel);
		
		JLabel add1Label = new JLabel("Addtional Info 1");
		add1Label.setBounds(18, 270, 111, 16);
		contentPane.add(add1Label);
		
		JLabel add2Label = new JLabel("Addtional Info 2");
		add2Label.setBounds(18, 300, 111, 16);
		contentPane.add(add2Label);
		
		JComboBox isbnComboBox = new JComboBox();
		isbnComboBox.setBounds(143, 30, 180, 27);
		contentPane.add(isbnComboBox);
		
		JComboBox typeComboBox = new JComboBox();
		typeComboBox.setBounds(143, 60, 180, 27);
		contentPane.add(typeComboBox);
		
		JComboBox titleComboBox = new JComboBox();
		titleComboBox.setBounds(143, 90, 180, 27);
		contentPane.add(titleComboBox);
		
		JComboBox languageComboBox = new JComboBox();
		languageComboBox.setBounds(143, 120, 180, 27);
		contentPane.add(languageComboBox);
		
		JComboBox genreComboBox = new JComboBox();
		genreComboBox.setBounds(143, 150, 180, 27);
		contentPane.add(genreComboBox);
		
		JComboBox releaseDateComboBox = new JComboBox();
		releaseDateComboBox.setBounds(141, 180, 182, 27);
		contentPane.add(releaseDateComboBox);
		
		JComboBox retailPriceComboBox = new JComboBox();
		retailPriceComboBox.setBounds(141, 210, 182, 27);
		contentPane.add(retailPriceComboBox);
		
		JComboBox quantityComboBox = new JComboBox();
		quantityComboBox.setBounds(141, 240, 182, 27);
		contentPane.add(quantityComboBox);
		
		JComboBox add1ComboBox = new JComboBox();
		add1ComboBox.setBounds(141, 270, 182, 27);
		contentPane.add(add1ComboBox);
		
		JComboBox add2ComboBox = new JComboBox();
		add2ComboBox.setBounds(141, 300, 182, 27);
		contentPane.add(add2ComboBox);
		
		JButton addButton = new JButton("Add To Basket");
		addButton.setBounds(8, 413, 121, 36);
		contentPane.add(addButton);
		
		JButton removeButton = new JButton("Remove From Basket");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeButton.setBounds(157, 413, 167, 36);
		contentPane.add(removeButton);
		
		JButton emptyBasketButton = new JButton("Empty Basket");
		emptyBasketButton.setBounds(337, 413, 153, 36);
		contentPane.add(emptyBasketButton);
		
		JButton payForItems = new JButton("Pay for Items");
		payForItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		payForItems.setBounds(510, 413, 140, 36);
		contentPane.add(payForItems);
		
		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List <List<String>> allBooksList =customer.findAll("Stock.txt");
				List<String> allBooksArray =new ArrayList<>();
		
				for (int i =0; i<allBooksList.size();i++){
					allBooksArray.add(allBooksList.get(i).toString().replace("[", "").replace("]", ""));
				}	

				System.out.println("x");
				System.out.println(allBooksArray.toString());

				displayBox=new JList(allBooksArray.toArray());

			}

		});
		viewAllButton.setBounds(815, 413, 111, 36);
		contentPane.add(viewAllButton);
		
		JButton viewBasketButton = new JButton("View Basket");
		viewBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {





			}
		});
		viewBasketButton.setBounds(953, 413, 140, 36);
		contentPane.add(viewBasketButton);
		
		JButton searchButton = new JButton("Search Books");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchButton.setBounds(671, 413, 121, 36);
		contentPane.add(searchButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(contentPane, "Confirm if you want to exit",
						"Library System", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			
			}
		});

		

		exitButton.setBounds(1119, 413, 89, 36);
		contentPane.add(exitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 30, 347, 286);
		contentPane.add(scrollPane);
		
		displayBox = new JList();
		displayBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(displayBox);

	}
}
