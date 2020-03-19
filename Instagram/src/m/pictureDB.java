package m;

import java.awt.image.BufferedImage;

public class pictureDB
{
	//picture_id	id	pic	picName	picDate	comment_id
	public int picture_id;
	public int user_id;
	public BufferedImage pic;
	public String driscripe;
	public String date;
	public int like;
	
	public pictureDB()
	{
		
	}

	public pictureDB(int xpicture_id, int xuser_id, BufferedImage xpic, String xpicName , String xpicDate ,int xlike)
	{
		picture_id=xpicture_id;
		user_id = xuser_id;
		pic=xpic;
		driscripe=xpicName;
		date =xpicDate;
		like =xlike;
	}
	
}
