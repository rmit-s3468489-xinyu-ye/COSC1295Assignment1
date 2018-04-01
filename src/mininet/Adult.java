/**
 *
 * @author Xinyu YE s3468489
 */
package mininet;
import java.util.*;

public class Adult extends User 
{
    private Adult spouse;
    private List<Dependent> children;
    
//    public Adult(String name)
//    {
//        this.name = name;
//        children = new ArrayList<Dependent>();
//    }
    
    public Adult(String name,int age, String photoPath, String status)
    {
        super(name,age, photoPath, status);
        children = new ArrayList<Dependent>();
    }
    
    public Adult getSpouse() 
    {
        return spouse;
    }
    
    public void setSpouse(Adult spouse) 
    {
        this.spouse = spouse;
    }

    public List<Dependent> getChildren() 
    {
        return children;
    }
    
    public void addChild(Dependent child) 
    {
        children.add(child);
    }
    
    @Override
    public String toString()
    {
        
        boolean nokid = children.isEmpty();
        String commonInfo = super.getUserInfo();
        
        StringBuilder result = new StringBuilder(commonInfo);
        
        if (!(spouse == null))
            result.append("\n\t\t\tSpouse: ")
                    .append(this.spouse.name);
        
        result.append("\n\t\t\tChild(ren):");
        
        if (!nokid) 
        {
            for (Dependent kid : children)
                result.append("\t").append(kid.getName());
        }
        else 
        		result.append("This user does not have any children");
        
        return result.toString();        
    }
}