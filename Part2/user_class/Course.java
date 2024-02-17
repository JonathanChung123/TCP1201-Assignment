package user_class;

import java.util.ArrayList;

/**
 * @author Jonathan Chung, Ho Jie Le
 * @version 1.0.0
 */

/**
  * The Course class provides information of a course. It can store and provide relevant data.
  */
public class Course {

    /**
     * Represents the name of the course
     */
    private String courseName;

    /**
     * Represents the credit of the course
     */
    private Integer credit;

    /**
     * Represents the pre-requisite courses of the course
     */
    private ArrayList<Course> preRequisiteCourse;

    /**
     * Represents the credit required to take the course
     */
    private Integer reqCredit;

    /**
     * The constructor initializes the course's information
     * @param courseName name of course
     * @param credit the credit of course
     * @param preRequisiteCourse the pre-requisite to take the course
     * @param reqCredit the required credit to take the course
     */
    public Course(String courseName, Integer credit, ArrayList<Course> preRequisiteCourse, Integer reqCredit){
        this.courseName = courseName;
        this.credit = credit;
        this.preRequisiteCourse = preRequisiteCourse;
        if (this.preRequisiteCourse == null) {
            this.preRequisiteCourse = new ArrayList<Course>();
        }
        this.reqCredit = reqCredit;
    }

    /**
     * Get the name of the course
     * @return The name of the course in String
     */
    public String getCourseName(){
        return courseName;
    }

    /**
     * Get the credit of the course
     * @return The credit of the course in Integer
     */
    public Integer getCredit(){
        return credit;
    }

    /**
     * Get the pre-requisite to take the course
     * @return The pre-requisite course to take the course in ArrayList
     */
    public ArrayList<Course> getPreRequisiteCourse(){
        return preRequisiteCourse;
    }

    /**
     * Get the credits required to take the course
     * @return The credits required to take the course in Integer
     */
    public Integer getReqCredit(){
        return reqCredit;
    }

    /**
     * Returns the information into toString format
     * @return "Course name: courseName, Credit: credit" in String
     */
    public String toString() {
        return "Course Name: " + courseName + ", Credit: " + credit;
    }

    /**
     * Returns the information into CSV format
     * @return courseName,credit,preRequisiteCourse,reqCredit in String
     */
    public String toCSVString(){
        ArrayList<String> c = new ArrayList<>();
        for(Course course:preRequisiteCourse)
            c.add(course.courseName);
        String listString = String.join(" ", c);
        if (c.isEmpty())
            listString = "null";
        return courseName + "," + credit + "," + listString + "," + reqCredit;
    }
}
