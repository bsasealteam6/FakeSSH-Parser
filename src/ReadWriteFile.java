import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWriteFile 
{
	public static void writeFile(String fileName, CsvList<SSHTry> sshTries)
	{
		try {
			PrintWriter file = new PrintWriter(fileName, "UTF-8");
			file.println("Date,Time,IP,Port,Software,Username,Password");
			file.println(sshTries.toCSV());
			
			file.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> readFile(String fileName)
	{
		try 
		{
			Scanner inFile = new Scanner(new File(fileName));
			ArrayList<String> lines = new ArrayList<String>();
			while (inFile.hasNext())
			{
				lines.add(inFile.nextLine());
			}
			inFile.close();
			return lines;
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return new CsvList<String>();
		
	}
}

