import java.util.ArrayList;

public class Course {
    protected String courseName;
    protected Integer credit;
    protected ArrayList<Course> preRequisite;

    public Course(String courseName, Integer credit, ArrayList<Course> preRequisite){
        this.courseName = courseName;
        this.credit = credit;
        this.preRequisite = preRequisite;
        if (this.preRequisite == null) {
            this.preRequisite = new ArrayList<Course>();
        }
    }
}
