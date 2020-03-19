package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import m.pictureDB;
import m.pictureManger;
import m.userDB;
import m.userManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class User_InsertImg extends JFrame
{

	private JPanel contentPane;
	private JTextField textField;

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
					User_InsertImg frame = new User_InsertImg();
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
	public User_InsertImg()
	{
		setTitle("Insert Picture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(10, 11, 245, 239);
		contentPane.add(imagePanel);
		
		JLabel lblYourPictureName = new JLabel("your picture name");
		lblYourPictureName.setBounds(265, 11, 100, 14);
		contentPane.add(lblYourPictureName);
		
		textField = new JTextField();
		textField.setBounds(265, 29, 159, 74);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pictureDB x=new pictureDB();
				x.picture_id=0;
				x.driscripe = textField.getText().trim();
				x.pic=(BufferedImage)imagePanel.getImage();
				
				pictureManger.saveNewpicture(x);
			
				account ac =new account();
				ac.setVisible(true);
				//JOptionPane.showMessageDialog(User_InsertImg.this, "finish!!");
				User_InsertImg.this.setVisible(false);
				
				//if(xInsertImgI != null)   //@@@@@@
				//{	
						
				//}
				
			}
		});
		btnOk.setBounds(298, 173, 100, 23);
		contentPane.add(btnOk);
		
		JButton btnBrowsImage = new JButton("Brows Image");
		btnBrowsImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc= new JFileChooser();      
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
				fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal =fc.showOpenDialog(User_InsertImg.this);
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
		btnBrowsImage.setBounds(298, 139, 100, 23);
		contentPane.add(btnBrowsImage);
		
	}

	//InsertImgI xInsertImgI;
	//public void reInsertImgI(InsertImgI x)
	//{
		//xInsertImgI=x;
	//}
}

//interface InsertImgI    
//{
	//public void refreash(pictureDB x);
//}
