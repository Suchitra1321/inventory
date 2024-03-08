package com.genericUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtils 
{
	/**
	 * 
	 * @return
	 */
	public int getRandomNo(int range)
	{
		Random ran=new Random();
		int ranNum = ran.nextInt(range);
		return ranNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSystemDate()
	{
		Date dt=new Date();
		String da = dt.toString();
		String date=da.replaceAll(":", "-");
		return date;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date dt = new Date();
		String date = df.format(dt);
		return date;
	}
}
