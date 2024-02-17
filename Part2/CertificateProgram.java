import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import user_class.Admin;
import user_class.Course;
import user_class.Lecturer;
import user_class.Student;

// Main class representing the certificate program, extending Application
public class CertificateProgram extends Application {

    //Store classes
    private static LinkedHashSet<Lecturer> lecturers = new LinkedHashSet<Lecturer>();
    private static LinkedHashSet<Student> students = new LinkedHashSet<Student>();
    private static LinkedHashSet<Admin> admins = new LinkedHashSet<Admin>();
    private static LinkedHashSet<Course> courses = new LinkedHashSet<Course>();
    
    //subdata: just to store username and password
    private static Map<String,String> lecturer_subdata = new HashMap<>();
    private static Map<String,String> student_subdata = new HashMap<>();
    private static Map<String,String> admin_subdata = new HashMap<>();
    private static List<Map<String,String>> loginData = new ArrayList<>();

    //Initailization to for user login type
    public static Lecturer lec_login = new Lecturer("","","lecturer","",null);
    public static Student stu_login = new Student("","","student","",null,null);
    public static Admin admin_login = new Admin("", "", "admin");

    /**
     * Main method to launch the JavaFX application and read from CSV files
     * @param args
     */
    public static void main(String[] args) {
        //initialization from csv files
        admins = readAdminFromFile();
        courses = readCourseFromFile();
        lecturers = readLecturerFromFile();
        students = readStudentFromFile();

        //initialization of all subdata
        loginData.add(lecturer_subdata);
        loginData.add(student_subdata);
        loginData.add(admin_subdata);

        launch(args);
    }

    /**
     * Get the data from admins.csv into the system
     * @return A LinkedHashSet<Admin> with the data taken from .csv file
     * @throws IOException When fail to read/write the file
     */
    private static LinkedHashSet<Admin> readAdminFromFile() {
        String filename = "admins.csv";
        LinkedHashSet<Admin> admins = new LinkedHashSet<Admin>();
        try {
            // read admin.csv into a list of lines.
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");
    
            //add the admin to admins
            admins.add(new Admin(items[0], items[1], "admin"));
            admin_subdata.put(items[0],items[1]);
        }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return admins;
    }

    /**
     * Get the data from courses.csv into the system
     * @return A LinkedHashSet<Course> with the data taken from .csv file
     * @throws IOException When fail to read/write the file
     */
    private static LinkedHashSet<Course> readCourseFromFile() {
        String filename = "courses.csv";
        LinkedHashSet<Course> courses = new LinkedHashSet<Course>();
        try {
            // read students.csv into a list of lines.
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");
            
            //Create a List with Type Course
            ArrayList<Course> preReqCourseList = new ArrayList<>();
            //If the pre-Requisite is not labeled as null
            if(!(items[2].equals("null"))){
                //course is a list that split at " " if there's multiple pre-Requisite
                String[] course = items[2].split(" ");
                Course preReqCourse = null; //basically will store the Course data that will be pre-Requisite for the current course
                for(String courseName:course){

                    //get the Course class
                    for(Course x:courses){
                        if(x.getCourseName().equals(courseName)){
                            preReqCourse = x;
                            break;
                        }
                    }
                    //if fail to get Course data
                    if (preReqCourse == null){
                        return null;
                    }

                    //add the Course into the list of Pre-Requisite Courses
                    
                    preReqCourseList.add(preReqCourse);
                }
            }
            //add the course to courses
            courses.add(new Course(items[0], Integer.parseInt(items[1]), preReqCourseList, Integer.parseInt(items[3])));
        }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return courses;
    }

    /**
     * Get the data from lecturers.csv into the system
     * @return A LinkedHashSet<Lecturer> with the data taken from .csv file
     * @throws IOException When fail to read/write the file
     */
    private static LinkedHashSet<Lecturer> readLecturerFromFile() {
        String filename = "lecturers.csv";
        LinkedHashSet<Lecturer> lecturers = new LinkedHashSet<Lecturer>();
        try {
            // read students.csv into a list of lines.
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");

            //Create a List with Type Course
            ArrayList<Course> CourseList = new ArrayList<>();
            //If the course is not labeled as null
            if(!(items[3].equals("null"))){
                //course is a list that split at " " if there's multiple courses
                String[] course = items[3].split(" ");
                Course lecCourse = null; //basically will store the Course data that the lecturer has
                for(String courseName:course){

                    //get the Course class
                    for(Course x:courses){
                        if(x.getCourseName().equals(courseName)){
                            lecCourse = x;
                            break;
                        }
                    }
                    //if fail to get Course data
                    if (lecCourse == null){
                        return null;
                    }
                    //add the Course into the list of Pre-Requisite Courses
                    CourseList.add(lecCourse);
                }
            }
            //add the lecturer to lecturers
            lecturers.add(new Lecturer(items[0], items[1], "lecturer", items[2], CourseList));
            lecturer_subdata.put(items[0], items[1]);
        }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return lecturers;
    }

