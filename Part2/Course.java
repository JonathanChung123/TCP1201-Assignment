import java.util.ArrayList;

/**
 * @author Jonathan Chung
 * @version 1.0.0
 */

public class Course {
    /**
     * Represents the name of the course
     */
    protected String courseName;

    /**
     * Represents the credit of the course
     */
    protected Integer credit;

    /**
     * Represents the pre-requisite courses of the course
     */
    protected ArrayList<Course> preRequisiteCourse;

    /**
     * Represents the credit required to take the course
     */
    protected Integer reqCredit;

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
     * 
     * @return Returns the information into CSV format: 
     * courseName,credit,preRequisiteCourse,reqCredit
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
