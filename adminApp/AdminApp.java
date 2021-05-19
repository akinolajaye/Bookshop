package adminApp;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import payEmail.PayEmail;
import admin.Admin;

import payCard.PayCard;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AdminApp extends JFrame {

	private JPanel contentPane;
	private JList displayBox;
	private String adminID;
	private JTextField typeTextField;
	private JTextField titleTextField;
	private JTextField langTextField;
	private JTextField genreTextField;
	private JTextField releaseDateTextField;
	private JTextField retailPriceTextField;
	private JTextField add1TextField;
	private JTextField add2TextField;
	private JTextField isbnTextField;

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminApp frame = new AdminApp();
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

	public AdminApp(){}
	
	public AdminApp(String id,JFrame login) {
		adminID=id;
		Admin admin=new Admin(adminID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1446 , 553);
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

		
		JComboBox quantityComboBox = new JComboBox();


		

		quantityComboBox.setBounds(141, 240, 182, 27);
		contentPane.add(quantityComboBox);
		
		JButton addButton = new JButton("Add Book");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				List<String> newBook = new ArrayList<>();

				newBook.add(isbnTextField.getText());
				newBook.add(typeLabel.getText());
				newBook.add(titleTextField.getText());
				newBook.add(langTextField.getText());
				newBook.add(genreTextField.getText());
				newBook.add(releaseDateTextField.getText());
				newBook.add(retailPriceLabel.getText());
				newBook.add(quantityComboBox.getSelectedItem().toString());
				
				newBook.add(add1TextField.getText());
				newBook.add(add2TextField.getText());
				admin.addNewBook(newBook);
	
				List <List<String>> allBooksList =admin.findAll("Stock.txt");
				List<String> allBooksArray =new ArrayList<>();
				
		
				for (int i =0; i<allBooksList.size();i++){
					allBooksArray.add(allBooksList.get(i).toString().replace("[", "").replace("]", ""));
				}	

				

				DefaultListModel model = new DefaultListModel<>();

				for (String i : allBooksArray) {

					model.addElement(i);
					
				}
				displayBox.setModel(model);



				
				isbnTextField.setText(null);
				typeTextField.setText(null);
				titleTextField.setText(null);
				langTextField.setText(null);
				genreTextField.setText(null);
				releaseDateTextField.setText(null);
				retailPriceTextField.setText(null);
				add1TextField.setText(null);
				add2TextField.setText(null);
				quantityComboBox.removeAllItems();
				

			}
		});

		addButton.setBounds(8, 413, 121, 36);
		contentPane.add(addButton);




		
		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List <List<String>> allBooksList =admin.findAll("Stock.txt");
				List<String> allBooksArray =new ArrayList<>();
				
		
				for (int i =0; i<allBooksList.size();i++){
					allBooksArray.add(allBooksList.get(i).toString().replace("[", "").replace("]", ""));
				}	

				

				DefaultListModel model = new DefaultListModel<>();

				for (String i : allBooksArray) {

					model.addElement(i);
					
				}
				displayBox.setModel(model);
				isbnTextField.setText(null);
				typeTextField.setText(null);
				titleTextField.setText(null);
				langTextField.setText(null);
				genreTextField.setText(null);
				releaseDateTextField.setText(null);
				retailPriceTextField.setText(null);
				add1TextField.setText(null);
				add2TextField.setText(null);
				quantityComboBox.removeAllItems();
	

				
			}

		});
		viewAllButton.setBounds(372, 413, 111, 36);
		contentPane.add(viewAllButton);
		
		JButton searchButton = new JButton("Search Books");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List <List<String>> searchList;
				List<String> searchArray = new ArrayList<>();
				List<String> searchReturn = new ArrayList<>();


				if(!isbnTextField.getText().equalsIgnoreCase("")){
					searchArray.add(isbnTextField.getText());
				}
				if(!isbnTextField.getText().equalsIgnoreCase("")){
					searchArray.add(isbnTextField.getText());
				}
				if(!typeTextField.getText().equalsIgnoreCase("")){
					searchArray.add(typeTextField.getText());
				}
				if(!titleTextField.getText().equalsIgnoreCase("")){
					searchArray.add(titleTextField.getText());
				}
				if(!langTextField.getText().equalsIgnoreCase("")){
					searchArray.add(langTextField.getText());
				}

				if(!genreTextField.getText().equalsIgnoreCase("")){
					searchArray.add(genreTextField.getText());
				}
				if(!releaseDateTextField.getText().equalsIgnoreCase("")){
					searchArray.add(releaseDateTextField.getText());
				}

				if(!retailPriceTextField.getText().equalsIgnoreCase("")){
					searchArray.add(retailPriceTextField.getText());
				}

				if(!add1TextField.getText().equalsIgnoreCase("")){
					searchArray.add(add1TextField.getText());
				}

				if(!add2TextField.getText().equalsIgnoreCase("")){
					searchArray.add(add2TextField.getText());
				}

				searchList=admin.searchBooks("Stock.txt", searchArray);


				for (int i =0; i<searchList.size();i++){
					searchReturn.add(searchList.get(i).toString().replace("[", "").replace("]", ""));
				}	

				DefaultListModel model = new DefaultListModel<>();

				for (String i : searchReturn) {

					model.addElement(i);
					
				}
				displayBox.setModel(model);

				isbnTextField.setText(null);
				typeTextField.setText(null);
				titleTextField.setText(null);
				langTextField.setText(null);
				genreTextField.setText(null);
				releaseDateTextField.setText(null);
				retailPriceTextField.setText(null);
				add1TextField.setText(null);
				add2TextField.setText(null);
				quantityComboBox.removeAllItems();
	



			}
		});
		searchButton.setBounds(186, 413, 121, 36);
		contentPane.add(searchButton);

		JButton clearButton = new JButton("Clear Entries");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

			
				isbnTextField.setText(null);
				typeTextField.setText(null);
				titleTextField.setText(null);
				langTextField.setText(null);
				genreTextField.setText(null);
				releaseDateTextField.setText(null);
				retailPriceTextField.setText(null);
				add1TextField.setText(null);
				add2TextField.setText(null);
				quantityComboBox.removeAllItems();
		



			}
		});
		clearButton.setBounds(531, 413, 140, 36);
		contentPane.add(clearButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(contentPane, "Confirm if you want to exit",
						"Library System", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			
			}
		});

		

		exitButton.setBounds(727, 413, 105, 36);
		contentPane.add(exitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 30, 824, 351);
		contentPane.add(scrollPane);
		
		displayBox = new JList();
		displayBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		displayBox.addListSelectionListener(new ListSelectionListener(){
			
			public void valueChanged (ListSelectionEvent event){

				try{
					if (!event.getValueIsAdjusting()) {
						quantityComboBox.removeAllItems();
						List<String> listArray;
						
						listArray=Arrays.asList(displayBox.getSelectedValue().toString().split("\\s*,\\s*"));	
								
						isbnTextField.setText(listArray.get(0));
						typeTextField.setText(listArray.get(1));
						titleTextField.setText(listArray.get(2));
						langTextField.setText(listArray.get(3));
						genreTextField.setText(listArray.get(4));
						releaseDateTextField.setText(listArray.get(5));
						retailPriceTextField.setText(listArray.get(6));
						add1TextField.setText(listArray.get(8));
						add2TextField.setText(listArray.get(9));

						
						int quant=Integer.parseInt( admin.findOne(listArray.get(0), "Stock.txt").get(7));
						
						MutableComboBoxModel model = (MutableComboBoxModel) quantityComboBox.getModel() ;
						for(int i=1;i<=quant;i++){
							//System.out.println(i);
							model.addElement(i);

						}

						
						
			
					}
				}catch(NullPointerException e){

				}
				
			}
		
		});

		scrollPane.setViewportView(displayBox);
		
		typeTextField = new JTextField();
		typeTextField.setBounds(143, 55, 180, 26);
		contentPane.add(typeTextField);
		typeTextField.setColumns(10);
		
		titleTextField = new JTextField();
		titleTextField.setColumns(10);
		titleTextField.setBounds(143, 85, 180, 26);
		contentPane.add(titleTextField);
		
		langTextField = new JTextField();
		langTextField.setColumns(10);
		langTextField.setBounds(143, 115, 180, 26);
		contentPane.add(langTextField);
		
		genreTextField = new JTextField();
		genreTextField.setColumns(10);
		genreTextField.setBounds(143, 145, 180, 26);
		contentPane.add(genreTextField);
		
		releaseDateTextField = new JTextField();
		releaseDateTextField.setColumns(10);
		releaseDateTextField.setBounds(143, 175, 180, 26);
		contentPane.add(releaseDateTextField);
		
		retailPriceTextField = new JTextField();
		retailPriceTextField.setColumns(10);
		retailPriceTextField.setBounds(143, 205, 180, 26);
		contentPane.add(retailPriceTextField);
		
		add1TextField = new JTextField();
		add1TextField.setColumns(10);
		add1TextField.setBounds(143, 265, 180, 26);
		contentPane.add(add1TextField);
		
		add2TextField = new JTextField();
		add2TextField.setColumns(10);
		add2TextField.setBounds(143, 300, 180, 26);
		contentPane.add(add2TextField);
		
		isbnTextField = new JTextField();
		isbnTextField.setBounds(141, 25, 182, 26);
		contentPane.add(isbnTextField);
		isbnTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("User: "+admin.username);
		nameLabel.setBounds(18, 6, 305, 16);
		contentPane.add(nameLabel);


		


	}
}

