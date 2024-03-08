package sales;

import org.testng.annotations.Test;

public class SampleTest 
{
	@Test(groups = "smoke")
	public void sample1()
	{
		System.out.println("---ts3---");
	}
	
	@Test(groups = {"smoke","regression"})
	public void sample2()
	{
		System.out.println("---ts4---");
	}
}
