package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public abstract class User 
{
    /*
     * The following fields' access control modifiers
     * have been set to be protected, for User's subclasses
     * to inherit
     */
    protected String name, photoPath, status;
    protected int age;
    protected List<User> friends;
    
//    protected User()
//    {
//        friends = new ArrayList<User>();
//    }
    
    protected User(String name,int age, String photoPath, String status)
    {
        this.name = name;
        this.age = age;
        this.photoPath = photoPath;
        this.status = status;
        this.friends = new ArrayList<User>();
    }
    
    public String getName() {
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
    
    public List<User> getFriends() 
    {
        return friends;
    }
    
    public void addFriend(User u) 
    {
        this.friends.add(u);
    }
    public void delFriend(User u) 
    {
        this.friends.remove(u);
    }
    
    public String getUserInfo()
    {
        boolean hasFriend = !friends.isEmpty();
        
        StringBuilder result = new StringBuilder();
     
        result.append("\n\t\t\tName: ")
                .append(this.name)
                .append("\n\t\t\tAge: ")
                .append(this.age)
                .append("\n\t\t\tPhoto: ")
                .append(this.photoPath)
                .append("\n\t\t\tStatus: ")
                .append(this.status)
                .append("\n\t\t\tFriend(s): ");
        
        if (hasFriend) 
        {
            for (User u : friends)
                result.append("\t")
                        .append(u.getName());
        }
        else
        		result.append("This user does not have any friends");
        
        return result.toString();
    }
}
