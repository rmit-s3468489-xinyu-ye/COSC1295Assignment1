/**
 *
 * @author Yifan ZHANG s3615625
 */
package mininet;
import java.util.List;
import java.util.Scanner;

public class Driver 
{

	private MenuOperation menuOperation;
	private User selectedUser;
	private List<User> theMininet;
	private static Scanner sc;

	public Driver(MenuOperation menuOperation)
	{
		this.menuOperation = menuOperation;
		theMininet = FileOperation.readFromFile();
		sc = new Scanner(System.in);
	}

	/*
     List the existed users in MiniNet
	 */

	public void listEveryone() 
	{
		int counter = 1;
		StringBuffer buffer = new StringBuffer("\n\tThe existed users in MiniNet:\n");
		for (User u : theMininet){
			buffer.append("\t").append(u.getName());

		}
		System.out.println(buffer.toString());
	}

	public void selectUser() 
	{
		String name;
		System.out.print("\n\nPlease enter the name "
				+ "of the user you want to select:");
		name = sc.nextLine();
		String input;
		int option;

		if (isUserExisted(name)) 
		{
			selectedUser = getUserByName(name);
			/*
            Display the submenu every time a certain user is selected
			 */

			MenuOperation.display(Interaction.SELECT_USER, selectedUser);

			input = sc.nextLine();
			while(!input.matches("[0-9]")) 
			{
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
					deleteFriend();
					break;
				case Interaction.SET_SPOUSE:
					setSpouse();
					break;
				case Interaction.SET_PARENTS:
					setParents();
					break;
				case Interaction.ADD_KIDS:
					addKids();
					break;
				case Interaction.CHANGE_STATUS:
					changeStatus();
					break;
				case Interaction.FINDOUTCHILDREN:
					searchChildren();
					break;
				case Interaction.FINDOUTPARENTS:
					searchParents();
					break;
				}

				//MenuOperation.display(Interaction.SELECT_USER, selectedUser);
				MenuOperation.displaySubMenu(selectedUser);
				option = Integer.parseInt(sc.nextLine());
				while(!input.matches("[0-9]")) 
				{
					System.out.print("\nInvaild input, please try again: ");
					input = sc.nextLine();
				}
			}

		}
		else
			System.out.println("\n\nThis user does not exist in MiniNet");
		
