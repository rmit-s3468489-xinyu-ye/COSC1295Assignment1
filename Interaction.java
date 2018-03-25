package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public interface Interaction 
{
    //MiniNet main menu
    public static final int LISTMEMBERS = 1;
    public static final int ADDUSER = 2;
    public static final int SELECTUSER = 3;
    public static final int CONNECTUSER = 4;
    public static final int INQUIREFRIENDSHIP = 5;
    public static final int FINDOUTCHILDREN = 6;
    public static final int FINDOUTPARENTS = 7;
    public static final int CHANGERELATIONSHIP = 8;
    public static final int EXIT = 0;
    
    //the selected user's submenu
    public static final int DISPLAYPROFILE = 1;
    public static final int UPDATEPROFILE = 2;
    public static final int DELETEPROFILE = 3;
    public static final int ADDFRIEND = 4;
    public static final int DELETEFRIEND = 5;
    public static final int CHANGESTATUS = 6;
    public static final int FRIENDRELATIONSHIP = 7;
    public static final int PARENTCHILDRELATIONSHIP = 8; 
    public static final int SPOUSERELATIONSHIP = 9;
    public static final int MAINMENU = 0;
}