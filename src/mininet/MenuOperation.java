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
    
    public int validateMenuInput() {
        String userInput = "";
        int selection = -1;
        System.out.print("\nPlease enter the number of the option and press enter: ");
        userInput = sc.nextLine();
        while(!userInput.matches("[0-4]")) {
            System.out.print("\nInvaild input, please try again: ");
            userInput = sc.nextLine();
        }
        selection = Integer.parseInt(userInput);
        return selection;
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
        List<String> vaildSelection = vaildSubMenuInput();
        int counter = 0;
        
        do {
            if (counter++ != 0)
                System.out.print("\ninvalid option, enter a valid one please:");
            String input = sc.nextLine();
            result = input.split("-");
            
        }while((result.length != 2) || (!vaildSelection.contains(result[0])));      
        return  result;
    }
    
    
    private String[] addUser() {
        String[] result = new String[6];
        
        do {
            int counter = 0;
            for (String create : addUserPrompts) {
                System.out.print(create);
                result[counter++] = sc.nextLine();
            }
            
        }while(result[0].isEmpty() ||!result[1].matches("^[0-9]+$")||
                ( Integer.parseInt(result[1])<=16 && (result[4].isEmpty() ||result[5].isEmpty())  )
                );
        
        return result;
    }
    
    //We treat an adult as a user who is older than 16
    private static ArrayList<String> createNewUser() {
        ArrayList<String> creates = new ArrayList<String>();
        creates.add("Please enter the user's name: ");
        creates.add("Please enter the user's age: ");
        creates.add("Please update your photo via'src/photo/yourname.jpg',press enter to skip this step: ");
        creates.add("Please update your status, press enter to skip this step: ");
        creates.add("Please enter the mother's name of the user(press enter to pass if the user is an adult): ");
        creates.add("Please enter the father's name of the user(press enter to pass if the user is an adult): ");
        return creates;
    }
    
    
    private ArrayList<String> vaildSubMenuInput() {
        ArrayList<String> vaildInput = new ArrayList<String>();
        
        vaildInput.add("1");
        vaildInput.add("2");
        vaildInput.add("3");
        vaildInput.add("4");
        vaildInput.add("5");
        vaildInput.add("6");
        vaildInput.add("7");
        vaildInput.add("0");
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
        System.out.println("\tSub Menu for the selected user");
        System.out.println("1. Update profile");
        System.out.println("2. Add a friend");
        System.out.println("3. delete a friend(unfinished)");
        System.out.println("4. Set spouse");
        System.out.println("5. Set parents");
        System.out.println("6. Add a child");
        System.out.println("7. Change status");
        System.out.println("0. Back to main menu");
        System.out.print("Please enter the number of option: ");
    }
    
    
    
    public static void displayMainMenu() {
        System.out.println("\n\t\t\tMiniNet\t Main \tMenu");
        System.out.println("1. List existing users");
        System.out.println("2. Add a user");
        System.out.println("3. Select a user");
        System.out.println("4. Delete a user");
        System.out.println("0. Exit");
    }

}
