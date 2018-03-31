/**
 *
 * @author Xinyu YE s3468489
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

public class FileOperation 
{
	String name, photoPath, status;
	static String line;
	static String[] args;
	static List<User> existedUsers = new ArrayList<User>();
	int age;
	
	public static List<User> readFromFile()
	{
		try 
		{
			File f = new File("existedUsers.txt");
			if (!f.exists() || f.isDirectory())
				f.createNewFile(); 
			
			BufferedReader inputStream = 
					new BufferedReader(new FileReader("existedUsers.txt"));

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
					existedUsers.add(new Adult(args[0], Integer.parseInt(args[1]), args[2], args[3]));
				else
				{
					Adult[] parents = new Adult[2];
					parents[0] = (Adult)getUserByName(args[4],existedUsers);
					parents[1] = (Adult)getUserByName(args[5],existedUsers);
					existedUsers.add(new Dependent(args[0], Integer.parseInt(args[1]), args[2], args[3], parents));
				}
			}
			//release the resource
			inputStream.close();
		} 
		catch (FileNotFoundException ffe) 
		{
			ffe.printStackTrace();
			System.out.println("File existedUsers.txt was not found");
			System.out.println("or could not be opened");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.out.println("Fail to read from existedUsers.txt");
		}
		return existedUsers;
	}

	public static void writeToFile()
	{

		PrintWriter outputStream = null;
		try
		{
			File f = new File("existedUsers.txt");
			if(!f.exists() || f.isDirectory())  
				f.createNewFile();
			
			outputStream = 
					new PrintWriter(new FileOutputStream("existedUsers.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error writing to the file existedUsers.txt.");
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println("Unhandled IOException");
			System.exit(0);
		}

		for(int i=0; i <existedUsers.size(); i++)
		{
			String userInfo="";
			User u = existedUsers.get(i);
			userInfo = u.getName() + "," + u.getAge() + "," + u.getPhotoPath() + "," + u.getStatus();
			
			if(u instanceof Dependent && ((Dependent) u).getParents() != null)
			{
				userInfo+=","+ ((Dependent)u).getParents()[0].getName()+",";
				userInfo+=","+ ((Dependent)u).getParents()[1].getName();
			}
			outputStream.println(userInfo);
		}
		outputStream.close( );
	}
	
	/**
	 * 
	 * @param name
	 * @param existedUsers
	 * @return a particular user existed in the ArrayList existedUsers
	 * 
	 */
	private static User getUserByName(String name,List existedUsers)
	{
        for (Object o: existedUsers) 
        {
        		User user = (User)o;
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }  
}
