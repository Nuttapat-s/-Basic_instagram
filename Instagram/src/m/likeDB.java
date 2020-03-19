package m;

public class likeDB
{
	//like_id	user_id	like_count	picture_id
	public int like_id;
	public int user_id;
	public int like_count;
	public int picture_id;
	
	public likeDB()
	{
	}
	
	public likeDB(int xlike_id,int xuser_id,int xlike_count,int xpicture_id)
	{
		like_id=xlike_id;
		user_id=xuser_id;
		like_count=xlike_count;
		picture_id=xpicture_id;
	}
}
