package mininet;

import java.io.IOException;

/**
 *
 * @author Yifan ZHANG s3615625
 */
public class MiniNet 
{
    private static Menu menu;
    private static Driver driver;
    
    public static void main(String[] args) throws IOException 
    {
        menu = new Menu();
        driver = new Driver(menu);
        
        while(true)
        {
            Menu.presentMainMenu();
            int userInput = menu.processMainMenuInput();
        }
    }
}
