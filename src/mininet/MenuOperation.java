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
    private static ArrayList<String> addAPerson;
    

    public MenuOperation(){
        sc = new Scanner(System.in);
        addAPerson = createNewUser();
    }

	public static List<User> readData() {
		return FileOperation.readData();
	}
    
    public int validateMenuInput() {
        String userInput = "";
        int choice = -1;
        System.out.print("\nplease enter the munber on the option and press enter: ");
        userInput = sc.nextLine();
        while(!userInput.matches("[0-4]")) {
            System.out.print("\ninvaild input, please try again: ");
            userInput = sc.nextLine();
        }
        choice = Integer.parseInt(userInput);
        return choice;
    }
    
    
    public String[] readAndCall(int flag) {
        String[] input = null;
        switch (flag){
            case Interaction.ADD_PERSON:
                input = addPerson();
                break;
            case Interaction.SELECT_USER:
                input = selectUserOperation();
                break;
                
        }

        return input;
    }
    

    private String[] selectUserOperation() {
        String[] result;
        List<String> vaildSelect = vaildSubMenuSelect();
        int counter = 0;

        do {
            if (counter++ != 0)
                System.out.print("\ninvalid option, enter a valid one please:");
            String input = sc.nextLine();
            result = input.split("-");

        }while((result.length != 2) || (!vaildSelect.contains(result[0])));

        return  result;
    }


    private String[] addPerson() {
        String[] result = new String[6];

        do {
            int counter = 0;
            for (String create : addAPerson) {
                System.out.print(create);
                result[counter++] = sc.nextLine();                
            }
            
        //validate the input. the system will let user retype until all the conditions are met
    }while(result[0].isEmpty() ||!result[1].matches("^[0-9]+$")||
            ( Integer.parseInt(result[1])<=16 && (result[4].isEmpty() ||result[5].isEmpty())  ) 
            );
        
        return result;
    }
    
    private static ArrayList<String> createNewUser() {
        ArrayList<String> creates = new ArrayList<String>();
        creates.add("\n Welcome! you'd better read thses rules to complete the registration."
        		+ "\n\n\t no same name here, teenager under 16 must have 2 parents"
        		+ "\n\nplease enter the new user's name: ");
        creates.add("please enter age: ");
        creates.add("please update your photo via'src/photo/yourname.jpg',you can press enter to skip:");
        creates.add("please update your status, you can press enter to skip:");
        creates.add("What is the mother's name of the child (press enter to pass if the person is above 16):");
        creates.add("What is the father's name of the child (press enter to pass if the person is above 16):");
        return creates;
        }
      

      private ArrayList<String> vaildSubMenuSelect() {
          ArrayList<String> vaildSelect = new ArrayList<String>();

          vaildSelect.add("1");
          vaildSelect.add("2");
          vaildSelect.add("3");

          return vaildSelect;
      }



    public static void display(int source, User user){

        switch (source){

            case Interaction.SELECT_USER:
                displaySubMenu(user);
                break;
        }
    }



    

    private static void displaySubMenu(User u) {
        System.out.println("=====================");
        System.out.println("the person's details you are currently selecting:");
        if (u.age > 16)
            System.out.println((Adult)u);
        else
            System.out.println((Dependent)u);
        System.out.println("\tSubMenu");
        System.out.println("======================================");
        System.out.println("1. update your profile");
        System.out.println("2. add a Friend");
        System.out.println("3. delete a Friend(unfinished)");
        System.out.println("4. set couple");
        System.out.println("5. set parents");
        System.out.println("6. add a child");
        System.out.println("7. change status");
        System.out.println("0. back to main menu");
        System.out.print("make your choice:");
    }


    
    public static void displayMainMenu() {
        System.out.println("\nMiniNet\tMenu");
        System.out.println("==================");
        System.out.println("1. list everyone");
        System.out.println("2. add a person");
        System.out.println("3. select a User");
        System.out.println("4. delete a User");
        System.out.println("0. exit");
    }
    
    


}
