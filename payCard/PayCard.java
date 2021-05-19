package payCard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import customer.Customer;
import customerApp.CustomerApp;
import loginPage.LoginPage;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PayCard extends JFrame {

	private JPanel contentPane;
	private JTextField cardNumTextField;
	private JTextField codeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayCard frame = new PayCard();
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
	public PayCard(){}
	public PayCard(Customer customer,JFrame login) {
		String payMethod= "Credit Card";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel cardNumberLabel = new JLabel("Enter Card Number: ");
		cardNumberLabel.setBounds(20, 98, 147, 16);
		contentPane.add(cardNumberLabel);
		
		JLabel codeLabel = new JLabel("Enter Security Code: ");
		codeLabel.setBounds(20, 145, 147, 16);
		contentPane.add(codeLabel);
		
		JLabel basketLabel = new JLabel("Your Basket:");
		basketLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		basketLabel.setBounds(433, 13, 172, 25);
		contentPane.add(basketLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(398, 56, 413, 231);
		contentPane.add(scrollPane);
		
	

		List <List<String>> basketList =customer.returnBasket();
		List<String> basketArray =new ArrayList<>();
		

		for (int i =0; i<basketList.size();i++){
			basketArray.add(basketList.get(i).toString().replace("[", "").replace("]", ""));

			
		}	
		JList displayBox = new JList(basketArray.toArray());
		scrollPane.setViewportView(displayBox);
		
		cardNumTextField = new JTextField();
		cardNumTextField.setBounds(145, 93, 130, 26);
		contentPane.add(cardNumTextField);
		cardNumTextField.setColumns(10);
		
		codeTextField = new JTextField();
		codeTextField.setBounds(145, 140, 130, 26);
		contentPane.add(codeTextField);
		codeTextField.setColumns(10);
		
		JLabel userLabel = new JLabel("User: "+customer.username+" "+customer.surname);
		userLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		userLabel.setBounds(20, 13, 210, 31);
		contentPane.add(userLabel);
		
		JButton payButton = new JButton("Pay");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float price = customer.getPrice();
				
				boolean pay= customer.payForItems(payMethod, cardNumTextField.getText(), codeTextField.getText(),"");
				if(pay){

					JOptionPane.showMessageDialog(contentPane,  "£ "+price+" paid using Credit Card");
					login.setVisible(true);
					PayCard.this.setVisible(false);
					
					
				}else{
					JOptionPane.showMessageDialog(contentPane, "Invalid Card Number or Security Code");
				}

			}
		});
		payButton.setBounds(20, 243, 117, 29);
		contentPane.add(payButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				customer.cancelItems(payMethod);

				JOptionPane.showMessageDialog(contentPane, "Payment Cancelled");
				login.setVisible(true);
				PayCard.this.setVisible(false);

				



			}
		});
		cancelButton.setBounds(192, 243, 117, 29);
		contentPane.add(cancelButton);
		
		JLabel costLabel = new JLabel("Total Price: £ " + customer.getPrice());
		costLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		costLabel.setBounds(633, 17, 216, 27);
		contentPane.add(costLabel);
		
		JLabel cardLabel = new JLabel("Paying by Card");
		cardLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cardLabel.setBounds(242, 5, 163, 38);
		contentPane.add(cardLabel);
	}
}
