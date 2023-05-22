package rest;

import classes.Project;
import java.time.LocalDateTime;
import java.util.Set;
import classes.ProjectAssignee;
import java.util.HashSet;

/**
 * Adapter to parse Date format correctly from JSON
 * @author lenaj
 */

public class ProjectAdapter {
    
    public ProjectAdapter(){
        assignees = new HashSet<>();
    }
    
    
    private String title;
    private Long id;
    private String startDate;
    private String deadline;
    private String summary;
    private String logopath;
    private int status;
    Set<ProjectAssignee> assignees;
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
//    public void setAssignee(ProjectAssignee assignee) {
//        this.assignees = assignee;
//    }
    public void setAssignees(Set<ProjectAssignee> pa) {
        
        this.assignees.addAll(pa) ;
        
    }
    
    
    public Project toProject(){
        Project proj = new Project();
        proj.setId(this.id);
        proj.setTitle(this.title);
        proj.setStartDate(LocalDateTime.parse(this.startDate));
        proj.setDeadline(LocalDateTime.parse(this.deadline));
        proj.setSummary(this.summary);
        proj.setLogopath(this.logopath);
        proj.setStatus(this.status);
        proj.setAssignees(this.assignees);
        return proj;
    }
    
}
