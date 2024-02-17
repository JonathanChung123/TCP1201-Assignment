import java.util.ArrayList;

/**
 * @author Jonathan Chung
 * @version 1.0.0
 */

/**
  * The Lecturer class provides information of a lecturer. It can store and provide relevant data.
  * The Lecturer class extends the User class
  */
public class Lecturer extends User{

    /**
     * Represents the name of the lecturer
     */
    private String name;

    /**
     * Represents the course the lecturer is teaching
     */
    private ArrayList<Course> courses = new ArrayList<>();

    /**
     * The constructor initializes the lecturer's information
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

    /**
     * Get the name of the lecturer
     * @return The name of the lecturer in String
     */
    public String getName(){
        return name;
    }

    /**
     * Add a course to the lecturer
     * @param course is type Course, to add a course into the lecturer
     */
    public void addCourses(Course course){
        this.courses.add(course);
    }

    /**
     * Get the courses of the lecturer
     * @return The courses of the lecturer in ArrayList<Course>
     */
    public ArrayList<Course> getCourses(){
        return courses;
    }

    /**
     * Returns the information into CSV format
     * @return username,password,name,courses in String
     */
    public String toCSVString(){
        ArrayList<String> c = new ArrayList<>();
        for(Course course:courses)
            c.add(course.getCourseName());
        String listString = String.join(" ", c);
        if(c.isEmpty())
            listString = "null";
        return getUsername() + "," + getPassword() + "," + name + "," + listString;
    }
}
