import java.util.ArrayList;

public class Course {
    protected String courseName;
    protected Integer credit;
    protected ArrayList<Course> preRequisiteCourse;
    protected Integer reqCredit;

    public Course(String courseName, Integer credit, ArrayList<Course> preRequisiteCourse, Integer reqCredit){
        this.courseName = courseName;
        this.credit = credit;
        this.preRequisiteCourse = preRequisiteCourse;
        if (this.preRequisiteCourse == null) {
            this.preRequisiteCourse = new ArrayList<Course>();
        }
        this.reqCredit = reqCredit;
    }
}
