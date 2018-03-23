/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mininet;
import java.util.*;
/**
 *
 * @author xinyuye
 */
public class Menu 
{
    private static Scanner sc;
    private static ArrayList<String> options;
    private static ArrayList<String> UserAddingPrompts;
    
    public Menu()
    {
        sc = new Scanner(System.in);
        options = ValidateInputsForMenu();
        userAddingPrompts = loadUserAddingPrompts();
    }
}
