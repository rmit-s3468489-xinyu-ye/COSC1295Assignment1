package mininet;
import java.util.*;

import com.sun.xml.internal.fastinfoset.util.StringArray;
/**
 *
 * @author Xinyu YE s3468489
 */
public abstract class User
{
    private String name, photoPath, status, father, mother, child1, child2;
    
    private int age;
    

    private List<User> friends;
  
    public User() {
    	friends = new ArrayList<User>();
    }
    public User(String name, int age, String photoPath, String status, 
    		String father, String mother, String child1, String child2)
    {
        this.name = name;
        this.age = age;
        this.photoPath = photoPath;
        this.status = status;
        this.father = father;
        this.mother = mother;
        this.child1 = child1;
        this.child2 = child2;
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
    

    public String getChild1()

    {
        return child1;
    }
    

    public void setChild1(String child1)

    {
        this.child1 = child1;
    }
    
    public String getChild2()
    {
        return child2;
    }
    
    public void setChild2(String child2)
    {
        this.child2 = child2;
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
        boolean hasFriends = false;
        
        if(!friends.isEmpty())
            hasFriends = true;
        
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
        
        if (this.child1 != null)
        	retrieval.append("\nchildren: ")
        			 .append(this.child1)
        			 .append("\t");
        if (this.child2 != null)
        	retrieval.append(this.child1);
        
        if (hasFriends = true) {
        	retrieval.append("\ntfriend(s):");
        	for (User u : friends)
        		retrieval.append("\t").append(u.getName());
            }
            	
        return retrieval.toString();
            	      	          
    }
}
