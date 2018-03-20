/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mininet;

/**
 *
 * @author xinyuye
 */
public interface Interaction 
{
    public abstract User listEveryOne();
	
	public abstract User selectAUser();
	
	public abstract User displayProfile();
	
	public abstract User updateProfile();
	
	//We treat the function of connecting two Users as one
	//User follow a selected User's profile
	public abstract User followAUser();
	
	//We treat the deleting function as unfollow Users' profiles
	public abstract User unfollowAUser();
	
	public abstract User findOutConnections();
}
