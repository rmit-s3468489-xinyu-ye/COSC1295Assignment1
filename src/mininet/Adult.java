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
public class Adult extends User
{
        private Adult spouse;

        private List<Dependent> children;

        public Adult(String name, String userName, String dateOfBirth, String password)
        {
            super(name, userName, dateOfBirth, password);

            spouse = null;

            children = null;

        }
    
        public Adult getSpouse() 
        {
		return spouse;
	}

	public void setSpouse(Adult spouse) 
        {
		this.spouse = spouse;
	}

	public List<Dependent> getChildren() 
        {
		return children;
	}
        
        public void setChildren(List<Dependent> children) 
        {
		this.children = children;
	}
        
        public void addChildren(Dependent child)
        {
            children.add(child);
        }
    
    
}
