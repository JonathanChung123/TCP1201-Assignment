/**
 * @author Ho Jie Le
 * @verison 1.0.0
 */

/**
  * The User class provides information of a user. It acts as a superclass.
  */
public class User {

    /**
     * Represents the user's username
     */
    private String username;

    /**
     * Represents the user's password
     */
    private String password;

    /**
     * Represents the user's user type
     */
    private String userType;

    /**
     * The constructor initializes the user's information
     * @param username username of the user
     * @param password password of the user
     * @param userType user type of the user
     */
    User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Get the username of the user
     * @return The username of the user in String
     */
    public String getUsername(){
        return username;
    }

    /**
     * Get the password of the user
     * @return The password of the user in String
     */
    public String getPassword(){
        return password;
    }

    /**
     * Get the user type of the user
     * @return The user type of the user in String
     */
    public String getuserType(){
        return userType;
    }

    /**
     * Returns the information into CSV format
     * @return username,password,userType in String
     */
    public String toCSVString(){
        return getUsername() + "," + getPassword() + "," + getuserType();
    }
}
