/**
 * @author Ho Jie Le
 * @verison 1.0.0
 */

public class Admin extends User{
    
    /**
     * The constructor initializes the admin's information
     * @param username username of the admin
     * @param password password of the admin
     * @param userType user type admin
     */
    public Admin(String username, String password, String userType){
        super(username,password,userType);
    }

    /**
     * 
     * @return Returns the information into string format
     */
    public String toString(){
        return username + ", " + password + ", " + userType;
    }

    /**
     * 
     * @return Returns the information into CSV format: 
     * username,password
     */
    public String toCSVString(){
        return username + "," + password;
    }
}
