package loginPage;

import java.awt.EventQueue;
import customerApp.CustomerApp;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;

import adminApp.AdminApp;
import customer.Customer;
import user.User;
import java.awt.event.ActionListener;
import java.lang.ProcessBuilder.Redirect.Type;
import java.awt.event.ActionEvent;

public class LoginPage {

	private JFrame frame;

	private JList loginList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<String> listArray;
				
				listArray=Arrays.asList(loginList.getSelectedValue().toString().split("\\s*,\\s*"));
				
				if (listArray.get(6).equalsIgnoreCase("customer")){
					
					CustomerApp create =new CustomerApp(listArray.get(0),frame);
					create.setVisible(true);
					frame.setVisible(false);

				}else if (listArray.get(6).equalsIgnoreCase("admin")){
					
					AdminApp create =new AdminApp(listArray.get(0),frame);
					create.setVisible(true);
					frame.setVisible(false);

				}
	

			}
		});
		
		loginButton.setBounds(54, 123, 89, 23);
		frame.getContentPane().add(loginButton);
		

		
		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		welcomeLabel.setBounds(26, 11, 157, 23);
		frame.getContentPane().add(welcomeLabel);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame= new JFrame("Exit");
				
				if (JOptionPane .showConfirmDialog(frame, "Confirm if you want to exit",
						"Library System", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			
			}
		});
		exitButton.setBounds(54, 199, 89, 23);
		frame.getContentPane().add(exitButton);

		
		User userClass = new User();
		List<List<String>> userList =userClass.findAll("UserAccounts.txt");
		List<String> userListString =new ArrayList<>();
		

		for (int i =0; i<userList.size();i++){
			userListString.add(userList.get(i).toString().replace("[", "").replace("]", ""));
		}		
			
			
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 12, 341, 276);
		frame.getContentPane().add(scrollPane);
		loginList = new JList(userListString.toArray());
		loginList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		
		
		scrollPane.setViewportView(loginList);
		
	}
}
