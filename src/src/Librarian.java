package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;

public class Librarian extends JFrame {
	static Librarian frame;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Librarian frame = new Librarian();
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
	public Librarian() {
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Librarian login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 27));
		lblNewLabel.setBounds(12, 13, 478, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter name");
		lblNewLabel_1.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblNewLabel_1.setBounds(34, 96, 117, 29);
		contentPane.add(lblNewLabel_1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(163, 97, 176, 29);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterPassword.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblEnterPassword.setBounds(0, 164, 151, 29);
		contentPane.add(lblEnterPassword);
		
		// The code that will test the login for the librarian
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String name = textFieldName.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			if (name.equals("meme") && password.equals("meme123")) {
				LibrarianSection.main(new String [] {});
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(Librarian.this , "Sorry, the username or the password is inccorect", "Login Error!", JOptionPane.ERROR_MESSAGE);
				textFieldName.setText("");
				passwordField.setText("");
					
				}
			
			}
			
			
		});
		button.setFont(new Font("Sitka Display", Font.BOLD, 30));
		button.setBounds(370, 94, 118, 97);
		contentPane.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 164, 176, 28);
		contentPane.add(passwordField);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Library.main(new String [] {});
				dispose();
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setBackground(SystemColor.info);
		button_1.setBounds(416, 245, 85, 25);
		contentPane.add(button_1);
	}
}
