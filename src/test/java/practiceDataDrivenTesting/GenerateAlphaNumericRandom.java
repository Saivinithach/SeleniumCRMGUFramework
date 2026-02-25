package practiceDataDrivenTesting;

public class GenerateAlphaNumericRandom 
{
	public static void main(String[] args) 
	{
		int n=20;
		//choose a character random from AlphaNumericString
		String AlphaNumericString="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		
		//create StringBuffer size of AlphaNumericString
		StringBuilder sb=new StringBuilder(n);
		
		for(int i=0;i<n;i++)
		{
			
			//generate a arndom number between 0 to AlphaNumericString variable length
			int index=(int)(AlphaNumericString.length()*Math.random());
			
			//add character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		System.out.println(sb);
	}
}
