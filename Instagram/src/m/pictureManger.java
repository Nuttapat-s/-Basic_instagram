package m;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Timestamp;
import java.sql.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import common.Globaldata;

public class pictureManger
{
	public static ArrayList<pictureDB> getAllPicture()
	{
		ArrayList<pictureDB> list = new ArrayList<pictureDB>(); // ต้องประกาศตัวแปร

		try
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM picture";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				// picture_id id pic picName picDate comment_id

				int picture_id = rs.getInt("picture_id");
				int user_id = rs.getInt("user_id");
				byte[] img_byte = rs.getBytes("pic");
				String picName = rs.getString("picName");
				String picDate = rs.getString("picDate");
				int like = rs.getInt("like_total");
				BufferedImage bufferedimg = null;
				if (img_byte == null || img_byte.length == 0)
				{

				} else
				{

					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}

				pictureDB cc = new pictureDB(picture_id, user_id, bufferedimg, picName, picDate, like);
				list.add(cc); // ให้customerตัวใหม่แอดลงlist
				// print the results

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static void saveNewpicture(pictureDB x)
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

			String query = "INSERT INTO picture VALUE(? , ? , ? , ? , ? , ? )";

			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setInt(2, Globaldata.CurrentUser_userID);
			st.setString(4, x.driscripe);
			st.setString(5, x.date);
			st.setInt(6, x.like);

			if (x.pic != null)
			{
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				ImageIO.write(x.pic, "png", outStream);
				byte[] buffer = outStream.toByteArray();
				st.setBytes(3, buffer);
				outStream.close();
			} else
			{
				byte[] buffer = new byte[0];
				st.setBytes(3, buffer);
			}

			st.executeUpdate();

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void updatelike( int like_total,int picture_id)
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

			String query = "UPDATE  picture SET like_total = ? WHERE picture_id = ? ";

			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,like_total);
			st.setInt(2,picture_id );
			
			st.executeUpdate();
			
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void deletePicture(int picture_id) // ก้อปข้างบนมาละเปลี่ยนที่จดเดิม,เปลี่ยนชื่อ
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

					String query = "DELETE FROM picture WHERE picture_id = ? ";

					PreparedStatement st = conn.prepareStatement(query);
					st.setInt(1,picture_id);
					
					
					st.executeUpdate();
					
					
					st.close();
				} catch (Exception e)
				{
					System.err.println("Got an exception! ");
					System.err.println(e.getMessage());
				}
	}
}
