import java.util.ArrayList;

/**
 * @author Jonathan Chung
 * @version 1.0.0
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

    public String getCourseName(){
        return courseName;
    }

    public Integer getCredit(){
        return credit;
    }

    public ArrayList<Course> getPreRequisiteCourse(){
        return preRequisiteCourse;
    }

    public Integer getReqCredit(){
        return reqCredit;
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
