 /**
  *
  * @author Yifan ZHANG s3615625
  */
package mininet;
import java.util.List;
import java.util.Scanner;

public class Driver {
    
    private MenuOperation menuOperation;
    private User selectedUser;
    private List<User> theMininet;
    private static Scanner sc;
    
    public Driver(MenuOperation menuOperation){
        this.menuOperation = menuOperation;
        theMininet = FileOperation.readFromFile();
        sc = new Scanner(System.in);
    }
    
    /*
     List the existing members in MiniNet
    */
    
    public void listEveryone() {
        int counter = 1;
        StringBuffer buffer = new StringBuffer("\n\tcurrent people in the social network are:\n");
        for (User u : theMininet){
            buffer.append("\t").append(u.getName());
            
        }
        System.out.println(buffer.toString());
    }
    
    
    public void selectUser() {
        String name;
        System.out.print("\n\nPlease enter the name "
                + "of the user you want to select:");
        name = sc.nextLine();
        String input;
        int option;
        
        if (isUserExisted(name)) {
            selectedUser = getUserByName(name);
            
            if (selectedUser.age > 16) {
                System.out.println((Adult)selectedUser);
            }else {
                System.out.println((Dependent)selectedUser);
            }
            
            /*
            Display the submenu every time a certain user is selected
            */
            
            MenuOperation.display(Interaction.SELECT_USER, selectedUser);
            
            input = sc.nextLine();
            while(!input.matches("[0-7]")) {
                System.out.print("\nInvaild input, please try again: ");
                input = sc.nextLine();
            }
            option = Integer.parseInt(input);
            
            while (option != 0) {
                switch (option){
                    case Interaction.BACK_MAIN:
                        break;
                    case Interaction.SET_PHOTO:
                        setPhoto();
                        break;
                    case Interaction.MAKE_FRIEND:
                        makeFriend();
                        break;
                    case Interaction.DEL_FRIEND:
                        delFriend();
                        break;
                    case Interaction.SET_SPOUSE:
                        setSpouse();
                    case Interaction.SET_PARENTS:
                        setParents();
                        break;
                    case Interaction.ADD_KIDS:
                        addKids();
                        break;
                    case Interaction.CHANGE_STATUS:
                        changeStatus();
                        break;
                }

                option = Integer.parseInt(sc.nextLine());
            }
            
        }else{
            System.out.println("\n\nThis user does not exist in MiniNet");
        }
        System.out.println("Operation on the selcted user completed");
    }
    
    
    public void addUser() {
        String name, photoPath, status, fatherName, motherName;
    	int age;
               
        String[] input;
        input = menuOperation.readAndCall(Interaction.ADD_USER);
        name = input[0];        
        age = Integer.parseInt(input[1]);
        photoPath = input[2];
        status = input[3];
        
        if (age>16) {
            addAdult(name, age, photoPath, status);
        }else{
            fatherName = input[4];
            motherName = input[5];
            try {
                addDependent(name, age, photoPath, status, fatherName, motherName);
            }catch (NumberFormatException e){
                System.out.println("\nThe age you input is invalid, failed to add this user");
                return;
            }
        }
    }
    
    
    public void deleteUser() {
        System.out.println("\nPlease enter the user name you want to delete: ");
        String input;
        input = sc.nextLine();
        if (!isUserExisted(input))
            System.out.println("\nUser not found, please enter again: ");
        else{
            selectedUser = getUserByName(input);
            System.out.println("\nUser "+ selectedUser.getName() + " is removed");
            System.out.println("deletion have been done successfully!");
            theMininet.remove(selectedUser);
            selectedUser = null;
        }
        return;
    }
    
    
    public void setPhoto() {
        
        System.out.println("\nPlease set your photo path (for example: src/yourname.jpg)"
                + "\nor enter 0 back to the Main Menu:");
        String input = sc.nextLine();
        selectedUser.setPhotoPath(input);
        return; 
    }
    
    
    public void makeFriend() {
        System.out.println("Please enter the user's name you want to add as friend:");
        String input = sc.nextLine();
        
        User user1, user2;
        int age1,age2;
 
        user1 = selectedUser;
        age1 = selectedUser.getAge();
        
        boolean eligible = false;
        
        //detect whether both users exist in MiniNet
        if (isUserExisted(input)){
            user2 = getUserByName(input);
            age1 = selectedUser.getAge();
            age2 = user2.getAge();
        }else{
            System.out.println("Failed to make friends, "
                    + "the user you input is not in Mininet");
            return;
        }
        
        //detect whether the two particular users are actually the same
        if (selectedUser.name.equals(input)){
            System.out.println("\nYou cannot add the same user as its friend");
            return;
        }
        
      /**
      * Only when the two particular users are all adults,
      * or when they are all of the dependents type but with
      * an age difference less than 3-years old, and are both
      * older than 2-years old. 
      */
        
        if (user1 instanceof Adult && user2 instanceof Adult)
            eligible = true;
        else if(user1 instanceof Dependent &&
                user2 instanceof Dependent &&  
                Math.abs(age1 - age2) <= 3 &&    
                (age1 >2 && age2 >2) &&            
                ((Dependent) user1).getParents()[0].equals(((Dependent) user2).getParents()[0]) )
            
            eligible = true;
        
        if (eligible) {
            user1.addFriend(user2);
            user2.addFriend(user1);
        }
    }
    
    
    
