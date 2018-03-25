package mininet;
import java.util.*;

/**
 *
 * @author Xinyu YE s3468489
 */
public abstract class User
{
    private String name, photoPath, status;

    private int age;

    private List<User> friends;

//    public User()
//    {
//        friends = new ArrayList<User>();
//    }

    
    public User(String name, int age)
    {
        this.name = name;
        this.age = age;
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

    public List<User> getFriends()
    {
        return friends;
    }

    public void addFriend(User u) 
    {
        this.friends.add(u);
    }

    public void deleteFriend(User u) 
    {
        this.friends.remove(u);  
    }

    public String retrieveProfile()
    {
        boolean hasChildren = false;

        boolean hasFriends = false;

        boolean hasParents = false;

        if (!children.isEmpty())
            hasChildren = true;

        if(!friends.isEmpty())
            hasFriends = true;

        if (parents != null)
            hasParents = true;

        StringBuffer retrieval = new StringBuffer();

        retrieval.append("\nname: ")
                .append(this.name)
                .append("\nage: ")
                .append(this.age)
                .append("\nprofile: ")
                .append(this.photoPath)
                .append("\nStatus: ")
                .append(this.status);

        if (hasChildren = true)
        {
            retrieval.append("\nChild(ren): ");
            for (Dependent d : children)
                retrieval.append("\t").append(d.getName());
        }

        if (hasFriends = true)
        {
            retrieval.append("\ntFriend(s):");
            for (User u : friends)
                retrieval.append("\t").append(u.getName());
        }

        if (hasParents = true)
        {
            retrieval.append("\nParents: ");
            for (Adult p : parents)
            {
                retrieval.append("\t").append(p.getName());
            }

        }
        return retrieval.toString();

    }
}
