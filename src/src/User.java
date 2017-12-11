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

public class User extends JFrame {
	static User frame;
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
					User frame = new User();
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
	public User() {
		setTitle("Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 13, 481, 34);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("Enter name");
		label.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label.setBounds(46, 108, 117, 29);
		contentPane.add(label);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(175, 109, 176, 29);
		contentPane.add(textFieldName);
		
		JLabel label_1 = new JLabel("Enter Password");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label_1.setBounds(12, 176, 151, 29);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 176, 176, 28);
		contentPane.add(passwordField);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				if (name.equals("meme") && password.equals("meme123")) {
					UserSection.main(new String [] {});
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(User.this , "Sorry, the username or the password is inccorect", "Login Error!", JOptionPane.ERROR_MESSAGE);
					textFieldName.setText("");
					passwordField.setText("");
						
					}
			}
		});
		button.setFont(new Font("Sitka Display", Font.BOLD, 30));
		button.setBounds(375, 108, 118, 97);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.setForeground(Color.BLACK);
		button_1.setBackground(SystemColor.info);
		button_1.setBounds(408, 259, 85, 25);
		contentPane.add(button_1);
	}

}
