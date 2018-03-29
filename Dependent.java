/**
 *
 * @author Xinyu YE s3468489
 */
package mininet;
import java.util.*;

public class Dependent extends User
{
    
    private Adult[] parents;
    
    public Dependent(String name, int age, String photoPath, String status, Adult[] parents)
    {
        super(name, age, photoPath, status);
        this.parents = parents;
    }
   
    public Adult[] getParents() 
    {
        return parents;
    }
    
    public void setParents(Adult[] parents) 
    {
        this.parents = parents;
    }
    
    //Append the name of the parents of a 
    //particular dependent to its profile
    @Override
    public String toString()
    {
        
        return super.getUserInfo() +
                "\nParents: " + parents[0].getName() + ", " + parents[1].getName();
    }
}
