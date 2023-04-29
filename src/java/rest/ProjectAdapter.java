package rest;

import classes.Project;
import java.time.LocalDateTime;

/**
 * Adapter to parse Date format correctly from JSON
 * @author lenaj
 */
public class ProjectAdapter {
    
    private String title;
    private Long id;
    private String startDate;
    private String deadline;
    private String summary;
    private String logopath;
    private int status;
    
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
    
    
    public Project toProject(){
        Project proj = new Project();
        proj.setId(this.id);
        proj.setTitle(this.title);
        proj.setStartDate(LocalDateTime.parse(this.startDate));
        proj.setDeadline(LocalDateTime.parse(this.deadline));
        proj.setSummary(this.summary);
        proj.setLogopath(this.logopath);
        proj.setStatus(this.status);
        return proj;
    }
    
}
