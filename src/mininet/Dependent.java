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
    these two following fields judges whether a particular dependent
    is less than 3 years-old, or older than 16 years-old,
    respectively
    */
    private boolean isThreeYearsOld, isSixteenYearsOld;
    
    public Dependent(String name, String userName, int age, Adult[] parents,
            boolean isThreeYearsOld, boolean isSixteenYearsOld)
    {
        super(name, userName, age);
        
        this.parents = parents;
        
        this.isThreeYearsOld = isThreeYearsOld;
        
        this.isSixteenYearsOld = isSixteenYearsOld;
        
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
        
        public boolean isSixteenYearsOld()
        {
            return isSixteenYearsOld;
        }
        
        public void setSixteenYearsOld(boolean isSixteenYearsOld)
        {
            this.isSixteenYearsOld = isSixteenYearsOld;
        }
        
}

