package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Dependent extends User{

    private Adult[] parents;
    private int age;

    public Dependent(String name, int age, String photoPath, String status, Adult[] parents){
        this.name = name;
        this.parents = parents;
        this.age = age;
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
                "\nage: "+age +
                "\nparents: " + parents[0] + ", " + parents[1] + "\n" ;
    }

}
