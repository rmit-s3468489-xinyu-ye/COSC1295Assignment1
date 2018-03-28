/**
 *
 * @author Yifan ZHANG s3615625
 */

package mininet;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {
	
	public static List<User> readData() {
        List<User> people = new ArrayList<User>();
        String line;
        String[] tokens;
        String name;
        int age;
        String photoPath;
        String status;

        try {
            LineNumberReader nread = new LineNumberReader(new FileReader("src/userInfo.csv"));
            while((line = nread.readLine()) != null){
                tokens = line.split(",,");
                name = tokens[0];
                age = Integer.parseInt(tokens[1]);
                photoPath = tokens[2];
                status = tokens[3];

                people.add(new Adult(name, age, photoPath, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }
}
