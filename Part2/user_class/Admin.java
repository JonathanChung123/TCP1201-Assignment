package user_class;

/**
 * @author Ho Jie Le
 * @version 1.0.0
 */


/**
  * The Admin class provides information of an admin. It can store and provide relevant data.
  * The Admin class extends the User class
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
     * Returns the information in toString format
     * @return username,password,userType in String
     */
    public String toString(){
        return getUsername() + ", " + getPassword() + ", " + getUserType();
    }

    /**
     * Returns the information into CSV format
     * @return username,password in String
     */
    @Override
    public String toCSVString(){
        return getUsername() + "," + getPassword();
    }
}
