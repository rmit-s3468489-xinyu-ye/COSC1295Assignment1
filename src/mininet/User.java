package mininet;
import java.util.*;

import com.sun.xml.internal.fastinfoset.util.StringArray;
/**
 *
 * @author Xinyu YE s3468489
 */
public abstract class User
{
    String name, photoPath, status, father, mother;
    
    int age;

    List<User> friends;
  
    public User() {
    	friends = new ArrayList<User>();
    }
    public User(String name, int age, String photoPath, String status, 
    		String father, String mother)
    {
        this.name = name;
        this.age = age;
        this.photoPath = photoPath;
        this.status = status;
        this.father = father;
        this.mother = mother;
        
    }
    
    public String getName()
    {
        return name;
    }
      
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getAge() 
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
        
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getPhotoPath()
    {
        return photoPath;
    }
    
    public void setPhotoPath(String photoPath)
    {
        this.photoPath = photoPath;
    }
    
    public String getFather()
    {
        return father;
    }
    
    public void setFather(String father)
    {
        this.father = father;
    }
    
    public String getMother()
    {
        return mother;
    }
    
    public void setMother(String mother)
    {
        this.mother = mother;
    }
    
    
    public List<User> getFriends()
    {
        return friends;
    }
    
    public void addFriends(User u) {
        this.friends.add(u);
    }
    
    public void deleteFriends(User u) {
    
        this.friends.remove(u);
    }
    
    public String selectUser()
    {
    	boolean hasFriend = !friends.isEmpty();
        
        StringBuffer retrieval = new StringBuffer();
        
        retrieval.append("\nname: ")
                 .append(this.name)
                 .append("\nprofile: ")
                 .append(this.photoPath)
                 .append("\nage: ")
                 .append(this.age)
                 .append("\nStatus: ")
                 .append(this.status);
        
        if (this.father != null)
        	retrieval.append("\nparents: ")
        	         .append(this.father)
        	         .append("\t")
        	         .append(this.mother);

        if (hasFriend) {
        	retrieval.append("\ntfriends:");
        	for (User u : friends)
        		retrieval.append("\t").append(u.getName());
            }
            	
        return retrieval.toString();
            	      	          
    }
}
