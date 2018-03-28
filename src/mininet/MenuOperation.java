/**
 *
 * @author Yifan ZHANG s3615625
 */
package mininet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MenuOperation {
	private static Scanner sc;
    private static ArrayList<String> addUserPrompts;
    

    public MenuOperation(){
        sc = new Scanner(System.in);
        addUserPrompts = createNewUser();
    }

	public static List<User> readData() {
		return FileOperation.readFromFile();
	}
    
    public int validateMainMenuInput() {
        String userInput = "";
        int choice = -1;
        System.out.print("\nPlease enter the number of the option and press enter: ");
        userInput = sc.next();
        while(!userInput.matches("[0-4]")) {
            System.out.print("\nInvaild input, please try again: ");
            userInput = sc.next();
        }
        choice = Integer.parseInt(userInput);
        return choice;
    }
    
    public String[] readAndCall(int flag) {
        String[] input = null;
        switch (flag){
            case Interaction.ADD_USER:
                input = addUser();
                break;
            case Interaction.SELECT_USER:
                input = selectUserOperation();
                break;
        }
        return input;
    }
    
    private String[] selectUserOperation() {
        String[] result;
        List<String> vaildInput = vaildateSubMenuInput();
        int counter = 0;

        do {
            if (counter++ != 0)
                System.out.print("\nInvalid option, enter a valid one please:");
            String input = sc.next();
            result = input.split("-");

        }while((result.length != 2) || (!vaildInput.contains(result[0])));

        return  result;
    }

    private String[] addUser() {
        String[] result = new String[6];

        do {
            int counter = 0;
            for (String create : addUserPrompts) {
                System.out.print(create);
                result[counter++] = sc.next();                
            }
            
        //validate the input. the system will let user retype until all the conditions are met
    }while(result[0].isEmpty() ||!result[1].matches("^[0-9]+$")||
            ( Integer.parseInt(result[1])<=16 && (result[4].isEmpty() ||result[5].isEmpty())  ) 
            );
        
        return result;
    }
    
    private static ArrayList<String> createNewUser() {
        ArrayList<String> creates = new ArrayList<String>();
        creates.add("\n Attention "
        		+ "\n\n\t there will never be duplicate names, dependents must have 2 parents"
        		+ "\n\nPlease enter the user's name: ");
        creates.add("Please enter the age of the user: ");
        creates.add("Please update your photo via'src/photo/yourname.jpg', you can also "
        		+ "press enter to skip this step and update it later:");
        creates.add("Please update your status, you can also "
        		+ "press enter to skip this step and update it later:");
        creates.add("Please enter the mother's name of the dependent (press enter to pass "
        		+ "if the user is older than 16):");
        creates.add("Please enterfather's name of the dependent (press enter to pass "
        		+ "if the person is older than 16):");
        return creates;
        }
      

      public ArrayList<String> vaildateSubMenuInput() {
          ArrayList<String> vaildInput = new ArrayList<String>();

          vaildInput.add("0");
          vaildInput.add("1");
          vaildInput.add("2");
          vaildInput.add("3");
          vaildInput.add("4");
          vaildInput.add("5");
          vaildInput.add("6");
          vaildInput.add("7");

          return vaildInput;
      }

    public static void display(int input, User user){

        switch (input){
            case Interaction.SELECT_USER:
                displaySubMenu(user);
                break;
        }
    }

    public static void displaySubMenu(User u) {
        System.out.println("The selected user's profile:");
        if (u.age > 16)
            System.out.println((Adult)u);
        else
            System.out.println((Dependent)u);
        System.out.println("\tSubMenu");
        System.out.println("1. Update your profile");
        System.out.println("2. Add a Friend");
        System.out.println("3. Delete a Friend(unfinished)");
        System.out.println("4. Set spouse");
        System.out.println("5. Set parents");
        System.out.println("6. Add a child");
        System.out.println("7. Change status");
        System.out.println("0. Back to main menu");
        System.out.print("Please enter your option:");
    }
    
    public static void displayMainMenu() {
        System.out.println("\n\t\t\t\t MiniNet Main \tMenu");
        System.out.println("\t\t\t\t1. List existing users");
        System.out.println("\t\t\t\t2. Add a user into MiniNet");
        System.out.println("\t\t\t\t3. Select a user");
        System.out.println("\t\t\t\t4. Delete a User");
        System.out.println("\t\t\t\t0. Exit");
    }
}
