package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Globaldata;
import m.userManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		setTitle("instagram Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlDkShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 101, 67, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(37, 126, 67, 20);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(114, 98, 222, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				{
					check();
					
				}
			}
		});
		btnOk.setBounds(167, 157, 89, 23);
		contentPane.add(btnOk);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 102));
		panel.setBounds(0, 0, 434, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblInstagram = new JLabel("Instagram");
		lblInstagram.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInstagram.setBounds(161, 11, 120, 27);
		panel.add(lblInstagram);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(114, 126, 222, 20);
		contentPane.add(passwordField);
		
		JButton btnRegister = new JButton("register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFrame rr =new registerFrame();
				rr.setVisible(true);
				
				//LoginFrame.this.setVisible(false);
			}
		});
		btnRegister.setBackground(SystemColor.controlDkShadow);
		btnRegister.setForeground(Color.RED);
		btnRegister.setBounds(167, 194, 89, 23);
		contentPane.add(btnRegister);
	}
	public void check() 
	{
		if(userManager.checkLogin(textField.getText(), new String(passwordField.getPassword()) ))
		{
			account f=new account();
			f.setVisible(true);
			
			LoginFrame.this.setVisible(false);
		}else
		{
			JOptionPane.showMessageDialog(LoginFrame.this, "username or password incorrect!!");
		}	
	}
}