		System.out.println("Operation on the selcted user completed");
	}

	public void addUser() 
	{
		String name, photoPath, status, fatherName, motherName;
		int age;

		String[] input;
		input = menuOperation.readAndCall(Interaction.ADD_USER);
		name = input[0];        
		age = Integer.parseInt(input[1]);
		photoPath = input[2];
		status = input[3];

		if (age>16) 
			addAdult(name, age, photoPath, status);
		else
		{
			fatherName = input[4];
			motherName = input[5];
			try 
			{
				addDependent(name, age, photoPath, status, fatherName, motherName);
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nThe age you input is invalid, failed to add this user");
				return;
			}
		}
	}

	public void deleteUser() 
	{
		System.out.println("\nPlease enter the user name you want to delete: ");
		String input;
		input = sc.nextLine();
		if (!isUserExisted(input))
			System.out.println("\nUser not found, please enter again: ");
		
		else{
			selectedUser = getUserByName(input);
			theMininet.remove(selectedUser);
			System.out.println("\nUser "+ selectedUser.getName() + " is"
					+ " removed successfully");
			selectedUser = null;
			}
		return;
	}

	public void setPhoto() 
	{

		System.out.println("\nPlease set your photo path (for example: src/yourname.jpg)"
				+ "\nor enter 0 back to the Main Menu:");
		String input = sc.nextLine();
		selectedUser.setPhotoPath(input);
		return; 
	}


	public void makeFriend() 
	{
		System.out.println("Please enter the user's name you want to add as friend:");
		String input = sc.nextLine();

		User user1, user2;
		int age1,age2;

		user1 = selectedUser;
		age1 = selectedUser.getAge();

		boolean eligible = false;

		//detect whether both users exist in MiniNet
		if (isUserExisted(input))
		{
			user2 = getUserByName(input);
			age1 = selectedUser.getAge();
			age2 = user2.getAge();
		}
		else
		{
			System.out.println("Failed to make friends, "
					+ "the user you input is not in Mininet");
			return;
		}

		//detect whether the two particular users are actually the same
		if (selectedUser.name.equals(input))
		{
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
				(age1 > 2 && age2 > 2) &&            
				!((Dependent) user1).getParents()[0].equals(((Dependent) user2).getParents()[0]) && !((Dependent) user1).getParents()[0].equals(((Dependent) user2).getParents()[0]))
			
			eligible = true;

		if (eligible) 
		{
			user1.addFriend(user2);
			user2.addFriend(user1);
		}
		else
			System.out.println("One or more restrictions on age (difference) "
					+ "for dependents making friends have not been met, "
					+ "failed to make friends between them");
	}

	public void deleteFriend() 
	{
		if (selectedUser == null)
        {
            System.out.println("You have to select a user first "
                    + "to find out whether he/she has any children");
            return;
        }
		else 
		{
			List<User> friends = selectedUser.getFriends();
			
			if(!friends.isEmpty()) 
			{
				System.out.println("The selected user's friends: ");
				
				for (int i = 0; i < friends.size(); i++)
		        {
		            User f = friends.get(i);
		            System.out.println(f.getName());
		        }
				
				System.out.println("Please enter the friend's name you want "
						+ "to delete for the selected user :");
				
				String input = sc.nextLine();
				
				boolean existed = false;
				
				if(isUserExisted(input))
					existed = true;
				
				if(existed) 
				{
					if(friends.contains(getUserByName(input))) 
					{
						selectedUser.delFriend(getUserByName(input));
						System.out.println("The The user you searched is successfully "
								+ "deleted from the selected user's friends list");
					}
					else
						System.out.println("The user you searched is not "
								+ "in the selected user's friends list");
				}
				else
					System.out.println("The user you searched is not a friend of " 
							+ selectedUser.getName());
			}
			else
				System.out.println(selectedUser.getName() + "does not have any friends");
		}
		
	}

	public void isFriend()
	{
		User user1, user2;

		System.out.println("Please enter the name of one user: ");
		String name1 = sc.nextLine();
		System.out.println("Please enter the name of another user: ");
		String name2 = sc.nextLine();

		//Check whether the two users are existing in the network
		if((isUserExisted(name1)) && isUserExisted(name2))
		{
			user1 = getUserByName(name1);
			user2 = getUserByName(name2);

			if(user1.getFriends().contains(user2))
				System.out.println(name1 + " and " + name2 + " are friends");
			else
				System.out.println(name1 + " and " + name2 + " are not friends");
		}
		else
		{
			System.out.println("One or more users do not exist in the network");
			System.out.println("Please try to select existed users.");
		}
	}

	//Find out a particular user's children
    public void searchChildren()
    {
        boolean hasChildren = false;
        List<Dependent> children = null;
        
        if (selectedUser == null)
        {
            System.out.println("You have to select a user first "
                    + "to find out whether he/she has any children");
            return;
        }
        
        if (selectedUser instanceof Adult)
        {
            children = ((Adult)selectedUser).getChildren();
            if(!children.isEmpty())
            {
                hasChildren = true;
                System.out.println("The children list of the selected user: ");
                for (int i = 0; i < children.size(); i++)
                {
                    Dependent d = children.get(i);
                    System.out.println(d.getName());
                }
            }
            else
                System.out.println("This user does not have any children");
        }
        
        if (selectedUser instanceof Dependent)
            System.out.println("A dependent does not have any children");
    }
    
    //Find out a particular user's parents
    public void searchParents()
    {
        Adult[] parents = null;
        
        if (selectedUser == null)
        {
            System.out.println("You have to select a user first "
                    + "to search for his/her parents");
            return;
        }
        
        if (selectedUser instanceof Adult)
        {
            System.out.println("The parents' info is not mandatory for Adults"
                    + ", no records to be retrieved");
            return;
        }
        else
        {
            parents = ((Dependent)selectedUser).getParents();
            System.out.println("The following two users are "
                    + "the selected user's parents: ");
            System.out.println("Father: " + ((Dependent)selectedUser).getParents()[0].getName() + ","
                    + "Mother: " +((Dependent)selectedUser).getParents()[1].getName());
        }
    }
    
	public void setSpouse() 
	{

		if (selectedUser.age <= 16) 
		{
			System.out.println("\nA dependent is not allowed to have a spouse");
			return;
		}
		else 
		{
			System.out.println("\nPlease enter the user's spouse's name: ");
			String input = sc.nextLine();
			Adult user1;
			User user2;
			user1 = (Adult)selectedUser;
			boolean eligible = false;

			//Find if the two users are in MiniNet
			if (isUserExisted(input))
			{
				user2 = getUserByName(input);
			}else
			{
				System.out.println("\nError, the user you searched "
						+ "does not exist in Mininet.");
				return;
			}
			//Compare whether the two users are the same one
			if (selectedUser.name.equals(input))
			{
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

			if (eligible) 
			{
				((Adult) user1).setSpouse((Adult)user2);
				((Adult) user2).setSpouse(user1);
			}
			else
				System.out.println("One or more users already have a spouse, "
						+ "failed to set spouse to each other");
		}
	}

	public void setParents() 
	{
		if (selectedUser.getAge() > 16) 
		{
			System.out.println("\nAn adult is not required to set parents!");
			return;

		}
		else 
		{
			User father, mother;
			while (true) 
			{
				System.out.println("Please enter the father's name for the selected user:");
				String input = sc.nextLine();


				//Detect whether the expected "parents" for the selected user are in MiniNet
				if (isUserExisted(input))
				{
					father = getUserByName(input);
					if(father.getAge() < 16) 
					{
						System.out.println("Error, a dependent cannot be parent of its peers");
						continue;
					}
					else 
					{
						((Dependent)selectedUser).setFather((Adult)father);
						System.out.println("Successfully set " + father.getName() +
								" as " + selectedUser.getName() + "'s father ");
						break;
					}
						
				}								
				else
				{
					System.out.println("\nError, the user you searched "
							+ "does not exist in Mininet.");
					continue;
				}
			}
			
			while (true) 
			{
				System.out.println("Please enter the mother's name for the selected user:");
				String input = sc.nextLine();

				//Detect whether the expected "parents" for the selected user are in MiniNet
				if (isUserExisted(input))
				{
					mother = getUserByName(input);
					if(mother.getAge() < 16) 
					{
						System.out.println("Error, a dependent cannot be parent of its peers");
						continue;
					}
					else 
					{
						((Dependent)selectedUser).setMother((Adult)mother);
						System.out.println("Successfully set " + mother.getName() +
								" as " + selectedUser.getName() + "'s mother ");
						break;
					}
						
				}								
				else
				{
					System.out.println("\nError, the user you searched "
							+ "does not exist in Mininet.");
					continue;
				}
			}
		}
	}

	public void addKids() 
	{
		if (selectedUser instanceof Dependent) 
		{
			System.out.println("\nError, a dependent cannot be parent of its peers");
			return;
		}
		else
		{
			String children = "Please select a dependent: ";
			for (User user : theMininet) 
			{
				if (user instanceof Dependent)
					children += user.getName() + ",";
			}
			System.out.println(children);
			while (true) 
			{
				String childName = sc.nextLine();
				
				boolean flag = false;
				for (User user : theMininet) 
				{
					if (user instanceof Dependent)
						if(childName.equals(user.getName()))
						{
							flag = true;
							break;
						}
				}
				List<Dependent> kids = ((Adult)selectedUser).getChildren();
				for (int i = 0; i < kids.size(); i++)
				{
					Dependent k = kids.get(i);
					if(childName.equals(k.getName()))
						System.out.println("This dependent is already a child of "
					+ selectedUser.getName());
					return;
				}
				if(flag)
				{
					((Adult)selectedUser).addChildren((Dependent)getUserByName(childName));
					System.out.println("Successfully added " + childName + " for the selected user");
					break;
				}else 
					System.out.println("The child you input does not exist "
							+ "in MiniNet, please enter again: ");
			} 
		}
	}


	public void changeStatus() 
	{       
		System.out.println("\nPlease update the status for the selected user:");
		String input = sc.nextLine();
		selectedUser.setStatus(input);
		return;      
	}

	private void addDependent(String name, int age, String photoPath, String status, String fatherName, String motherName) 
	{
		boolean isFatherExisted;
		boolean isMotherExisted;
		boolean isExisted;
		Adult[] parents = new Adult[2];

		isFatherExisted = isUserExisted(fatherName);
		isMotherExisted = isUserExisted(motherName);
		isExisted = isUserExisted(name);

		if (isFatherExisted && isMotherExisted && (!isExisted))
		{

			//Find out the parents of this dependent
			User u1 = getUserByName(fatherName);
			User u2 = getUserByName(motherName);


			/**
			 * Detect whether the expected "parents" inputs are actually of the Dependent type
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
			
			//add the dependent to the two parent objects
			for (Adult parent: parents)
				parent.addChildren(kid);
			System.out.println("\nThis dependent is added successfully.");
		}

		else
		{
			System.out.println("Failed to add this dependent, "
					+ "the reason might be either its parents do not exist in MiniNet,"
					+ "or it is already existed in MiniNet");
		}
	}
	private void addAdult(String name, int age, String photoPath, String status) 
	{

		if (!isUserExisted(name))
		{
			User adult = new Adult(name, age, photoPath, status);
			theMininet.add(adult);
			System.out.println("\n"+name+" is successful added");
		}
		else{
			System.out.println("This adult is already existed in Mininet");
		}
	}

	private boolean isUserExisted(String name)
	{
		for (User user:theMininet) 
		{
			if(user.getName().equals(name))
				return true;
		}
		return false;
	}

	private User getUserByName(String name)
	{
		for (User user:theMininet) 
		{
			if(user.getName().equals(name))
				return user;
		}
		return null;
	}    
}
