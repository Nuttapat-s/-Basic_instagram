package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.events.Comment;

import common.Globaldata;

import m.commentDB;
import m.commentManager;
import m.likeDB;
import m.likeManager;
import m.pictureDB;
import m.pictureManger;
import m.userDB;
import m.userManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.nio.charset.Charset;

public class PictureView extends JFrame
{
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Charset ISO = Charset.forName("ISO-8859-1");
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
					PictureView frame = new PictureView();
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
	public PictureView()
	{
		setTitle("Picture View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(10, 11, 301, 353);
		contentPane.add(imagePanel);

		lblDate = new JLabel("date");
		lblDate.setBounds(20, 375, 185, 14);
		contentPane.add(lblDate);

		JLabel lblDriscripe = new JLabel("driscripe");
		lblDriscripe.setBounds(321, 21, 63, 14);
		contentPane.add(lblDriscripe);

		lbl_dris = new JLabel("New label");
		lbl_dris.setBounds(398, 21, 149, 14);
		contentPane.add(lbl_dris);

		textField = new JTextField();
		textField.setBounds(321, 365, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commentDB x = new commentDB();
				x.comment_id = 0;
				x.user_id = Globaldata.CurrentUser_userID;
				x.comment = new String(textField.getText().getBytes(ISO), UTF_8).trim();
				// textField.getText().trim();
				x.picture_id = Globaldata.CurrentPicture_pictureId;

				commentManager.saveNewComment(x);
				textField.setText("");

				load();
			}
		});
		btnSend.setBounds(490, 364, 89, 23);
		contentPane.add(btnSend);

		JLabel lblComments = new JLabel("comments");
		lblComments.setBounds(321, 70, 63, 14);
		contentPane.add(lblComments);

		JLabel lblLike = new JLabel("Like");
		lblLike.setBounds(321, 46, 46, 14);
		contentPane.add(lblLike);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(398, 46, 46, 14);
		contentPane.add(lblNewLabel);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(Globaldata.checkuser_or_not);
				PictureView.this.setVisible(false);
				if(Globaldata.checkuser_or_not == true)
				{
				account ac =new account();
				ac.setVisible(true);
				
				}
				if(Globaldata.checkuser_or_not == false)
				{
					OtherView ot =new OtherView();
					ot.setVisible(true);
					
				}

			}
		});
		btnBack.setBounds(500, 0, 89, 20);
		contentPane.add(btnBack);

		JButton btnLike = new JButton("Like");
		btnLike.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean z = true;
				for (likeDB c : listLike)
				{
					System.out.println("t1");
					if (c.picture_id == Globaldata.CurrentPicture_pictureId && c.user_id == Globaldata.CurrentUser_userID )
					{
						JOptionPane.showMessageDialog(PictureView.this, "already liked !!");
						z=false;
						
						return;
					}
				}
				if(z==true)
				{
					saveThisLike();
					load();
					likeload();
				}
				
			}
		});
		btnLike.setBounds(10, 398, 89, 23);
		contentPane.add(btnLike);

		textArea = new JTextArea();
		textArea.setBounds(321, 89, 258, 265);
		textArea.setEditable(false);
		contentPane.add(textArea);

		load();
		likeload();

	}

	ArrayList<likeDB> listLike;
	ArrayList<pictureDB> list;
	ArrayList<commentDB> listCom;
	ArrayList<userDB> listU;
	private ImagePanel imagePanel;
	private JLabel lblDate;
	private JLabel lbl_dris;
	private JTextArea textArea;
	private JLabel lblNewLabel;

	public void load()
	{
		list = pictureManger.getAllPicture();
		listCom = commentManager.getAllComment();
		listU = userManager.getAllUser();
		listLike = likeManager.getAllLike();
		
		for (pictureDB c : list) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			if (c.picture_id == Globaldata.CurrentPicture_pictureId)
			{
				BufferedImage bimg = c.pic;
				imagePanel.setImage(bimg);

				lbl_dris.setText(c.driscripe);
				lblDate.setText(c.date);
			}

		}
		// comment_id user_id comment com_date picture_id
		String s = "";
		for (commentDB m : listCom)
		{
			if (m.picture_id == Globaldata.CurrentPicture_pictureId)
			{
				for (userDB n : listU)
				{
					if (n.user_id == m.user_id)
					{
						s += n.username + " : " + m.comment + "   " + "(" + m.com_date + ")" + "\n";
					}
				}
			}
		}
		textArea.setText(s);
	
	}

	public void saveThisLike()
	{
		JOptionPane.showMessageDialog(PictureView.this, "Like finish !!!");
		likeDB x = new likeDB();
		x.like_id = 0;
		x.user_id = Globaldata.CurrentUser_userID;
		x.like_count = x.like_count + 1;
		x.picture_id = Globaldata.CurrentPicture_pictureId;

		likeManager.SaveLike(x);
	}
	
	public void likeload()
	{
		listLike = likeManager.getAllLike();
		list=pictureManger.getAllPicture();
		int sum =0;
		for(likeDB c : listLike)
		{
			if (c.picture_id == Globaldata.CurrentPicture_pictureId )
			{
				sum+=c.like_count;
			}
		}
		lblNewLabel.setText(""+sum);
		
		for(pictureDB c : list)
		{
			if (c.picture_id == Globaldata.CurrentPicture_pictureId )
			{
				pictureManger.updatelike(sum, Globaldata.CurrentPicture_pictureId);
				System.out.println("UPDATE");
			}
		}
	}
}
