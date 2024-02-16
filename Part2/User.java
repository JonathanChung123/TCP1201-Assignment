/**
 * @author Ho Jie Le
 * @verison 1.0.0
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

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getuserType(){
        return userType;
    }

}
