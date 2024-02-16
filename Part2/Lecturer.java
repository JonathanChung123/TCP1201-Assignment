import java.util.ArrayList;

/**
 * @author Jonathan Chung
 * @version 1.0.0
 */

public class Lecturer extends User{

    /**
     * Represents the name of the lecturer
     */
    protected String name;

    /**
     * Represents the course the lecturer is teaching
     */
    protected ArrayList<Course> courses = new ArrayList<>();


    /**
     * 
     * @param username username of the lecturer
     * @param password password of the lecturer
     * @param userType user type Lecturer
     * @param name name of the lecturer
     * @param courses courses of the lecturer
     */
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

    public String toCSVString(){
        ArrayList<String> c = new ArrayList<>();
        for(Course course:courses)
            c.add(course.courseName);
        String listString = String.join(" ", c);
        if(c.isEmpty())
            listString = "null";
        return username + "," + password + "," + name + "," + listString;
    }
}
