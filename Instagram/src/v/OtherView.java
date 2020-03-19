package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import common.Globaldata;
import m.FollowingDB;
import m.FollowingManager;
import m.followerDB;
import m.followerManager;
import m.likeDB;
import m.likeManager;
import m.pictureDB;
import m.pictureManger;
import m.userDB;
import m.userManager;

import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class OtherView extends JFrame
{

	private JPanel contentPane;

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
					OtherView frame = new OtherView();
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
	public OtherView()
	{
		setTitle("ll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(10, 34, 161, 150);
		contentPane.add(imagePanel);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 54, 363, 87);
		contentPane.add(scrollPane);

		table_follow = new JTable();
		table_follow.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table_follow);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 213, 708, 318);
		contentPane.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		JButton btnBackToSearch = new JButton("Back to Search");
		btnBackToSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtherView.this.setVisible(false);
			}
		});
		btnBackToSearch.setForeground(Color.BLACK);
		btnBackToSearch.setBackground(Color.LIGHT_GRAY);
		btnBackToSearch.setBounds(587, 0, 121, 23);
		contentPane.add(btnBackToSearch);

		JButton btnFollow = new JButton("Follow");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean z = true;
				listfollower = followerManager.getAllFollower();
				for (followerDB c : listfollower)
				{
					
					if ( c.user_id == Globaldata.CurrentUser_userID && c.other_id == Globaldata.CurrentOtherAccount_ViewOtherAccount )
					{
						JOptionPane.showMessageDialog(OtherView.this, "already follow !!");
						z=false;
						
						return;
					}
				}
				if(z==true)
				{
					saveFollow();
					load();
					getfollow();
					
					
				}
				
			}
		});
		btnFollow.setBackground(Color.LIGHT_GRAY);
		btnFollow.setBounds(385, 152, 89, 42);
		contentPane.add(btnFollow);

		label = new JLabel((String) null);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label.setBounds(361, 16, 128, 27);
		contentPane.add(label);
		
		JButton btnSelect = new JButton("select picture");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() < 0) // getselectedrow ถ้าเป็นศูนย์ก็กลับไปหน้าเดิมไม่ได้
				{
					JOptionPane.showMessageDialog(OtherView.this, "Please select row!!");
					return;
				} else
				{
					Globaldata.CurrentPicture_pictureId = list2.get(table.getSelectedRow());
					System.out.println(Globaldata.CurrentPicture_pictureId);
				}
				if (list2 != null)
				{
					OtherView.this.setVisible(false);

					PictureView pv = new PictureView();
					pv.setVisible(true);
				}

			}
		});
		btnSelect.setBounds(201, 179, 112, 23);
		contentPane.add(btnSelect);

		load();
		load_proflieimg();
		getfollow();
		Globaldata.checkuser_or_not = false;
		
	}

	ArrayList<pictureDB> list;
	ArrayList<Integer> list2 = new ArrayList<Integer>();
	ArrayList<userDB> listUser;
	ArrayList<followerDB> listfollower;
	ArrayList<FollowingDB> listfollowing;
	
	private JTable table;
	private JTable table_follow;
	private ImagePanel imagePanel;
	private JLabel label;

	public void load()
	{
		list = pictureManger.getAllPicture();
		list2.clear();

		DefaultTableModel model = new DefaultTableModel(); // ต้องcast

		model.addColumn("pic");
		model.addColumn("discripe");
		model.addColumn("date");
		model.addColumn("like");

		for (pictureDB c : list) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			if (c.user_id == Globaldata.CurrentOtherAccount_ViewOtherAccount)
			{
				model.addRow(new Object[]
				{ c.pic, c.driscripe, c.date, c.like });
				list2.add(c.picture_id);
			}
		}

		// +++++++++++++++++

		table.setModel(model);

		table.getColumn("pic").setCellRenderer(new InvoiceDetailTableRenderer()); // ________________________การเพิ่งตารางในcolum_________

		for (int i = 0; i < table.getRowCount(); i++)
		{
			table.setRowHeight(i, 200);
		}

	}

	public void load_proflieimg()
	{
		listUser =userManager.getAllUser();
		for (userDB c : listUser) 
		{
			if (c.user_id == Globaldata.CurrentOtherAccount_ViewOtherAccount)
			{
				imagePanel.setImage(c.profile_image);
				label.setText(c.username);
			}

		}
	}
	
	public void saveFollow()
	{
		JOptionPane.showMessageDialog(OtherView.this, "follow finish !!!");
		followerDB x = new followerDB();
		x.follower_id = 0;
		x.user_id = Globaldata.CurrentUser_userID;
		x.follower_num = x.follower_num + 1;
		x.other_id = Globaldata.CurrentOtherAccount_ViewOtherAccount;
		

		followerManager.SaveFollower(x);
		
		FollowingDB y = new FollowingDB();
		y.following_id=0;
		y.user_id=Globaldata.CurrentUser_userID;
		y.following_num=y.following_num+1;
		y.other_id = Globaldata.CurrentOtherAccount_ViewOtherAccount;
		
		FollowingManager.NewFollowing(y);
		
	}
	public void getfollow()
	{
		listfollower = followerManager.getAllFollower();
		listfollowing = FollowingManager.getAllFollowing();
		DefaultTableModel model = new DefaultTableModel(); // ต้องcast
		int sum=0;
		int sum2=0;
		model.addColumn("follower");
		model.addColumn("following");
		
		for (FollowingDB d : listfollowing) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			if(d.user_id == Globaldata.CurrentOtherAccount_ViewOtherAccount)
			{
				sum2+=d.following_num;
			}
		}
		for (followerDB c : listfollower) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			if (c.other_id == Globaldata.CurrentOtherAccount_ViewOtherAccount)
			{
				sum+=c.follower_num;
				model.addRow(new Object[]
				{ sum, sum2 });
				
			}
		}
		table_follow.setModel(model);
		for (int i = 0; i < table_follow.getRowCount(); i++)
		{
			table_follow.setRowHeight(i, 50);
		}
	}
}
