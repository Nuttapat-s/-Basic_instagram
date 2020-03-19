package m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.Globaldata;

public class FollowingManager
{

	public static void NewFollowing(FollowingDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "INSERT INTO following VALUE(? , ? , ? , ? )";

			PreparedStatement st = conn.prepareStatement(query);
			
			st.setInt(1, x.following_id);
			st.setInt(2, x.user_id);
			st.setInt(3, x.following_num);
			st.setInt(4, x.other_id);
			
			st.executeUpdate();

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	
	public static ArrayList<FollowingDB> getAllFollowing()
	{
		ArrayList<FollowingDB> list = new ArrayList<FollowingDB>(); // ต้องประกาศตัวแปร

		try
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM following";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				// picture_id id pic picName picDate comment_id
				int following_id =rs.getInt("following_id");
				int user_id = rs.getInt("user_id");
				int following_num=rs.getInt("following_num");
				int other_id =rs.getInt("other_id");
				
				

				FollowingDB cc = new FollowingDB(following_id,user_id,following_num,other_id);
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

