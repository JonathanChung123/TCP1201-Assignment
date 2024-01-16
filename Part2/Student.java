import java.util.ArrayList;

public class Student extends User{
    protected String name;
    protected ArrayList<Course> previousCourses;
    protected ArrayList<Course> currentCourses;

    public Student(String username, String password, String userType, String name, 
    ArrayList<Course> previousCourses, ArrayList<Course> currentCourses){
        super(username,password,userType);
        this.name = name;
        this.previousCourses = previousCourses;
        this.currentCourses = currentCourses;
        if (this.previousCourses == null) {
            this.previousCourses = new ArrayList<Course>();
        }
        if (this.currentCourses == null) {
            this.currentCourses = new ArrayList<Course>();
        }

    }

    public void addCourses(Course course){
        currentCourses.add(course);
    }

    public ArrayList<Course> getCourses(){
        return currentCourses;
    }

    public Integer totalCredit(){
        Integer totalCredit = 0;
        for(Course course:currentCourses)
            totalCredit += course.credit;
        for(Course course:previousCourses)
            totalCredit +=course.credit;
        return totalCredit;
    }
}
