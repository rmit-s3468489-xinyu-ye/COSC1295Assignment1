package mininet;
import java.util.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class Adult extends User {
    
	private Adult couple;
    private List<Dependent> kids;

    public Adult(String name){
        this.name = name;
        kids = new ArrayList<Dependent>();
    }

    public Adult(String name,int age, String photoPath, String status){
        super(name,age, photoPath, status);
        kids = new ArrayList<Dependent>();
    }
    
    public Adult getCouple() {
        return couple;
    }

    public void setCouple(Adult couple) {
        this.couple = couple;
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
        String prefixInfo = super.getUserInfo();

        StringBuilder result = new StringBuilder(prefixInfo);
        
        if (!(couple == null)) 
        	result.append("\n\tCouple: ")
        		  .append(this.couple.name); 
        
            result.append("\nchild(ren):");
        if (!nokid) {            
            for (Dependent kid : kids)
                result.append("\n\t").append(kid.getName());
        }else {
        	result.append("the user has not set ady child yet.");
        }
        result.append("\n");

        return result.toString();

    }
}