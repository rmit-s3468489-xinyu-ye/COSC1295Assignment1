/**
 *
 * @author Yifan ZHANG s3615625
 */
package mininet;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.internal.runtime.options.Options;

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

	public void listExitedUsers() 
	{
		int counter = 1;
		StringBuffer buffer = new StringBuffer("\n\t\t\tThe existed users in MiniNet:\n");
		for (User u : theMininet){
			buffer.append("\t\t").append(u.getName());

		}
		System.out.println(buffer.toString());
	}

	public void selectUser() 
	{
		String name;
		System.out.print("\n\t\t\tPlease enter the name "
				+ "of the user you want to select:");
		name = sc.next();
		String input;
		int option;

		if (isUserExisted(name)) 
		{
			selectedUser = getUserByName(name);
			/*
            Display the submenu every time a certain user is selected
			 */

			MenuOperation.display(Interaction.SELECT_USER, selectedUser);

			input = sc.next();
			while(!input.matches("[0-9]")) 
			{
				System.out.print("\n\t\t\tInvaild input, please try again: ");
				input = sc.next();
			}
			
			option = Integer.parseInt(input);
			//while (option != 0) 
			while (option >= 0 && option <= 9) 
			{
				switch (option)
				{
				case Interaction.BACK_MAIN:
					return;
					//break;
				case Interaction.SET_PHOTO:
					setPhoto();
					break;
				case Interaction.MAKE_FRIEND:
					makeFriends();
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
					updateStatus();
					break;
				case Interaction.FINDOUTCHILDREN:
					searchChildren();
					break;
				case Interaction.FINDOUTPARENTS:
					searchParents();
					break;
				}
				
				MenuOperation.display(Interaction.SELECT_USER, selectedUser);
//				MenuOperation.displaySubMenu(selectedUser);
//				try 
//				{
//					option = Integer.parseInt(sc.next());
//				} 
//				catch (InputMismatchException ime) 
//				{
//					System.out.println("\n\t\t\tPlease enter valid number.");
//				}
				input = sc.next();
				
				while(!input.matches("[0-9]")) 
				{	
					System.out.print("\n\t\t\tInvaild input, please try again: ");
					input = sc.next();
				}
				option = Integer.parseInt(input);
			}

		}
		
		else 
		{
			System.out.println("\n\t\t\tThis user does not exist in MiniNet");
		}
		
		System.out.println("\n\t\t\tOperation on the selcted user completed");
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

		if (age > 16) 
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
				System.out.println("\n\t\t\tThe age you input is invalid, failed to add this user");
				return;
			}
		}
	}

	public void deleteUser() 
	{
		System.out.println("\n\t\tPlease enter the user name you want to delete: ");
		String input;
		input = sc.nextLine();
		if (!isUserExisted(input))
			System.out.println("\n\t\tUser not found, please enter again: ");
		
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

		System.out.println("\n\t\t\tPlease set your photo path (for example: src/yourname.jpg)");
		String input = sc.next();
		selectedUser.setPhotoPath(input);
		return; 
	}

	public void makeFriends() 
	{
		System.out.println("\n\t\t\tPlease enter the user's name you want to add as friend:");
		String input = sc.next();

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
			System.out.println("\n\t\t\tFailed to make friends, "
					+ "the user you input is not in Mininet");
			return;
		}

		//detect whether the two particular users are actually the same
		if (selectedUser.name.equals(input))
		{
			System.out.println("\n\t\t\tYou cannot add the same user as its friend");
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
				(((Dependent) user1).getParents() != null && ((Dependent) user2).getParents() != null) &&
				!((Dependent) user1).getParents()[0].equals(((Dependent) user2).getParents()[0]) &&
				!((Dependent) user1).getParents()[1].equals(((Dependent) user2).getParents()[1]))
			
			eligible = true;

		if (eligible) 
		{
			user1.addFriend(user2);
			user2.addFriend(user1);
		}
		else
			System.out.println("\n\t\t\tOne or more restrictions "
					+ "for dependents making friends have not been met, "
					+ "failed to make friends between them");
	}

	public void deleteFriend() 
	{
		if (selectedUser == null)
        {
            System.out.println("\n\t\t\tYou have to select a user first "
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
				
				System.out.println("\n\t\t\tPlease enter the friend's name you want "
						+ "to delete for the selected user :");
				
				String input = sc.next();
				
				boolean existed = false;
				
				if(isUserExisted(input))
					existed = true;
				
				if(existed) 
				{
					if(friends.contains(getUserByName(input))) 
					{
						selectedUser.delFriend(getUserByName(input));
						System.out.println("\n\t\t\tThe The user you searched is successfully "
								+ "deleted from the selected user's friends list");
					}
					else
						System.out.println("\n\t\t\tThe user you searched is not "
								+ "in the selected user's friends list");
				}
				else
					System.out.println("\n\t\t\tThe user you searched is not a friend of " 
							+ selectedUser.getName());
			}
			else
				System.out.println("\n\t\t\t" + selectedUser.getName() + " does not have any friends");
		}
		
	}

	public void isFriend()
	{
		User user1, user2;

		System.out.println("\n\t\t\tPlease enter the name of one user: ");
		String name1 = sc.next();
		System.out.println("\n\t\t\tPlease enter the name of another user: ");
		String name2 = sc.next();

		//Check whether the two users exist in the network
		if((isUserExisted(name1)) && isUserExisted(name2))
		{
			user1 = getUserByName(name1);
			user2 = getUserByName(name2);

			if(user1.getFriends().contains(user2))
				System.out.println("\n\t\t\t" + name1 + " and " + name2 + " are friends");
			else
				System.out.println("\n\t\t\t" + name1 + " and " + name2 + " are not friends");
		}
		else
		{
			System.out.println("\n\t\t\tOne or more users do not exist in the network");
			System.out.println("n\t\t\tPlease try to select existed users.");
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
            System.out.println("The parents' info is not required for Adults"
                    + ", no records to be retrieved");
            return;
        }
        else
        {
            parents = ((Dependent)selectedUser).getParents();
            System.out.println("The following two users are "
                    + "the selected user's parents: ");
            System.out.println("\n\t\t\tFather: " + ((Dependent)selectedUser).getParents()[0].getName() + " , "
                    + "\t\t\tMother: " +((Dependent)selectedUser).getParents()[1].getName());
        }
    }
    
	public void setSpouse() 
	{

		if (selectedUser.getAge() <= 16) 
		{
			System.out.println("\n\t\t\tA dependent is not allowed to have a spouse");
			return;
		}
		else 
		{
			System.out.println("\n\t\t\tPlease enter the user's spouse's name: ");
			String input = sc.next();
			Adult user1;
			User user2;
			user1 = (Adult)selectedUser;
			boolean eligible = false;

			//Find if the two users are in MiniNet
			if (isUserExisted(input))
			{
				user2 = getUserByName(input);
			}
			else
			{
				System.out.println("\n\t\t\tError, the user you searched "
						+ "does not exist in Mininet.");
				return;
			}
			//Compare whether the two users are the same one
			if (selectedUser.name.equals(input))
			{
				System.out.println("\n\t\t\tError, the user you searched "
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
			System.out.println("\n\t\t\tAn adult is not required to set parents!");
			return;

		}
		else 
		{
			if(((Dependent)selectedUser).getParents() != null) 
			{
				System.out.println("\n\t\t\t" + selectedUser.getName() +  " already has parents,"
						+ " no need to set parents for " + selectedUser.getName());
				return;
			}
			
			else 
			{
				User father, mother;

				while (true) 
				{
					System.out.println("\n\t\t\tPlease enter the father's name for the selected user:");
					String input = sc.next();


					//Detect whether the expected "parents" for the selected user are in MiniNet
					if (isUserExisted(input))
					{
						father = getUserByName(input);
						if(father.getAge() <= 16) 
						{
							System.out.println("\n\t\t\tError, a dependent cannot be parent of its peers");
							continue;
						}
						else 
						{
							((Dependent)selectedUser).setFather((Adult)father);
							System.out.println("\n\t\t\tSuccessfully set " + father.getName() +
									" as " + selectedUser.getName() + "'s father ");
							break;
						}

					}								
					else
					{
						System.out.println("\n\t\t\tError, the user you searched "
								+ "does not exist in Mininet.");
						continue;
					}
				}

				while (true) 
				{
					System.out.println("\n\t\t\tPlease enter the mother's name for the selected user:");
					String input = sc.next();

					//Detect whether the expected "parents" for the selected user are in MiniNet
					if (isUserExisted(input))
					{
						mother = getUserByName(input);
						if(mother.getAge() <= 16) 
						{
							System.out.println("\n\t\t\tError, a dependent cannot be parent of its peers");
							continue;
						}
						else 
						{
							((Dependent)selectedUser).setMother((Adult)mother);
							System.out.println("\n\t\t\tSuccessfully set " + mother.getName() +
									" as " + selectedUser.getName() + "'s mother ");
							break;
						}

					}								
					else
					{
						System.out.println("\n\t\t\tError, the user you searched "
								+ "does not exist in Mininet.");
						continue;
					}
				}
			}
		}
	}

	public void addKids() 
	{
		if (selectedUser instanceof Dependent) 
		{
			System.out.println("\n\t\t\tError, a dependent cannot be parent of its peers");
			return;
		}
		else
		{
			String children = "\n\t\t\tPlease select a dependent in MiniNet: ";
			
			for (User user : theMininet) 
			{
				if (user instanceof Dependent)
					children += user.getName() + ",";
			}
			System.out.println(children);
			
			while (true) 
			{
				String childName = sc.next();
				
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
						System.out.println("\n\t\t\tThis dependent is already a child of "
					+ selectedUser.getName());
					return;
				}
				if(flag)
				{
					((Adult)selectedUser).addChildren((Dependent)getUserByName(childName));
					System.out.println("\n\t\t\tSuccessfully set " + childName
							+ " as a child of " + selectedUser.getName());
					break;
				}else 
					System.out.println("\n\t\t\tThe child you searched does not exist "
							+ "in MiniNet, please enter again: ");
			} 
		}
	}


	public void updateStatus() 
	{       
		System.out.println("\n\t\t\tPlease update the status for the selected user:");
		String input = sc.next();
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
				System.out.println("\n\t\t\tFailed to add this dependent"
						+ " as the parent for the new user, "
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
			
			System.out.println("\n\t\t\tThis dependent is successfully"
					+ " added into MiniNet.");
		}

		else
		{
			System.out.println("\n\t\t\tFailed to add this dependent, "
					+ "the reason might be either its parents do not exist in MiniNet,"
					+ "or this dependent already exists in MiniNet");
		}
	}
	
	private void addAdult(String name, int age, String photoPath, String status) 
	{

		if (!isUserExisted(name))
		{
			User adult = new Adult(name, age, photoPath, status);
			theMininet.add(adult);
			System.out.println("\n\t\t\t"+ name +" is successfully "
					+ "added into MiniNet");
		}
		else
			System.out.println("\n\t\t\tThis adult already exists in Mininet");
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
