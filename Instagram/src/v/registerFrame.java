package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import m.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class registerFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldusername;
	private JPasswordField passwordField;
	private  ImagePanel imagePanel ;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField textFieldID;
	


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
					registerFrame frame = new registerFrame();
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
	public registerFrame()
	{
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 670);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlDkShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(204, 153, 102));
		panel.setBounds(0, 0, 434, 45);
		contentPane.add(panel);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRegister.setBounds(161, 11, 120, 27);
		panel.add(lblRegister);
		
		JLabel lblId = new JLabel("username");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(39, 82, 78, 14);
		contentPane.add(lblId);
		
		textFieldusername = new JTextField();
		textFieldusername.setBounds(143, 81, 170, 20);
		contentPane.add(textFieldusername);
		textFieldusername.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(39, 113, 78, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(8);
		passwordField.setBounds(143, 112, 170, 20);
		contentPane.add(passwordField);
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(39, 147, 78, 14);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(39, 183, 78, 14);
		contentPane.add(lblSurname);
		
		JLabel lblCountry = new JLabel("country");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCountry.setBounds(39, 280, 78, 14);
		contentPane.add(lblCountry);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Thailand", "Japan", "England", "Vietnam"}));
		comboBox.setBounds(143, 279, 170, 20);
		contentPane.add(comboBox);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textFieldPhone.getText().trim().matches("[-+]?\\d*\\.?\\d+"))  //เช็คถ้าไม่ใช่ตัวเลขให้ไปแก้
				{
					JOptionPane.showMessageDialog(registerFrame.this, "number Only!!");
					textFieldPhone.requestFocus();
					textFieldPhone.selectAll();
				}
				// user_id username password name surname Email phone country profile_image
				userDB x=new userDB();
				x.user_id=0;
				x.username = textFieldusername.getText().trim();
				x.password=new String(passwordField.getPassword());
				x.name=textFieldName.getText().trim();
				x.surname=textFieldSurname.getText().trim();
				x.Email=textFieldEmail.getText().trim();
				x.phone=Integer.parseInt(textFieldPhone.getText().trim());
				x.country=comboBox.getSelectedItem().toString().trim();
				x.profile_image=(BufferedImage)imagePanel.getImage();
				
				userManager.saveNewuser(x);
			
				JOptionPane.showMessageDialog(registerFrame.this, "finish!!");
				LoginFrame gg =new LoginFrame();
				gg.setVisible(true);
				
				registerFrame.this.setVisible(false);
				
			}
		});
		btnOk.setBounds(162, 581, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(39, 220, 78, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(39, 251, 78, 14);
		contentPane.add(lblPhone);
		
		JLabel lblProfilePicture = new JLabel("profile picture");
		lblProfilePicture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProfilePicture.setBounds(39, 313, 97, 20);
		contentPane.add(lblProfilePicture);
		
		JButton btnBrowse = new JButton("browse image");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc= new JFileChooser();      
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
				fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal =fc.showOpenDialog(registerFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION )
				{
					File f = fc.getSelectedFile();
					try
					{
					BufferedImage bimg = ImageIO.read(f);
					imagePanel.setImage(bimg);
					}catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			
			}
		});
		btnBrowse.setBounds(143, 310, 170, 23);
		contentPane.add(btnBrowse);
		
		imagePanel = new ImagePanel((Image) null); 
		imagePanel.setBounds(49, 354, 323, 202);
		contentPane.add(imagePanel);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(143, 146, 170, 20);
		contentPane.add(textFieldName);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(143, 182, 170, 20);
		contentPane.add(textFieldSurname);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(143, 219, 170, 20);
		contentPane.add(textFieldEmail);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(143, 248, 170, 20);
		contentPane.add(textFieldPhone);
		
		textFieldID = new JTextField();
		textFieldID.setBackground(SystemColor.activeCaption);
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		textFieldID.setBounds(143, 50, 29, 20);
		contentPane.add(textFieldID);
		
		JLabel labelID = new JLabel("Id");
		labelID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelID.setBounds(39, 56, 78, 14);
		contentPane.add(labelID);
		
	}
}
class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
}

		
		
	

