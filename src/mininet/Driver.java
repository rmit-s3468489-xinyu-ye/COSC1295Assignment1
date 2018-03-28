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
    
/***List existing members in MiniNet***/
    
    public void listMembers() {
        int counter = 1;
        StringBuffer buffer = new StringBuffer("\n\tThe existing members in MiniNet:\n");
        for (User u : theMininet){
            buffer.append("\t").append(u.getName());
//            if (counter++ % 4 == 0) 
//                buffer.append("\n\t");
        }
        System.out.println(buffer.toString());
    }
    

    public void selectUser() {
        String name;
        System.out.print("\n\nPlease enter the user's name:");
        name = sc.next();
        String userInput;
        int option;

        if (isUserExisted(name)) {
            selectedUser = getUserByName(name);

            if (selectedUser.age > 16) {
                System.out.println((Adult)selectedUser);
            }else {
                System.out.println((Dependent)selectedUser);
                }
            
 /***Display the sub menu every time when a certain user is selected successfully***/   
            
            MenuOperation.display(Interaction.SELECT_USER, selectedUser);
            
            userInput = sc.next();
            while(!userInput.matches("[0-7]")) {
                System.out.print("\ninvaild input, please try again: ");
                userInput = sc.next();
            }
            option = Integer.parseInt(userInput);
            
            while (option != 0) {
    	        switch (option){
    	        case Interaction.BACK_MAIN:	       
    	            break;
    	        case Interaction.SET_PROFILE_PHOTO:
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
    	            addKids(null);
    	            break;
    	        case Interaction.CHANGE_STATUS:
    	            changeStatus();
    	            break;
    	        }
    	        
            
            MenuOperation.display(Interaction.SELECT_USER, selectedUser);
            option = Integer.parseInt(sc.next());
            }

        }else{
            System.out.println("\n\nthis user is not in MiniNet");
        }
        System.out.println("modification have been done successfully!");
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
    			System.out.println("\nThe age you input is invalid, failed to add this user.");
    			return;
    		}
    	}
    }
    
    
    public void deleteUser() {
    	System.out.println("\nPlease enter the user name you want to delete: ");
    	String input;
    	input = sc.next();
        if (!isUserExisted(input))
            System.out.println("\nUser not found, please enter again: ");
        else{
	        	selectedUser = getUserByName(input);
	        	theMininet.remove(selectedUser);
	        	System.out.println("\nuser "+ selectedUser.name+ " is  successfully removed");
	        	selectedUser = null;
        }
        return;
    }

    
    public void setPhoto() {
    	   	
    	System.out.println("\nPlease enter your photo path (for example: src/yourname.jpg)"
    			+ "\nor enter 0 back to the main menu:");
    	String input = sc.next();
    	selectedUser.setPhotoPath(input);
    	return;
    	
    }
   
    
    public void makeFriend() {
    	System.out.println("please enter the user's name you want to add as a friend:");
        String input = sc.next();
        
        User u1,u2;
        int age1, age2;
        
        u1 = selectedUser;
        age1 = selectedUser.age;
        
        boolean eligible = false;

        //detect whether the selected two users exist in MiniNet
        if (isUserExisted(input)){       	
            u2 = getUserByName(input);
            age1 = selectedUser.age;
            age2 = u2.age;
        }else{
            System.out.println("\nFailed to make friends, the user you input is not in Mininet.");
            return;
        }

        //detect whether the two particular users are actually the same
        if (selectedUser.name.equals(input)){
            System.out.println("\nYou cannot add the same user as its friend.");
            return;
        }        
 
     /**
      * Only when the two particular users are all adults,
      * or when they are all of the dependents type but with
      * an age difference less than 3-years old 
      */

      if (u1 instanceof Adult && u2 instanceof Adult) //Detect whether the selected two users are all adults
          eligible = true;
      else if(u1 instanceof Dependent &&  
              u2 instanceof Dependent &&  //If they are not adults, then they should all be dependents
              Math.abs(age1 - age2) <= 3 &&    // Detect the age difference to see whether it is less than 3
              age1 > 2 && age2 > 2 &&            //They should both be older than 2
              ((Dependent) u1).getParents()[0].equals(((Dependent) u2).getParents()[0])) 
    	  		//They should have different parents, as stated in the assignment specification, all couples are
    	  		//exclusive to other couples, hence we only check 1 parent of them.
          	eligible = true;

      if (eligible) {
          u1.addFriend(u2);
          u2.addFriend(u1);
      }
    }
    
  
    public void delFriend() {
    	   	
//    	boolean hasFriend = !selecteduser.friends.isEmpty();
//    	if (hasFriend) { 
//    	System.out.println("please enter the user's name you want to delete from friend list:");
//    	String input = sc.next();
//    	for (User u : friends) {
//            if(u.getName().equals(input))
//                return true;
//        }
//        return false;
//    	
//    	else {
//    		System.out.println("the user current has not set any friends");
//    		return;
//    	}
//    	System.out.println("the user"+ input +"has successful delete from friend list!");
//    	return;
    }
    
   
    
    public void setSpouse() {
    	
    	if (selectedUser.age <= 16) {
            System.out.println("\nA dependent is not allowed to have a spouse");
            return;
            
        }else {
            System.out.println("\nPlease enter the user's spuose's name: ");
            String input = sc.next();       
	        Adult user1 = (Adult) selectedUser;
	        User user2;	       	             	        
	        boolean valid = false;
	
	        //check if the two user are in MiniNet
	        if (isUserExisted(input)){       	
	            user2 = getUserByName(input);
	        }else{
	            System.out.println("\nError, the user you input does not exist in Mininet.");
	            return;
	        }	
	        //check if the two user are the same one
	        if (selectedUser.name.equals(input)){
	            System.out.println("\nError, you cannot set the selected user as its spouse.");
	            return;
	        }        

      if (user2 instanceof Adult && ((user1).getSpouse() == null) && (((Adult) user2).getSpouse() == null))
          valid = true;

      if (valid) {
	    	  ((Adult) user1).setSpouse((Adult)user2);
	    	  ((Adult) user2).setSpouse((Adult)user1);
      	}
      else {
    	  	System.out.println("One or more users already has a spouse");
        	return;
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
            Dependent kids = (Dependent)selectedUser;
            age1 = kids.getAge();
            Adult[] parents = new Adult[2];
            
            String input = sc.next();  
            System.out.println("please enter the father's name:");
            boolean valid = false;

            //Detect the existence of the two users in MiniNet
            if (isUserExisted(input)){       	
            	father = getUserByName(input);
            	age2 = father.age;
            	input = sc.next(); 
            }else{
                System.out.println("\nError, the user you input does not exist in Mininet.");
                return;
            }
            System.out.println("please enter the mother's name:");
            if (isUserExisted(input)){       	
            	mother = getUserByName(input);
            	age3 = mother.age;
            	input = sc.next(); 
            }else{
                System.out.println("\n sorry, the user you input does not exist in Mininet.");
                return;
            }
            if ( (father instanceof Adult && mother instanceof Adult)&&
            	age2>age1 && age3>age1)
                valid = true;

            if (valid) {
            	kids.setParents(parents);
              parents[0].addKids(kids);
              parents[1].addKids(kids);
            }
        }
    }
            

    public void changeStatus() {
    	
    	System.out.println("\nPlease update your status: ");
    	String input = sc.next();
    	selectedUser.setStatus(input);
    	return;   	
    }
    
    public void addKids(Dependent child) {
    	if (selectedUser instanceof Dependent) {
    		System.out.println("\nError, a dependent cannot be parent of its peers");
    		return;
    		}
    	else{
    		((Adult)selectedUser).addKids(child); 
    		System.out.println("Successfully added this child for the selected user");	
    			}
    		
    	}
    
    
    public void addDependent(String name, int age, String photoPath, String status, String fatherName, String motherName) {
	    	boolean isExisted = isUserExisted(name),
	    			isFatherExisted = isUserExisted(fatherName),
	    			isMotherExisted = isUserExisted(motherName);
     			
        Adult[] parents = new Adult[2];

        if (isFatherExisted && isMotherExisted && (!isExisted))
        {

            //Find out the parents of this dependent
            User u1 = getUserByName(fatherName);
            User u2 = getUserByName(motherName);


            /**
             * Detect whether the expected "parents" are actually of the Dependent type
             */
            if (u1 instanceof Dependent || u2 instanceof Dependent)
            {
                System.out.println("Failed to add this dependent as a user, "
						+ "dependents cannot be their peers' parents.");
                return;
            }
            
            parents[0] = (Adult)u1;
            parents[1] = (Adult)u2;
            Dependent kid = new Dependent(name, age, photoPath, status, parents);

            theMininet.add(kid);

            //add the child to the parents object
            for (Adult parent: parents)
                parent.addKids(kid);

            System.out.println("\nThis dependent is added successfully.");

        }
        else
        {
            System.out.println("Failed to add this dependent, "
            		+ "the reason might be either its parents do not exist in MiniNet,"
            		+ "or it is already existed in MiniNet");
        }
    }
    

    
    public void addAdult(String name, int age, String photoPath, String status) {
        
        if (!isUserExisted(name)){
            User adult = new Adult(name, age, photoPath, status);
            theMininet.add(adult);
            System.out.println("\n"+name+" is successfully added");
        }else{
            System.out.println("This adult is already existed in Mininet");
        }
    }


    
    public boolean isUserExisted(String name){
        for (User user:theMininet) {
            if(user.getName().equals(name))
                return true;
        }
        return false;
    }
    
  
    
    public User getUserByName(String name){
        for (User user:theMininet) {
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }

  

 }