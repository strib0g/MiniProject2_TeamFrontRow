package package1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UserForm extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 496);
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
		
		JLabel lblE = new JLabel("Email ");
		lblE.setHorizontalAlignment(SwingConstants.RIGHT);
		lblE.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblE.setBounds(51, 257, 141, 29);
		contentPane.add(lblE);
		
		JLabel lblAccumulatedFines = new JLabel("Accumulated Fines");
		lblAccumulatedFines.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblAccumulatedFines.setBounds(12, 299, 180, 29);
		contentPane.add(lblAccumulatedFines);
	}

}
