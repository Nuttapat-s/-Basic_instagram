package m;

public class commentDB
{
	//	comment_id	id	comment	com_date	picture_id
	public int comment_id;
	public int user_id;
	public String comment;
	public String com_date;
	public int picture_id;
	
	public commentDB()
	{
	}
	
	public commentDB(int xcomment_id ,int xuser_id ,String xcomment ,String xcom_date, int xpicture_id)
	{
		comment_id = xcomment_id;
		user_id =xuser_id;
		comment =xcomment;
		com_date =xcom_date;
		picture_id =xpicture_id;
	}
}


