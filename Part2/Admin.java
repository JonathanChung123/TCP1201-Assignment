public class Admin extends User{
    public Admin(String username, String password, String userType){
        super(username,password,userType);
    }

    public String toCSVString(){
        return username + "," + password;
    }
}
