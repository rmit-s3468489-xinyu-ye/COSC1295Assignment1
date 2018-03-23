package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public abstract class User
{
    private String name, status, photoPath;
    
    private int age;
    
    private List<User> connections;
    
    public User(String name, int age)
    {
        this.name = name;
        this.age = age;
        
       /*
        The following three fields have been set to be null
        by default,
        because they are optional for a user as instructed in the
        assignment specification
        */
        status = null;
        photoPath = null;
        connections = null;
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
    
    public String getPhotoPath()
    {
        return photoPath;
    }
    
    public void setPhotoPath(String photoPath)
    {
        this.photoPath = photoPath;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public List<User> getConnections()
    {
        return connections;
    }
    
    /* Only when a particular user start to connect to other
       users, they will have a connections list
    */
    public void setConnections(List<User> connections)
    {
        this.connections = connections;
    }
    
    //we treat the literal meanign of adding a person
    //into the network as following a user's profile
    public void addUser(User u)
    {
        this.connections.add(u);
    }
    
    //we treat the literal meanign of deleting the
    //selected person as unfollowing a user's profile
    public void deleteUser(User u)
    {
        this.connections.remove(u);
    }
    
    public String retrieveProfile()
    {
        boolean hasConnections = false;
        
        if(!connections.isEmpty())
            hasConnections = true;
        
        StringBuffer retrieval = new StringBuffer();
        if(this instanceof Adult)
            retrieval.append("\nAdult");
        else
            retrieval.append("\nDependent");
        
        retrieval.append("\nname: ")
                 .append(this.name)
                 .append("\nStatus: ")
                 .append(this.status)
                 .append("\nProfile Photo: ")
                 .append(this.photoPath);
        
        /*
        The retrieved profile will display its connections
        only when the selected user is connected to other
        users.
        */
        if(hasConnections == true)
        {
            retrieval.append("\nConnections: ");
            for (User u : connections) 
            {
                retrieval.append("\n").append(u.getName());
            }
        }     
        return retrieval.toString();             
    }
}
