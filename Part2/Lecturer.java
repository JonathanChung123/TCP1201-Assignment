import java.util.ArrayList;

public class Lecturer extends User{

    protected String name;
    protected ArrayList<Course> courses = new ArrayList<>();


    public Lecturer(String username, String password, String userType, 
    String name, ArrayList<Course> courses){
        super(username,password,userType);
        this.name = name;
        this.courses = courses;
        if (this.courses == null) {
            this.courses = new ArrayList<Course>();
        }
    }

    public String getUsername(){
        return username;
    }

    public void addCourses(Course course){
        this.courses.add(course);
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }
}
