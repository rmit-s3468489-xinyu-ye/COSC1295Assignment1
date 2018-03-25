package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Adult extends User
{
    private Adult spouse;
    
    private List<Dependent> children;
    
    public Adult(String name, int age)
    {
        super(name, age);   
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
    
    public void setChildren(List<Dependent> children)
    {
        this.children = children;
    }
    
    public void addChildren(Dependent child)
    {
        children.add(child);
    }
    
    
}
