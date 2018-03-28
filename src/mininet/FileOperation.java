/**
 *
 * @author Yifan ZHANG s3615625
 */

package mininet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {
	
//	public static List<User> readData() {
//        List<User> members = new ArrayList<User>();
//        String line;
//        String[] tokens;
//        String name;
//        int age;
//        String photoPath;
//        String status;
//        
//
//        try {
//            LineNumberReader nread = new LineNumberReader(new FileReader("src/userInfo.csv"));
//            while((line = nread.readLine()) != null){
//                tokens = line.split(",,");
//                name = tokens[0];
//                age = Integer.parseInt(tokens[1]);
//                photoPath = tokens[2];
//                status = tokens[3];
//
//                members.add(new Adult(name, age, photoPath, status));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return members;
//    }
	
	public static List<User> readFromFile()
	{
		List<User> members = new ArrayList<User>();
        String line,name, photoPath, status;
        String[] args;
        int age;
        	
        try 
		{
			File f = new File("src/userInfo.csv");
			if (!f.exists() || f.isDirectory())
			{
				f.createNewFile(); 
			}       
			BufferedReader inputStream = 
					new BufferedReader(new FileReader("src/userInfo.csv"));

			line = "";

			while(true)
			{
				line = inputStream.readLine();
				if (line == null)
				{
					break;
				}  
				args = line.split(",,");

				members.add(new Adult(args[0], Integer.parseInt(args[1]), args[2], args[3]));
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
}
