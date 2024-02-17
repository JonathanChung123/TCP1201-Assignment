package user_class;

import java.util.ArrayList;

/**
 * @author Jonathan Chung
 * @version 1.0.0
 */


/**
  * The Student class provides information of a student. It can store and provide relevant data.
  * The Student class extends the User class
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

    /**
     * Get the name of the student
     * @return The name of the student in String
     */
    public String getName(){
        return name;
    }

    /**
     * Add a course to currentCourses
     * @param course is type Course, to add a course into the student
     */
    public void addCourses(Course course){
        currentCourses.add(course);
    }

    /**
     * Get the current courses taken by the student
     * @return The current courses taken by the student in ArrayList
     */
    public ArrayList<Course> getCurrentCourses(){
        return currentCourses;
    }

    /**
     * Get the previous courses taken by the student
     * @return The previous courses taken by the student in ArrayList
     */
    public ArrayList<Course> getPrevCourses(){
        return previousCourses;
    }

    /**
     * Get the total credit of the courses of previous triemesters
     * @return The total credit of previous courses in Integer
     */
    public Integer getTotalPrevCredit(){
        Integer totalCredit = 0;
        for(Course course:previousCourses)
            totalCredit += course.getCredit();
        return totalCredit;
    }

    /**
     * Gets the total credit of the courses of current triemester
     * @return The total credit of current courses in Integer
     */
    public Integer getTotalCurrentCredit(){
        Integer totalCredit = 0;
        for(Course course:currentCourses)
            totalCredit += course.getCredit();
        return totalCredit;
    }
    
    /**
     * Returns the information into CSV format
     * @return username,password,name,previousCourses,currentCourses in String
     */
    @Override
    public String toCSVString(){
        ArrayList<String> c1 = new ArrayList<>();
        for(Course course:previousCourses)
            c1.add(course.getCourseName());
        String listString1 = String.join(" ", c1);
        if (c1.isEmpty())
        listString1 = "null";

        ArrayList<String> c2 = new ArrayList<>();
        for(Course course:currentCourses)
            c2.add(course.getCourseName());
        String listString2 = String.join(" ", c2);
        if (c2.isEmpty())
            listString2 = "null";
        return getUsername() + "," + getPassword() + "," + name + "," + listString1 + "," + listString2;
    }
}
