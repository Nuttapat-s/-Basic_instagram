package m;

public class followerDB
{
	//follower_id	user_id	follower_num
	public int follower_id;
	public int user_id;
	public int follower_num;
	public int other_id;
	
	
	public  followerDB()
	{
		
	}
	
	public followerDB(int xfollower_id,int xuser_id,int xfollower_num,int xother_id)
	{
		follower_id=xfollower_id;
		user_id=xuser_id;
		follower_num=xfollower_num;
		other_id=xother_id;
	}
}