    /**
     * Get the data from students.csv into the system
     * @return A LinkedHashSet<Student> with the data taken from .csv file
     * @throws IOException When fail to read/write the file
     */
    private static LinkedHashSet<Student> readStudentFromFile() {
        String filename = "students.csv";
        LinkedHashSet<Student> students = new LinkedHashSet<Student>();
        try {
            // read students.csv into a list of lines.
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");

            //Create a List with Type Course
            ArrayList<Course> preCourseList = new ArrayList<>();
            //If the course is not labeled as null
            if(!(items[3].equals("null"))){
                //course is a list that split at " " if there's multiple courses
                String[] course = items[3].split(" ");
                Course preCourse = null; //basically will store the Course data that the lecturer has
                for(String courseName:course){

                    //get the Course class
                    for(Course x:courses){
                        if(x.getCourseName().equals(courseName)){
                            preCourse = x;
                            break;
                        }
                    }
                    //if fail to get Course data
                    if (preCourse == null){
                        return null;
                    }
                    //add the Course into the list of Pre-Requisite Courses
                    preCourseList.add(preCourse);
                }
            }

            //Create a List with Type Course
            ArrayList<Course> currCourseList = new ArrayList<>();
            //If the course is not labeled as null
            if(!(items[4].equals("null"))){
                //course is a list that split at " " if there's multiple courses
                String[] course = items[4].split(" ");
                Course currCourse = null; //basically will store the Course data that the lecturer has
                for(String courseName:course){

                    //get the Course class
                    for(Course x:courses){
                        if(x.getCourseName().equals(courseName)){
                            currCourse = x;
                            break;
                        }
                    }
                    //if fail to get Course data
                    if (currCourse == null){
                        return null;
                    }
                    //add the Course into the list of Pre-Requisite Courses
                    currCourseList.add(currCourse);
                }
            }

            //add the lecturer to lecturers
            students.add(new Student(items[0], items[1], "student", items[2], preCourseList, currCourseList));
            student_subdata.put(items[0], items[1]);
        }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return students;
    }

