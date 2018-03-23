package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */

import java.util.*;

public class Driver
{
    private Menu menu;

    private User selectedUser;

    //the field's name "sns"
    //refers to "social networking site"
    private ArrayList<User> sns;

    public Driver(Menu menu)
    {

    }

    public void listEveryOne()
    {
        StringBuffer retrieval = 
        		new StringBuffer("\nThe existed members in this network: \n");

        for (User u : sns)
        {
            retrieval.append("\n\t").append(u.getName());
        }
        System.out.println(retrieval.toString());
    }


    public void addUser(String name, int age)
    {
        boolean isExisted = false;
    }

    public void selectUser(String name)
    {
        if (isUserExisted(name))
        {
            selectedUser = getUserByName(name);
            System.out.println("You have selected " + name + "");
        }
    }

    private void AddDependent(String name, int age,
                              boolean TwoYearsOldOrYounger, 
                              String fatherName, String motherName)
    {
        User u1 = null, u2 = null;
        
        boolean isExisted = isUserExisted(name),
                isFatherExisted = isUserExisted(fatherName),
                isMotherExisted = isUserExisted(motherName);

        Adult[] parents = new Adult[2];

        if (!(isExisted) && isFatherExisted && isMotherExisted)
        {
            u1 = getUserByName(fatherName);
            u2 = getUserByName(motherName);
        

        /*
        Validate to see whether the two names entered are of the type Adult
         */

            if (u1 instanceof Dependent || u2 instanceof Dependent)
                System.out.println("Failed to add this dependent as a user, "
                		+ "dependents cannot be their peers' parents.");
            else
            {
                parents[0] = (Adult)u1;
                parents[1] = (Adult)u2;
                Dependent d = new Dependent(name, age,
                        parents, TwoYearsOldOrYounger);
                sns.add(d);
                //Add the dependent to the children list 
                //of corresponding adult users
                for(Adult parent : parents)
                    parent.addChildren(d);
                
                System.out.println("This dependent is added successfully");
            }
        }
    }

    private void addAdult(String name, int age)
    {

        boolean isExisted = false;

        isExisted = isUserExisted(name);

        if ((!isExisted) && (age > 16))
        {
            User adult = new Adult(name,age);
            
            sns.add(adult);
               
            System.out.println("This adult is successfully added.");
        }
        else if (isExisted)
            System.out.println("This adult is already existed in the network!");    
        else
            System.out.println("The user to be added is an underage, "
                    + " thus cannot be added as an adult");
    }


    private boolean isUserExisted(String name)
    {
        for (User user : sns)
        {
            if(user.getName().equals(name))
                return true;
        }
        return false;
    }


    private User getUserByName(String name)
    {
        for (User user : sns)
        {
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }


    private boolean followFriends(User u1, User u2)
    {
        boolean eligible = false;
        boolean completed;

        //As stated in the assignment specification,
        //only when two particular users are all adults,
        //or dependents whose ages are both older than two,
        //with the age difference less than or equal to three,
        //and from different families

        if(u1 instanceof Adult && u2 instanceof Adult)
            eligible = true;

        else if((u1 instanceof Dependent && u2 instanceof Dependent)
                && (Math.abs(u1.getAge() - u2.getAge()) <= 3)
                && (u1.getAge() > 2 && u2.getAge() > 2)
                && !((Dependent)u1).getParents()[0].equals(((Dependent)u2).getParents()[0]))
            eligible = true;

        if(eligible)
        {
            u1.addFriend(u2);
            u2.addFriend(u1);
        }

        return completed = true;
    }
}
