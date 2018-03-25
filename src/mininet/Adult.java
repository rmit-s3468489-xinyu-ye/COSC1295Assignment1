package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Adult extends User
{  
    private List<Dependent> child;     
    
    public Adult (String name, int age, String photoPath, String status, String father, String mother) {
    	
        super(name, age, photoPath, status, father, mother);
        child = new ArrayList<Dependent>();        
    }    
    
    public List<Dependent> getChild()
    {
        return child;
    }
    
    public void setChild(List<Dependent> child)
    {
        this.child = child;
    }
    
    @Override
    public String toString(){
        boolean hasChild = child.isEmpty();
        String changeAdultInfo = super.selectUser();

        StringBuilder result = new StringBuilder(changeAdultInfo);

        if (hasChild) {
            result.append("\nchild:");
            for (Dependent Dependent : child)
                result.append("\n").append(Dependent.getName());
        }
        result.append("\n");

        return result.toString();

    }
    
}
