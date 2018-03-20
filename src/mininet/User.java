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
public abstract class User 
{
        private String name, userName, dateOfBirth, photoPath;
        private String password, status;

        private List<User> connections;

        public User(String name, String userName, String dateOfBirth, String password)
        {
            this.name = name;
            this.userName = userName;
            this.dateOfBirth = dateOfBirth;
            this.password = password;
            status = null;
            photoPath = null;
            connections = new ArrayList<User>();
        }
    
        public String getName()
        {
            return name;
        }
    
    	
	public void setName(String name) 
        {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) 
        {
		this.userName = userName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) 
        {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() 
        {
		return password;
	}
	public void setPassword(String password) 
        {
		this.password = password;
	}
		
	public String getPhotoPath() 
        {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) 
        {
		this.photoPath = photoPath;
	}
	public String getStatus() 
        {
		return status;
	}
	public void setStatus(String status) 
        {
		this.status = status;
	}
        
        public List<User> getConnections()
        {
            return connections;
        }
        
        public void followUser(User u)
        {
            this.connections.add(u);
        }
        
        public void unfollowUser(User u)
        {
            this.connections.remove(u);
        }
        
        
}
