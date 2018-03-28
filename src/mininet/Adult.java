package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Adult extends User {
    
	private Adult spouse;
    private List<Dependent> kids;

    public Adult(String name){
        this.name = name;
        kids = new ArrayList<Dependent>();
    }

    public Adult(String name,int age, String photoPath, String status){
        super(name,age, photoPath, status);
        kids = new ArrayList<Dependent>();
    }
    
    public Adult getSpouse() {
        return spouse;
    }

    public void setSpouse(Adult spouse) {
        this.spouse = spouse;
    }

    public List<Dependent> getKids() {
        return kids;
    }

    public void addKids(Dependent child) {
        kids.add(child);
    }

    @Override
    public String toString(){
        
        boolean nokid = kids.isEmpty();
        String commonInfo = super.getUserInfo();

        StringBuffer retrieval = new StringBuffer(commonInfo);
        
        if (!(spouse == null)) 
        	retrieval.append("\n\tSpouse: ")
        		  .append(this.spouse.name); 
        
            retrieval.append("\nChild(ren):");
        if (!nokid) {            
            for (Dependent kid : kids)
                retrieval.append("\n\t").append(kid.getName());
        }else {
        	retrieval.append("The selected user does not have any children");
        }
        retrieval.append("\n");

        return retrieval.toString();

    }
}