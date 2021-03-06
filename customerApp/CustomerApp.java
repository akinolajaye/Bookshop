package customerApp;



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
import customer.Customer;

import payCard.PayCard;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class CustomerApp extends JFrame {

	private JPanel contentPane;
	private JList displayBox;
	private String customerID;
	private JTextField typeTextField;
	private JTextField titleTextField;
	private JTextField langTextField;
	private JTextField genreTextField;
	private JTextField releaseDateTextField;
	private JTextField retailPriceTextField;
	private JTextField add1TextField;
	private JTextField add2TextField;
	private JTextField isbnTextField;
	JButton enableButton;

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
	
	public CustomerApp(String id,JFrame login) {
		customerID=id;
		Customer customer=new Customer(customerID);
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
		
		JButton addButton = new JButton("Add To Basket");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					customer.addItemToBasket(isbnTextField.getText(), Integer.parseInt(quantityComboBox.getSelectedItem().toString()));//adds item to basket

					List <List<String>> basketList =customer.returnBasket();
					List<String> basketArray =new ArrayList<>();
					
			
					for (int i =0; i<basketList.size();i++){
						basketArray.add(basketList.get(i).toString().replace("[", "").replace("]", ""));//gets line an converts it to a formatted string to print in the jlist
		
						
					}	
	
					
				
	
					DefaultListModel model = new DefaultListModel<>();
	
					for (String i : basketArray) {
	
						model.addElement(i);//puts string in array
						
						
					}
					displayBox.setModel(model);
	
					/* empties entry fields after adding items */
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

				}catch(NullPointerException x){


					JOptionPane.showMessageDialog(contentPane, "Item Out of Stock!!");
				}


				

			}
		});

		addButton.setBounds(8, 413, 121, 36);
		contentPane.add(addButton);
		
		JButton removeButton = new JButton("Remove From Basket");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				customer.removeItemFromBasket(isbnTextField.getText(), Integer.parseInt(quantityComboBox.getSelectedItem().toString()));//removes item from basket
				
				List <List<String>> basketList =customer.returnBasket();
				List<String> basketArray =new ArrayList<>();
				
		
				for (int i =0; i<basketList.size();i++){
					basketArray.add(basketList.get(i).toString().replace("[", "").replace("]", ""));
	
					
				}	

				DefaultListModel model = new DefaultListModel<>();

				for (String i : basketArray) {

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
		removeButton.setBounds(157, 413, 167, 36);
		contentPane.add(removeButton);
		
		JButton emptyBasketButton = new JButton("Empty Basket");

		emptyBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				customer.emptyItemsFromBasket();//empties basket
				
				List <List<String>> basketList =customer.returnBasket();
				List<String> basketArray =new ArrayList<>();
				
		
				for (int i =0; i<basketList.size();i++){
					basketArray.add(basketList.get(i).toString().replace("[", "").replace("]", ""));
	
					
				}	

				DefaultListModel model = new DefaultListModel<>();

				for (String i : basketArray) {

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

		emptyBasketButton.setBounds(337, 413, 153, 36);
		contentPane.add(emptyBasketButton);


		
		JButton payForItems = new JButton("Pay for Items");
		payForItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(customer.getPrice()!=0.0){

					Object[] options={"Cancel","Pay by Credit Card","Paypal"};
				
					/* creats a dialog box to decide between which pay method */
					int choice= (JOptionPane.showOptionDialog(contentPane, "Choose a payment method", "Payment", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null));
					if (choice==1){ 
						PayCard pay =new PayCard(customer,login);
						pay.setVisible(true);//makes gui visible
						CustomerApp.this.setVisible(false);//hides this gui
	
					}else if (choice==2){
						PayEmail pay =new PayEmail(customer,login);
						pay.setVisible(true);
						
						CustomerApp.this.setVisible(false);
	
					}
					
					
				}else{

					JOptionPane.showMessageDialog(contentPane, "Nothing in Basket!");
				}


			}
		});
		payForItems.setBounds(510, 413, 140, 36);
		contentPane.add(payForItems);




		
		JButton viewAllButton = new JButton("View All Books");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List <List<String>> allBooksList =customer.findAll("Stock.txt");
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
		viewAllButton.setBounds(815, 413, 111, 36);
		contentPane.add(viewAllButton);
		
		JButton viewBasketButton = new JButton("View Basket");
		viewBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				List <List<String>> basketList =customer.returnBasket();
				List<String> basketArray =new ArrayList<>();
				
		
				for (int i =0; i<basketList.size();i++){
					basketArray.add(basketList.get(i).toString().replace("[", "").replace("]", ""));					
				}	
				DefaultListModel model = new DefaultListModel<>();

				for (String i : basketArray) {

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
		viewBasketButton.setBounds(953, 413, 140, 36);
		contentPane.add(viewBasketButton);
		
		JButton searchButton = new JButton("Search Books");
		searchButton.setEnabled(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List <List<String>> searchList = new ArrayList<>();
				List<String> searchArray = new ArrayList<>();
				List<String> searchReturn = new ArrayList<>();

				
				/*validation to check for whehter the entry field or combo box is empty
				if not empty the the value is added to the array */
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

				try{

					if(!quantityComboBox.getSelectedItem().toString().equalsIgnoreCase("")){
						searchArray.add(quantityComboBox.getSelectedItem().toString());
					}
					
				}catch(NullPointerException err){

				}

				searchList=customer.searchBooks("Stock.txt", searchArray);


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
	
				/* disables the entry field so that the cannot they  cannot be used when not be unless its a search
				that way the values added to the basket are the ones displayed by the view all button */
				isbnTextField.setEditable(false);
				typeTextField.setEditable(false);
				titleTextField.setEditable(false);
				langTextField.setEditable(false);
				genreTextField.setEditable(false);
				releaseDateTextField.setEditable(false);
				retailPriceTextField.setEditable(false);
				add1TextField.setEditable(false);
				add2TextField.setEditable(false);
				quantityComboBox.setEditable(false);

				/* enables the buttons so that they can be used again  */
				addButton.setEnabled(true);
				removeButton.setEnabled(true);
				emptyBasketButton.setEnabled(true);
				payForItems.setEnabled(true);
				enableButton.setEnabled(true);
				viewAllButton.setEnabled(true);
				viewBasketButton.setEnabled(true);
				searchButton.setEnabled(false);


			}
		});
		searchButton.setBounds(671, 463, 121, 36);
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
		clearButton.setBounds(1119, 413, 140, 36);
		contentPane.add(clearButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(contentPane, "Confirm if you want to exit",//creates exit dialog box
						"Library System", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) { //closes system if yes is pressed
					System.exit(0);
				}
			
			}
		});

		

		exitButton.setBounds(1287, 413, 105, 36);
		contentPane.add(exitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(493, 28, 824, 351);
		contentPane.add(scrollPane);
		
		List<List<String>> bookList =customer.findAll("Stock.txt");//gets sll data from text file
		List<String> bookListString =new ArrayList<>();
		

		for (int i =0; i<bookList.size();i++){
			bookListString.add(bookList.get(i).toString().replace("[", "").replace("]", ""));//adds line of data to the list and remove square brackets
		}		
		displayBox = new JList(bookListString.toArray());//sets the deault list box values
		displayBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		displayBox.addListSelectionListener(new ListSelectionListener(){
			
			public void valueChanged (ListSelectionEvent event){

				try{
					if (!event.getValueIsAdjusting()) {
						quantityComboBox.removeAllItems();
						List<String> listArray;
						
						listArray=Arrays.asList(displayBox.getSelectedValue().toString().split("\\s*,\\s*"));//splits the listbox items into an array	
						
						/* puts list box items in entry fields */
						isbnTextField.setText(listArray.get(0));
						typeTextField.setText(listArray.get(1));
						titleTextField.setText(listArray.get(2));
						langTextField.setText(listArray.get(3));
						genreTextField.setText(listArray.get(4));
						releaseDateTextField.setText(listArray.get(5));
						retailPriceTextField.setText(listArray.get(6));
						add1TextField.setText(listArray.get(8));
						add2TextField.setText(listArray.get(9));

						
						int quant=Integer.parseInt( listArray.get(7));
						
						// puts the possible quantites in the combo box
						MutableComboBoxModel model = (MutableComboBoxModel) quantityComboBox.getModel() ;
						for(int i=1;i<=quant;i++){
							
							model.addElement(i);

						}

						
						
			
					}
				}catch(NullPointerException e){

				}
				
			}
		
		});

		scrollPane.setViewportView(displayBox);
		
		typeTextField = new JTextField();
		typeTextField.setEditable(false);
		typeTextField.setBounds(143, 55, 180, 26);
		contentPane.add(typeTextField);
		typeTextField.setColumns(10);
		
		titleTextField = new JTextField();
		titleTextField.setEditable(false);
		titleTextField.setColumns(10);
		titleTextField.setBounds(143, 85, 180, 26);
		contentPane.add(titleTextField);
		
		langTextField = new JTextField();
		langTextField.setEditable(false);
		langTextField.setColumns(10);
		langTextField.setBounds(143, 115, 180, 26);
		contentPane.add(langTextField);
		
		genreTextField = new JTextField();
		genreTextField.setEditable(false);
		genreTextField.setColumns(10);
		genreTextField.setBounds(143, 145, 180, 26);
		contentPane.add(genreTextField);
		
		releaseDateTextField = new JTextField();
		releaseDateTextField.setEditable(false);
		releaseDateTextField.setColumns(10);
		releaseDateTextField.setBounds(143, 175, 180, 26);
		contentPane.add(releaseDateTextField);
		
		retailPriceTextField = new JTextField();
		retailPriceTextField.setEditable(false);
		retailPriceTextField.setColumns(10);
		retailPriceTextField.setBounds(143, 205, 180, 26);
		contentPane.add(retailPriceTextField);
		
		add1TextField = new JTextField();
		add1TextField.setEditable(false);
		add1TextField.setColumns(10);
		add1TextField.setBounds(143, 265, 180, 26);
		contentPane.add(add1TextField);
		
		add2TextField = new JTextField();
		add2TextField.setEditable(false);
		add2TextField.setColumns(10);
		add2TextField.setBounds(143, 300, 180, 26);
		contentPane.add(add2TextField);
		
		isbnTextField = new JTextField();
		isbnTextField.setEditable(false);
		isbnTextField.setBounds(141, 25, 182, 26);
		contentPane.add(isbnTextField);
		isbnTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("User: "+customer.username+" "+customer.surname);
		nameLabel.setBounds(18, 6, 341, 16);
		contentPane.add(nameLabel);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				login.setVisible(true);//goe
				CustomerApp.this.setVisible(false);


			}
		});
		logOutButton.setBounds(350, 17, 117, 29);
		contentPane.add(logOutButton);
		
		enableButton = new JButton("Enable Search");//buttons to enable search function
		enableButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				/* makes the entryy fields editable so that that search criteria can be entered  */
				isbnTextField.setEditable(true);
				typeTextField.setEditable(true);
				titleTextField.setEditable(true);
				langTextField.setEditable(true);
				genreTextField.setEditable(true);
				releaseDateTextField.setEditable(true);
				retailPriceTextField.setEditable(true);
				add1TextField.setEditable(true);
				add2TextField.setEditable(true);
				quantityComboBox.setEditable(true);

				/* disables buttons so that they cant be used while searching */
				addButton.setEnabled(false);
				removeButton.setEnabled(false);
				emptyBasketButton.setEnabled(false);
				payForItems.setEnabled(false);
				enableButton.setEnabled(false);
				viewAllButton.setEnabled(false);
				viewBasketButton.setEnabled(false);
				searchButton.setEnabled(true);

				DefaultListModel model = new DefaultListModel<>();
				model.addElement("");
				displayBox.setModel(model);



				JOptionPane.showMessageDialog(contentPane, "Search Enabled Enter Search Criteria \n In Entry Field and Press Search");
				

				


			}
		});
		enableButton.setBounds(671, 417, 117, 36);
		contentPane.add(enableButton);


		


	}
}

