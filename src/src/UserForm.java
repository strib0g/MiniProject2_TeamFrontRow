package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;



public class UserForm extends JFrame {

	static UserForm frame;
	private JPanel contentPane;
	private JTextField textFieldPinCode;
	private JTextField textFieldName;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldEmailAddress;
	private JTextField textFieldHomeAddress;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForm frame = new UserForm();
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
	public UserForm() {
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddUser = new JLabel("Add User");
		lblAddUser.setToolTipText("");
		lblAddUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddUser.setFont(new Font("Sitka Display", Font.BOLD, 27));
		lblAddUser.setBounds(23, 13, 457, 34);
		contentPane.add(lblAddUser);
		
		JLabel lblName = new JLabel("ID:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblName.setBounds(51, 89, 141, 29);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("Pin Code:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblId.setBounds(51, 131, 141, 29);
		contentPane.add(lblId);
		
		JLabel label_1 = new JLabel("Name: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label_1.setBounds(51, 173, 141, 29);
		contentPane.add(label_1);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblPhoneNumber.setBounds(51, 215, 141, 29);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblE = new JLabel("Email address:");
		lblE.setHorizontalAlignment(SwingConstants.RIGHT);
		lblE.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblE.setBounds(51, 257, 141, 29);
		contentPane.add(lblE);
		
		JLabel lblAccumulatedFines = new JLabel("Home address:");
		lblAccumulatedFines.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccumulatedFines.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblAccumulatedFines.setBounds(12, 299, 180, 29);
		contentPane.add(lblAccumulatedFines);
		
		textFieldPinCode = new JTextField();
		textFieldPinCode.setColumns(10);
		textFieldPinCode.setBounds(204, 131, 257, 29);
		contentPane.add(textFieldPinCode);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(204, 177, 257, 29);
		contentPane.add(textFieldName);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setColumns(10);
		textFieldPhoneNumber.setBounds(204, 219, 257, 29);
		contentPane.add(textFieldPhoneNumber);
		
		textFieldEmailAddress = new JTextField();
		textFieldEmailAddress.setColumns(10);
		textFieldEmailAddress.setBounds(204, 261, 257, 29);
		contentPane.add(textFieldEmailAddress);
		
		textFieldHomeAddress = new JTextField();
		textFieldHomeAddress.setColumns(10);
		textFieldHomeAddress.setBounds(204, 303, 257, 29);
		contentPane.add(textFieldHomeAddress);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianSection.main(new String [] {});
				dispose();
				
			}
		});
		btnBack.setBackground(SystemColor.info);
		btnBack.setForeground(SystemColor.textText);
		btnBack.setBounds(395, 445, 85, 25);
		contentPane.add(btnBack);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(204, 89, 257, 29);
		contentPane.add(textFieldID);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = textFieldID.getText();
				String pinCode = textFieldPinCode.getText();
				String name = textFieldName.getText();
				String phoneNumber = textFieldPhoneNumber.getText();
				String emailAddress = textFieldEmailAddress.getText();
				String homeAddress = textFieldHomeAddress.getText();
				int i = UserDao.save(ID, pinCode, name, phoneNumber, emailAddress, homeAddress);  
				//This is the SQL related code. It will be put in a class called UserDao. The class will be created by Filip ad Majed. 
					
				if (i>0) {
					JOptionPane.showMessageDialog(UserForm.this, "User added successfully!");
					LibrarianSection.main(new String [] {});
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(UserForm.this,"Sorry, unable to save!");
				}
				}
				
		});
		btnAdd.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnAdd.setBounds(148, 358, 180, 47);
		contentPane.add(btnAdd);
	}

}
