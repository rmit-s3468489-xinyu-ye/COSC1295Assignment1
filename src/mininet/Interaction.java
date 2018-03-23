package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public interface Interaction {
	//mininet Main menu selection list
    public static final int ListEveryone = 1;
    public static final int SelectaUser = 2;
    public static final int AddaUser = 3;
    public static final int DelaUser = 4;
    public static final int EXIT = 0;
    
    //select user's menu list
    public static final int UpdateProfile = 1;
    public static final int DelProfile = 2;
    public static final int AddaFriend = 3;
    public static final int DelaFriend = 4;
    public static final int ChangeStatus = 5;
    public static final int CheckDetails = 6;   
    public static final int BackMainMenu = 0;
}