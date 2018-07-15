package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class MiscUtilities
{
	private static Random random = new Random();
	
	public static void appendToLabel(Label label, String text)
	{
		label.setText(label.getText() + text);
	}
	
	public static boolean isEven(int x)
	{
		if ( (x & 1) == 0 ) { return true; } else { return false; }
	}
	
	public static void setInvisible(TableColumn<?, ?> ... columns)
	{
		for(TableColumn<?, ?> c:columns)
		{
			c.setVisible(false);
		}
	}
	
	public static void setVisible(TableColumn<?, ?> ... columns)
	{
		for(TableColumn<?, ?> c:columns)
		{
			c.setVisible(true);
		}
	}
	
	public static String generateRandomString()
	{
		return generateRandomString("", 0);
	}
	
	public static String generateRandomString(String characters, int length)
	{
		if(characters=="")characters="abcdefghijklmnopqrstuvwxyz";
		if(length==0)length=8;
		
		char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(random.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	/** Generates a random int between 0 and 100 
	 *
	 */
	public static int generateRandomInt(){
		return generateRandomInt(0, 100);
	}
	public static int generateRandomInt(int min, int max)
	{
		int randomNum = random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static double generateRandomDouble(double min, double max)
	{
		double randomValue = min + (max - min) * random.nextDouble();
		return randomValue;
	}
	
	public static double round(double number, int decimalPlaces)
	{
	   if (decimalPlaces < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(number);
	    bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static String readFromFile(String filePath)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while((line=bufferedReader.readLine())!=null)
			{
				sb.append(line);
				sb.append("\n");
			}
			
			
			bufferedReader.close();
			fileReader.close();
		}
		catch(Exception e)
		{
			System.out.println((e.toString()));
		}
		return sb.toString();
	}
	
	public static void writeToFile(String filePath, String content, boolean append)
	{
		try
		{
			FileWriter fw = new FileWriter(filePath,append);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void log(String s)
	{
		System.out.println("\r\n"+s);
		writeToFile("C:/Users/Ankur/Downloads/java/randomshit/log.txt", "\r\n"+(new Timestamp((new Date()).getTime())).toString() + " : \r\n"+s + "\r\n", true);
	}
	
	public static void log(Exception e)
	{
		System.out.println("\r\n"+e.toString());
		System.out.println("\r\n"+e.getLocalizedMessage());
		System.out.println("\r\n"+e.getMessage());
		System.out.println("\r\n"+e.getStackTrace());
		writeToFile("C:/Users/Ankur/Downloads/java/randomshit/log.txt", "\r\n"+(new Timestamp((new Date()).getTime())).toString() + " : \r\n"+e.toString() + "\r\n", true);
		writeToFile("C:/Users/Ankur/Downloads/java/randomshit/log.txt", "\r\n"+(new Timestamp((new Date()).getTime())).toString() + " : \r\n"+e.getMessage() + "\r\n", true);
		writeToFile("C:/Users/Ankur/Downloads/java/randomshit/log.txt", "\r\n"+(new Timestamp((new Date()).getTime())).toString() + " : \r\n"+e.getLocalizedMessage() + "\r\n", true);
	}
	
	public static double formatDouble(double d)
	{
		if(Double.isNaN(d)||Double.isInfinite(d))
		{
			return 0.00;
		}
		else
		{
			return round(d, 2);
		}
	}
}