    public void delFriend() {
        
//
    }
    
    
    
    public void setSpouse() {
        
        if (selectedUser.age <= 16) {
            System.out.println("\nA dependent is not allowed to have a spouse");
            return;
            
        }else {
            System.out.println("\nPlease enter the user's spouse's name: ");
            String input = sc.nextLine();
            Adult user1;
            User user2;
            user1 = (Adult) selectedUser;
            boolean eligible = false;
            
            //Find if the two users are in MiniNet
            if (isUserExisted(input)){
                user2 = getUserByName(input);
            }else{
                System.out.println("\nError, the user you searched "
                        + "does not exist in Mininet.");
                return;
            }
            //Compare whether the two users are the same one
            if (selectedUser.name.equals(input)){
                System.out.println("\nError, the user you searched "
                        + "does not exist in Mininet.");
                return;
            }
            
	        /*
	        As stated in the specification,
	        all couples are exclusive to other couples,
	        hence before coupling two particular adults,
	        their spouse field will be checked, adults can only
	        become spouses when the spouse fields of both are null.
	        Plus, dependents are not eligible for becoming couples.
		 */	     
            if (user2 instanceof Adult && ((user1).getSpouse() == null) && (((Adult) user2).getSpouse() == null))
                eligible = true;
            
            if (eligible) {
                ((Adult) user1).setSpouse((Adult)user2);
                ((Adult) user2).setSpouse(user1);
            }
        }
    }
      
    public void setParents() {
        if (selectedUser.age > 16) {
            System.out.println("\nAn adult is not required to set parents!");
            return;
            
        }else {
            User father, mother;
            int age1, age2, age3;

            Dependent child = (Dependent) selectedUser;
            age1 = child.getAge();
            Adult[] parents = new Adult[2];
            
            String input = sc.nextLine();
            System.out.println("Please enter the father's name for the selected user:");
            boolean valid = false;
            
            //Detect whether the expected "parents" for the selected user are in MiniNet
            if (isUserExisted(input)){
                father = getUserByName(input);
                age2 = father.age;
                input = sc.nextLine();
            }else{
                System.out.println("\nError, the user you searched "
                        + "does not exist in Mininet.");
                return;
            }
            System.out.println("please enter the mother's name for the selected user:");
            if (isUserExisted(input)){
                mother = getUserByName(input);
                age3 = mother.age;
                input = sc.nextLine();
            }else{
                System.out.println("\nError, the user you searched "
                        + "does not exist in Mininet.");
                return;
            }
            if ( (father instanceof Adult && mother instanceof Adult)&&
                    age2>age1 && age3>age1)
                valid = true;
            
            if (valid) {
                child.setParents(parents);
                parents[0].addKids(child);
                parents[1].addKids(child);
            }
        }
    }
    
    
    
    public void addKids() {
        Dependent child = new Dependent(null, 0, null, null, null);
        if (selectedUser instanceof Dependent) {
            System.out.println("\nError, a dependent cannot be parent of its peers");
            return;
        }else{
              ((Adult)selectedUser).addKids(child);
              System.out.println("Successfully added this child for the selected user");
        }
        
    }
    
    
    public void changeStatus() {       
        System.out.println("\nPlease update the status for the selected user:");
        String input = sc.nextLine();
        selectedUser.setStatus(input);
        return;      
    }
    
    
    
    private void addDependent(String name, int age, String photoPath, String status, String fatherName, String motherName) {
        boolean isFatherExisted;
        boolean isMotherExisted;
        boolean isExisted;
        Adult[] parents = new Adult[4];
        
        isFatherExisted = isUserExisted(fatherName);
        isMotherExisted = isUserExisted(motherName);
        isExisted = isUserExisted(name);
        
        if (isFatherExisted && isMotherExisted && (!isExisted)){
            
            //Find out the parents of this dependent
            User u1 = getUserByName(fatherName);
            User u2 = getUserByName(motherName);
            
            
            /**
             * Detect whether the expected "parents" inputs are actually of the Dependent type
             */
            if (u1 instanceof Dependent || u2 instanceof Dependent){
                System.out.println("Failed to add this dependent as a user, "
						+ "dependents cannot be their peers' parents.");
                return;
            }
            
            parents[0] = (Adult)u1;
            parents[1] = (Adult)u2;
            Dependent kid = new Dependent(name, age, photoPath, status, parents);
            
            theMininet.add(kid);
            
            //add the dependent to the two parent objects
            for (Adult parent: parents)
                parent.addKids(kid);
            
            System.out.println("\nThis dependent is added successfully.");
            
        }else{
             System.out.println("Failed to add this dependent, "
            		+ "the reason might be either its parents do not exist in MiniNet,"
            		+ "or it is already existed in MiniNet");
        }
    }
    
    
    
    private void addAdult(String name, int age, String photoPath, String status) {
        
        if (!isUserExisted(name)){
            User adult = new Adult(name, age, photoPath, status);
            theMininet.add(adult);
            System.out.println("\n"+name+" is successful added");
        }else{
            System.out.println("This adult is already existed in Mininet");
        }
    }
       
    private boolean isUserExisted(String name){
        for (User user:theMininet) {
            if(user.getName().equals(name))
                return true;
        }
        return false;
    }
       
    private User getUserByName(String name){
        for (User user:theMininet) {
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }    
}