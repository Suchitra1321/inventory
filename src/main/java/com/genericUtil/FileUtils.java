package com.genericUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils 
{
	public String readDataFromPropertyFile(String key) throws Throwable
	{

		FileInputStream fis=new FileInputStream(IpathConstants.Filepath);
		Properties pobj = new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;
	}
}
