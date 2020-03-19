package m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.Globaldata;

public class followerManager
{
	public static void SaveFollower(followerDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "INSERT INTO follower VALUE(? , ? , ? , ? )";

			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setInt(2, x.user_id);
			st.setInt(3, x.follower_num);
			st.setInt(4, x.other_id);
			
			st.executeUpdate();

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	
	public static ArrayList<followerDB> getAllFollower()
	{
		ArrayList<followerDB> list = new ArrayList<followerDB>(); // µÈÕßª√–°“»µ—«·ª√

		try
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM follower";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				// picture_id id pic picName picDate comment_id

				int follower_id = rs.getInt("follower_id");
				int user_id = rs.getInt("user_id");
				int follower_num=rs.getInt("follower_num");
				int other_id =rs.getInt("other_id");
				
				

				followerDB cc = new followerDB(follower_id,user_id,follower_num,other_id);
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
