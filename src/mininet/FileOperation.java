/**
 *
 * @author Yifan ZHANG s3615625
 */

package mininet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {

	static List<User> members = new ArrayList<User>();
	static String line;
	String name;
	String photoPath;
	String status;
	static String[] args;
	int age;
	
	public static List<User> readFromFile()
	{
		
		try 
		{
			File f = new File("members.txt");
			if (!f.exists() || f.isDirectory())
			{
				f.createNewFile(); 
			}       
			BufferedReader inputStream = 
					new BufferedReader(new FileReader("members.txt"));

			line = "";

			while(true)
			{
				line = inputStream.readLine();
				if (line == null)
				{
					break;
				}  
				args = line.split(",");
				

				if(Integer.parseInt(args[1]) > 16)
					members.add(new Adult(args[0], Integer.parseInt(args[1]), args[2], args[3]));
				else
				{
					Adult[] parents = new Adult[2];
					parents[0] = (Adult)getUserByName(args[4],members);
					parents[1] = (Adult)getUserByName(args[5],members);
					members.add(new Dependent(args[0], Integer.parseInt(args[1]), args[2], args[3], parents));
				}

			}
			//release the resource
			inputStream.close();
		} 
		catch (FileNotFoundException ffe) 
		{
			ffe.printStackTrace();
			System.out.println("File members.txt was not found");
			System.out.println("or could not be opened");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.out.println("Fail to read from members.txt");
		}
		return members;
	}

	public static void writeToFile()
	{


		PrintWriter outputStream = null;
		try
		{
			String filePath = "members.txt";
			File f = new File(filePath);
			if(!f.exists() || f.isDirectory()) { 
				f.createNewFile();
			}


			outputStream = 
					new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error writing to the file members.txt.");
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println("Unhandled IOException");
			System.exit(0);
		}

		for(int i=0; i<members.size(); i++)
		{
			String userstr="";
			User u = members.get(i);
			userstr=u.getName() + "," + u.getAge() + "," + u.getPhotoPath() + "," + u.getStatus();
			
			
			if(u instanceof Dependent)
			{
			
				userstr+=","+ ((Dependent)u).getParents()[0].getName()+",";
				userstr+=","+ ((Dependent)u).getParents()[1].getName();
			}
			outputStream.println(userstr);
				

		}

		outputStream.close( );

	}
	
	private static User getUserByName(String name,List members){
        for (Object o: members) {
        		User user=(User)o;
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }  
}
