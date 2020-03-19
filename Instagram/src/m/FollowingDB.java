package m;

public class FollowingDB
{
	public int following_id;
	public int user_id;
	public int following_num;
	public int other_id;
	
	
	public  FollowingDB()
	{
		
	}
	
	public FollowingDB(int xfollowing_id,int xuser_id,int xfollowing_num,int xother_id)
	{
		following_id=xfollowing_id;
		user_id=xuser_id;
		following_num=xfollowing_num;
		other_id=xother_id;
	}
}
