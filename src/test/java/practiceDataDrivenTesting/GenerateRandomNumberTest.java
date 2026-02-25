package practiceDataDrivenTesting;

import java.util.Random;

public class GenerateRandomNumberTest {

	public static void main(String[] args) 
	{
		Random r=new Random();
		int rNum=r.nextInt(1000);
		System.out.println(rNum);
	}

}
