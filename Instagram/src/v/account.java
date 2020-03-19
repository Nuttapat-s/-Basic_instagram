package v;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.fabric.xmlrpc.base.Array;

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
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class account extends JFrame
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
					account frame = new account();
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
	public account()
	{
		setTitle("Instagram");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(10, 23, 161, 150);
		contentPane.add(imagePanel);
		byte[] img_byte = Globaldata.CurrentUser_profilePic;
		BufferedImage bufferedimg = null;
		if (img_byte == null || img_byte.length == 0)
		{

		} else
		{

			ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
			try
			{
				bufferedimg = ImageIO.read(bais);
				imagePanel.setImage(bufferedimg);
				bais.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		JScrollPane scrollPane_main = new JScrollPane();
		scrollPane_main.setBounds(0, 202, 708, 318);
		contentPane.add(scrollPane_main);

		table_main = new JTable();
		scrollPane_main.setViewportView(table_main);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 58, 363, 68);
		contentPane.add(scrollPane);

		table_follow = new JTable();
		table_follow.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table_follow);

		lblHead = new JLabel(Globaldata.CurrentUser_name);
		lblHead.setForeground(Color.RED);
		lblHead.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblHead.setBounds(339, 11, 117, 27);
		contentPane.add(lblHead);

		JButton btnSearchFriend = new JButton("search friend");
		btnSearchFriend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SearchFriendFrame sf =new SearchFriendFrame();
				sf.setVisible(true);
				
				account.this.setVisible(false);
			}

		});
		btnSearchFriend.setBounds(591, 0, 117, 23);
		contentPane.add(btnSearchFriend);

		JButton btnInsertPicture = new JButton("Insert picture");
		btnInsertPicture.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				account.this.setVisible(false);

				User_InsertImg ui = new User_InsertImg();
				ui.setVisible(true);

			}
		});
		btnInsertPicture.setBounds(365, 168, 117, 23);
		contentPane.add(btnInsertPicture);

		JButton btnDeletePicture = new JButton("delete picture");
		btnDeletePicture.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(account.this, "Do you want to DELETE ???", "confrim",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					if (table_main.getSelectedRow() < 0) // getselectedrow ถ้าเป็นศูนย์ก็กลับไปหน้าเดิมไม่ได้
					{
						JOptionPane.showMessageDialog(account.this, "Please select row!!");
						return;
					} else
					{
						pictureDB p = new pictureDB();
						p.picture_id = list2.get(table_main.getSelectedRow());
						pictureManger.deletePicture(p.picture_id);
						load();
						
					}
				}

			}
		});
		btnDeletePicture.setBounds(497, 168, 117, 23);
		contentPane.add(btnDeletePicture);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				int result = JOptionPane.showConfirmDialog(account.this, "Do you want to Logout ???", "confrim",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					LoginFrame z = new LoginFrame();
					z.setVisible(true);

					account.this.setVisible(false);
				}
			}
		});
		btnLogout.setBackground(Color.RED);
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setBounds(10, 533, 89, 23);
		contentPane.add(btnLogout);

		JButton btnSelect = new JButton("Select picture");
		btnSelect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if (table_main.getSelectedRow() < 0) // getselectedrow ถ้าเป็นศูนย์ก็กลับไปหน้าเดิมไม่ได้
				{
					JOptionPane.showMessageDialog(account.this, "Please select row!!");
					return;
				} else
				{
					Globaldata.CurrentPicture_pictureId = list2.get(table_main.getSelectedRow());
					System.out.println(Globaldata.CurrentPicture_pictureId);
				}
				if (list2 != null)
				{
					account.this.setVisible(false);

					PictureView pv = new PictureView();
					pv.setVisible(true);
				}

			}

		});

		btnSelect.setBounds(238, 168, 117, 23);
		contentPane.add(btnSelect);

		load();
		getfollow();
		
		Globaldata.checkuser_or_not = true;

	}

	ArrayList<pictureDB> list;
	ArrayList<Integer> list2 = new ArrayList<Integer>();
	ArrayList<followerDB> listfollower;
	ArrayList<FollowingDB> listfollowing;
	private JTable table_main;
	private JLabel lblHead;
	private JTable table_follow;

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
			if (c.user_id == Globaldata.CurrentUser_userID)
			{
				model.addRow(new Object[]
				{ c.pic, c.driscripe, c.date, c.like });
				list2.add(c.picture_id);
			}
		}

		// +++++++++++++++++
		System.out.println(Arrays.deepToString(list2.toArray()));
		table_main.setModel(model);

		table_main.getColumn("pic").setCellRenderer(new InvoiceDetailTableRenderer()); // ________________________การเพิ่งตารางในcolum_________

		for (int i = 0; i < table_main.getRowCount(); i++)
		{
			table_main.setRowHeight(i, 200);
		}

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
			if(d.user_id == Globaldata.CurrentUser_userID)
			{
				sum2+=d.following_num;
			}
		}
		for (followerDB c : listfollower) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			if (c.other_id == Globaldata.CurrentUser_userID)
			{
				sum+=c.follower_num;
				
			}
		}
		
		model.addRow(new Object[]
				{ sum, sum2 });
		table_follow.setModel(model);
		for (int i = 0; i < table_follow.getRowCount(); i++)
		{
			table_follow.setRowHeight(i, 50);
		}

	}

}

class InvoiceDetailTableRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col)
	{
		ImageComponent i = new ImageComponent();
		i.image = (BufferedImage) value;
		return i;
	}

}

class ImageComponent extends JComponent
{
	public BufferedImage image;

	public void paint(Graphics g)
	{
		if (image != null)
		{
			g.drawImage(image, 0, 0, 200, 200, 0, 0, image.getWidth(), image.getHeight(), this);
		}
	}
}
