import java.util.ArrayList;

public class Student extends User{
    protected String name;
    protected ArrayList<String> previousCourses;
    protected ArrayList<String> currentCourses;

    public Student(String username, String password, String userType, String name, 
    ArrayList<String> previousCourses, ArrayList<String> currentCourses){
        super(username,password,userType);
        this.name = name;
        this.previousCourses = previousCourses;
        this.currentCourses = currentCourses;
        if (this.previousCourses == null) {
            this.previousCourses = new ArrayList<String>();
        }
        if (this.currentCourses == null) {
            this.currentCourses = new ArrayList<String>();
        }

    }

    public void addCourses(String course){
        currentCourses.add(course);
    }

    public ArrayList<String> getCourses(){
        return currentCourses;
    }

    public void deleteCourse(){
        
    }
}
