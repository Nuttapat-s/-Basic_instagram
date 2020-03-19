package v;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import common.Globaldata;
import m.pictureDB;
import m.pictureManger;
import m.userDB;
import m.userManager;
import v.InvoiceDetailTableRenderer;
import v.ImageComponent;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchFriendFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table;

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
					SearchFriendFrame frame = new SearchFriendFrame();
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
	public SearchFriendFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 419, 489);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchFriendFrame.this.setVisible(false);
				
				account ac =new account();
				ac.setVisible(true);
			}
		});
		btnBack.setBounds(354, 0, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnVIEW = new JButton("VIEW");
		btnVIEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0) // getselectedrow ถ้าเป็นศูนย์ก็กลับไปหน้าเดิมไม่ได้
				{
					JOptionPane.showMessageDialog(SearchFriendFrame.this, "Please select row!!");
					return;
				} else
				{
					Globaldata.CurrentOtherAccount_ViewOtherAccount = list2.get(table.getSelectedRow());
					
				}
				if (list2 != null)
				{
					OtherView pv = new OtherView();
					pv.setVisible(true);
				}
				
			}
		});
		btnVIEW.setBounds(169, 542, 89, 23);
		contentPane.add(btnVIEW);
		
		load();
	}
	
	ArrayList<userDB> list;
	ArrayList<Integer> list2 = new ArrayList<Integer>();
	public void load()
	{
		list=userManager.getAllUser();
		list2.clear();
		System.out.println(Arrays.deepToString(list.toArray()));
		
		
		
		DefaultTableModel model = new DefaultTableModel(); // ต้องcast

		model.addColumn("profile_picture");
		model.addColumn("username");
		

		for (userDB c : list) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			if (c.user_id != Globaldata.CurrentUser_userID)
			{
				model.addRow(new Object[]
				{ c.profile_image,c.username });
				list2.add(c.user_id);
				
			}
		}

	
		
		table.setModel(model);
		table.getColumn("profile_picture").setCellRenderer(new InvoiceDetailTableRenderer()); // ________________________การเพิ่งตารางในcolum_________

		for (int i = 0; i < table.getRowCount(); i++)
		{
			table.setRowHeight(i, 150);
			
		}
	}
}
	
	
	




