package payEmail;

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

public class PayEmail extends JFrame {

	private JPanel contentPane;
	private JTextField emailTextField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayEmail frame = new PayEmail();
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
	public PayEmail(){}
	public PayEmail(Customer customer,JFrame login) {
		String payMethod= "PayPal";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel emailLabel = new JLabel("Enter PayPal Email: ");
		emailLabel.setBounds(20, 98, 147, 16);
		contentPane.add(emailLabel);
		
		
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
		
		emailTextField = new JTextField();
		emailTextField.setBounds(145, 93, 130, 26);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		

		
		JLabel userLabel = new JLabel("User: "+customer.username+" "+customer.surname);
		userLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		userLabel.setBounds(20, 13, 195, 31);
		contentPane.add(userLabel);
		
		JButton payButton = new JButton("Pay");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				boolean pay= customer.payForItems(payMethod, "","",emailTextField.getText());
				if(pay){

					JOptionPane.showMessageDialog(contentPane, "Payment Successful");
					login.setVisible(true);
					PayEmail.this.setVisible(false);
					
					
				}else{
					JOptionPane.showMessageDialog(contentPane, "Invalid Email");
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
				PayEmail.this.setVisible(false);

				



			}
		});
		cancelButton.setBounds(192, 243, 117, 29);
		contentPane.add(cancelButton);
		
		JLabel costLabel = new JLabel("Total Price: £ " + customer.getPrice());
		costLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		costLabel.setBounds(633, 17, 180, 27);
		contentPane.add(costLabel);
		
		JLabel payEmailLabel = new JLabel("Paying by PayPal");
		payEmailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		payEmailLabel.setBounds(237, 5, 163, 38);
		contentPane.add(payEmailLabel);
	}
}




