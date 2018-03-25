package mininet;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Dependent extends User
{
    /*
    The ollowing field judges whether a particular dependent
    is less than 3 years-old
    respectively
    */
    private boolean underTwoYears;
    
    public Dependent(String name, int age, String photoPath, String status, String father, String mother,
            boolean underTwoYears)
    {
        super(name, age, photoPath, status, father, mother);        
        
        this.underTwoYears = underTwoYears;
    }     
    
    public boolean underTwoYears()
    {
        return underTwoYears;
    }
    
    public void setThreeYearsOld(boolean underTwoYears)
    {
        this.underTwoYears = underTwoYears;
    }

	
}
