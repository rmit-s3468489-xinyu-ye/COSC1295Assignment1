/**
 *
 * @author Yifan ZHANG s3615625
 */
package mininet;

public class MiniNet 
{
    
    private static MenuOperation menuOperation;
    private static Driver driver;
       
    public static void main(String[] args)
    {
        int userInput = -1;
        
        menuOperation = new MenuOperation();
        driver = new Driver(menuOperation);
        
        while(true) 
        {
            MenuOperation.displayMainMenu();
            userInput = menuOperation.validateMainMenuInput();
            userSelection(userInput);
        }
    }
    
    private static void userSelection(int input)
    {
        switch (input)
        {
            case Interaction.EXIT:
            		FileOperation.writeToFile();
                System.exit(0);
                break;
            case Interaction.LIST_EXISTED_USERS:
                driver.listExitedUsers();
                break;
            case Interaction.ADD_USER:
                driver.addUser();
                break;
            case Interaction.SELECT_USER:
                driver.selectUser();
                break;
            case Interaction.DELETE_USER:
                driver.deleteUser();
                break; 
            case Interaction.INQUIRE_FRIENDSHIP:
            		driver.isFriend();
            		break;
        }
    }
}
