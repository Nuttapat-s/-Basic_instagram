package m;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import common.Globaldata;

public class likeManager
{
	public static void SaveLike(likeDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "INSERT INTO likes VALUE(? , ? , ? , ? )";

			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setInt(2, x.user_id);
			st.setInt(3, x.like_count);
			st.setInt(4, x.picture_id);

			st.executeUpdate();

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<likeDB> getAllLike()
	{
		ArrayList<likeDB> list = new ArrayList<likeDB>(); // µÈÕßª√–°“»µ—«·ª√

		try
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM likes";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				// picture_id id pic picName picDate comment_id

				int like_id = rs.getInt("like_id");
				int user_id = rs.getInt("user_id");
				int like_count=rs.getInt("like_count");
				int picture_id =rs.getInt("picture_id");
				

				likeDB cc = new likeDB(like_id,user_id,like_count,picture_id);
				list.add(cc); // „ÀÈcustomerµ—«„À¡Ë·Õ¥≈ßlist
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
}
