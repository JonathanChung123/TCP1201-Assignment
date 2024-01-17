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

    public String toCSVString(){
        ArrayList<String> c1 = new ArrayList<>();
        for(Course course:previousCourses)
            c1.add(course.courseName);
        String listString1 = String.join(" ", c1);

        ArrayList<String> c2 = new ArrayList<>();
        for(Course course:currentCourses)
            c2.add(course.courseName);
        String listString2 = String.join(" ", c2);
        return username + "," + password + "," + name + "," + listString1 + "," + listString2;
    }
}
