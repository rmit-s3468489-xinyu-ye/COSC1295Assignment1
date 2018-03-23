package mininet;
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
    private boolean isThreeYearsOld;
    
    public Dependent(String name, int age, Adult[] parents,
            boolean isThreeYearsOld)
    {
        super(name, age);
        
        this.parents = parents;
        
        this.isThreeYearsOld = isThreeYearsOld;
    }
      
    public Adult[] getParents()
    {
        return parents;
    }
    
    public void setParents(Adult[] parents)
    {
        this.parents = parents;
    }
    
    public boolean isThreeYearsOld()
    {
        return isThreeYearsOld;
    }
    
    public void setThreeYearsOld(boolean isThreeYearsOld)
    {
        this.isThreeYearsOld = isThreeYearsOld;
    }   
}
