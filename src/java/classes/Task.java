package classes;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Representation of a work task
 * @author lenaj, lisaj
 */
@XmlRootElement
public class Task implements Serializable{
 
    private static final long serialVersionUID = 1L;
    private Long id;    
    private String title;
    private String summary;
    private Category category;
    private long scheduledWorkingTime;
    private LocalDateTime deadline;
    private boolean achieved;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getScheduledWorkingTime() {
        return scheduledWorkingTime;
    }

    public void setScheduledWorkingTime(long scheduledWorkingTime) {
        this.scheduledWorkingTime = scheduledWorkingTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
    
    
    
}
