package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Dependent extends User
{
    //As instructed in the assignment specification,
    //a dependent must have 2 parents exactly,
    //assuming there are no orphans or dependents
    //who have a single parent
    private Adult parents[];
    /*
    The ollowing field judges whether a particular dependent
    is less than 3 years-old
    respectively
    */
    private boolean TwoYearsOldOrYounger;
    
    public Dependent(String name, int age, Adult[] parents,
            boolean TwoYearsOldOrYounger)
    {
        super(name, age);
        this.parents = parents;
        this.TwoYearsOldOrYounger = TwoYearsOldOrYounger;

    }
      
    public Adult[] getParents()
    {
        return parents;
    }
    
    public void setParents(Adult[] parents)
    {
        this.parents = parents;
    }
    
    public boolean TwoYearsOldOrYounger()
    {
        return TwoYearsOldOrYounger;
    }
    
    public void setThreeYearsOld(boolean TwoYearsOldOrYounger)
    {
        this.TwoYearsOldOrYounger = TwoYearsOldOrYounger;
    }   
}
