package m;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import common.Globaldata;

public class userManager
{
	public static void saveNewuser(userDB x)
	{
		try
		{
			
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "INSERT INTO user VALUE(? , ? , ? , ? , ? , ? , ? , ? , ? )";

			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setString(2, x.username);
			st.setString(3, x.password);
			st.setString(4, x.name);
			st.setString(5, x.surname);
			st.setString(6, x.Email);
			st.setInt(7, x.phone);
			st.setString(8, x.country);

			if (x.profile_image != null)
			{
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				ImageIO.write(x.profile_image, "png", outStream);
				byte[] buffer = outStream.toByteArray();
				st.setBytes(9, buffer);
				outStream.close();
			} else
			{
				byte[] buffer = new byte[0];
				st.setBytes(9, buffer);
			}

			st.executeUpdate();

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	public static boolean checkLogin(String username, String password)
	{
		try       
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM user WHERE username = ? AND password = ? ";     //xx
			System.out.println(query);
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2,password);
			
			
			
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				Globaldata.CurrentUser_userID = rs.getInt(1);
				Globaldata.CurrentUser_username = rs.getString(2);
				Globaldata.CurrentUser_profilePic=rs.getBytes(9);
				Globaldata.CurrentUser_name=rs.getString(4);
				
				return true;

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	
	
	public static ArrayList<userDB> getAllUser()
	{
		ArrayList<userDB> list = new ArrayList<userDB>(); 

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM user";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				//user_id	username	password	name	surname	Email	phone	country	profile_image
				int user_id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String Email = rs.getString("Email");
				int phone =rs.getInt("phone");
				String country =rs.getString("country");
				byte[] img_byte = rs.getBytes("profile_image");
				BufferedImage bufferedimg = null;
				if (img_byte == null || img_byte.length == 0)
				{

				} else
				{

					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}

				userDB cc = new userDB(user_id, username, password, name,surname,Email,phone,country,bufferedimg);
				list.add(cc);

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	
}
