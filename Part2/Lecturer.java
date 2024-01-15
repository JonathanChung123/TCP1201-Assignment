import java.util.ArrayList;

public class Lecturer extends User{

    protected String name;
    protected ArrayList<String> courses = new ArrayList<>();


    public Lecturer(String username, String password, String userType, 
    String name, ArrayList<String> courses){
        super(username,password,userType);
        this.name = name;
        this.courses = courses;
        if (this.courses == null) {
            this.courses = new ArrayList<String>();
        }
    }

    public String getUsername(){
        return username;
    }

    public void addCourses(String course){
        this.courses.add(course);
    }

    public ArrayList<String> getCourses(){
        return courses;
    }
}
