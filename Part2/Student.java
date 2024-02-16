import java.util.ArrayList;

/**
 * @author Jonathan Chung
 * @version 1.0.0
 */

public class Student extends User{
    /**
     * Represents the name of the student
     */
    private String name;

    /**
     * Represents the previous courses took by the student
     */
    private ArrayList<Course> previousCourses;

    /**
     * Represents the current courses of the student
     */
    private ArrayList<Course> currentCourses;

    /**
     * The constructor initializes the student's information
     * @param username username of the student
     * @param password password of the student
     * @param userType user type Student
     * @param name name of the student
     * @param previousCourses Courses taken previously by student
     * @param currentCourses Courses taken currently by student
     */
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

    public String getName(){
        return name;
    }
    /**
     * Add a course to currentCourses
     * @param course The course to add to the current courses the student is taking
     */
    public void addCourses(Course course){
        currentCourses.add(course);
    }

    /**
     * 
     * @return Returns the current courses taken by the student
     */
    public ArrayList<Course> getCurrentCourses(){
        return currentCourses;
    }

    /**
     * 
     * @return Returns the previous courses taken by the student
     */
    public ArrayList<Course> getPrevCourses(){
        return previousCourses;
    }

    /**
     * 
     * @return Returns the total credit of previous courses
     */
    public Integer getTotalPrevCredit(){
        Integer totalCredit = 0;
        for(Course course:previousCourses)
            totalCredit += course.credit;
        return totalCredit;
    }

    /**
     * 
     * @return Returns the total credit of current courses
     */
    public Integer getTotalCurrentCredit(){
        Integer totalCredit = 0;
        for(Course course:currentCourses)
            totalCredit += course.credit;
        return totalCredit;
    }
    
    /**
     * 
     * @return Returns the information into CSV format: 
     * username,password,name,previousCourses,currentCourses
     */
    public String toCSVString(){
        ArrayList<String> c1 = new ArrayList<>();
        for(Course course:previousCourses)
            c1.add(course.courseName);
        String listString1 = String.join(" ", c1);
        if (c1.isEmpty())
        listString1 = "null";

        ArrayList<String> c2 = new ArrayList<>();
        for(Course course:currentCourses)
            c2.add(course.courseName);
        String listString2 = String.join(" ", c2);
        if (c2.isEmpty())
            listString2 = "null";
        return username + "," + password + "," + name + "," + listString1 + "," + listString2;
    }
}
