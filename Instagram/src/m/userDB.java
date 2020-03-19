package m;

import java.awt.image.BufferedImage;

public class userDB
{
	//user_id	username	password	name	surname	Email	phone	country	profile_image

	public int user_id;
	public String username;
	public String password;
	public String name;
	public String surname;
	public String Email;
	public int phone;
	public String country;
	public BufferedImage profile_image;

	public userDB()
	{
	}

	public userDB(int xuser_id, String xusername, String xpassword, String xname,String xsurname,String xEmail,int xphone,String xcountry,BufferedImage xprofile_image)
	{
		user_id = xuser_id;
		username=xusername;
		password=xpassword;
		name=xname;
		surname=xsurname;
		Email=xEmail;
		phone=xphone;
		country=xcountry;
		profile_image=xprofile_image;
		
		
		
	}
}
