
package rest;

import classes.Category;
import classes.Task;
import java.time.LocalDateTime;

/**
 *
 * @author lenaj
 */
public class TaskAdapter {
    
    private Long id;  
    private String title;
    private String deadline;
    private String summary;
    private Category category;
    private long scheduledWorkingTime;
    private boolean achieved;
    
    

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setScheduledWorkingTime(long scheduledWorkingTime) {
        this.scheduledWorkingTime = scheduledWorkingTime;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
    
    
    
    public Task toProject() {
        Task task = new Task();
        task.setId(this.id);
        task.setTitle(this.title);
        task.setDeadline(LocalDateTime.parse(this.deadline));  //ISO-cata format yyyy-mm-ddT10:15:30
        task.setAchieved(this.achieved);
        task.setScheduledWorkingTime(this.scheduledWorkingTime);
        task.setCategory(this.category);
        task.setSummary(this.summary);
        return task;
    }
    
    
}
