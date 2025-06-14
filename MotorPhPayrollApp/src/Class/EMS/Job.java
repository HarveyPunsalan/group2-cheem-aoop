/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a job or position in the organization.
 * 
 */
public class Job {

    private int jobID;           
    private String jobTitle;   
    private Department department;   

    public Job() {
    }

    // Constructor for insert
    public Job(String jobTitle, Department department) {
        this.jobTitle = jobTitle;
        this.department = department;
    }

    // Constructor for retrieval
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

    @Override
    public String toString() {
        return String.format("Job [ID: %d, Title: %s, Department: %s]",
                jobID, jobTitle,
                (department != null ? department.getDepartmentName() : "N/A"));
    }
}