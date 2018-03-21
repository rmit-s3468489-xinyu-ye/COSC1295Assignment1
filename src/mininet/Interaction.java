package mininet;
/**
 *
 * @author Xinyu YE s3468489
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