    /**
     * Override method to define the initial stage (window) of the application
     * 
     */
    // @Override
    public void start(Stage primaryStage) {
        // Set the title of the main stage
        primaryStage.setTitle("Registration System");

        // if any of the csv files have invalid data, end the program
        if (courses == null || lecturers == null || students == null){
            showAlert("Error","Data from csv file is incorrect");
            return;
        }

        // Create the main grid layout for the login screen
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create buttons for login and exit
        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");

        // Add buttons to the grid layout
        grid.add(loginButton, 0, 0);
        grid.add(exitButton, 1, 0);

        // Set actions for login and exit buttons
        loginButton.setOnAction(e -> login(primaryStage));
        exitButton.setOnAction(e -> {
            System.out.println("Exiting the system. Goodbye!");
            primaryStage.close();
        });

        // Create the scene with the grid layout and set it to the stage
        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method to handle the login screen
     * @param primaryStage
     */
    private void login(Stage primaryStage) {
        // Create a grid layout for the login screen
        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels, text fields, and buttons for username, password, and login
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        // Add components to the login grid layout
        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);

        // Create a new scene for the login screen
        Scene loginScene = new Scene(loginGrid, 300, 150);
        primaryStage.setScene(loginScene);
        
        // Set action for the login button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            

            // Check if the entered username and password match any user in the list
            for(int i = 0; i < 3; i++){
                for (var entry: loginData.get(i).entrySet()){
                    if(entry.getKey().equals(username) && entry.getValue().equals(password)){
                        if (i == 0){
                            for(Lecturer login:lecturers){
                                if (entry.getKey().equals(login.getUsername())){
                                    lec_login = login;
                                    showLecturerMenu(primaryStage);
                                    return;
                                }
                            }
                        }
                        else if (i == 1){
                            for(Student login:students){
                                if (entry.getKey().equals(login.getUsername())){
                                    stu_login = login;
                                    showStudentMenu(primaryStage);
                                    return;
                                }
                            }
                        }
                        else{
                            for(Admin login:admins){
                                if (entry.getKey().equals(login.getUsername())){
                                    admin_login = login;
                                    showAdminMenu(primaryStage);
                                    return;
                                }
                            }
                        }
                        
                    }
                }
            }

            // If no match, show an error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
            }
        );
    }

    // Method to show the admin menu
    private void showAdminMenu(Stage primaryStage) {
        System.out.println("Welcome, User Type: Admin");

        // Set the title of the stage
        primaryStage.setTitle("Admin System");

        // Create a grid layout for the admin menu
        GridPane adminGrid = new GridPane();
        adminGrid.setAlignment(Pos.CENTER);
        adminGrid.setHgap(10);
        adminGrid.setVgap(10);
        adminGrid.setPadding(new Insets(25, 25, 25, 25));

        // Create buttons for various admin actions
        Button createStudentButton = new Button("Create Student");
        Button createLecturerButton = new Button("Create Lecturer");
        Button createCourseButton = new Button("Create Course");
        Button assignCourseButton = new Button("Assign Course to Lecturer");
        Button viewAllButton = new Button("View All Students and Lecturers");
        Button logoutButton = new Button("Logout");

        // Add buttons to the admin grid layout
        adminGrid.add(createStudentButton, 0, 0);
        adminGrid.add(createLecturerButton, 1, 0);
        adminGrid.add(createCourseButton, 2, 0);
        adminGrid.add(assignCourseButton, 0, 1);
        adminGrid.add(viewAllButton, 1, 1);
        adminGrid.add(logoutButton, 2, 1);

        // Set actions for admin buttons
        createStudentButton.setOnAction(e -> createStudent());
        createLecturerButton.setOnAction(e -> createLecturer());
        createCourseButton.setOnAction(e -> createCourse());
        assignCourseButton.setOnAction(e -> assignCourseToLecturer());
        viewAllButton.setOnAction(e -> viewAllStudentsAndLecturers());
        logoutButton.setOnAction(e -> start(primaryStage));

        // Create the admin scene with the grid layout and set it to the stage
        Scene adminScene = new Scene(adminGrid, 600, 300);
        primaryStage.setScene(adminScene);
    }

    // Method to create a student with a dialog box
    private void createStudent() {
        // Create a grid layout for the student creation dialog
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(10);
        studentGrid.setVgap(10);
        studentGrid.setPadding(new Insets(25, 25, 25, 25));
    
        // Username input
        Label usernameLabel = new Label("Enter student username:");
        TextField usernameField = new TextField();
        studentGrid.add(usernameLabel, 0, 0);
        studentGrid.add(usernameField, 1, 0);
    
        // Password input
        Label passwordLabel = new Label("Enter student password:");
        PasswordField passwordField = new PasswordField();
        studentGrid.add(passwordLabel, 0, 1);
        studentGrid.add(passwordField, 1, 1);
    
        // Full name input
        Label fullNameLabel = new Label("Enter student full name:");
        TextField fullNameField = new TextField();
        studentGrid.add(fullNameLabel, 0, 2);
        studentGrid.add(fullNameField, 1, 2);
    
        // Create a dialog box for student creation
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Create Student");
        dialog.getDialogPane().setContent(studentGrid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    
        // Wait for the user to click OK or Cancel in the dialog
        Optional<ButtonType> result = dialog.showAndWait();
    
        // Process the result if OK is clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String fullName = fullNameField.getText().trim();
    
            // Check if any field is empty
            if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                showAlert("Error", "All fields must be filled. Please enter valid values.");
                return;
            }
    
            // Check for duplicate username
            if (isUsernameExists(username)) {
                showAlert("Error", "Username already exists. Please choose a different one.");
                return;
            }
    
            // Add the new student to the list of users
            students.add(new Student(username, password, "Student", fullName, null, null));
            student_subdata.put(username, password);
            saveStudentToFile(students);
            System.out.println("Student created successfully. Student Username: " + username);
        }
    }

    private void createLecturer() {
        // Create a grid layout for the lecturer creation dialog
        GridPane lecturerGrid = new GridPane();
        lecturerGrid.setAlignment(Pos.CENTER);
        lecturerGrid.setHgap(10);
        lecturerGrid.setVgap(10);
        lecturerGrid.setPadding(new Insets(25, 25, 25, 25));
    
        // Full name input
        Label fullNameLabel = new Label("Enter lecturer full name:");
        TextField fullNameField = new TextField();
        lecturerGrid.add(fullNameLabel, 0, 2);
        lecturerGrid.add(fullNameField, 1, 2);
    
        // Username input
        Label usernameLabel = new Label("Enter lecturer username:");
        TextField usernameField = new TextField();
        lecturerGrid.add(usernameLabel, 0, 0);
        lecturerGrid.add(usernameField, 1, 0);
    
        // Password input
        Label passwordLabel = new Label("Enter lecturer password:");
        PasswordField passwordField = new PasswordField();
        lecturerGrid.add(passwordLabel, 0, 1);
        lecturerGrid.add(passwordField, 1, 1);
    
        // Show the dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Create Lecturer");
        dialog.getDialogPane().setContent(lecturerGrid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    
        // Wait for the user to click OK or Cancel
        Optional<ButtonType> result = dialog.showAndWait();
    
        // Process the result if OK is clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Retrieve the entered full name, username, and password
            String fullName = fullNameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
    
            // Check if any field is empty
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showAlert("Error", "All fields must be filled. Please enter valid values.");
                createLecturer();
                return;
            }
    
            // Check for duplicate username
            if (isUsernameExists(username)) {
                showAlert("Error", "Username already exists. Please choose a different one.");
                createLecturer();
                return;
            }
    
            // Add the new lecturer to the list of users
            lecturers.add(new Lecturer(username, password, "lecturer", fullName, null));
            lecturer_subdata.put(username, password);
            saveLecturerToFile(lecturers);
            System.out.println("Lecturer created successfully. Lecturer Username: " + username);
        }
    }

    // Method to check if a username already exists in the list of users
    private boolean isUsernameExists(String username) {
        for(int i = 0; i < 3; i++){
            if(loginData.get(i).containsKey(username))
                return true; // Username already exists
        }
        return false; // Username does not exist
    }

    // Method to create a new course with a dialog box
    private void createCourse() {
        // Create a grid layout for the lecturer creation dialog
        GridPane courseGrid = new GridPane();
        courseGrid.setAlignment(Pos.CENTER);
        courseGrid.setHgap(10);
        courseGrid.setVgap(10);
        courseGrid.setPadding(new Insets(25, 25, 25, 25));

        // Course name input
        Label courseLabel = new Label("Enter course name:");
        TextField courseField = new TextField();
        courseGrid.add(courseLabel, 0, 0);
        courseGrid.add(courseField, 1, 0);

        // Credit input
        Label creditLabel = new Label("Enter course credit:");
        TextField creditField = new TextField();
        courseGrid.add(creditLabel, 0, 1);
        courseGrid.add(creditField, 1, 1);

        // Pre-requisite input
        Label prereqLabel = new Label("Course pre-requisite \n(empty if none, space if multiple):");
        TextField prereqField = new TextField();
        courseGrid.add(prereqLabel, 0, 2);
        courseGrid.add(prereqField, 1, 2);

        Label reqcreLabel = new Label("Credits required to register course:");
        TextField reqcreField = new TextField();
        courseGrid.add(reqcreLabel, 0, 3);
        courseGrid.add(reqcreField, 1, 3);

        // Show the dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Create Lecturer");
        dialog.getDialogPane().setContent(courseGrid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Wait for the user to click OK or Cancel
        Optional<ButtonType> result = dialog.showAndWait();

        // Process the result if OK is clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Retrieve the entered username and password
            String courseName = courseField.getText().trim();
            String credit = creditField.getText().trim();
            String prereq = prereqField.getText().trim();
            String reqcre = reqcreField.getText().trim();

            // Check for duplicate course name
            if (isCourseNameExists(courseName)) {
                showAlert("Error", "Course with this name already exists. Please choose a different name.");
                createCourse();
                return;
            }

            //check if credit is all integers
            if (!isInteger(credit)){
                showAlert("Error","Credit is not an integer value");
                createCourse();
                return;
            }

            ArrayList<Course> prereqlist = new ArrayList<>();
            if(!prereq.isEmpty()){
                //split the preRequisite if there's more than 1
                String[] items = prereq.split(" ");
                for(String i:items){
                    if(!isCourseNameExists(i)){
                        showAlert("Error", "Course doesn't exist, Pre-requisite course doesn't exist");
                        createCourse();
                        return;
                    }
                }

                for(String i:items){
                    prereqlist.add(retrieveCourseData(i));
                }
            }

            // Add the new course to the list of courses
            courses.add(new Course(courseName, Integer.parseInt(credit),prereqlist,Integer.parseInt(reqcre)));
            saveCourseToFile(courses);
            System.out.println("Course created successfully. CourseCode: " + courseName);
        }
    }

    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    // Method to check if a course name already exists in the list of courses
    private boolean isCourseNameExists(String courseName) {
        for(Course course:courses){
            if (course.getCourseName().equals(courseName))
                return true;
        }
        return false;
    }  

    // Method to display an alert dialog with the given title and content
    private static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method to assign a course to a lecturer with a dialog box
    private void assignCourseToLecturer() {
        // Create a grid layout for the assignment dialog
        GridPane assignCourseGrid = new GridPane();
        assignCourseGrid.setAlignment(Pos.CENTER);
        assignCourseGrid.setHgap(10);
        assignCourseGrid.setVgap(10);
        assignCourseGrid.setPadding(new Insets(25, 25, 25, 25));

        // Course name input
        Label courseLabel = new Label("Enter course name:");
        TextField courseField = new TextField();
        assignCourseGrid.add(courseLabel, 0, 0);
        assignCourseGrid.add(courseField, 1, 0);

        // Lecturer username input
        Label lecturerLabel = new Label("Enter lecturer username:");
        TextField lecturerField = new TextField();
        assignCourseGrid.add(lecturerLabel, 0, 1);
        assignCourseGrid.add(lecturerField, 1, 1);

        // Show the dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Assign Course");
        dialog.getDialogPane().setContent(assignCourseGrid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Wait for the user to click OK or Cancel
        Optional<ButtonType> result = dialog.showAndWait();

        // Process the result if OK is clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Retrieve the entered course name and lecturer username
            String courseName = courseField.getText().trim();
            String lecturer = lecturerField.getText().trim();

            // Check if the course name or lecturer username is empty
            if (courseName.isEmpty() || lecturer.isEmpty()) {
                showAlert("Error", "Course name or lecturer username cannot be empty. Please enter valid values.");
                assignCourseToLecturer();
                return;
            }

            //Check if field entered is equal "null"
            if (courseName == "null" || lecturer == "null"){
                showAlert("Error", "Course name or lecturer username cannot be null.");
                assignCourseToLecturer();
                return;
            }

            // Check if the course exists
            String selectedCourse = null;
            if (isCourseNameExists(courseName))
                selectedCourse = courseName;

            // If the course is found, proceed with assignment
            if (selectedCourse != null) {
                // Check if the lecturer username exists
                if (!isUsernameExists(lecturer) && getUserType(lecturer).equals("lecturer")) {
                    showAlert("Error", "Lecturer username does not exist. Please enter a valid username.");
                    assignCourseToLecturer();
                    return;
                }
                
                //check if lecturer already has the course
                Lecturer selected_lec = retrieveLecturerData(lecturer);
                if(selected_lec.getCourses().contains(retrieveCourseData(selectedCourse))){
                    showAlert("Error", "Lecturer is already assigned to course");
                    assignCourseToLecturer();
                    return;
                }


                // Assign the course to the lecturer
                selected_lec.addCourses(retrieveCourseData(selectedCourse));
                saveLecturerToFile(lecturers);
                System.out.println("Course assigned to lecturer successfully.");
                System.out.println("Lecturer: " + lecturer);
                System.out.println("Course: " + courseName);
            } 
            else{
                showAlert("Error", "Course not found");
                assignCourseToLecturer();
                return;
            }
        }
    }

    // use username to find the class from Student (didn't use, might use)
    public static Student retrieveStudentData(String username){
        for (Student x:students){
            if(x.getUsername().equals(username))
                return x;
        }
        return null;
    }

    // use username to find the class from Lecturer
    public static Lecturer retrieveLecturerData(String username){
        for (Lecturer x:lecturers){
            if(x.getUsername().equals(username))
                return x;
        }
        return null;
    }

    // use username to find the class from Admin (didn't use, might use)
    public static Admin retrieveAdminData(String username){
        for (Admin x:admins){
            if(x.getUsername().equals(username))
                return x;
        }
        return null;
    }

    // use course name to find the class from Course
    public static Course retrieveCourseData(String course){
        for(Course x:courses){
            if(x.getCourseName().equals(course))
                return x;
        }
        return null;
    }

    //check userType using username
    public static String getUserType(String username){
        for(int i = 0; i < 3; i++){
            for (var entry: loginData.get(i).entrySet()){
                if(entry.getKey().equals(username)){
                    if (i == 0)
                        return "lecturer";
                    else if (i == 1)
                        return "student";
                    else
                        return "admin";
                    }
                }
            }
    return null;
    }

    // Method to display a list of all students and lecturers
    private void viewAllStudentsAndLecturers() {
        // Create VBox layout to hold the content
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));

        // Add a title label
        Label titleLabel = new Label("All Students and Lecturers:");
        titleLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);

        // Iterate through the list of students
        for (Student student : students) {
            VBox studentBox = new VBox();
            studentBox.setSpacing(5);

            // Add student username and name labels
            studentBox.getChildren().addAll(new Label("Student Username: " + student.getUsername()),
                                            new Label("Student Name: " + student.getName()));

            // Check if student has courses assigned
            ArrayList<Course> viewCourses = student.getCurrentCourses();
            if (!viewCourses.isEmpty()) {
                VBox coursesBox = new VBox();
                coursesBox.setSpacing(3);

                // Add assigned courses labels
                Label coursesLabel = new Label("Assigned Courses:");
                coursesLabel.setStyle("-fx-font-weight: bold;");
                coursesBox.getChildren().add(coursesLabel);
                for (Course course : viewCourses) {
                    coursesBox.getChildren().add(new Label("- " + course.getCourseName()));
                }

                studentBox.getChildren().add(coursesBox);
            }

            // Add a separator between students
            studentBox.getChildren().add(new Label("----------------------------"));
            layout.getChildren().add(studentBox);
        }

        // Iterate through the list of lecturers
        for (Lecturer lecturer : lecturers) {
            VBox lecturerBox = new VBox();
            lecturerBox.setSpacing(5);

            // Add lecturer username and name labels
            lecturerBox.getChildren().addAll(new Label("Lecturer Username: " + lecturer.getUsername()),
                                            new Label("Lecturer Name: " + lecturer.getName()));

            // Check if lecturer has courses assigned
            ArrayList<Course> viewCourses = lecturer.getCourses();
            if (!viewCourses.isEmpty()) {
                VBox coursesBox = new VBox();
                coursesBox.setSpacing(3);

                // Add assigned courses labels
                Label coursesLabel = new Label("Assigned Courses:");
                coursesLabel.setStyle("-fx-font-weight: bold;");
                coursesBox.getChildren().add(coursesLabel);
                for (Course course : viewCourses) {
                    coursesBox.getChildren().add(new Label("- " + course.getCourseName()));
                }

                lecturerBox.getChildren().add(coursesBox);
            }

            // Add a separator between lecturers
            lecturerBox.getChildren().add(new Label("----------------------------"));
            layout.getChildren().add(lecturerBox);
        }

        // Wrap the layout in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if needed
        scrollPane.setPrefViewportHeight(500); // Set preferred height
        scrollPane.setMaxHeight(600); // Set maximum height

        // Display the information in an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Students and Lecturers");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();
    }

    private void showLecturerMenu(Stage primaryStage) {
        System.out.println("Welcome, User Type: Lecturer");

        // Set the title of the stage
        primaryStage.setTitle("Lecturer System");

        // Create a grid layout for the lecturer menu
        GridPane adminGrid = new GridPane();
        adminGrid.setAlignment(Pos.CENTER);
        adminGrid.setHgap(10);
        adminGrid.setVgap(10);
        adminGrid.setPadding(new Insets(25, 25, 25, 25));

        // Create buttons for various lecturer actions
        Button viewAllButton = new Button("View All Students");
        Button logoutButton = new Button("Logout");

        // Add buttons to the lecturer grid layout

        adminGrid.add(viewAllButton, 1, 1);
        adminGrid.add(logoutButton, 2, 1);

        // Set actions for lecturer buttons
        logoutButton.setOnAction(e -> start(primaryStage));
        viewAllButton.setOnAction(e -> viewAllStudents());

        // Create the admin scene with the grid layout and set it to the stage
        Scene adminScene = new Scene(adminGrid, 600, 300);
        primaryStage.setScene(adminScene);
    }

    // Method to display all students and their assigned courses in the GUI
    private void viewAllStudents() {
        // Create VBox layout to hold the content
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));

        // Add a label for all students
        Label titleLabel = new Label("All Students:");
        titleLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);

        // Iterate through the list of students
        for (Student student : students) {
            VBox studentBox = new VBox();
            studentBox.setSpacing(5);

            // Add student username and name labels
            studentBox.getChildren().addAll(new Label("Student Username: " + student.getUsername()),
                                            new Label("Student Name: " + student.getName()));

            // Check if student has courses assigned
            ArrayList<Course> courses = student.getCurrentCourses();
            if (!courses.isEmpty()) {
                VBox coursesBox = new VBox();
                coursesBox.setSpacing(3);

                // Add assigned courses labels
                Label coursesLabel = new Label("Assigned Courses:");
                coursesLabel.setStyle("-fx-font-weight: bold;");
                coursesBox.getChildren().add(coursesLabel);
                for (Course course : courses) {
                    coursesBox.getChildren().add(new Label("- " + course.getCourseName()));
                }

                studentBox.getChildren().add(coursesBox);
            }

            layout.getChildren().add(studentBox);
        }

        // Wrap the layout in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if needed
        scrollPane.setPrefViewportHeight(500); // Set preferred height
        scrollPane.setMaxHeight(600); // Set maximum height

        // Display the information in an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Students");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();
    }


    private void showStudentMenu(Stage primaryStage) {
        System.out.println("Welcome, User Type: Student");

        // Set the title of the stage
        primaryStage.setTitle("Student System");

        // Create a grid layout for the student menu
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(10);
        studentGrid.setVgap(10);
        studentGrid.setPadding(new Insets(25, 25, 25, 25));

        // Create buttons for various student actions
        Button courseRegisterButton = new Button("Register Course");
        Button viewCourseButton = new Button("View Course"); // Added button for viewing courses
        Button logoutButton = new Button("Logout");

        // Add buttons to the student grid layout
        studentGrid.add(courseRegisterButton, 0, 0);
        studentGrid.add(viewCourseButton, 1, 0); // Added view course button
        studentGrid.add(logoutButton, 2, 0); // Adjusted grid positions

        // Set actions for student buttons
        courseRegisterButton.setOnAction(e -> assignStudentCourse());
        viewCourseButton.setOnAction(e -> viewCourses()); // Set action for view course button
        logoutButton.setOnAction(e -> start(primaryStage));

        // Create the admin scene with the grid layout and set it to the stage
        Scene adminScene = new Scene(studentGrid, 800, 600); // Adjusted scene size
        primaryStage.setScene(adminScene);

        if(stu_login.getTotalCurrentCredit() < 3){
            showAlert("Error", "Student must have a minimum of 3 credits per trimester. Please add a course");
            assignStudentCourse();
        }
    }

    // Method to display a student's courses in the GUI
    private void viewCourses() {
        // Get current, previous, and future courses of the student
        ArrayList<Course> currentCourses = stu_login.getCurrentCourses();
        ArrayList<Course> previousCourses = stu_login.getPrevCourses();
        // Assuming future courses can be retrieved from another method or class
        ArrayList<Course> futureCourses = getFutureCourses();

        // Create VBox layout to hold the content
        VBox layout = new VBox();
        layout.setSpacing(10); // Set spacing between nodes
        layout.setPadding(new Insets(20)); // Set padding around the layout

        // Add labels for current courses
        Label currentLabel = new Label("Current Courses:");
        layout.getChildren().add(currentLabel);
        for (Course course : currentCourses) {
            layout.getChildren().add(new Label(course.toString()));
        }

        // Add labels for previous courses
        Label previousLabel = new Label("Previous Courses:");
        layout.getChildren().add(previousLabel);
        for (Course course : previousCourses) {
            layout.getChildren().add(new Label(course.toString()));
        }

        // Add labels for future courses
        Label futureLabel = new Label("Future Courses:");
        layout.getChildren().add(futureLabel);
        for (Course course : futureCourses) {
            layout.getChildren().add(new Label(course.toString()));
        }

        // Display the information in an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Courses");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(layout);
        alert.showAndWait();
    }

    public ArrayList<Course> getFutureCourses(){
        ArrayList<Course> futureCourses = new ArrayList<>();
        for(Course course:courses){
            if (stu_login.getPrevCourses().contains(course) || stu_login.getCurrentCourses().contains(course))
                continue;
            else
                futureCourses.add(course);
        }
        return futureCourses;
    }

    private void assignStudentCourse() {
        // Create a grid layout for the student creation dialog
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(10);
        studentGrid.setVgap(10);
        studentGrid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels, text fields, and a dialog for entering course information
        Label courseLabel = new Label("Enter course name:");
        TextField courseField = new TextField();
        studentGrid.add(courseLabel, 0, 0);
        studentGrid.add(courseField, 1, 0);

        // Create a dialog box for student creation
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Course");
        dialog.getDialogPane().setContent(studentGrid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Wait for the user to click OK or Cancel in the dialog
        Optional<ButtonType> result = dialog.showAndWait();

        //if credit lesser than 3
        if(result.get() == ButtonType.CANCEL && stu_login.getTotalCurrentCredit() < 3){
            showAlert("Error", "Student must have a minimum of 3 credits per trimester. Please add a course");
            assignStudentCourse();
        }

        // Process the result if OK is clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String courseName = courseField.getText().trim();

            // Check if the course name is empty
            if (courseName.isEmpty()) {
                showAlert("Error", "Course name cannot be empty. Please enter valid values.");
                assignStudentCourse();
                return;
            }

            //Check if field entered is equal "null"
            if (courseName == "null"){
                showAlert("Error", "Course name cannot be null.");
                assignCourseToLecturer();
                return;
            }

            // Check if the course exists
            String selectedCourse = null;
            if (isCourseNameExists(courseName))
                selectedCourse = courseName;

            if (selectedCourse != null) {
                //check if student already has the course
                if(stu_login.getCurrentCourses().contains(retrieveCourseData(selectedCourse))
                || stu_login.getPrevCourses().contains(retrieveCourseData(selectedCourse))){
                    showAlert("Error", "Course already has been assigned to student");
                    assignStudentCourse();
                    return;
                }

                //check if it is the maximum credit for the course
                if((stu_login.getTotalCurrentCredit() + retrieveCourseData(selectedCourse).getCredit()) > 12){
                    showAlert("Error", "Exceed maximum credit for trimester");
                    assignStudentCourse();
                    return;
                }

                //check if there is course with required credit
                if(retrieveCourseData(selectedCourse).getReqCredit() > stu_login.getTotalPrevCredit()){
                    showAlert("Error", "Required Credit not met");
                    assignStudentCourse();
                    return;
                }

                //check does the course has the pre-requisite courses
                ArrayList<Course> list = retrieveCourseData(selectedCourse).getPreRequisiteCourse();
                for(Course course:list){
                    if(stu_login.getPrevCourses().contains(course))
                        continue;
                    else{
                        showAlert("Error", "Required Pre-requisite subject not met");
                        assignStudentCourse();
                        return;
                    }
                }
                

                // Assign the course to the student
                stu_login.addCourses(retrieveCourseData(selectedCourse));
                saveStudentToFile(students);
                System.out.println("Course assigned to student successfully.");
                System.out.println("Course: " + courseName);
            } 
            else{
                showAlert("Error","Course not found.");
                assignStudentCourse();
                return;
            }
        }
    }

    private static void saveStudentToFile(LinkedHashSet<Student> students) {
        // read students.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (Student student:students)
            sb.append(student.toCSVString() + "\n");
        try {
            Files.write(Paths.get("students.csv"), sb.toString().getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void saveLecturerToFile(LinkedHashSet<Lecturer> lecturers) {
        // read lecturers.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (Lecturer lecturer:lecturers)
            sb.append(lecturer.toCSVString() + "\n");
        try {
            Files.write(Paths.get("lecturers.csv"), sb.toString().getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void saveCourseToFile(LinkedHashSet<Course> courses) {
        // read courses.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (Course course:courses)
            sb.append(course.toCSVString() + "\n");
        try {
            Files.write(Paths.get("courses.csv"), sb.toString().getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}