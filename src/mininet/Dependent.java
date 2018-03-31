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
    
    public void setFather(Adult father) 
    {
    		this.parents[0] = father;
    }
    
    public void setMother(Adult mother) 
    {
    		this.parents[1] = mother;
    }
    
    //Append the name of the parents of a 
    //particular dependent to its profile
    @Override
    public String toString()
    {
    		if(this.parents[0] == null || this.parents[1] == null)
    			return super.getUserInfo();
    		else
    			return super.getUserInfo() +
                "\n\t\t\tParents: " + parents[0].getName() + ", " + parents[1].getName();
			
    }
}
