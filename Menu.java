/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mininet;
import java.io.*;
import java.util.*;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public class Menu 
{
    private static Scanner sc;
    private static ArrayList<String> options;
    private static ArrayList<User> members = new ArrayList<User>();
    
    
    
    public Menu()
    {
        sc = new Scanner(System.in);
        options = ValidateInputForMainMenu();
    }

    /*
    Read data from a text file

    @return a list of users which is alreay existed in the network
     */

    public static ArrayList<User> readFromFile()
    {
        String name;
        int age;
       
        
        try 
        {
            File f = new File("members.txt");
            if (!f.exists() || f.isDirectory())
            {
               f.createNewFile(); 
            }       
                
            
            BufferedReader inputStream = 
                    new BufferedReader(new FileReader("members.txt"));
            
            String line = "";
            
            while(true)
            {
                line = inputStream.readLine();
                if (line == null)
                {
                    break;
                }  
                
                
                
            String arg[] = line.split(",");
            
            members.add(new Adult(arg[0], Integer.parseInt(arg[1])));
            }
          
            //release the resource
            inputStream.close();
            
        } 
            
        catch (FileNotFoundException ffe) 
        {
            ffe.printStackTrace();
            System.out.println("File members.txt was not found");
            System.out.println("or could not be opened");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Fail to read from members.txt");
        }
        
        return members;
    }
    
    /**
     * read user input form the main menu
     * and validate it
     * 
     * @return 
     */
    
    public int processMainMenuInput()
    {
        boolean continueLoop;
        
        int choice = -1;
        
        List<Integer> validChoices = new ArrayList<Integer>();
        validChoices.add(0);
        validChoices.add(1);
        validChoices.add(2);
        validChoices.add(3);
        validChoices.add(4);
        validChoices.add(5);
        validChoices.add(6);
        validChoices.add(7);
        validChoices.add(8);
                    
        
        do
        {
            continueLoop = false;
            try 
            {
                int input = sc.nextInt();
                
                switch (input)
                {
                    case Interaction.EXIT:
                        input = 0;
                        break;
                    case Interaction.LISTMEMBERS:
                        input = 1;
                        break;
                    case Interaction.ADDUSER:
                        input = 2;
                        break;
                    case Interaction.SELECTUSER:
                        input = 3;
                        break;
                    case Interaction.CONNECTUSER:
                        input = 4;
                        break;
                    case Interaction.INQUIREFRIENDSHIP:
                        input = 5;
                        break;
                    case Interaction.FINDOUTCHILDREN:
                        input = 6;
                        break;
                    case Interaction.FINDOUTPARENTS:
                        input = 7;
                        break;
                    case Interaction.CHANGERELATIONSHIP:
                        input = 8;
                        break;
                    
                    default:
                        System.out.println("Incorrect number of Choice！Enter Again !");
                        if (!validChoices.contains(input))
                            continueLoop = true;                                       
                }
            } 
            catch (InputMismatchException ime) 
            {
                System.out.println("Invalid input ! Enter again: ");
                continueLoop = true;
            }
        } while(continueLoop);
        
        return choice;
    }

    private String selectUserInput()
    {
        String result = new String();
        System.out.println("Please enter the name of the user you want to select: ");
        
        result = sc.nextLine();
        
        return result;
    }
    
     
    
    public static void present(int input, User u)
    {
        switch(input)
        {
            case Interaction.MAINMENU:
                presentMainMenu();
                break;
            case Interaction.DISPLAYPROFILE:
                
            case Interaction.INQUIREFRIENDSHIP:
                
        }
    }
    
    
    public static void presentMainMenu()
    {
        System.out.println("\n MiniNet Menu");
        System.out.println();
        System.out.println("\t1. List existing users");
        System.out.println("\t2. Add a new user to MiniNet");
        System.out.println("\t3. Select a particuler user");
        System.out.println("\t4. Connect two users");
        System.out.println("\t5. Find out friendship between two users");
        System.out.println("\t6. Find out the name of an adult's children");
        System.out.println("\t7. Find out the name of a dependent's parents");
        System.out.println("\t8. Alter the relationship between two users");
        System.out.println("\t0 Exit");
    }
    
    public static void presentSelectedUserSubMenu()
    {
        System.out.println("\n Please select the operation you want"
                + " to perform on the selected user");
        System.out.println();
        System.out.println("\t1. Display the selected user's profile");
        System.out.println("\t2. Update the selected user's profile");
        System.out.println("\t3. Delete the selected user's profile");
        System.out.println("\t4. Add friends for the selected user");
        System.out.println("\t5. Delete friends for the selected user");
        System.out.println("\t6. Change status for the selected user");
        System.out.println("\t7. Update friend relationship");
        System.out.println("\t8. Update parent-children relationship");
        System.out.println("\t9. Update spouse relationship");
        System.out.println("\t0. Back to the main menu");
        
    }
    
    private ArrayList<String> ValidateInputForMainMenu()
    {
        ArrayList<String> validInput = new ArrayList<String>();
        
        validInput.add("0");
        validInput.add("1");
        validInput.add("2");
        validInput.add("3");
        validInput.add("4");
        validInput.add("5");
        validInput.add("6");
        validInput.add("7");
        validInput.add("8");
        
        return validInput;
        
    }
    
    private ArrayList<String> ValidateSelectedUserSubMenu()
    {
        ArrayList<String> validInput = new ArrayList<String>();
        
        validInput.add("0");
        validInput.add("1");
        validInput.add("2");
        validInput.add("3");
        validInput.add("4");
        validInput.add("5");
        validInput.add("6");
        validInput.add("7");
        validInput.add("8");
        validInput.add("9");
        
        return validInput;
    }
    
    private ArrayList<String> ValidateProfileUpdatingInput()
    {
         ArrayList<String> validInput = new ArrayList<String>();
         
         validInput.add("1");
         validInput.add("2");
         
         return validInput;
    }
    
    
    private static void inquireFriendshipPrompts()
    {
        System.out.println("Finding out whether two users are friends: ");
        System.out.println();
        System.out.println("Please enter the names "
                + "of the two that you want to check: ");
        System.out.println("Names are split by a whitespace");
    }
    
    private static void profileUpdatingPrompts(User u)
    {
        System.out.println("You are about to update the "
                + "profile of the selected user");
        System.out.println();
        System.out.println("The selected user's profile: ");
        System.out.println(u);
        System.out.println("You are allowed to change "
                + "the following two fields of the selected user: ");
        System.out.println("1. Profile Photo Path");
        System.out.println("2. Status");
        System.out.println("Enter the corresponding option "
                + "and the alternate information,split them by a white space");
    }
    
    private static void relationshipChangingPrompts()
    {
        System.out.println("Please be aware that you are about to "
                + "change the relationship between two particular users: ");
        System.out.println();
        System.out.println("Please set the relationship between "
                + "the two users to be one of the following three types");
        System.out.println("\t Friends");
        System.out.println("\t Spouses");
        System.out.println("\t Parent-Child");
    }
   
    
    
    
    private void writeToFile()
    {
        PrintWriter outputStream = null;
        
        try 
        {
            File f = new File("members.txt");
            if (!f.exists()|| f.isDirectory())
                f.createNewFile();
            
            outputStream =
                    new PrintWriter(new FileOutputStream("members.txt"));
                    
        } 
        catch (FileNotFoundException ffe) 
        {
            System.out.println("File members.txt was not found");
            System.exit(0);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Fail to save date to members.txt");
        }
        
        for (int i = 0; i < members.size(); i++) 
        {
           User u = members.get(i);
           outputStream.println(u.getName() + "," + u.getAge());     
        }      
        outputStream.close();
    }
}
