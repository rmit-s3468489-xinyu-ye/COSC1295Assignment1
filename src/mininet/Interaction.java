package mininet;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public interface Interaction
{
    public static final int EXIT = 0,
            
                            LIST_EVERYONE = 1,
            
                            ADD_USER = 2,
                            
                            SELECT_USER = 3,
                            
                            DISPLAY_SELECTED_PROFILE = 4,
            
                            UPDATE_SELECTED_PROFILE = 5,
            
                            DELETE_SELECTED_USER = 6,
                            
                            CONNECT_TWO_USERS = 7,
                                    
                            QUERY_FRIENDSHIP = 8,
                            
                            QUERY_PARENT_CHILD_RELATIONSHIP = 9,
                                    
                            BACK_TO_MAIN_MENU = 10,
    
                            FRIENDSHIP = 11,
            
                            SPOUSE_RELATIONSHIP = 12,
            
                            PARENT_CHILD_RELATIONSHIP = 13;                          
}
