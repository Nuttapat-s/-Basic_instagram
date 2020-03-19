package m;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import common.Globaldata;

public class commentManager
{
	public static ArrayList<commentDB> getAllComment()
	{
		ArrayList<commentDB> list = new ArrayList<commentDB>(); // µÈÕßª√–°“»µ—«·ª√

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM comments";

		
			Statement st = conn.createStatement();


			ResultSet rs = st.executeQuery(query);

			
			while (rs.next())
			{
//				comment_id	user_id	comment	com_date	picture_id
				int comment_id = rs.getInt("comment_id");
				int user_id =rs.getInt("user_id");
				String comment =rs.getString("comment");
				String com_date = rs.getString("com_date");
				int picture_id = rs.getInt("picture_id");

				commentDB cc = new commentDB(comment_id, user_id,comment , com_date, picture_id);
				list.add(cc); // „ÀÈcustomerµ—«„À¡Ë·Õ¥≈ßlist
				// print the results
				System.out.format("%s, %s, %s, %s \n", comment_id, user_id,comment , com_date, picture_id);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static void saveNewComment(commentDB x) 
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

			String query = "INSERT INTO comments VALUE(? , ? , ? , ? , ? )";

			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setInt(2, x.user_id);
			st.setString(3, x.comment);
			st.setString(4, x.com_date);
			st.setInt(5, x.picture_id);

			st.executeUpdate();
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
