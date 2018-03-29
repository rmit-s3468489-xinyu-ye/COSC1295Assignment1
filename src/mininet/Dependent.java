package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Dependent extends User{
    
    private Adult[] parents;
    
    public Dependent(String name, int age, String photoPath, String status, Adult[] parents){
        super(name, age, photoPath, status);
        this.parents = parents;
    }
   
    public Adult[] getParents() {
        return parents;
    }
    
    public void setParents(Adult[] parents) {
        this.parents = parents;
    }
    
    @Override
    public String toString(){
        
        return super.getUserInfo() +
                "\nParents: " + parents[0] + ", " + parents[1];
    }
}
