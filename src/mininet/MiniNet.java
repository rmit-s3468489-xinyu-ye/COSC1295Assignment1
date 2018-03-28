package mininet;


/**
 *
 * @author Yifan ZHANG s3615625
 */
public class MiniNet {
    
    private static MenuOperation menuOperation;
    private static Driver driver;
       
    public static void main(String[] args){
        int userInput = -1;
        
        menuOperation = new MenuOperation();
        driver = new Driver(menuOperation);
        
        while(true) {
            MenuOperation.displayMainMenu();
            userInput = menuOperation.validateMenuInput();
            userSelection(userInput);
        }
    }
    
    private static void userSelection(int input){
        
        switch (input){
            case Interaction.EXIT:
//            	fileOperation.saveChanges();
                System.exit(0);
                break;
            case Interaction.LIST_MEMBERS:
                driver.listEveryone();
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
        }
    }
    
}
