/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a job or position within the organization.
 *
 * This class contains the job's title, its unique identifier, and the
 * department to which the job belongs.
 */
public class Job {

    private int jobID;   // Unique identifier for the job position        
    private String jobTitle;   // Title or name of the job
    private Department department;   // Department to which this job belongs

    //Default constructor
    public Job() {
    }

    /**
     * Constructor used when inserting a new job record
     *
     * @param jobTitle   the title of the job
     * @param department the associated department
     */
    public Job(String jobTitle, Department department) {
        this.jobTitle = jobTitle;
        this.department = department;
    }

    /**
     * Constructor used when retrieving an existing job from the database.
     *
     * @param jobID      the unique ID of the job
     * @param jobTitle   the title of the job
     * @param department the associated department
     */
    public Job(int jobID, String jobTitle, Department department) {
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    // Getters and setters
    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @return a string representation of the job for display or debugging.
     *
     */
    @Override
    public String toString() {
        return String.format("Job [ID: %d, Title: %s, Department: %s]",
                jobID, jobTitle,
                (department != null ? department.getDepartmentName() : "N/A"));
    }
}