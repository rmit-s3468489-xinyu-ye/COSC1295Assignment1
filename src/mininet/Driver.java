/**
 *
 * @author Yifan ZHANG s3615625
 */
package mininet;
import java.util.List;
import java.util.Scanner;

public class Driver {

    private MenuOperation menuOperation;
    private User selectedPerson;                
    private List<User> inMininet;
    private static Scanner sc;

    public Driver(MenuOperation menuOperation){
        this.menuOperation = menuOperation;
        inMininet = FileOperation.readData();
        sc = new Scanner(System.in);
    }
    
/***1. list every one***/
    
    public void listEveryone() {
        int counter = 1;
        StringBuilder builder = new StringBuilder("\n\tcurrent people in the social network are:\n");
        for (User u : inMininet){
            builder.append("\t").append(u.getName());
            if (counter++ % 4 == 0) 
                builder.append("\n\t");
        }
        System.out.println(builder.toString());
    }
    

    public void selectaUser() {
        String name;
        System.out.print("\n\nplease enter the name:");
        name = sc.nextLine();
        String token;
        int option;

        if (isNameInNet(name)) {
            selectedPerson = getUserByName(name);

            if (selectedPerson.age > 16) {
                System.out.println((Adult)selectedPerson);
            }else {
                System.out.println((Dependent)selectedPerson);
                }
            
 /***display the sub menu every time when select a user successfully***/   
            
            MenuOperation.display(Interaction.SELECT_USER, selectedPerson);
            
            token = sc.nextLine();
            while(!token.matches("[0-7]")) {
                System.out.print("\ninvaild input, please try again: ");
                token = sc.nextLine();
            }
            option = Integer.parseInt(token);
            
            while (option != 0) {
    	        switch (option){
    	        case Interaction.BACK_MAIN:	       
    	            break;
    	        case Interaction.SET_PROFILE:
    	            setPhoto();
    	            break;
    	        case Interaction.MAKE_FRIEND:
    	            makeFriend();                   
    	            break;
    	        case Interaction.DEL_FRIEND:
    	            delFriend();
    	            break;
    	        case Interaction.SET_COUPLE:
    	            setCouple();
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
    	        
            
            MenuOperation.display(Interaction.SELECT_USER, selectedPerson);
            option = Integer.parseInt(sc.nextLine());
            }

        }else{
            System.out.println("\n\nthis person is not in the network");
        }
        System.out.println("modification have been done successfully!");
    }
    
    
    public void addPerson() {
    	String name;
    	int age;
        String photoPath;
        String status;
        String fatherName;
        String motherName;
 
        
        String[] tokens;
        tokens = menuOperation.readAndCall(Interaction.ADD_PERSON);
        name = tokens[0];
        
        age = Integer.parseInt(tokens[1]);
        photoPath = tokens[2];
        status = tokens[3];
       
        if (age>16) {
            addAdult(name, age, photoPath, status);
        }else{
        	fatherName = tokens[4];
            motherName = tokens[5];
            try {               
                addDependent(name, age, photoPath, status, fatherName, motherName);
            }catch (NumberFormatException e){
                System.out.println("\na teen cannot be added without a valid age, oeration abort!");
                return;
            }
        }
    }
    
    
    public void deleteUser() {
    	System.out.println("\n please enter the user name you want to delete: ");
    	String input;
    	input = sc.nextLine();
        if (!isNameInNet(input))
            System.out.println("\n user not found, please enter again: ");
        else{
        	selectedPerson = getUserByName(input);
            System.out.println("\nuser "+ selectedPerson.name+ " is removed");
            System.out.println("deletion have been done successfully!");
            inMininet.remove(selectedPerson);
            selectedPerson = null;
        }
        return;
    }

    
    public void setPhoto() {
    	   	
    	System.out.println("\n please input your photo path (for example: src/yourname.jpg)"
    			+ "\nor enter 0 back to SubMenu:");
    	String token = sc.nextLine();
    	selectedPerson.setPhotoPath(token);
    	return;
    	
    }
   
    
    public void makeFriend() {
    	System.out.println("please enter the user's name you want to add as friend:");
        String tokens = sc.nextLine();
        
        User person1;
        User person2;
        int age1;
        int age2;
        
        person1 = selectedPerson;
        age1 = selectedPerson.age;
        
        boolean isValid = false;

        //check if the two person are in the network
        if (isNameInNet(tokens)){       	
            person2 = getUserByName(tokens);
            age1 = selectedPerson.age;
            age2 = person2.age;
        }else{
            System.out.println("\n sorry, the user you input is not in Mininet.");
            return;
        }

        //check if the two person are the same one
        if (selectedPerson.name.equals(tokens)){
            System.out.println("\n sorry, you cannot add the selected user as firend.");
            return;
        }        
 
      /*
       *   there are several situations in which two person can become friends:
       *   adult-adult
       *   teen-teen (age difference is within 3, no one's age is below 2)
       */

      if (person1 instanceof Adult && person2 instanceof Adult)
          isValid = true;
      else if(person1 instanceof Dependent &&  
              person2 instanceof Dependent &&  //both of them should be Teen
              Math.abs(age1 - age2) <= 3 &&    // the age span should be less then 3
              age1 >2 && age2 >2 &&            // they should be above 2
              ((Dependent) person1).getParents()[0].equals(((Dependent) person1).getParents()[0]) )
    	  // they should be from different family              
          isValid = true;

      if (isValid) {
          person1.addFriend(person2);
          person2.addFriend(person1);
      }
    }
    
   
    
    public void delFriend() {
    	   	
//    	boolean hasFriend = !selectedPerson.friends.isEmpty();
//    	if (hasFriend) { 
//    	System.out.println("please enter the user's name you want to delete from friend list:");
//    	String token = sc.nextLine();
//    	for (User u : friends) {
//            if(u.getName().equals(token))
//                return true;
//        }
//        return false;
//    	
//    	else {
//    		System.out.println("the user current has not set any friends");
//    		return;
//    	}
//    	System.out.println("the user"+ token +"has successful delete from friend list!");
//    	return;
    }
    
   
    
    public void setCouple() {
    	
    	if (selectedPerson.age <= 16) {
            System.out.println("\n the user is a Teenager!");
            return;
            
        }else {
            System.out.println("\n please enter the user's spuose's name: ");
            String tokens = sc.nextLine();       
	        Adult person1;
	        User person2;	       	        
	        person1 = (Adult) selectedPerson;	        	        
	        boolean isValid = false;
	
	        //check if the two person are in the network
	        if (isNameInNet(tokens)){       	
	            person2 = getUserByName(tokens);
	        }else{
	            System.out.println("\n sorry, the user you input is not in Mininet.");
	            return;
	        }	
	        //check if the two person are the same one
	        if (selectedPerson.name.equals(tokens)){
	            System.out.println("\n sorry, you cannot input the user you have selected.");
	            return;
	        }        

      if (person2 instanceof Adult && (person1).getCouple() == null && (((Adult) person2).getCouple() == null))
          isValid = true;

      if (isValid) {
    	  ((Adult) person1).setCouple((Adult)person2);
    	  ((Adult) person2).setCouple(person1);
      	}
      }
    }
    
   
    
    public void setParents() {
    	if (selectedPerson.age > 16) {
            System.out.println("\n the user is not required to set parents!");
            return;
            
        }else {
            User father;
            User mother;
            int age1;
            int age2;
            int age3;
            Dependent kids = (Dependent) selectedPerson;
            age1 = kids.getAge();
            Adult[] parents = new Adult[2];
            
            String tokens = sc.nextLine();  
            System.out.println("please enter the father's name:");
            boolean isValid = false;

            //check if the two person are in the network
            if (isNameInNet(tokens)){       	
            	father = getUserByName(tokens);
            	age2 = father.age;
            	tokens = sc.nextLine(); 
            }else{
                System.out.println("\n sorry, the user you input is not in Mininet.");
                return;
            }
            System.out.println("please enter the mother's name:");
            if (isNameInNet(tokens)){       	
            	mother = getUserByName(tokens);
            	age3 = mother.age;
            	tokens = sc.nextLine(); 
            }else{
                System.out.println("\n sorry, the user you input is not in Mininet.");
                return;
            }
            if ( (father instanceof Adult && mother instanceof Adult)&&
            	age2>age1 && age3>age1)
                isValid = true;

            if (isValid) {
            	kids.setParents(parents);
              parents[0].addKids(kids);
              parents[1].addKids(kids);
            }
        }
    }
            
   
    
    public void addKids() {
    	if (selectedPerson instanceof Dependent) {
    		System.out.println("\n this user is a teenager!");
    		return;
    		}else {
    			
    		
    			}
    		
    	}        
   

    public void changeStatus() {
    	
    	System.out.println("\n please update your status:");
    	String token = sc.nextLine();
    	selectedPerson.setStatus(token);
    	return;
    	
    }
    
    
    
    private void addDependent(String name, int age, String photoPath, String status, String fatherName, String motherName) {
        boolean isFatherIn;
        boolean isMotherIn;
        boolean isConflict;
        Adult[] parents = new Adult[4];

        isFatherIn = isNameInNet(fatherName);
        isMotherIn = isNameInNet(motherName);
        isConflict = isNameInNet(name);

        if (isFatherIn && isMotherIn && (!isConflict)){

            //find the parents of this child
            User p1 = getUserByName(fatherName);
            User p2 = getUserByName(motherName);


            //though the name are in the network, we still need to verify if the name belongs to a adult
            //if it belongs to another teen, we cannot let this teen in.
            if (p1 instanceof Dependent || p2 instanceof Dependent){
                System.out.println("\na teen can be added if both of his/her parents are adults!");
                System.out.println("failed to add a new teen!");
                return;
            }
            
            parents[0] = (Adult)p1;
            parents[1] = (Adult)p2;
            Dependent kid = new Dependent(name, age, photoPath, status, parents);

            inMininet.add(kid);

            //add the child to the parents object
            for (Adult parent: parents)
                parent.addKids(kid);

            System.out.println("\nadd teen successfully!");

        }else{
            System.out.println("\na teen can be added if both of his/her parents are in the network");
            System.out.println("\nthe teens name should be unique");
            System.out.println("failed to add a new teen!");
        }
    }
    

    
    private void addAdult(String name, int age, String photoPath, String status) {
        
        if (!isNameInNet(name)){
            User adult = new Adult(name, age, photoPath, status);
            inMininet.add(adult);
            System.out.println("\n"+name+" is successful added");
        }else{
            System.out.println("failed to add a new person due to name conflict");
        }
    }


    
    private boolean isNameInNet(String name){
        for (User user:inMininet) {
            if(user.getName().equals(name))
                return true;
        }
        return false;
    }
    
  
    
    private User getUserByName(String name){
        for (User user:inMininet) {
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }

  

 }